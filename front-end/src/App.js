import './App.css';
import HomePage from './HomePage';
import Login from './Login';
import Gift from './Gift';
import Purchsase from './Purchase';
import Sell from './Sell';

import Payment from './Payment';
import { BrowserRouter, Routes, Route } from "react-router-dom";

// https://reactrouter.com/en/main/start/examples
function App() {
  return (
    <BrowserRouter>
      <div>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/login" element={<Login />} />
            <Route path="/gift" element={<Gift />} />
          <Route path="/purchase" element={<Purchsase />} />
          <Route path="/payment" element={<Payment />} />
          <Route path="/sell" element={<Sell />} />

        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
