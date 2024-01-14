import React, { useEffect, useState } from 'react';
import { Navigate } from 'react-router-dom';

export default function HomePage() {
  const [redirectToPayment, setRedirectToPayment] = useState(false);
  const [redirectToLogin, setRedirectToLogin] = useState(false);
  const [redirectToSell, setRedirectToSell] = useState(false);
  const [products, setProducts] = useState([]);
  const [LoggedInUser, setLoginUser] = useState([]);
  const [userInfo, setUserInfo] = useState([]);
  const [isLoggedIn, setLoginStatus] = useState(false);
    const [RedirectToHome, setRedirectToHome] = React.useState(false);
 
  React.useEffect(() => {
    // Fetch product data from the backend
    fetch("/getItems")
      .then((res) => res.json())
      .then((apiRes) => {
        console.log(apiRes);
        
        setProducts(apiRes.data);
      })
      .catch((error) => {
        console.error(error);
      });
      fetch("/isUserLoggedIn")
      .then((res) => res.json())
      .then((LoggedInUser) => {
        console.log(LoggedInUser);
       
        setLoginUser(LoggedInUser.data);
        if(LoggedInUser.data!=null){
          setLoginStatus(true);
        }
        
      })
      .catch((error) => {
        setLoginStatus(false);
        console.error(error);
      });
      fetch("/getUserInfo")
      .then((res) => res.json())
      .then((userInfo) => {
        console.log(userInfo);
        setUserInfo(userInfo.data); 
      })
      .catch(() => {
        console.log("User Not Logged in");
        setLoginStatus(false);
      });

      
  }, []);

  const renderUserInfo = (userInfo) => {
    // Fix: Remove <h4> tag, directly return the array
    return userInfo.map((user) => (
      <div key={user.uniqueId} className="userName">
        <a>Hi! {user.userName}</a>
        <a>. Your Balance ${user.balance.toFixed(2)}</a>
      </div>
    ));
  };



  const renderProducts = (products) => {
    // Render products on the page
    return products.map((product) => (
        
      <div key={product.uniqueId}className="product">
        <img src={product.image} alt={product.id} />
        <h2>{product.itemName}</h2>
        <p>${product.cost.toFixed(2)}</p>
        <div class="purchaseButton" id="purchaseButton">
        <button onClick={() => addToCart(product.Id)}>Purchase this</button>
        </div>
      </div>
    ));
  };

  const renderLogin=()=>{
    if (isLoggedIn) {
        
        return renderUserInfo(userInfo);
      } else {
        return <button onClick={login}>Wanna Login?</button>;
      }
  }
  const renderLogoutButton = () => {
    if (isLoggedIn) {
        
      return <button onClick={logout}>Wanna Logout?</button>;
    } else {
      return ;
    }
  };
  const renderSellButton = () => {
    if (isLoggedIn) {
      return <button onClick={sell}>Wanna Sell Item?</button>;
    } else {
      return;
    }
  };
  

  const addToCart = (productId) => {
    alert(`Added product with ID ${productId} to the cart!`);
    const id = productId;
    //fetch some stuff to connect to puchase/gift
    setRedirectToPayment(true);
    

  
  };

  const login = () => {
    setRedirectToLogin(true);
  };
  const logout = () => {
    setLoginStatus(false);
    fetch("/logout")
    .then((res) => res.json())
    .then((user) => {
      console.log(user);
      // Update the products state with the fetched data
      setLoginUser(user.data);
    })
    .catch((error) => {
      console.error(error);
    });
    
    setRedirectToHome(true);

  };

 
  const payment = () => {
    setRedirectToPayment(true);
  };

  const sell = () => {
    setRedirectToSell(true);
  };

  if (redirectToLogin) {
    return <Navigate to="/Login" replace={true} />;
  } else if (redirectToPayment) {
    return <Navigate to="/payment" replace={true} />;
  } else if (redirectToSell) {
    return <Navigate to="/Sell" replace={true} />;
  }

  return (
    <div>
      <div class="header">
     
        {renderLogin()}
      {renderLogoutButton()}
      
      {renderSellButton()}
      
      </div>
      
      <h4>---------Listed Items--------</h4>
      <main>
      <div class="product-list" id="productlist">
      {renderProducts(products)}
      </div>
      
        </main>
    </div>
  );
}
