import React from 'react';

function Footer() {
    return (
        <footer style={{
            backgroundColor: '#282c34', // Màu nền tối
            color: 'white', // Màu chữ trắng
            textAlign: 'center',
            padding: '20px',
            marginTop: '20px',
            position: 'relative',
            boxShadow: '0 -2px 10px rgba(0, 0, 0, 0.5)', // Hiệu ứng bóng
            fontFamily: 'Arial, sans-serif'
        }}>
            <p style={{ margin: 0 }}>
                &copy; 2024 My Website. All rights reserved.
            </p>
            <div style={{ marginTop: '10px' }}>
                <a href="#!" style={{ color: 'lightblue', textDecoration: 'none', margin: '0 10px' }}>
                    Privacy Policy
                </a>
                <a href="#!" style={{ color: 'lightblue', textDecoration: 'none', margin: '0 10px' }}>
                    Terms of Service
                </a>
                <a href="#!" style={{ color: 'lightblue', textDecoration: 'none', margin: '0 10px' }}>
                    Contact Us
                </a>
            </div>
        </footer>
    );
}

export default Footer;