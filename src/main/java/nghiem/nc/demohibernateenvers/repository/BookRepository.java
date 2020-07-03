package nghiem.nc.demohibernateenvers.repository;

import nghiem.nc.demohibernateenvers.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
