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

public class GetUserInfoHandler implements BaseHandler {


    public HttpResponseBuilder handleRequest(ParsedRequest request) {
        TransactionDao transactionDao = TransactionDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        AuthFilter.AuthResult authResult = AuthFilter.doFilter(request);
        if (!authResult.isLoggedIn) {
            return (new HttpResponseBuilder()).setStatus("401 Unauthorized");
        } else {
            UserDto user = (UserDto)userDao.query(new Document("userName", authResult.userName)).get(0);
            RestApiAppResponse res;
                res = new RestApiAppResponse(true, List.of(user), (String)null);
                return (new HttpResponseBuilder()).setStatus("200 OK").setBody(res);

        }
    }
}
