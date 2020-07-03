package nghiem.nc.demohibernateenvers.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import nghiem.nc.demohibernateenvers.entity.listener.CustomRevisionListener;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity
@RevisionEntity(CustomRevisionListener.class)
@Table(name = "revinfo", schema = "audit")
@AttributeOverrides({
    @AttributeOverride(name = "timestamp", column = @Column(name = "revtstmp")),
    @AttributeOverride(name = "id", column = @Column(name = "rev"))})
@Getter
@Setter
public class CustomRevisionEntity extends DefaultRevisionEntity {

  @Column(name = "username", nullable = false)
  private String username;

}
