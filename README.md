# Final Project


Add your names here!
John King
Yuto Mori
Cole Douglas
Javi Buenrostro


Description,Yuto:
I added homepage where it shows products info including the image, name, price. I also added feature on the head part where if user login it shows more button such as sell and name of user and their balance and logout button but if user is not login they will be hidden.  I implemented front-end, html and css homepage and login and payment and header part. The handler I added are getItemhandler where it takes all inof of items on the market, getuserinfo-handler where it take userinfo from the database, and Isuserlogin-handler to check if the user is login( I think this no longer needed since I made userinfohandler), and I also added logout-handler to logout user.

The difficulties I encountered was I was not being able to collect product info even my code was perfectly working, turns out it was working with my other project member function as well, my takeover from this is communication is immportant making a project together. I also made a lot of error through javascript because I do not have a lot of knowlegde of it, but at the end I learned few basic things to make a website.

Description,Cole:
The feature I am working on implementing is refund which would include the handler and the pieces needed on the front end as well.

Difficulties I enountered so far is trying navigate git and github with a group of people is not something I have had to work with before so the coordination a difficulty.

Description,John:

The first feature I added was the sell handler. What this handler does is allows people on the website to sell items on the website for a specific price, this item then gets put in the database ItemDto and can then be purchased. I approached this by creating an ItemDto that items are put in with an item name, the seller's id, and the cost. In the sell handler, ItemDtos objects are created and then used to put data into the database. The Auth Dto gets the username, and puts it in the database as sellId, then the item name and cost are received as requests from the website and put in the username. This creates an item document in the database that could then be used to receive the price and sell the item for the correct price to whoever is buying it, and give that amount of money to the seller's Id.
I encountered difficulties with this project as well. One of my difficulties was trying to figure out why it was not putting things into the database. I did not know how to fix it until I figured I could use postman to tell me what the problem is and fix it. Most of my difficulties with this problem came from the front end. I did not know how to connect the front end to the back end, but when I saw how my team members did it I realized how, but it still didn't work, because I did not have the server running, which I figured out.

The other feature I did was the purchase handler. This handler takes an item from the database and gives that amount of money to the seller using sellId and takes money from the buyer buyId. I approached this handler by creating user and item dto objects and using request input to find the item and charge the correct amount. The user dto sets the correct seller and buyer and gives each of them the correct amount of money depending on the input. The handler also stops you from buying an object if you do not have enough money to purchase it. The handler puts the new amount of money in the database and removes the item from the database.
I had difficulties with this project more than the other one I believe. It worked when I tested it in postman and when I tried to combine it with my teammates’ code it did not work. I only made the handler to purchase an object, and they had created a shopping cart and it did not work anymore with the shopping cart. I also had difficulty figuring out what to do for the front end because a purchase does not really have a front.

I am not yet done with my project. I am done with the sell handler, but not the purchase handler. I need to either try to figure out how to get my handler to work with the shopping cart, or ask them if they are ok to make it so users purchase objects one at a time. Once I can get the purchase handler somewhere in the project, then it is complete.


Description,Javi:

 I made the front end for the Gift.js page along with its css styling that has a input box for the To and the message you want to send clicking show preview makes those show up in a previewd format and clicking show preview again makes that preview hidden. I also included a check box that would have been an added charge for the gift being wraped up and the three back end additions I wanted to get added are getting the proper logged in user onto the from part that is shown in the preview, the item that was added to cart, and the payment itself meaning charging the user. (I now have the user added into the from hopefully its set to the proper user thats logged in from the backend)

 Dificulties I encountered: I struggled trying to get the user from the backend, more so I was worried about breaking something there besides that I also had the issue of forgetting to get the server running to send data to the backend. I also struggled trying to figure out how to connect the backend to my front end part on the gift page 



Demo video link:

https://youtu.be/42PRgSleA7A