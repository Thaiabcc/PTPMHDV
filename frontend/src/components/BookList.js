import React, { useEffect, useState } from "react";
import { Grid, Container, Typography, TextField, Button, Pagination, AppBar, Toolbar, Box } from "@mui/material";
import axios from "axios";
import BookCard from "../pages/BookCard";

const BookList = () => {
    const [bestsellers, setBestsellers] = useState([]);
    const [highStockBooks, setHighStockBooks] = useState([]);
    const [filteredBooks, setFilteredBooks] = useState([]);
    const [searchTerm, setSearchTerm] = useState("");
    const [page, setPage] = useState(1);
    const [rowsPerPage] = useState(3);
    const [error, setError] = useState(null);
    const [isSearching, setIsSearching] = useState(false);

    // Trạng thái cho phân trang
    const [highStockPage, setHighStockPage] = useState(1);
    const [bestsellerPage, setBestsellerPage] = useState(1);

    useEffect(() => {
        const fetchBooks = async () => {
            try {
                const bestsellerResponse = await axios.get("http://localhost:8080/api/bestsellers");
                const highStockResponse = await axios.get("http://localhost:8080/api/high-stock");

                setBestsellers(bestsellerResponse.data.items || []);
                setHighStockBooks(highStockResponse.data.items || []);
            } catch (error) {
                console.error(error);
                setError("Có lỗi xảy ra khi tải dữ liệu.");
            }
        };

        fetchBooks();
    }, []);

    const handleSearch = async () => {
        if (searchTerm) {
            try {
                const response = await axios.get(`http://localhost:8080/api/search?tenSach=${encodeURIComponent(searchTerm)}`);
                setFilteredBooks(response.data || []);
                setIsSearching(true);
            } catch (error) {
                console.error(error);
                setError("Có lỗi xảy ra khi tìm kiếm.");
            }
        } else {
            setFilteredBooks([]);
            setIsSearching(false);
        }
        setPage(1);
    };

    const handleChangePage = (event, value) => {
        setPage(value);
    };

    const handleHighStockChangePage = (event, value) => {
        setHighStockPage(value);
    };

    const handleBestsellerChangePage = (event, value) => {
        setBestsellerPage(value);
    };

    // Tính toán chỉ số sách hiển thị
    const startIndexFiltered = (page - 1) * rowsPerPage;
    const endIndexFiltered = startIndexFiltered + rowsPerPage;

    const startIndexHighStock = (highStockPage - 1) * rowsPerPage;
    const endIndexHighStock = startIndexHighStock + rowsPerPage;

    const startIndexBestseller = (bestsellerPage - 1) * rowsPerPage;
    const endIndexBestseller = startIndexBestseller + rowsPerPage;

    return (
        <Container sx={{ marginTop: 4 }}>
            <Box sx={{ display: "flex", justifyContent: "center", margin: "20px 0" }}>
                <TextField
                    label="Tìm kiếm sách"
                    variant="outlined"
                    value={searchTerm}
                    onChange={(event) => setSearchTerm(event.target.value)}
                    sx={{ width: "400px", marginRight: 2 }}
                />
                <Button variant="contained" onClick={handleSearch}>
                    Tìm kiếm
                </Button>
            </Box>

            {error && (
                <Typography variant="body1" color="error" align="center" sx={{ marginBottom: 2 }}>
                    {error}
                </Typography>
            )}

            {isSearching ? (
                <>
                    <AppBar position="static" sx={{ marginTop: 4 }}>
                        <Toolbar>
                            <Typography variant="h6" sx={{ flexGrow: 1 }} textAlign={"center"}>
                                Kết quả tìm kiếm
                            </Typography>
                        </Toolbar>
                    </AppBar>
                    <Grid container spacing={4} sx={{ marginTop: 2 }}>
                        {filteredBooks.length > 0 ? (
                            filteredBooks.slice(startIndexFiltered, endIndexFiltered).map((book) => (
                                <Grid item xs={12} sm={6} md={4} key={book.maSach}>
                                    <BookCard book={book} />
                                </Grid>
                            ))
                        ) : (
                            <Typography variant="body1" align="center" sx={{ marginTop: 2 }}>
                                Không tìm thấy sách nào phù hợp.
                            </Typography>
                        )}
                    </Grid>
                    <Pagination
                        count={Math.ceil(filteredBooks.length / rowsPerPage)}
                        page={page}
                        onChange={handleChangePage}
                        sx={{ display: "flex", justifyContent: "center", marginTop: 4 }}
                    />
                </>
            ) : (
                <>
                    <AppBar position="static">
                        <Toolbar>
                            <Typography variant="h6" sx={{ flexGrow: 1 }} textAlign={"center"}>
                                Sách Có Nhiều Hàng
                            </Typography>
                        </Toolbar>
                    </AppBar>
                    <Grid container spacing={4}>
                        {highStockBooks.slice(startIndexHighStock, endIndexHighStock).map((book) => (
                            <Grid item xs={12} sm={6} md={4} key={book.maSach}>
                                <BookCard book={book} />
                            </Grid>
                        ))}
                    </Grid>
                    <Pagination
                        count={Math.ceil(highStockBooks.length / rowsPerPage)}
                        page={highStockPage}
                        onChange={handleHighStockChangePage}
                        sx={{ display: "flex", justifyContent: "center", marginTop: 4 }}
                    />

                    <AppBar position="static" sx={{ marginTop: 4 }}>
                        <Toolbar>
                            <Typography variant="h6" sx={{ flexGrow: 1 }} textAlign={"center"}>
                                Sách Bestseller
                            </Typography>
                        </Toolbar>
                    </AppBar>
                    <Grid container spacing={4}>
                        {bestsellers.slice(startIndexBestseller, endIndexBestseller).map((book) => (
                            <Grid item xs={12} sm={6} md={4} key={book.maSach}>
                                <BookCard book={book} />
                            </Grid>
                        ))}
                    </Grid>
                    <Pagination
                        count={Math.ceil(bestsellers.length / rowsPerPage)}
                        page={bestsellerPage}
                        onChange={handleBestsellerChangePage}
                        sx={{ display: "flex", justifyContent: "center", marginTop: 4 }}
                    />
                </>
            )}
        </Container>
    );
};

export default BookList;