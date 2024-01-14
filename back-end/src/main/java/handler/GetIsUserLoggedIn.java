package handler;

import dto.TransactionDto;

import java.util.List;

import request.ParsedRequest;
import response.HttpResponseBuilder;
import response.RestApiAppResponse;

public class GetIsUserLoggedIn implements BaseHandler {

    public HttpResponseBuilder handleRequest(ParsedRequest request) {


        AuthFilter.AuthResult authResult = AuthFilter.doFilter(request);
        if (!authResult.isLoggedIn) {

            RestApiAppResponse<TransactionDto> res = new RestApiAppResponse(true, (List)null, "Not Logged in");
            return (new HttpResponseBuilder()).setStatus(StatusCodes.OK).setBody(res);
        } else {
            var userName= authResult.userName;
            RestApiAppResponse<TransactionDto> res = new RestApiAppResponse(true, List.of(userName), "already logged in");
            return (new HttpResponseBuilder()).setStatus(StatusCodes.OK).setBody(res);
        }
    }
}
