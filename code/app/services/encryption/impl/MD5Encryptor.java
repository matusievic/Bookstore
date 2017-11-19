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
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            Logger.error("No MD5 algorithm", e);
            throw new EncryptorException(e);
        }
        return result;
    }
}
