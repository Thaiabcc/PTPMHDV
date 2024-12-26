import React, { useState } from "react";
import { Card, CardContent, CardMedia, Typography, Box } from "@mui/material";
import { useNavigate } from 'react-router-dom';
import { useCart } from '../components/CartContext';
import { useFavorites } from '../components/FavoritesContext';
import FavoriteIcon from '@mui/icons-material/Favorite';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import Button from '@mui/material/Button';

const BookCard = ({ book }) => {
    const navigate = useNavigate();
    const { addToCart } = useCart();
    const { addToFavorites, removeFromFavorites, favorites } = useFavorites();
    const [hovered, setHovered] = useState(false);

    const isFavorite = favorites.some(fav => fav.maSach === book.maSach);

    const handleFavoriteToggle = () => {
        if (isFavorite) {
            removeFromFavorites(book.maSach);
        } else {
            addToFavorites(book);
        }
    };

    const handleAddToCart = () => {
        addToCart(book.maSach, 1);
        navigate('/cart');
    };

    const handleViewDetail = () => {
        navigate(`/profit/${book.maSach}`);
    };

    const formatPrice = (priceString) => {
        return parseFloat(priceString.replace(/,/g, '').replace('₫', '').trim());
    };

    const originalPrice = formatPrice(book.giaGoc);
    const salePrice = formatPrice(book.giaKM);
    const discountPercentage = ((originalPrice - salePrice) / originalPrice) * 100;

    return (
        <Card
            sx={{ maxWidth: 300, margin: 'auto', boxShadow: 3 }}
            onMouseEnter={() => setHovered(true)}
            onMouseLeave={() => setHovered(false)}
        >
            <CardMedia
                component="img"
                sx={{
                    height: 350,
                    objectFit: 'contain',
                    backgroundColor: '#f9f9f9',
                    transition: 'transform 0.3s ease',
                    transform: hovered ? 'scale(1.05)' : 'scale(1)',
                }}
                image={book.linkAnh}
                alt={book.tenSach}
            />
            <CardContent>
                <Typography variant="h6" fontWeight="bold" gutterBottom>
                    {book.tenSach}
                </Typography>
                <Typography variant="subtitle1" color="text.secondary">
                    Tác giả: {book.tenTG}
                </Typography>
                <Typography
                    variant="body2"
                    color="text.secondary"
                    sx={{ textDecoration: 'line-through', marginTop: 1 }}
                >
                    Giá gốc: {book.giaGoc}
                </Typography>
                <Typography variant="body2" color="primary" sx={{ marginTop: 1 }}>
                    Giá khuyến mãi: {book.giaKM}
                </Typography>

                {salePrice < originalPrice && (
                    <Typography variant="body2" color="error" sx={{ marginTop: 1 }}>
                        Giảm giá: {Math.round(discountPercentage)}%
                    </Typography>
                )}

                <Box
                    display="flex"
                    justifyContent="space-between"
                    alignItems="center"
                    sx={{ marginTop: 2 }}
                >
                    <Box
                        display="flex"
                        sx={{
                            transition: 'transform 0.3s ease-in-out',
                            transform: hovered ? 'translateX(0)' : 'translateX(-30px)', // Trượt từ trái sang phải
                            opacity: hovered ? 1 : 0, // Ẩn khi không hover
                        }}
                    >
                        <Button
                            onClick={handleFavoriteToggle}
                            sx={{ minWidth: 'auto', padding: 0 }}
                        >
                            {isFavorite ? (
                                <FavoriteIcon color="secondary" />
                            ) : (
                                <FavoriteBorderIcon />
                            )}
                        </Button>
                    </Box>

                    <Box
                        display="flex"
                        flexDirection="column"
                        sx={{
                            transition: 'transform 0.3s ease-in-out',
                            transform: hovered ? 'translateY(0)' : 'translateY(20px)', // Trượt từ dưới lên
                            opacity: hovered ? 1 : 0, // Ẩn khi không hover
                            gap: 1,
                        }}
                    >
                        <Button
                            variant="outlined"
                            color="primary"
                            onClick={handleViewDetail}
                            sx={{ width: '100%' }} // Giữ nguyên hình dạng
                        >
                            Xem Chi Tiết
                        </Button>
                        <Button
                            variant="contained"
                            color="primary"
                            onClick={handleAddToCart}
                            sx={{ width: '100%' }} // Giữ nguyên hình dạng
                        >
                            Thêm vào giỏ hàng
                        </Button>
                    </Box>
                </Box>
            </CardContent>
        </Card>
    );
};

export default BookCard;