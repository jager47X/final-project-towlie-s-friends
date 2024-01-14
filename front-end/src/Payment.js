import React, { useEffect, useState } from 'react';
import { Navigate } from 'react-router-dom';

export default function PaymentPage() {
  const [RedirectToHome, setRedirectToHome] = useState(false);
  const [RedirectToPurchase, setRedirectToPurchase] = useState(false);
  const [RedirectToGift, setRedirectToGift] = useState(false);
  const [userInfo, setUserInfo] = useState([]);
  const [isLoggedIn, setLoginStatus] = useState(false);
  const [LoggedInUser, setLoginUser] = useState([]);
  const [message, setMessage] = useState('');
  const [transactions, setTransactions] = useState([]);
  const [RedirectToLogin, setRedirectToLogin] = useState(false);
 
  useEffect(() => {
    // Fetch user data from the backend
    fetch("/isUserLoggedIn")
      .then((res) => res.json())
      .then((LoggedInUser) => {
        console.log(LoggedInUser);
        setLoginUser(LoggedInUser.data);
        setLoginStatus(true);
      })
      .catch((error) => {
        console.error(error);
      });

    fetch("/getUserInfo")
      .then((res) => res.json())
      .then((userInfo) => {
        console.log(userInfo);
        setUserInfo(userInfo.data);
        setLoginStatus(true);
      })
      .catch((error) => {
        console.error(error);
      });

  }, []);

  const renderUserInfo = (userInfo) => {
    return userInfo.map((user) => (
      <div key={user.uniqueId} className="userName">
        <p>Hi! {user.userName}</p>
        <p>Your Balance ${user.balance.toFixed(2)}</p>
      </div>
    ));
  };

  const renderLogin = () => {
    if (isLoggedIn) {
      return renderUserInfo(userInfo);
    } else {
      return <button onClick={login}>Login</button>;
    }
  };

  const logout = () => {
    setLoginStatus(false);
    fetch("/logout")
      .then((res) => res.json())
      .then((user) => {
        console.log(user);
        setLoginUser(user.data);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const renderLogoutButton = () => {
    if (isLoggedIn) {
      return <button onClick={logout}>Logout</button>;
    } else {
      return null;
    }
  };

  function gift() {
    setRedirectToGift(true);
  }

  function homePage() {
    setRedirectToHome(true);
  }

  function purchase() {
    setRedirectToPurchase(true);
  }

  function login() {
    setRedirectToLogin(true);
  }

  // redirect
  if (RedirectToHome) {
    return <Navigate to="/" replace={true} />;
  } else if (RedirectToGift) {
    return <Navigate to="/gift" replace={true} />;
  } else if (RedirectToPurchase) {
    return <Navigate to="/purchase" replace={true} />;
  } else if (RedirectToLogin) {
    return <Navigate to="/login" replace={true} />;
  }

  return (
    <div>
      <h1>Wanna pay it?</h1>
      {message}
      <div class="header">
      
        {renderLogin()}
        <button onClick={homePage}>HomePage</button>
        {renderLogoutButton()}
        </div>
<div class="payment">
        <div class="total">Your Total $</div>
        <button onClick={gift}>Purchase for Gift</button>
        <button onClick={purchase}>Purchase for Myself</button>
        
     
     
        </div>
    </div>
  );
}
