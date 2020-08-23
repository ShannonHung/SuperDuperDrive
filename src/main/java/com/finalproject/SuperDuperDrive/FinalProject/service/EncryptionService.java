package com.finalproject.SuperDuperDrive.FinalProject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class EncryptionService {
    private Logger logger = LoggerFactory.getLogger(EncryptionService.class);

    public String encryptValue(String data, String key){
        byte[] encryptedValue = null;

        try{
            System.out.println("test1");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            System.out.println("test2");
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
            System.out.println("test3");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            System.out.println("test4");
            encryptedValue = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            System.out.println("test2");
        } catch (NoSuchPaddingException | InvalidKeyException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException e) {
            logger.error("我在EncryptionService.java" + e.getMessage());
        }
        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    public String decryptValue(String data, String key){
        byte[] decryptedValue = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptedValue = cipher.doFinal(Base64.getDecoder().decode(data));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            logger.error("這裡是Decrypt 發生錯誤喔" + e.getMessage());
        }
        return new String(decryptedValue);
    }
}
