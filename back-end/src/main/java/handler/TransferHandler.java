//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package handler;

import dao.TransactionDao;
import dao.UserDao;
import dto.BaseDto;
import dto.TransactionDto;
import dto.TransactionType;
import dto.TransferRequestDto;
import dto.UserDto;
import java.util.List;
import org.bson.Document;
import request.ParsedRequest;
import response.HttpResponseBuilder;
import response.RestApiAppResponse;

public class TransferHandler implements BaseHandler {
    public TransferHandler() {
    }

    @Override
    public HttpResponseBuilder handleRequest(ParsedRequest request) {
        UserDao userDao = UserDao.getInstance();
        TransferRequestDto transferRequestDto = (TransferRequestDto)GsonTool.gson.fromJson(request.getBody(), TransferRequestDto.class);
        AuthFilter.AuthResult authResult = AuthFilter.doFilter(request);
        if (!authResult.isLoggedIn) {
            return (new HttpResponseBuilder()).setStatus("401 Unauthorized");
        } else {
            UserDto fromUser = (UserDto)userDao.query(new Document("userName", authResult.userName)).iterator().next();
            if (fromUser == null) {
                RestApiAppResponse<BaseDto> res = new RestApiAppResponse(false, (List)null, "Invalid from user.");
                return (new HttpResponseBuilder()).setStatus("400 Bad Request").setBody(GsonTool.gson.toJson(res));
            } else {
                UserDto toUser = (UserDto)userDao.query(new Document("userName", transferRequestDto.toId)).iterator().next();
                RestApiAppResponse res;
                if (toUser == null) {
                    res = new RestApiAppResponse(false, (List)null, "Invalid user to transfer.");
                    return (new HttpResponseBuilder()).setStatus("400 Bad Request").setBody(GsonTool.gson.toJson(res));
                } else if (fromUser.getBalance() < transferRequestDto.amount) {
                    res = new RestApiAppResponse(false, (List)null, "Not enough funds.");
                    return (new HttpResponseBuilder()).setStatus("400 Bad Request").setBody(GsonTool.gson.toJson(res));
                } else {
                    fromUser.setBalance(fromUser.getBalance() - transferRequestDto.amount);
                    toUser.setBalance(toUser.getBalance() + transferRequestDto.amount);
                    userDao.put(fromUser);
                    userDao.put(toUser);
                    TransactionDao transactionDao = TransactionDao.getInstance();
                    TransactionDto transaction = new TransactionDto();
                    transaction.setTransactionType(TransactionType.Transfer);
                    transaction.setAmount(transferRequestDto.amount);
                    transaction.setToId(transferRequestDto.toId);
                    transaction.setUserId(authResult.userName);
                    transactionDao.put(transaction);
                    RestApiAppResponse<UserDto> response = new RestApiAppResponse(true, List.of(fromUser, toUser), (String)null);
                    return (new HttpResponseBuilder()).setStatus("200 OK").setBody(GsonTool.gson.toJson(response));
                }
            }
        }
    }
}