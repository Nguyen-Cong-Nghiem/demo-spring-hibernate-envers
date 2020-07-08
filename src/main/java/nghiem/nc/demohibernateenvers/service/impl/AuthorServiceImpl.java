package nghiem.nc.demohibernateenvers.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import nghiem.nc.demohibernateenvers.dto.AuthorAddDto;
import nghiem.nc.demohibernateenvers.dto.AuthorEditDto;
import nghiem.nc.demohibernateenvers.entity.Author;
import nghiem.nc.demohibernateenvers.entity.Book;
import nghiem.nc.demohibernateenvers.repository.AuthorRepository;
import nghiem.nc.demohibernateenvers.repository.BookRepository;
import nghiem.nc.demohibernateenvers.service.AuthorService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.criterion.MatchMode;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@Log4j2
public class AuthorServiceImpl implements AuthorService {

  @Autowired
  private AuthorRepository authorRepository;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private AuditReader auditReader;

  @Override
  @Transactional
  public Author addNewAuthor(AuthorAddDto authorAddDto) {
    Author saveAuthor = new Author();
    BeanUtils.copyProperties(authorAddDto, saveAuthor);
    saveAuthor.setCreatedAt(LocalDateTime.now());
    saveAuthor.setUpdatedAt(LocalDateTime.now());
    saveAuthor.setCreatedBy(1L);
    saveAuthor.setUpdatedBy(1L);
    Author newAuthor = authorRepository.save(saveAuthor);
    List<Book> saveBookList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(authorAddDto.getBookAddDtos())) {
      saveBookList = authorAddDto.getBookAddDtos().stream()
          .map(b -> Book.builder().name(b.getName())
              .type(b.getType()).authorId(newAuthor.getId())
              .createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).createdBy(1L)
              .updatedBy(1L)
              .build()).collect(Collectors.toList());
    }

    bookRepository.saveAll(saveBookList);
    return newAuthor;

  }

  @Override
  @Transactional
  public Author editAuthor(AuthorEditDto authorEditDto) {
    Author oldAuthor = authorRepository.findById(authorEditDto.getId()).get();
    BeanUtils.copyProperties(authorEditDto, oldAuthor);
    oldAuthor.setCreatedAt(LocalDateTime.now());
    oldAuthor.setUpdatedAt(LocalDateTime.now());
    oldAuthor.setCreatedBy(2L);
    oldAuthor.setUpdatedBy(2L);
    authorRepository.save(oldAuthor);
    return oldAuthor;
  }

  @Override
  public Author getDetailAuthor(Long id) {
    return authorRepository.findById(id).get();
  }

  @Override
  @Transactional
  // Require use method delete object of hibernate if want audit.
  public void deleteAuthor(Long id) {
    Author author = authorRepository.getOne(id);
    authorRepository.delete(author);
  }

  @Override
  public List<String> readingAudit(Long id, String entity) {
    List<String> result = new ArrayList<>();

    Class<?> classType = getClassType(entity);

    // Tìm ra danh sách revision tương ứng với id và kiểu entity.
    List<Number> revisionNumbers = auditReader.getRevisions(classType, id);

    for (Number rev : revisionNumbers) {

      // Hàm find tìm kiếm thông tin entity trong bảng entity_audit với rev tương ứng.
      result.add(
          auditReader.find(classType, id, rev) + " save information audit at revision [ " + rev
              + " ]");
    }

    // Tìm chính xác theo 1 bản ghi của entity_audit theo revision number
    // sẽ tìm thấy thông tin của entity_audit có revision nhỏ hơn gần nhất với revision number nhập vào.
    Author author = auditReader.find(Author.class, 13L, 17);
    System.out.println("Author : " + author);

    return result;
  }


  @Override
  public List<String> getInfoOfEntity(Long id, String entity) {
    // Lấy tất cả thông tin thay đổi của entity theo ID bao gồm cả thông tin thêm sửa xoá với tham số selectEntityOnly = false.
    Class<?> aClass = getClassType(entity);
    AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(aClass, false, true);
    auditQuery.add(AuditEntity.id().eq(id));
    List<Object> authors = auditQuery.getResultList();
    List<String> result = new ArrayList<>();
    for (Object a : authors) {
      result.add(ToStringBuilder.reflectionToString(a));
    }
    return result;
  }


  @Override
  public Long countTimeChange(String entity, Long id) {
    // Lấy số lần thay đổi của entity với ID cần check.
    Class<?> classType = getClassType(entity);
    AuditQuery auditQuery2 = auditReader.createQuery().forRevisionsOfEntity(classType, false, true);
    auditQuery2.addProjection(AuditEntity.revisionNumber().count());
    auditQuery2.add(AuditEntity.property("id").eq(id));
    Number revision = (Number) auditQuery2.getSingleResult();
    revision = revision != null ? revision : 0;
    AuditQuery auditQuery = auditReader.createQuery()
        .forRevisionsOfEntity(Author.class, false, true);
    auditQuery.addProjection(AuditEntity.revisionNumber().max());
    auditQuery.add(AuditEntity.property("id").eq(2L));
    Number revisionLastest = (Number) auditQuery.getSingleResult();
    System.out.println(revisionLastest);
    return revision.longValue();
  }

  @Override
  public List<String> getByFilter(String entity, Integer revisionNumber, String name) {

    // Lấy tất cả các entity thay đổi theo query.
    // tìm kiếm tất cả entity trong khoảng nhỏ hơn revision truyền vào.
    Class<?> classType = getClassType(entity);
    List<String> result = new ArrayList<>();
    AuditQuery auditQuery1 = auditReader.createQuery()
        .forEntitiesAtRevision(classType, revisionNumber);
    auditQuery1.add(AuditEntity.property("name").like(name, MatchMode.ANYWHERE));
    auditQuery1.addOrder(AuditEntity.property("name").asc());
    List<Author> authorList = auditQuery1.getResultList();
    for (Author a : authorList) {
      result.add("=======================>>>>>" + a);
    }
    return result;
  }


  private Class<?> getClassType(String entity) {
    Class<?> classType = null;
    try {
      classType = Class.forName(entity);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return classType;
  }

}
