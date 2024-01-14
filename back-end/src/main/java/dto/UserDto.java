package dto;

import org.bson.Document;
import org.bson.types.ObjectId;

public class UserDto extends BaseDto{

  private String userName;
  private String password;
  private Double balance = 0.0d;

  public UserDto() {
    super();
  }

  public UserDto(String uniqueId) {
    super(uniqueId);
  }

  public String getPassword() {
    return password;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Double getBalance() {
    return balance;
  }


  public void setBalance(Double balance) {
    this.balance = balance;
  }


  public Document toDocument() {
    return new Document()
    .append("userName",this.userName)
    .append("balance",this.balance)
    .append("password",this.password);

  }

  public static UserDto fromDocument(Document match) {
    UserDto user=new UserDto();
    if(match.get("_id")!=null){
        user.loadUniqueId(match);
    }
    user.setUserName(match.getString("userName"));
    user.setBalance(match.getDouble("balance"));
    user.setPassword(match.getString("password"));
    return user;
  
  }
}
