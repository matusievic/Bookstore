package services.encryption;

import services.exception.EncryptorException;

public interface Encryptor {
    String encrypt(String source) throws EncryptorException;
}
