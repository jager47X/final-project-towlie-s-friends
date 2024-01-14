package dto;

import org.bson.Document;

import java.time.Instant;

public class TransactionDto extends BaseDto{

  private String userId;
  private String toId;
  private Double amount;
  private Double loan_amount;
  private TransactionType transactionType;
  private Long timestamp;
  

  public TransactionDto(){
    timestamp = Instant.now().toEpochMilli();
  }

  public TransactionDto(String uniqueId) {
    super(uniqueId);
    timestamp = Instant.now().toEpochMilli();
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getToId() {
    return toId;
  }

  public void setToId(String toId) {
    this.toId = toId;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }
    public Double getLoanAmount() {
    return loan_amount;
  }

  public void setLoanAmount(Double loan_amount) {
    this.loan_amount= loan_amount;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
  }


  public Document toDocument(){
    Document doc=new Document();
    doc.append("userId",this.userId);
    doc.append("toId",this.toId);
    doc.append("amount",this.amount);
    doc.append("transactionType",this.transactionType.toString());
    doc.append("timestamp",this.timestamp);
    return doc;
  }

  public static TransactionDto fromDocument(Document document) {
    TransactionDto Transaction = new TransactionDto();
    Transaction.setToId(document.getString("toId"));
    Transaction.setUserId(document.getString("userId"));
    Transaction.setAmount(document.getDouble("amount"));
    Transaction.setTransactionType(TransactionType.valueOf(document.getString("transactionType")));
    Transaction.setTimestamp(document.getLong("timeStamp"));
    return Transaction;
  }
}
