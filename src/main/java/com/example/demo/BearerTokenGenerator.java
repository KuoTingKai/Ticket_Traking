package com.example.demo;

import com.example.demo.model.Staff;
import com.example.demo.model.Token;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

public class BearerTokenGenerator {
    // 設定Token的有效時間為1小時
    private static final long TOKEN_VALIDITY = 3600000;

    // 設定隨機字元的位數
    private static final int RANDOM_BYTE_LENGTH = 32;

    // 用於產生隨機字元的SecureRandom物件
    private SecureRandom secureRandom = new SecureRandom();

    /**
     * 產生一個自定義的Bearer token
     * @return Bearer token字串
     */
    public String generateToken(Staff.Type type) {
        // 產生隨機字元
        String randomString = String.valueOf(type);
        byte[] randomStringtoByte = randomString.getBytes(StandardCharsets.UTF_8);
//        byte[] randomBytes = new byte[RANDOM_BYTE_LENGTH];
        secureRandom.nextBytes(randomStringtoByte);

        // 轉換隨機字元為Base64編碼字串
        String tokenValue = Base64.getUrlEncoder().withoutPadding().encodeToString(randomStringtoByte);

        // 設定Token的過期時間
        Date expirationDate = new Date(System.currentTimeMillis() + TOKEN_VALIDITY);

        // 創造Token對象
        Token token = new Token(tokenValue,expirationDate);

        // 返回Token字串
        return token.toString();
    }

    public boolean vaildToken(String tokenString){
        Token token = parseToken(tokenString);
        if (token.getExpirationDate().before(new Date())){
            return false;
        }
        return true;
    }

    public Token parseToken(String tokenString) {
        // 切割Token字串，去掉"Bearer "前綴
        String[] parts = tokenString.split(" ");
        String tokenValue = parts[1];

        // 解碼Token值，得到原始的隨機字元
        byte[] randomBytes = Base64.getUrlDecoder().decode(tokenValue);

        // 解析Token過期時間
        Date expirationDate = new Date(Long.parseLong(parts[2]));

        // 創造Token對象
        Token token = new Token(tokenValue, expirationDate);

        // 返回Token對象
        return token;
    }
}
