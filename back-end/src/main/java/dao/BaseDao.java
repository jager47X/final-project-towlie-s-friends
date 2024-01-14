package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import dto.BaseDto;
import java.util.List;

import dto.UserDto;
import org.bson.Document;

public abstract class BaseDao<T extends BaseDto> {

  final MongoCollection<Document> collection;

  protected BaseDao(MongoCollection<Document> collection) {
    this.collection = collection;
  }

  abstract List<T> query(Document filter);


  public void put(T dto) {
    if (dto.getUniqueId() == null) {
      collection.insertOne(dto.toDocument());
    } else {
      collection.replaceOne(dto.getObjectId(), dto.toDocument());
    }
  }

  public void bought(T dto) {
    collection.deleteOne(dto.toDocument());
  }
}
