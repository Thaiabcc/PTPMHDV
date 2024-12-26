import React from "react";
import { AppBar, Toolbar, Typography, Button } from "@mui/material";
import { Link } from "react-router-dom";
import HomeIcon from "@mui/icons-material/Home";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import FavoriteIcon from "@mui/icons-material/Favorite";
import PersonAddIcon from "@mui/icons-material/PersonAdd";
import LoginIcon from "@mui/icons-material/Login";
import PaymentIcon from "@mui/icons-material/Payment";

const Header = () => {
    return (
        <AppBar
            position="sticky"
            sx={{
                background: "linear-gradient(45deg, #4caf50 30%, #81c784 90%)",
                boxShadow: 4,
            }}
        >
            <Toolbar sx={{ display: "flex", justifyContent: "space-between", alignItems: "center", padding: "0 1rem" }}>
                {/* Các nút điều hướng bên trái */}
                <div>
                    <Button
                        color="inherit"
                        component={Link}
                        to="/"
                        startIcon={<HomeIcon />}
                        sx={{
                            marginLeft: 2,
                            padding: "8px 16px",
                            borderRadius: "30px",
                            transition: "0.3s",
                            "&:hover": {
                                backgroundColor: "rgba(255, 255, 255, 0.2)",
                                transform: "scale(1.05)",
                            },
                        }}
                    >
                        Home
                    </Button>
                    <Button
                        color="inherit"
                        component={Link}
                        to="/checkout"
                        startIcon={<PaymentIcon />}
                        sx={{
                            marginLeft: 2,
                            padding: "8px 16px",
                            borderRadius: "30px",
                            transition: "0.3s",
                            "&:hover": {
                                backgroundColor: "rgba(255, 255, 255, 0.2)",
                                transform: "scale(1.05)",
                            },
                        }}
                    >
                        Thanh Toán
                    </Button>
                    <Button
                        color="inherit"
                        component={Link}
                        to="/cart"
                        startIcon={<ShoppingCartIcon />}
                        sx={{
                            marginLeft: 2,
                            padding: "8px 16px",
                            borderRadius: "30px",
                            transition: "0.3s",
                            "&:hover": {
                                backgroundColor: "rgba(255, 255, 255, 0.2)",
                                transform: "scale(1.05)",
                            },
                        }}
                    >
                        Giỏ Hàng
                    </Button>
                </div>

                {/* Tiêu đề ở giữa */}
                <Typography
                    variant="h5"
                    sx={{
                        textAlign: "center",
                        fontWeight: "bold",
                        fontSize: "1.8rem",
                        color: "#fff",
                        textShadow: "1px 1px 3px rgba(0, 0, 0, 0.3)",
                    }}
                >
                    Book Store
                </Typography>

                {/* Các nút điều hướng bên phải */}
                <div>
                    <Button
                        color="inherit"
                        component={Link}
                        to="/favorites"
                        startIcon={<FavoriteIcon />}
                        sx={{
                            marginLeft: 2,
                            padding: "8px 16px",
                            borderRadius: "30px",
                            transition: "0.3s",
                            "&:hover": {
                                backgroundColor: "rgba(255, 255, 255, 0.2)",
                                transform: "scale(1.05)",
                            },
                        }}
                    >
                        Sách Yêu Thích
                    </Button>
                    <Button
                        color="inherit"
                        component={Link}
                        to="/register"
                        startIcon={<PersonAddIcon />}
                        sx={{
                            marginLeft: 2,
                            padding: "8px 16px",
                            borderRadius: "30px",
                            transition: "0.3s",
                            "&:hover": {
                                backgroundColor: "rgba(255, 255, 255, 0.2)",
                                transform: "scale(1.05)",
                            },
                        }}
                    >
                        Đăng Ký
                    </Button>
                    <Button
                        color="inherit"
                        component={Link}
                        to="/login"
                        startIcon={<LoginIcon />}
                        sx={{
                            marginLeft: 2,
                            padding: "8px 16px",
                            borderRadius: "30px",
                            transition: "0.3s",
                            "&:hover": {
                                backgroundColor: "rgba(255, 255, 255, 0.2)",
                                transform: "scale(1.05)",
                            },
                        }}
                    >
                        Đăng Nhập
                    </Button>
                </div>
            </Toolbar>
        </AppBar>
    );
};

export default Header;
