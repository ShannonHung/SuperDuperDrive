package com.finalproject.SuperDuperDrive.FinalProject.service;

import com.finalproject.SuperDuperDrive.FinalProject.mapper.CredentialMapper;
import com.finalproject.SuperDuperDrive.FinalProject.model.Credential;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.*;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper,EncryptionService encryptionService){
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public boolean addCredential(Credential credential){
        Map<String, String> encrypt = Encrypt(credential.getPassword());
        credential.setPassword(encrypt.get("encryptPassword"));
        credential.setKey(encrypt.get("key"));
        int credentialId = credentialMapper.insert(credential);

        if(credentialId > 0){
            return true;
        }else{
            return false;
        }
    }
    public boolean deleteCredential(int credentialId){
        int deleteId = credentialMapper.delete(credentialId);
        System.out.println("deleteId : " + deleteId);
        if(deleteId > 0) return true;
        else{
            return false;
        }
    }

    public List<Credential> getCredentials(int userid){
        System.out.println("CredentialService was called "+credentialMapper.getCredentials(userid).size());
        for(Credential cre : credentialMapper.getCredentials(userid)){
            System.out.println(cre.getCredentialId());
            System.out.println(cre.getUrl());
        }
        return credentialMapper.getCredentials(userid);
    }

    public Map<String, String> Encrypt(String password){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        System.out.println("here is password and key : "+ password + "\n" + encodedKey);
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
        Map<String, String> encrypt = new HashMap<>();
        encrypt.put("key", encodedKey);
        encrypt.put("encryptPassword",encryptedPassword);
        return encrypt;
    }

    public Boolean updateCred(Credential credential){
        Map<String, String> encrypt = Encrypt(credential.getPassword());
        credential.setPassword(encrypt.get("encryptPassword"));
        credential.setKey(encrypt.get("key"));
        int UpdateResult = credentialMapper.update(credential);
        if(UpdateResult > 0){
            return true;
        }else{
            return false;
        }
    }
}
