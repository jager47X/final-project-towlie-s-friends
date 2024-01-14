package dto;

import org.bson.Document;

import java.time.Instant;

public class ItemDto extends BaseDto{

    private String sellId;
    private Double cost;
    private String itemName;
    private String buyId;
    private String image;






    private TransactionType transactionType;
    private Long timestamp;

    public ItemDto(){
        timestamp = Instant.now().toEpochMilli();
    }

    public ItemDto(String uniqueId) {
        super(uniqueId);
        timestamp = Instant.now().toEpochMilli();
    }

    public String getSellId() {
        return sellId;
    }

    public void setSellId(String sellId) {
        this.sellId = sellId;
    }

    public String getBuyId(){
        return buyId;
    }

    public void setBuyId(String buyId){
        this.buyId = buyId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public void setUniqueId(String uniqueId) {
        super.setUniqueId(uniqueId);
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Document toDocument(){
        return new Document("sellId",this.sellId)
                .append("itemName",this.itemName)
                .append("cost",this.cost)
                .append("image",this.image)
                .append("transactionType",this.transactionType.toString())
                .append("timestamp",this.timestamp);


    }

    public static ItemDto fromDocument(Document document) {

        ItemDto item=new ItemDto();
        item.setSellId(document.getString("sellId"));
        item.setItemName(document.getString("itemName"));
        item.setCost(document.getDouble("cost"));
        item.setImage(document.getString("image"));
        item.setTransactionType(TransactionType.valueOf(document.getString("transactionType")));
        item.setTimestamp(document.getLong("timestamp"));

        return item;
    }
}