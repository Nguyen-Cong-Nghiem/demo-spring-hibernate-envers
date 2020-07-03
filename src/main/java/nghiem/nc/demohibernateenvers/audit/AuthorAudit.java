package nghiem.nc.demohibernateenvers.audit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nghiem.nc.demohibernateenvers.entity.Author;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorAudit {

  private Author author;
  private Integer revision;

}
