// src/components/ImageSlider.js
import React from "react";
import Slider from "react-slick";
import { Box } from "@mui/material";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

const ImageSlider = () => {
    const settings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 2500,
    };

    const images = [
        "https://theme.hstatic.net/200000343865/1001052087/14/ms_banner_img2.jpg?v=1556",
        "https://theme.hstatic.net/200000343865/1001052087/14/ms_banner_img5.jpg?v=1556",
        "https://theme.hstatic.net/200000343865/1001052087/14/ms_banner_img1.jpg?v=1556",
        "https://theme.hstatic.net/200000343865/1001052087/14/ms_banner_img3.jpg?v=1556",
        "https://theme.hstatic.net/200000343865/1001052087/14/ms_banner_img4.jpg?v=1556",

    ];

    return (
        <Box sx={{ marginY: 0, paddingY: 2, width: '100%' }}>
            <Slider {...settings}>
                {images.map((image, index) => (
                    <div key={index}>
                        <img
                            src={image}
                            alt={`Slide ${index + 1}`}
                            style={{
                                width: '100%',
                                height: 'auto',
                                borderRadius: '8px', // Bo góc cho hình ảnh
                                boxShadow: '0 4px 10px rgba(0, 0, 0, 0.2)' // Thêm bóng cho hình ảnh
                            }}
                        />
                    </div>
                ))}
            </Slider>
        </Box>
    );
};

export default ImageSlider;