package nghiem.nc.demohibernateenvers.repository;

import nghiem.nc.demohibernateenvers.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

  @Modifying
  @Query("delete from Author a where a.id =:id")
  void deleteAuthor(Long id);
}