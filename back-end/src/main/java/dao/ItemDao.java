package dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import dao.MongoConnection;
import dto.AuthDto;
import dto.ItemDto;
import dto.TransactionDto;
import dto.UserDto;
import jdk.jfr.Name;
import org.bson.Document;



// TODO fill this out
public class ItemDao extends BaseDao<ItemDto> {

    private static ItemDao instance;

    private ItemDao(MongoCollection<Document> collection){
        super(collection);
    }

    public static ItemDao getInstance(){
        if(instance != null){
            return instance;
        }
        instance = new ItemDao(MongoConnection.getCollection("ItemDao"));
        return instance;
    }

    public static ItemDao getInstance(MongoCollection<Document> collection){
        instance = new ItemDao(collection);
        return instance;
    }

    public List<ItemDto> query(Document filter){
        return collection.find(filter)
                .into(new ArrayList<>())
                .stream()
                .map(ItemDto::fromDocument)
                .collect(Collectors.toList());
    }


    public List<ItemDto> getAll(){

        return  collection.find( )
                .into(new ArrayList<>())
                .stream()
                .map(ItemDto::fromDocument)
                .collect(Collectors.toList());

    }


}