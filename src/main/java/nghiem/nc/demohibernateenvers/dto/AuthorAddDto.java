package nghiem.nc.demohibernateenvers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorAddDto {

  private String name;
  private String type;
  private BookAddDto bookAddDto;

}
