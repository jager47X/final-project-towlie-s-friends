package handler;

import dao.TransactionDao;
import dto.TransactionDto;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import request.ParsedRequest;
import response.HttpResponseBuilder;
import response.RestApiAppResponse;

public class GetTransactionsHandler implements BaseHandler {

    public HttpResponseBuilder handleRequest(ParsedRequest request) {
        TransactionDao transactionDao = TransactionDao.getInstance();
        AuthFilter.AuthResult authResult = AuthFilter.doFilter(request);
        if (!authResult.isLoggedIn) {
            return (new HttpResponseBuilder()).setStatus("401 Unauthorized");
        } else {
            List<Document> filterList = new ArrayList();
            filterList.add(new Document("userId", authResult.userName));
            filterList.add(new Document("toId", authResult.userName));
            Document orFilter = new Document("$or", filterList);
            RestApiAppResponse<TransactionDto> res = new RestApiAppResponse(true, transactionDao.query(orFilter), (String)null);
            return (new HttpResponseBuilder()).setStatus("200 OK").setBody(res);
        }
    }
}