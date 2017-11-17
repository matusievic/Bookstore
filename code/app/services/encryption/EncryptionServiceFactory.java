package services.encryption;

import services.encryption.impl.MD5Encryptor;

public final class EncryptionServiceFactory {
    private static final EncryptionServiceFactory instance = new EncryptionServiceFactory();

    private Encryptor encryptor = new MD5Encryptor();

    private EncryptionServiceFactory() {}

    public static EncryptionServiceFactory getInstance() {
        return instance;
    }

    public Encryptor getEncryptor() {
        return encryptor;
    }
}
