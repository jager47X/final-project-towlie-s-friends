package handler;

import dao.AuthDao;
import dao.UserDao;
import dto.AuthDto;
import dto.UserDto;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import request.ParsedRequest;
import response.CustomHttpResponse;
import response.HttpResponseBuilder;
import response.RestApiAppResponse;

class LoginDto{
  String userName;
  String password;
}

public class LoginHandler implements BaseHandler{

  @Override
  public HttpResponseBuilder handleRequest(ParsedRequest request) {
      HttpResponseBuilder res = new HttpResponseBuilder();
      LoginDto userDto = (LoginDto)GsonTool.gson.fromJson(request.getBody(), LoginDto.class);
      UserDao userDao = UserDao.getInstance();
      AuthDao authDao = AuthDao.getInstance();
      Document userQuery = (new Document()).append("userName", userDto.userName).append("password", DigestUtils.sha256Hex(userDto.password));
      List<UserDto> result = userDao.query(userQuery);
      if (result.size() == 0) {
          res.setStatus(StatusCodes.UNAUTHORIZED);
      } else {
          AuthDto authDto = new AuthDto();
          authDto.setExpireTime(Instant.now().getEpochSecond() + 60L);
          String user = authDto.getUserName();
          String hash = DigestUtils.sha256Hex(user + authDto.getExpireTime());
          authDto.setHash(hash);
          authDto.setUserName(userDto.userName);
          authDao.put(authDto);
          res.setStatus(StatusCodes.OK);
          res.setHeader("Set-Cookie", "auth=" + hash);
      }

      return res;
  }
}
