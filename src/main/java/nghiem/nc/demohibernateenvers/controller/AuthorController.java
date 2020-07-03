package nghiem.nc.demohibernateenvers.controller;

import java.util.List;
import nghiem.nc.demohibernateenvers.dto.AuthorAddDto;
import nghiem.nc.demohibernateenvers.dto.AuthorEditDto;
import nghiem.nc.demohibernateenvers.entity.Author;
import nghiem.nc.demohibernateenvers.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/authors")
public class AuthorController {

  @Autowired
  private AuthorService authorService;

  @PostMapping
  public Author addNewAuthor(@RequestBody AuthorAddDto authorAddDto) {
    return authorService.addNewAuthor(authorAddDto);
  }

  @PutMapping
  public Author editAuthor(@RequestBody AuthorEditDto authorEditDto) {
    return authorService.editAuthor(authorEditDto);
  }

  @GetMapping("{id}")
  public Author getDetailAuthor(@PathVariable Long id) {
    return authorService.getDetailAuthor(id);
  }

  @DeleteMapping
  public void deleteAuthor(@RequestParam Long id) {
    authorService.deleteAuthor(id);
  }

  @GetMapping("reader")
  // read action changelog of entity
  // parameter entity require full path (ex: nghiem.nc.demohibernateenvers.entity.Author)
  public List<String> readerAuditForOneObject(Long id, String entity) {
    return authorService.readingAudit(id, entity);
  }


  @GetMapping("all-change")
  // read action changelog of entity
  // parameter entity require full path (ex: nghiem.nc.demohibernateenvers.entity.Author)
  public List<String> infoAudit(Long id, String entity) {
    return authorService.getInfoOfEntity(id, entity);
  }

  @GetMapping("count")
  // read action changelog of entity
  // parameter entity require full path (ex: nghiem.nc.demohibernateenvers.entity.Author)
  public Long countChange(Long id, String entity) {
    return authorService.countTimeChange(entity, id);
  }

  @GetMapping("find-by-query")
  // read action changelog of entity
  // parameter entity require full path (ex: nghiem.nc.demohibernateenvers.entity.Author)
  public List<String> findByQuery(Integer revisionNumber, String entity, String name) {
    return authorService.getByFilter(entity, revisionNumber, name);
  }

}
