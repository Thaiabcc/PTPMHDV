import React from 'react';
import { useCart } from '../components/CartContext';
import { Typography, Button, Box } from '@mui/material';
import axios from 'axios'; // Nhập axios để gửi yêu cầu đến backend

// Hàm để chuyển đổi chuỗi giá thành số
const parsePrice = (priceStr) => {
    return parseFloat(priceStr.replace(/[^0-9.-]+/g, ""));
};

// Hàm để định dạng số với dấu phẩy
const formatCurrency = (amount) => {
    return amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "₫";
};

const Cart = () => {
    const { cart, removeFromCart, increaseQuantity, decreaseQuantity } = useCart();

    if (cart.length === 0) {
        return (
            <Typography
                variant="h6"
                textAlign="center"
                style={{
                    marginTop: '20px',
                    color: '#888',
                    fontStyle: 'italic',
                }}
            >
                Giỏ hàng trống!
            </Typography>
        );
    }

    // Tính tổng tiền
    const totalAmount = cart.reduce((total, item) => {
        const price = parsePrice(item.giaKM); // Chuyển đổi giá thành số
        return total + (price * item.quantity); // Tính tổng
    }, 0);

    // Hàm xử lý thanh toán
    const handlePayment = async () => {
        try {
            const response = await axios.post('http://localhost:8080/thanhtoan/payment', { cartTotal: totalAmount });
            window.location.href = response.data.url; // Chuyển hướng đến trang thanh toán
        } catch (error) {
            console.error("Error initiating payment:", error);
        }
    };

    return (
        <Box
            style={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                padding: '20px',
                backgroundColor: '#f5f5f5',
                borderRadius: '8px',
                boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
            }}
        >
            <h2 style={{ textAlign: 'center', marginBottom: '30px', color: '#3f51b5' }}>
                Giỏ hàng của bạn
            </h2>
            <ul style={{ listStyleType: 'none', padding: 0, width: '100%' }}>
                {cart.map(item => (
                    <li
                        key={item.maSach} // Sử dụng maSach làm khóa
                        style={{
                            display: 'flex',
                            alignItems: 'center',
                            justifyContent: 'space-between',
                            backgroundColor: '#fff',
                            padding: '15px',
                            borderRadius: '8px',
                            boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
                            marginBottom: '15px',
                        }}
                    >
                        {/* Hình ảnh sách */}
                        <img
                            src={item.linkAnh}
                            alt={item.tenSach}
                            style={{
                                width: '70px',
                                height: '70px',
                                objectFit: 'cover',
                                borderRadius: '8px',
                                marginRight: '15px',
                            }}
                        />

                        {/* Thông tin sách */}
                        <div style={{ flex: 1, marginRight: '15px' }}>
                            <Typography variant="subtitle1" style={{ fontWeight: 'bold' }}>
                                {item.tenSach}
                            </Typography>
                            <Typography variant="body2" color="text.secondary">
                                Đơn giá: {item.giaKM}
                            </Typography>
                            <div style={{ display: 'flex', alignItems: 'center' }}>
                                <Button onClick={() => decreaseQuantity(item.maSach)}>-</Button>
                                <Typography style={{ margin: '0 10px' }}>{item.quantity}</Typography>
                                <Button onClick={() => increaseQuantity(item.maSach)}>+</Button>
                            </div>
                            <Typography variant="body2" color="text.secondary">
                                Thành tiền: {formatCurrency(parsePrice(item.giaKM) * item.quantity)}
                            </Typography>
                        </div>

                        {/* Nút xóa */}
                        <Button
                            variant="contained"
                            color="secondary"
                            onClick={() => removeFromCart(item.maSach)}
                            style={{
                                backgroundColor: '#f44336',
                                color: '#fff',
                            }}
                        >
                            Xóa
                        </Button>
                    </li>
                ))}
            </ul>

            {/* Tổng tiền */}
            <Box
                style={{
                    marginTop: '20px',
                    padding: '10px',
                    backgroundColor: '#e0f7fa',
                    borderRadius: '8px',
                    width: '100%',
                    display: 'flex',
                    justifyContent: 'space-between',
                }}
            >
                <Typography variant="h6" style={{ fontWeight: 'bold' }}>
                    Tổng: {formatCurrency(totalAmount)}
                </Typography>
                <Button
                    variant="contained"
                    color="primary"
                    onClick={handlePayment} // Gọi hàm xử lý thanh toán
                >
                    Tiến hành thanh toán
                </Button>
            </Box>
        </Box>
    );
};

export default Cart;