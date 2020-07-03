package nghiem.nc.demohibernateenvers.service;

import java.util.List;
import nghiem.nc.demohibernateenvers.dto.AuthorAddDto;
import nghiem.nc.demohibernateenvers.dto.AuthorEditDto;
import nghiem.nc.demohibernateenvers.entity.Author;

public interface AuthorService {

  Author addNewAuthor(AuthorAddDto authorAddDto);

  Author editAuthor(AuthorEditDto authorEditDto);

  Author getDetailAuthor(Long id);

  void deleteAuthor(Long id);

  List<String> readingAudit(Long id, String entity);

  List<String> getInfoOfEntity(Long id, String entity);

  Long countTimeChange(String entity, Long id);

  List<String> getByFilter(String entity, Integer revisionNumber, String name);

}
