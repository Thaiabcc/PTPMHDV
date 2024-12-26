import React, { useState } from 'react';
import axios from 'axios';
import { Container, TextField, Button, Typography, Box } from '@mui/material';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/nguoidung/login', { username, password });
            alert(response.data);
            // Xử lý lưu trữ token nếu cần
        } catch (error) {
            console.error(error);
            alert('Đăng nhập thất bại!');
        }
    };

    return (
        <Container maxWidth="xs">
            <Typography variant="h5" align="center" gutterBottom>
                Đăng Nhập
            </Typography>
            <Box component="form" onSubmit={handleLogin} sx={{ mt: 1 }}>
                <TextField
                    margin="normal"
                    required
                    fullWidth
                    label="Username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <TextField
                    margin="normal"
                    required
                    fullWidth
                    label="Password"
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <Button
                    type="submit"
                    fullWidth
                    variant="contained"
                    color="primary"
                    sx={{ mt: 3, mb: 2 }}
                >
                    Đăng Nhập
                </Button>
            </Box>
        </Container>
    );
};

export default Login;