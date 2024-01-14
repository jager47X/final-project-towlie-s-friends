package handler;


import dao.ItemDao;
import dto.ItemDto;
import dto.TransactionType;
import dto.UserDto;
import org.bson.Document;
import request.ParsedRequest;
import response.HttpResponseBuilder;
import response.RestApiAppResponse;

import java.util.List;

public class SellHandler implements BaseHandler{

    public HttpResponseBuilder handleRequest(ParsedRequest request) {
        ItemDto itemDto = GsonTool.gson.fromJson(request.getBody(), ItemDto.class);
        ItemDao itemDao = ItemDao.getInstance();
        itemDto.setTransactionType(TransactionType.Sell);

        AuthFilter.AuthResult authResult = AuthFilter.doFilter(request);
        if (!authResult.isLoggedIn) {
            return (new HttpResponseBuilder()).setStatus("401 Unauthorized");
        } else {
        Document match = new Document("itemName", itemDto.getItemName());
//itemname, cost, image
                    itemDto.setSellId(authResult.userName);

List<ItemDto> itemDtoList = itemDao.query(match);
        itemDao.put(itemDto);

        var res = new RestApiAppResponse<>(true, itemDtoList, "Item for sale");
        return new HttpResponseBuilder().setBody(res).setStatus(StatusCodes.OK);
        }
    }
  }