package com.health.util;

import com.health.common.Constant;
import com.health.entity.security.UserEntity;
import com.health.exception.RequestConflictedException;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.codec.Hex;

import javax.ws.rs.core.HttpHeaders;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author henry
 */
public class TokenUtils {

    public static String createToken(UserEntity user, Integer loginType, Long currentTime) {
        long expires = currentTime + 1000L * 60 * 60 * 8;
        StringBuffer tokenBuffer = new StringBuffer();
        if(loginType.equals(Constant.LOGIN_TYPE_CODE)){
            tokenBuffer.append(loginType).append(Constant.SPLIT);
            tokenBuffer.append(user.getMobile()).append(Constant.SPLIT);
            tokenBuffer.append(expires).append(Constant.SPLIT);
            tokenBuffer.append(user.getUserId()).append(Constant.SPLIT);
            tokenBuffer.append(TokenUtils.computeSignature(user.getMobile(), user.getvCode(), expires));
        } else if(loginType.equals(Constant.LOGIN_TYPE_PWD)){
            tokenBuffer.append(loginType).append(Constant.SPLIT);
            tokenBuffer.append(user.getAccount()).append(Constant.SPLIT);
            tokenBuffer.append(expires).append(Constant.SPLIT);
            tokenBuffer.append(user.getUserId()).append(Constant.SPLIT);
            tokenBuffer.append(TokenUtils.computeSignature(user.getMobile(), user.getPassword(), expires));
        }
        return tokenBuffer.toString();
    }

    public static String computeSignature(String mobile, String password, long expires){
        StringBuffer signatureBuffer = new StringBuffer();
        signatureBuffer.append(mobile).append(Constant.SPLIT);
        signatureBuffer.append(expires).append(Constant.SPLIT);
        signatureBuffer.append(password).append(Constant.SPLIT);
        signatureBuffer.append(Constant.MAGIC_KEY);

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }
        return new String(Hex.encode(digest.digest(signatureBuffer.toString().getBytes())));
    }

    public static String makePassword(String key, String slat){
        String password = key + slat;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] byteData = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            password = sb.toString();
        } catch (Exception e) {
            password = key;
        }

        return password;
    }

    public static String getAuthToken(HttpHeaders headers){
        String authToken = headers.getHeaderString(Constant.TOKEN_HEADER_KEY);
        if (StringUtils.isNotBlank(authToken)) {
            return authToken;
        }

        authToken = headers.getHeaderString("token");
        if(authToken != null && authToken.indexOf('%') > 0){
            try {
                authToken = URLDecoder.decode(authToken,"utf-8");
            } catch (UnsupportedEncodingException e) {
                authToken = authToken.replaceAll("%3A", ":");
            }
        }
        if(StringUtils.isBlank(authToken)){
            throw new RequestConflictedException("E0000001", "token");
        }
//        if (isExpireToken(authToken)) {
//            throw new RequestConflictedException("E2000001");
//        }
        return authToken;
    }

    public static Long getUserIdFromToken(String authToken){
        if (null == authToken) {
            return null;
        }

        String[] parts = authToken.split(Constant.SPLIT);
        String userIdStr = parts.length < 4 ? null : parts[3];
        
        return Long.parseLong(userIdStr);
    }
    
    public static String makeSlat(){
        return UUID.randomUUID().toString();
    }

    public static String getExpiresFromToken(String authToken) {
        if (null == authToken) {
            return null;
        }

        String[] parts = authToken.split(Constant.SPLIT);
        
        return parts.length < 3 ? null : parts[2];
    }

    public static boolean isExpireToken(String authToken){
        long expires = Long.parseLong(getExpiresFromToken(authToken));
        return expires < System.currentTimeMillis();
    }
    
}
