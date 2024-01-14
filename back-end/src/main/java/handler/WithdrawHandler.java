
package handler;

import dao.TransactionDao;
import dao.UserDao;
import dto.TransactionDto;
import dto.TransactionType;
import dto.UserDto;
import java.util.List;
import org.bson.Document;
import request.ParsedRequest;
import response.HttpResponseBuilder;
import response.RestApiAppResponse;

public class WithdrawHandler implements BaseHandler {
    public WithdrawHandler() {
    }

    public HttpResponseBuilder handleRequest(ParsedRequest request) {
        TransactionDto transactionDto = (TransactionDto)GsonTool.gson.fromJson(request.getBody(), TransactionDto.class);
        TransactionDao transactionDao = TransactionDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        AuthFilter.AuthResult authResult = AuthFilter.doFilter(request);
        if (!authResult.isLoggedIn) {
            return (new HttpResponseBuilder()).setStatus("401 Unauthorized");
        } else {
            UserDto user = (UserDto)userDao.query(new Document("userName", authResult.userName)).get(0);
            RestApiAppResponse res;
            if (user.getBalance() < transactionDto.getAmount()) {
                res = new RestApiAppResponse(false, (List)null, "Not enough funds.");
                return (new HttpResponseBuilder()).setStatus("400 Bad Request").setBody(GsonTool.gson.toJson(res));
            } else {
                transactionDto.setUserId(authResult.userName);
                transactionDto.setTransactionType(TransactionType.Withdraw);
                transactionDao.put(transactionDto);
                user.setBalance(user.getBalance() - transactionDto.getAmount());
                userDao.put(user);
                res = new RestApiAppResponse(true, List.of(transactionDto), (String)null);
                return (new HttpResponseBuilder()).setStatus("200 OK").setBody(res);
            }
        }
    }
}
