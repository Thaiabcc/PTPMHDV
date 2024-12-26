package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/thanhtoan")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Value("${vnp.tmnCode}")
    private String tmnCode;

    @Value("${vnp.hashSecret}")
    private String hashSecret;

    @Value("${vnp.returnUrl}")
    private String returnUrl;

    @PostMapping("/payment")
    public Map<String, String> createPayment(@RequestBody Map<String, Object> request, HttpServletRequest req) {
        String txnRef = String.valueOf(System.currentTimeMillis());
        String ipAddress = getIpAddress(req);
        
        long amount = parseCartTotal(request.get("cartTotal"));

        Map<String, String> vnpParams = buildVnpParams(txnRef, ipAddress, amount);
        
        // Create and log the hash data
        String hashData = buildHashData(vnpParams);
        logger.info("Hash Data: {}", hashData);

        // Generate HMAC SHA512 secure hash (updated from SHA256 to SHA512)
        String secureHash = hmacSHA512(hashData, hashSecret);
        vnpParams.put("vnp_SecureHash", secureHash);
        logger.info("Secure Hash: {}", secureHash);

        // Create payment URL
        String paymentUrl = buildPaymentUrl(vnpParams);
        logger.info("Payment URL: {}", paymentUrl);

        Map<String, String> response = new HashMap<>();
        response.put("url", paymentUrl);
        return response;
    }

    private String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    private long parseCartTotal(Object cartTotalObj) {
        if (cartTotalObj instanceof String) {
            return convertToCents((String) cartTotalObj);
        } else if (cartTotalObj instanceof Number) {
            return ((Number) cartTotalObj).longValue() * 100;
        } else {
            throw new IllegalArgumentException("Giá trị cartTotal không hợp lệ.");
        }
    }

    private long convertToCents(String amountStr) {
        String cleanedAmount = amountStr.replace("₫", "").replace(",", "").trim();
        return Long.parseLong(cleanedAmount) * 100;
    }

    private Map<String, String> buildVnpParams(String txnRef, String ipAddress, long amount) {
        Map<String, String> vnpParams = new HashMap<>();
        vnpParams.put("vnp_Version", "2.1.0");
        vnpParams.put("vnp_Command", "pay");
        vnpParams.put("vnp_TmnCode", tmnCode);
        vnpParams.put("vnp_Amount", String.valueOf(amount));
        vnpParams.put("vnp_CurrCode", "VND");
        vnpParams.put("vnp_OrderInfo", "Thanh toán đơn hàng");
        vnpParams.put("vnp_ReturnUrl", returnUrl);
        vnpParams.put("vnp_TxnRef", txnRef);
        vnpParams.put("vnp_IpAddr", ipAddress);

        String createDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        vnpParams.put("vnp_CreateDate", createDate);

        String expireDate = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date(System.currentTimeMillis() + 30 * 60 * 1000));
        vnpParams.put("vnp_ExpireDate", expireDate);

        vnpParams.put("vnp_Locale", "vn");
        vnpParams.put("vnp_OrderType", "billpayment");

        return vnpParams;
    }

    private String buildHashData(Map<String, String> params) {
        return params.entrySet().stream()
                .filter(entry -> entry.getValue() != null && !entry.getValue().isEmpty() && !entry.getKey().equals("vnp_SecureHash"))
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
    }

    private String hmacSHA512(String data, String key) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            Mac mac = Mac.getInstance("HmacSHA512");  // Updated to HMAC-SHA512
            mac.init(secretKeySpec);
            byte[] hmacData = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hmacData) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tạo chữ ký HMAC SHA512", e);
        }
    }

    private String buildPaymentUrl(Map<String, String> params) {
        return "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?" + params.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
    }

    @GetMapping("/return")
    public String returnPage(@RequestParam Map<String, String> allRequestParams) {
        // Xử lý thông tin trả về từ VNPAY
        return "redirect:/success";
    }
}
