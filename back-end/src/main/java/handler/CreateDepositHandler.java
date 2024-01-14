//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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

public class CreateDepositHandler implements BaseHandler {

    public HttpResponseBuilder handleRequest(ParsedRequest request) {
        TransactionDto transactionDto = (TransactionDto)GsonTool.gson.fromJson(request.getBody(), TransactionDto.class);
        TransactionDao transactionDao = TransactionDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        AuthFilter.AuthResult authResult = AuthFilter.doFilter(request);
        if (!authResult.isLoggedIn) {
            return (new HttpResponseBuilder()).setStatus("401 Unauthorized");
        } else {
            UserDto user = (UserDto)userDao.query(new Document("userName", authResult.userName)).get(0);
            transactionDto.setTransactionType(TransactionType.Deposit);
            transactionDto.setUserId(authResult.userName);
            transactionDao.put(transactionDto);
            user.setBalance(user.getBalance() + transactionDto.getAmount());
            userDao.put(user);
            RestApiAppResponse<TransactionDto> res = new RestApiAppResponse(true, List.of(transactionDto), (String)null);
            return (new HttpResponseBuilder()).setStatus("200 OK").setBody(res);
        }
    }
}
