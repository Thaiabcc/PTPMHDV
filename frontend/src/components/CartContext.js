import React, { createContext, useContext, useState, useEffect } from 'react';
import axios from 'axios';

// Tạo context
const CartContext = createContext();

// Provider cho giỏ hàng
export const CartProvider = ({ children }) => {
    const [cart, setCart] = useState(() => {
        const savedCart = localStorage.getItem('cart');
        return savedCart ? JSON.parse(savedCart) : [];
    });

    useEffect(() => {
        localStorage.setItem('cart', JSON.stringify(cart));
    }, [cart]);

    const addToCart = async (maSach, quantity) => {
        try {
            const response = await axios.get(`http://localhost:8080/api/profit/${maSach}`);
            const product = response.data;

            setCart((prevCart) => {
                const existingProduct = prevCart.find(item => item.maSach === product.maSach);
                if (existingProduct) {
                    return prevCart.map(item =>
                        item.maSach === product.maSach
                            ? { ...item, quantity: item.quantity + quantity } // Cập nhật số lượng
                            : item
                    );
                }
                return [...prevCart, { ...product, quantity }]; // Thêm sản phẩm mới
            });
        } catch (error) {
            console.error('Có lỗi xảy ra khi thêm sản phẩm:', error);
        }
    };
    const removeFromCart = (maSach) => {
        setCart((prevCart) => prevCart.filter(item => item.maSach !== maSach));
    };

    const increaseQuantity = (maSach) => {
        setCart((prevCart) =>
            prevCart.map(item =>
                item.maSach === maSach ? { ...item, quantity: item.quantity + 1 } : item
            )
        );
    };

    const decreaseQuantity = (maSach) => {
        setCart((prevCart) =>
            prevCart.map(item =>
                item.maSach === maSach && item.quantity > 1
                    ? { ...item, quantity: item.quantity - 1 }
                    : item
            )
        );
    };

    return (
        <CartContext.Provider value={{ cart, addToCart, removeFromCart, increaseQuantity, decreaseQuantity }}>
            {children}
        </CartContext.Provider>
    );
};

// Hook để sử dụng context
export const useCart = () => {
    return useContext(CartContext);
};