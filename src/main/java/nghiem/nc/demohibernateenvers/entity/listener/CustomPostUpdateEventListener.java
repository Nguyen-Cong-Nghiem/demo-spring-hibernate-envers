package nghiem.nc.demohibernateenvers.entity.listener;

import nghiem.nc.demohibernateenvers.entity.Author;
import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversPostUpdateEventListenerImpl;
import org.hibernate.event.spi.PostUpdateEvent;

public class CustomPostUpdateEventListener extends EnversPostUpdateEventListenerImpl {

  public CustomPostUpdateEventListener(EnversService enversService) {
    super(enversService);
  }

  @Override
  public void onPostUpdate(PostUpdateEvent event) {
    if (event.getEntity() instanceof Author
        && ((Author) event.getEntity()).getName() == null) {
      System.out.println("Name must assign for an author.");

    }
    System.out.println("On Edit event");
    super.onPostUpdate(event);
  }
}
