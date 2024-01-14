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
import org.apache.commons.codec.digest.DigestUtils;
import org.bson.Document;
import request.ParsedRequest;
import response.HttpResponseBuilder;
import response.RestApiAppResponse;

public class CreateUserHandler implements BaseHandler {

    public HttpResponseBuilder handleRequest(ParsedRequest request) {
        UserDto userDto = (UserDto)GsonTool.gson.fromJson(request.getBody(), UserDto.class);
        UserDao userDao = UserDao.getInstance();
        Document query = new Document("userName", userDto.getUserName());
        List<UserDto> resultQ = userDao.query(query);
        RestApiAppResponse res;
        if (resultQ.size() != 0) {
            res = new RestApiAppResponse(false, (List)null, "Username already taken");
        } else {
            userDto.setPassword(DigestUtils.sha256Hex(userDto.getPassword()));



            userDto.setBalance(1000.0);
            userDao.put(userDto);
            res = new RestApiAppResponse(true, (List)null, "User Created");
            return (new HttpResponseBuilder()).setStatus(StatusCodes.OK).setBody(res);


        }

        return (new HttpResponseBuilder()).setStatus(StatusCodes.OK).setBody(res);
    }
}
