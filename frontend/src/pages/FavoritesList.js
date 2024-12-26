import React from 'react';
import { useFavorites } from '../components/FavoritesContext';
import { Typography, Grid, Box } from '@mui/material';
import BookCard from './BookCard';

const FavoritesList = () => {
    const { favorites } = useFavorites();

    return (
        <div>
            <Typography variant="h4" align="center">Sách Yêu Thích</Typography>
            <Grid container spacing={4}>
                {favorites.length > 0 ? (
                    favorites.map(book => (
                        <Grid item xs={12} sm={6} md={4} key={book.maSach}>
                            <BookCard book={book} />
                        </Grid>
                    ))
                ) : (
                    <Grid item xs={15}>
                        <Box display="flex" justifyContent="center" alignItems="center" height="80vh">
                            <Typography variant="body1" align="center">
                                Chưa có sách nào trong danh sách yêu thích.
                            </Typography>
                        </Box>
                    </Grid>
                )}
            </Grid>
        </div>
    );
};

export default FavoritesList;