//package nghiem.nc.demohibernateenvers.entity;
//
//import javax.persistence.AttributeOverride;
//import javax.persistence.AttributeOverrides;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import nghiem.nc.demohibernateenvers.entity.listener.AuditRevisionListener;
//import org.hibernate.envers.DefaultRevisionEntity;
//import org.hibernate.envers.RevisionEntity;
//
//@Entity()
//@RevisionEntity(AuditRevisionListener.class)
//@Table(name = "revision_info")
//@AttributeOverrides({
//    @AttributeOverride(name = "timestamp", column = @Column(name = "rev_timestamp")),
//    @AttributeOverride(name = "id", column = @Column(name = "revision_id"))
//})
//public class AuditRevisionEntity extends DefaultRevisionEntity {
//
//  @Column(name = "username", nullable = false)
//  private String username;
//
//
//  public String getUsername() {
//    return username;
//  }
//
//  public void setUsername(String username) {
//    this.username = username;
//  }
//}
