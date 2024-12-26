import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import {
    Container,
    Paper,
    Typography,
    CardMedia,
    CardContent,
    Button,
    Grid,
    Box,
    CircularProgress,
    IconButton
} from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';
import { useCart } from '../components/CartContext'; // Import useCart

const BookDetail = () => {
    const { maSach } = useParams(); // Lấy maSach từ URL
    const [book, setBook] = useState(null);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true); // Trạng thái loading
    const [quantity, setQuantity] = useState(1); // Số lượng sách
    const { addToCart } = useCart(); // Lấy hàm addToCart từ context

    // Hàm chuyển đổi giá từ chuỗi "19,800₫" thành số
    const parsePrice = (priceString) => {
        const numericString = priceString.replace(/[^0-9.-]+/g, ""); // Loại bỏ các ký tự không phải là số
        return parseFloat(numericString); // Chuyển chuỗi thành số
    };

    useEffect(() => {
        const fetchBookDetail = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/profit/${maSach}`);
                setBook(response.data); // Giả sử response.data chứa thông tin sách
            } catch (error) {
                setError('Không tìm thấy thông tin sách.');
            } finally {
                setLoading(false); // Kết thúc loading
            }
        };

        fetchBookDetail();
    }, [maSach]);

    const handleIncrease = () => {
        setQuantity(prevQuantity => prevQuantity + 1);
    };

    const handleDecrease = () => {
        setQuantity(prevQuantity => Math.max(prevQuantity - 1, 1)); // Giới hạn số lượng tối thiểu là 1
    };

    const handleAddToCart = () => {
        addToCart(book.maSach, quantity); // Gọi hàm addToCart với maSach và quantity
        console.log(`Thêm ${quantity} ${book.tenSach} vào giỏ hàng.`);
    };

    const handleBuyNow = async () => {
        const price = parsePrice(book.giaKM); // Chuyển giá khuyến mãi thành số
        const totalAmount = price * quantity; // Tính tổng tiền (giả sử tính theo giá khuyến mãi)

        const orderData = {
            tenSanPham: book.tenSach,       // Tên sách
            soLuong: quantity,              // Số lượng sách
            tongTien: totalAmount           // Tổng tiền
        };

        try {
            const response = await axios.post('http://localhost:8080/api/order', orderData);
            alert('Đặt mua thành công!');
            console.log(response.data); // Xử lý phản hồi từ backend
        } catch (error) {
            alert('Có lỗi xảy ra khi đặt mua.');
            console.error(error);
        }
    };

    if (loading) {
        return <CircularProgress />;
    }

    if (error) {
        return <Typography variant="body1" color="error" align="center">{error}</Typography>;
    }

    return (
        <Container>
            {book ? (
                <Grid container spacing={1} alignItems="flex-start">
                    <Grid item xs={12} sm={6}>
                        <Box display="flex" justifyContent="center">
                            <Paper elevation={3} style={{ padding: '10px', borderRadius: '8px' }}>
                                <CardMedia
                                    component="img"
                                    className="zoom-image"
                                    style={{
                                        width: '70%',
                                        height: 'auto',
                                        objectFit: 'cover',
                                        margin: '0 auto',
                                    }}
                                    image={book.linkAnh}
                                    alt={book.tenSach}
                                />
                            </Paper>
                        </Box>
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <CardContent>
                            <Typography variant="h4" gutterBottom>
                                {book.tenSach}
                            </Typography>
                            <Typography variant="h6" color="textSecondary">
                                Tác giả: {book.tenTG}
                            </Typography>
                            <Typography variant="body1" color="textPrimary" style={{ marginTop: '10px' }}>
                                Mã sách: {book.maSach}
                            </Typography>
                            <Typography variant="body1" style={{ textDecoration: 'line-through', color: 'red' }}>
                                Giá Gốc: {book.giaGoc}
                            </Typography>
                            <Typography variant="body1" style={{ color: 'red' }}>
                                Giá Khuyến Mãi: {book.giaKM}
                            </Typography>
                            <Typography variant="body1" color="textPrimary" style={{ marginTop: '10px' }}>
                                Đối Tượng: {book.tenDoiTuong}
                            </Typography>
                            <Typography variant="body1" color="textPrimary" style={{ marginTop: '10px' }}>
                                Số Trang: {book.soTrang}
                            </Typography>
                            <Typography variant="body1" color="textPrimary" style={{ marginTop: '10px' }}>
                                Số Lượng Còn: {book.soLuongCon}
                            </Typography>
                            <Typography variant="body2" style={{ marginTop: '10px' }}>
                                Mô tả: {book.moTa}
                            </Typography>

                            <Box mt={2} display="flex" alignItems="center">
                                <IconButton onClick={handleDecrease} aria-label="Giảm số lượng">
                                    <RemoveIcon />
                                </IconButton>
                                <Typography variant="h6" style={{ margin: '0 10px' }}>
                                    {quantity}
                                </Typography>
                                <IconButton onClick={handleIncrease} aria-label="Tăng số lượng">
                                    <AddIcon />
                                </IconButton>
                            </Box>

                            <Box mt={2}>
                                <Button variant="contained" color="primary" fullWidth onClick={handleAddToCart}>
                                    THÊM VÀO GIỎ HÀNG
                                </Button>
                                <Button variant="outlined" color="primary" fullWidth style={{ marginTop: '10px' }} onClick={handleBuyNow}>
                                    MUA NGAY
                                </Button>
                            </Box>
                        </CardContent>
                    </Grid>
                </Grid>
            ) : (
                <Typography variant="body1" align="center">Không có thông tin sách.</Typography>
            )}
        </Container>
    );
};

export default BookDetail;
