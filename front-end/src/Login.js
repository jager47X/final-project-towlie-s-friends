import React from 'react';
import { Navigate } from 'react-router-dom';

export default function Login() {
    const [userName, setUserName] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [shouldRedirect, setShouldRedirect] = React.useState(false);
    const [message, setMessage] = React.useState('');


 

    
    function updateUserName(event) {
        setUserName(event.target.value); // updating react state variable
    }

    function updatePassword(event) {
        setPassword(event.target.value);
    }

    function register() {
        setMessage('');
        console.log('Registering ' + userName + ' ' + password);
        // send request to back end
        const userDto = {
            userName: userName,
            password: password
        };
        console.log(userDto);
        const options = {
            method: 'POST',
            body: JSON.stringify(userDto)
        };
        fetch('/createUser', options) // network call = lag
            .then((res) => res.json()) // it worked, parse result
            .then((apiRes) => {
                console.log(apiRes); // RestApiAppResponse
                if (apiRes.status) { // at the app layer, tell if worked or not
                    setUserName('');
                    setPassword('');
                    setMessage('Your account has been created!');
                    
                } else {
                    setMessage(apiRes.message); // tell end user why?
                }
            })
            .catch((error) => {
                console.log(error);
            }) // it did not work
    }

    function logIn() {
        setMessage('');
        console.log('Loging in ' + userName + ' ' + password);
        // send request to back end
        const userDto = {
            userName: userName,
            password: password
        };
        console.log(userDto);
        const options = {
            method: 'POST',
            body: JSON.stringify(userDto)
        };
        fetch('/login', options) // network call = lag
            //.then((res) => res.json()) // it worked, parse result
            .then((apiRes) => {
                console.log(apiRes);
                if(apiRes.ok){
                    console.log('Login worked');
                    setShouldRedirect(true);
                }else{
                    setMessage('Failed to log in');
                }
                console.log('Worked'); // RestApiAppResponse

            })
            .catch((error) => {
                console.log(error);
                setMessage('Failed to log in');
            }) // it did not work
    }

    // redirect
    if(shouldRedirect){
        return <Navigate to="/" replace={true} />;
    }

    return (
        <main class="form-main">
        <div class="login-form">
        <div>
            <h3 class="main-title">Login Form</h3>
        </div>
            
            {message}
            <form>
            <div class="username">
            <label for="username">Username</label>
                <input value={userName} onChange={updateUserName} UserName />
               </div>
               <div class="password">
               <label for="password">Password</label>
                <input type="password" value={password} onChange={updatePassword}Password />
            </div>
            </form>
            <div>
                <button type="submit" class="btn btn-outline-primary" onClick={register}>Register</button>
                <button type="submit" class="btn btn-outline-primary" onClick={logIn}>Login</button>
            </div>
            
        </div>
        </main>
    );
}