package dto;

import org.bson.Document;

public class AuthDto extends BaseDto{

  private String userName;
  private Long expireTime;
  private String hash;

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public void setExpireTime(Long expireTime) {
    this.expireTime = expireTime;
  }

  public String getUserName() {
    return userName;
  }

  public Long getExpireTime() {
    return expireTime;
  }

  public String getHash() {
    return hash;
  }

  @Override
  public Document toDocument() {
    Document doc= new Document();
    doc.append("userName",this.userName);
    doc.append("hash",this.hash);
    doc.append("expireTime",this.expireTime);
    return doc;
  }

  public static AuthDto fromDocument(Document document){
    AuthDto auth = new AuthDto();
      if(document.get("_id")!=null){
          auth.loadUniqueId(document);
      }

    auth.setUserName(document.getString("userName"));
    auth.setHash(document.getString("hash"));
    auth.setExpireTime(document.getLong("expireTime"));
    return auth;
  }
}
