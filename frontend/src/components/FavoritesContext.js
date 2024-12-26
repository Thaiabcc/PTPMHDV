import React, { createContext, useContext, useState } from 'react';

// Tạo context
const FavoritesContext = createContext();

// Provider cho sách yêu thích
export const FavoritesProvider = ({ children }) => {
    const [favorites, setFavorites] = useState(() => {
        const savedFavorites = localStorage.getItem('favorites');
        return savedFavorites ? JSON.parse(savedFavorites) : [];
    });

    const addToFavorites = (book) => {
        setFavorites((prevFavorites) => {
            if (!prevFavorites.find(item => item.maSach === book.maSach)) {
                return [...prevFavorites, book];
            }
            return prevFavorites;
        });
    };

    const removeFromFavorites = (maSach) => {
        setFavorites(prevFavorites => prevFavorites.filter(item => item.maSach !== maSach));
    };

    return (
        <FavoritesContext.Provider value={{ favorites, addToFavorites, removeFromFavorites }}>
            {children}
        </FavoritesContext.Provider>
    );
};

// Hook để sử dụng context
export const useFavorites = () => {
    return useContext(FavoritesContext);
};