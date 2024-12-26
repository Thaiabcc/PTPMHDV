import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { PayPalScriptProvider } from "@paypal/react-paypal-js";
import Header from "./components/Header";
import Home from "./pages/Home";
import BookDetail from "./pages/BookDetail";
import Cart from "./pages/Cart";
import Footer from "./components/Footer";
import ImageSlider from "./components/ImageSlider";
import { CartProvider } from './components/CartContext';
import { FavoritesProvider } from './components/FavoritesContext';
import FavoritesList from './pages/FavoritesList';
import Register from './components/Register'; // Import Register
import Login from './components/Login'; // Import Login

function App() {
  return (
    <CartProvider>
      <FavoritesProvider>
        <PayPalScriptProvider options={{ "client-id": "AbQwVxr872fgQ6ybIU1zj6aDAk3Km5nIka13ot308SJg1_d1bnkqqxaGnkixGSQgKrFG39R0wTSl47plpl" }}>
          <Router>
            <Header />
            <ImageSlider />
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/profit/:maSach" element={<BookDetail />} />
              <Route path="/cart" element={<Cart />} />
              <Route path="/favorites" element={<FavoritesList />} />
              <Route path="/register" element={<Register />} /> {/* Route cho trang đăng ký */}
              <Route path="/login" element={<Login />} /> {/* Route cho trang đăng nhập */}
            </Routes>
            <Footer />
          </Router>
        </PayPalScriptProvider>
      </FavoritesProvider>
    </CartProvider>
  );
}

export default App;