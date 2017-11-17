package services.encryption.impl;

import play.Logger;
import services.encryption.Encryptor;
import services.exception.EncryptorException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encryptor implements Encryptor {
    @Override
    public String encrypt(String source) throws EncryptorException {
        byte[] sourceStringBytes = null, resultStringBytes = null;
        try {
            sourceStringBytes = source.getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("MD5");
            resultStringBytes = md.digest(sourceStringBytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            Logger.error("No MD5 algorithm", e);
            throw new EncryptorException(e);
        }

        return new String(resultStringBytes);
    }
}
