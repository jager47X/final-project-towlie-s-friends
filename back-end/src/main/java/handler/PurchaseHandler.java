package handler;

import dao.ItemDao;
import dao.UserDao;
import dto.ItemDto;
import dto.TransactionType;
import dto.UserDto;
import request.ParsedRequest;
import response.HttpResponseBuilder;
import dao.TransactionDao;
import dao.UserDao;
import dto.TransactionDto;
import dto.TransactionType;
import dto.UserDto;
import org.bson.Document;
import request.ParsedRequest;
import response.HttpResponseBuilder;

import java.util.List;
import org.bson.Document;
import response.RestApiAppResponse;

import java.util.List;
import java.util.ListResourceBundle;


        public class PurchaseHandler implements BaseHandler {
            @Override
            public HttpResponseBuilder handleRequest(ParsedRequest request) {

                TransactionDto payment = GsonTool.gson.fromJson(request.getBody(), TransactionDto.class);
                var paymentType = payment.getTransactionType();//get payment type
                //filter the current user in DB
                String userId = payment.getUserId();
                Document filter = new Document("userId", userId);
                List<UserDto> userDtoList = UserDao.getInstance().query(filter);
                UserDto currentUser = userDtoList.get(0);


                if (AuthFilter.doFilter(request).userName.equals(currentUser.getUserName()) && AuthFilter.doFilter(request).isLoggedIn) {//auth

                    //yuto
                    TransactionDao transactionDao = TransactionDao.getInstance();
                    transactionDao.put(payment);
                    UserDao.getInstance().put(currentUser);//insert






                    //start John
                    ItemDto itemDto = (ItemDto) GsonTool.gson.fromJson(request.getBody(), ItemDto.class);
// get buyId, objectid, sellId

                    itemDto.setTransactionType(TransactionType.Purchase);
                    UserDao userDao = UserDao.getInstance();
                    ItemDao itemDao = ItemDao.getInstance();

                    ItemDto item = itemDao.query(new Document("_id", itemDto.getObjectId())).iterator().next();

                    //UserDto buyId = (UserDto) userDao.query(new Document("userName", itemDto.getBuyId())).iterator().next();
                    UserDto sellId = (UserDto) userDao.query(new Document("userName", itemDto.getSellId())).iterator().next();


                    itemDto.setBuyId(currentUser.getUserName());


                    itemDto.setCost(item.getCost());
                    double costAmount = item.getCost();


                    itemDto.setSellId(item.getSellId());


                    if (currentUser.getBalance() < costAmount) {

                        var res = new RestApiAppResponse<>(false, null, "Not enough funds for this item");
                        return new HttpResponseBuilder().setBody(res).setStatus(StatusCodes.UNAUTHORIZED);
                    } else {
                        currentUser.setBalance(currentUser.getBalance() - itemDto.getCost());


                        sellId.setBalance((sellId.getBalance() + itemDto.getCost()));


                    }

                    userDao.put(currentUser);
                    userDao.put(sellId);
                    ItemDao.getInstance().put(itemDto);
                    itemDao.bought(itemDto);

                    return new HttpResponseBuilder()
                            .setStatus(StatusCodes.OK)
                            .setBody(transactionDao.toString());

                }

                return new HttpResponseBuilder()
                        .setStatus(StatusCodes.UNAUTHORIZED)
                        .setBody("User Not Found");


            }
        }


