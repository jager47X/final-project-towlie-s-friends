package handler;

import request.ParsedRequest;

public class HandlerFactory {
  // routes based on the path. Add your custom handlers here

public static BaseHandler getHandler(ParsedRequest request) {
      switch (request.getPath()) {
          case "/createDeposit" -> {

              return new CreateDepositHandler();
          }
          case "/createUser" -> {

              return new CreateUserHandler();
          }
          case "/isUserLoggedIn" -> {

              return new GetIsUserLoggedIn();
          }
          case "/transfer" -> {

              return new TransferHandler();
          }
          case "/withdraw" -> {

              return new WithdrawHandler();
          }

          case "/purchase" -> {

              return new PurchaseHandler();
          }
          case "/gift" -> {

              return new GiftHandler();
          }
          case "/sell" -> {

              return new SellHandler();
          }
          case "/login" -> {

              return new LoginHandler();
          }
          case "/logout" -> {

              return new LogoutHandler();
          }
          case "/getItems" -> {

              return new GetItemsHandler();
          }
          case "/getUserInfo" -> {

              return new GetUserInfoHandler();
          }
          case "/getPayInfo" -> {

                return new PaymentHandler();
          }
          case "/transactions"->{
              return new GetTransactionsHandler();
          }

          default -> {
              return new FallbackHandler();
          }
      }
  }

}
