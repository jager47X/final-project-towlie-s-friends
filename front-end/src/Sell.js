import React from "react";
import { redirect } from 'react-router-dom';
import { Navigate } from 'react-router-dom';
import Login from './Login';
import Gift from './Gift';
import Purchsase from './Purchase';





export default function Sell(){
    const [itemName, setItemName] = React.useState('');
    const [cost, setCost] = React.useState('');
    const [sellId, setSellId] = React.useState('');
    const [image, setImage] = React.useState('');

    const [transactionType, setTransactionType] = React.useState('');

    const [shouldRedirect, setShouldRedirect] = React.useState(false);
    const [message, setMessage] = React.useState('');

    const [RedirectToHome, setRedirectToHome] = React.useState(false);
    const [RedirectToPayment, setRedirectToPayment] = React.useState(false);
    const [RedirectToLogin, setRedirectToLogin] = React.useState(false);
    const [timestamp, setTimestamp] = React.useState(false);


    function login() {
        setRedirectToLogin(true);
};
function payment(){
    setRedirectToPayment(true);
};
function HomePage() {
    setRedirectToHome(true);
}

    function updateItemName(event) {
        setItemName(event.target.value); // updating react state variable
    }

    function updateImage(event) {
        setImage(event.target.value); // updating react state variable
    }

    function updateCost(event) {
const numberValue = Number(event.target.value);
if (isNaN(numberValue)){
    return;
}
setCost(event.target.value)
    }
    function updateSellId(event) {
        setSellId(event.target.value);
    }


function sell(){
    setMessage('');
    const itemDto= {
        itemName: itemName,
        cost: Number(cost),
        sellId: sellId,
        image: image,
    };
    console.log(itemDto);
    const options = {
        method: 'POST',
        body: JSON.stringify(itemDto)
    };
        fetch('/sell', options)
    //.then(res => res.json())
    .then((apiRes) => {
        console.log(apiRes);
        if (apiRes.status){
            setItemName('');
            setCost('');
            setSellId('');
            setImage('');

            setMessage("Item for sale")

        }else{
            setMessage("Server error");
        }
    })
    
}

React.useEffect(() => {
    // triggers when componenet mounds
    // https://react.dev/reference/react/useEffect
    // fetching data
    // https://developer.mozilla.org/en-US/docs/Web/API/fetch
}, []);

if(RedirectToLogin){
    return <Navigate to="/Login" replace={true} />;
}else if(RedirectToHome){
    return <Navigate to="/" replace={true} />;
}else if(RedirectToPayment){
    return <Navigate to="/payment" replace={true} />;

}

    return (

        <div>
            

            <div>
            <button onClick={payment}>Go to Cart</button>
            <button onClick={HomePage}>HomePage</button>
                <h1>Sell Item</h1>
                <div>
                {message}
                </div>
                <input placeholder="Item Name" value={itemName} onChange={updateItemName}></input>
                <input type="number" placeholder="Cost" value={cost} onChange={updateCost}></input>
            
        
                    <input placeholder="Image Address (optional)" value={image} onChange={updateImage}></input>            </div>
            
                <div>
                <button onClick={sell}>Sell</button>
                </div>

        </div>

    );}