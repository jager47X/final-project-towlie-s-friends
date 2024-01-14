import React, { useEffect, useState } from 'react';
import './Gift.css'
import { Navigate } from 'react-router-dom';


export default function Login() {
    const [RedirectToPurchase, setRedirectToPurchase] = React.useState(false);
    const [RedirectToGift, setRedirectToGift] = React.useState(false);
    const [RedirectToHome, setRedirectToHome] = React.useState(false);
    const [message, setMessage] = React.useState('');
    const [user, setUser] = React.useState('');
    const [userInfo, setUserInfo] = useState([]);
    const [LoggedInUser, setLoginUser] = useState([]);
    const [isLoggedIn, setLoginStatus] = useState(false);
    const [transactions, setTransactions] = React.useState([]);
    const [Preview, setPreviewShow] = React.useState(false);


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

    //limits for the user inputs
    const userLimit = (e) => {
        const limit = 30;
        setUser(e.target.value.slice(0, limit));
        }

    const mesLimit = (e) => {
        const limit = 245;
        setMessage(e.target.value.slice(0, limit));
    }
    //end of limits for user input

    function HomePage() {
        setRedirectToHome(true);
    }
    //setting up the preview and purchase buttons
    const purchase = (e) =>{
       console.log('Im buying this as a gift');
    };
    //preview is supposed to show the preview of the message + to user and click again to close
    //the from will be updated once backend for constent user is set up
    const showPreview = ()=>{
         
       setPreviewShow((Preview)=>!Preview);
        
    };

    if(RedirectToHome){
        return <Navigate to="/" replace={true} />;
    }

    return (
        <div>
            <h1>Gift Page</h1>
            <div>
                
                <div className="flexbox">
                <h4> Gift message</h4>
                    
                <input 
                    className="user" 
                    type='text' 
                    onChange={userLimit}
                    value={user} 
                    placeholder='To user'
                />        
                <input 
                    className="message" 
                    type='text'
                    onChange={mesLimit}
                    value={message} 
                    //maxLength={215} 
                    placeholder='Happy holidays!'
                />
                    
                <h3>Add ons</h3>
      <button type="checkbox" onClick={showPreview}>Show Preview</button>
             
                <div id="preview" className={Preview ? " " : "hidden"}>
                       
                <h1>Preview</h1>
                <p>To {user} </p>
                {message}
                <p>from {user.userName}</p>
                </div>
                
                <label>
                
                <input type="checkbox"></input>
                wraped up gift at $2.99
                </label>
                
                </div>
             
            <button onClick={HomePage}>Back to Home</button>
            <button onClick={purchase}>Pay Gift</button>           
            </div>
        </div>
    );
}