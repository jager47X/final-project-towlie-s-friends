package handler;

import dao.AuthDao;
import dao.UserDao;
import dto.AuthDto;
import dto.TransactionDto;
import dto.UserDto;

import java.time.Instant;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.bson.Document;
import request.ParsedRequest;
import response.HttpResponseBuilder;
import response.RestApiAppResponse;


public class LogoutHandler implements BaseHandler{

    @Override
    public HttpResponseBuilder handleRequest(ParsedRequest request) {

        AuthFilter.AuthResult authResult = AuthFilter.doFilter(request);
        if (!authResult.isLoggedIn) {

            RestApiAppResponse<TransactionDto> res = new RestApiAppResponse(true, (List)null, "Not Logged in");
            return (new HttpResponseBuilder()).setStatus(StatusCodes.OK).setBody(res);
        } else {

            var userName= authResult.userName;
            UserDao userDao = UserDao.getInstance();
            AuthDao authDao = AuthDao.getInstance();
            AuthDto authDto = new AuthDto();
            authDto.setExpireTime(Instant.now().getEpochSecond() + 1);
            String user = authDto.getUserName();
            String hash = DigestUtils.sha256Hex(user + authDto.getExpireTime());
            authDto.setHash(hash);
            authDto.setUserName(authResult.userName);
            authDao.put(authDto);

            RestApiAppResponse<TransactionDto> res = new RestApiAppResponse(true, (List)null, "logged out");
            return (new HttpResponseBuilder()).setStatus(StatusCodes.OK).setBody(res);
        }
    }
}