package nghiem.nc.demohibernateenvers.entity.listener;

import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;

public class CustomEnversPreInsertEventListenerImpl implements PreInsertEventListener {

  @Override
  public boolean onPreInsert(PreInsertEvent event) {
    System.out.println("On Pre Insert event ");
    return false;
  }
}
