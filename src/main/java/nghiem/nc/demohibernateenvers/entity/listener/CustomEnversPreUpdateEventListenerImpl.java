package nghiem.nc.demohibernateenvers.entity.listener;

import lombok.extern.log4j.Log4j2;
import nghiem.nc.demohibernateenvers.entity.Author;
import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversPreUpdateEventListenerImpl;
import org.hibernate.event.spi.PreUpdateEvent;

@Log4j2
public class CustomEnversPreUpdateEventListenerImpl extends
    EnversPreUpdateEventListenerImpl {


  public CustomEnversPreUpdateEventListenerImpl(EnversService enversService) {
    super(enversService);
  }

  @Override
  public boolean onPreUpdate(PreUpdateEvent event) {
    if (event.getEntity() instanceof Author
        && ((Author) event.getEntity()).getName() == null) {
      System.out.println("Name should be assign for an author.");
      return false;
    }
    System.out.println("On Pre Update event");
    return super.onPreUpdate(event);
  }

}
