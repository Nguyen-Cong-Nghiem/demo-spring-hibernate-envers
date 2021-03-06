//package nghiem.nc.demohibernateenvers.entity.listener;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.io.UnsupportedEncodingException;
//import javax.servlet.http.HttpServletRequest;
//import nghiem.nc.demohibernateenvers.entity.AuditRevisionEntity;
//import org.apache.tomcat.util.codec.binary.Base64;
//import org.hibernate.envers.RevisionListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.configurationprocessor.json.JSONException;
//import org.springframework.boot.configurationprocessor.json.JSONObject;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AuditRevisionListener implements RevisionListener {
//
//  @Autowired
//  private HttpServletRequest httpServletRequest;
//
//
//  @Override
//  public void newRevision(Object revisionEntity) {
//
//    AuditRevisionEntity rev = (AuditRevisionEntity) revisionEntity;
//    rev.setUsername(getUsername());
//  }
//
//  private String getUsername() {
//
//    String token = httpServletRequest.getHeader("access_token");
//
//    String[] splitToken = token.split("\\.");
//    Base64 base64Url = new Base64(true);
//    byte[] decodedBytes = base64Url.decode(splitToken[1]);
//    JSONObject tokenObj = null;
//    try {
//      tokenObj = new JSONObject(new String(decodedBytes, "UTF-8"));
//      ObjectMapper mapper = new ObjectMapper();
//      return mapper.convertValue(tokenObj.get("username"), String.class);
//
//    } catch (JSONException | UnsupportedEncodingException e) {
//      e.printStackTrace();
//    }
//    if (tokenObj == null) {
//      return null;
//    }
//    return null;
//
//  }
//}