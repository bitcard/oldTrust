package com.wallet.crypto.trustapp.util;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.security.keystore.UserNotAuthenticatedException;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import trust.blockchain.entity.ServiceErrorException;

public class KS {
    public static void delete(File file, String str) {
        str = str.toLowerCase();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("iv");
        removeAliasAndFiles(file, str, str, stringBuilder.toString());
    }

    public static byte[] get(File file, String str) throws ServiceErrorException {
        String toLowerCase = str.toLowerCase();
        String toLowerCase2 = str.toLowerCase();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str.toLowerCase());
        stringBuilder.append("iv");
        return getData(file, toLowerCase, toLowerCase2, stringBuilder.toString());
    }

    private static synchronized byte[] getData(File r6, String r7, String r8, String r9) throws ServiceErrorException {
        synchronized (KS.class) {
            try {
                String path = getFilePath(r6, r8);
                try {
                    KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                    keyStore.load(null);
                    SecretKey r2 = (SecretKey)keyStore.getKey(r7, null);
                    if (r2 == null) {
                        if (!new File(path).exists())
                            return null;
                        throw new ServiceErrorException(1005, "file is present but the key is gone: " + r7);
                    }
                    Boolean r3 = new File(getFilePath(r6, r9)).exists();
                    Boolean r4 = new File(getFilePath(r6, r8)).exists();
                    if (r3 && r4) {
                        byte[] data = readBytesFromFile(getFilePath(r6, r9));
                        if (data != null && data.length != 0) {
                            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                            cipher.init(2, r2, new IvParameterSpec(data));
                            return readBytesFromStream(new CipherInputStream(new FileInputStream(path), cipher));
                        }
                        throw new NullPointerException("iv is missing for " + r7);
                    }
                    removeAliasAndFiles(r6, r7, r8, r9);
                    if (r3 != r4) {
                        throw new ServiceErrorException(1006, "file is present but the key is gone: " + r7);
                    }
                    throw new ServiceErrorException(1006, "!ivExists && !aliasExists: " + r7);
                } catch (InvalidKeyException e) {
                    throw new ServiceErrorException(1001);
                } catch (Exception e) {
                    if (e instanceof UserNotAuthenticatedException) {
                        throw new ServiceErrorException(1004);
                    }
                    throw new ServiceErrorException(1007);
                }
            } catch (ServiceErrorException e) {
                throw e;
            }
        }
    }

    private static synchronized String getFilePath(File file, String str) {
        String absolutePath;
        synchronized (KS.class) {
            absolutePath = new File(file, str).getAbsolutePath();
        }
        return absolutePath;
    }

    public static void put(File file, String str, String str2) throws ServiceErrorException {
        byte[] bytes = str2.getBytes();
        String toLowerCase = str.toLowerCase();
        String toLowerCase2 = str.toLowerCase();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str.toLowerCase());
        stringBuilder.append("iv");
        setData(file, bytes, toLowerCase, toLowerCase2, stringBuilder.toString());
    }

    private static byte[] readBytesFromFile(String str) {
        try {
            return readBytesFromStream(new FileInputStream(new File(str)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] readBytesFromStream(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        try {
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1)
                    break;
                byteArrayOutputStream.write(bArr, 0, read);
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e1){
            e1.printStackTrace();
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return byteArrayOutputStream.toByteArray();
    }

    private static synchronized void removeAliasAndFiles(File file, String str, String str2, String str3) {
        synchronized (KS.class) {
            try {
                KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
                instance.load(null);
                instance.deleteEntry(str);
                new File(getFilePath(file, str2)).delete();
                new File(getFilePath(file, str3)).delete();
            } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static synchronized boolean setData(File r7, byte[] r8, String r9, String r10, String r11) throws ServiceErrorException {
        synchronized (KS.class) {
            try {
                if (r8 == null) {
                    throw new ServiceErrorException(1, "keystore insert data is null");
                }
                String path = getFilePath(r7, r10);
                String path2 = getFilePath(r7, r11);    // r7
                try {
                    KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");    // r2
                    keyStore.load(null);
                    if (keyStore.containsAlias(r9) == false) {
                        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore"); // r4
                        keyGenerator.init(new KeyGenParameterSpec.Builder(r9, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                                .setKeySize(256)
                                .setUserAuthenticationRequired(false)
                                .setRandomizedEncryptionRequired(true)
                                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                                .build());
                        keyGenerator.generateKey();
                    }

                    SecretKey r4 = (SecretKey) keyStore.getKey(r9, null);
                    if (r4 != null) {
                        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                        cipher.init(1, r4);
                        if (writeBytesToFile(path2, cipher.getIV())) {
                            try {
                                CipherOutputStream cipherOutputStream = new CipherOutputStream(new FileOutputStream(path), cipher);
                                try {
                                    cipherOutputStream.write(r8);
                                } catch (Throwable throwable) {
                                    throw throwable;
                                } finally {
                                    cipherOutputStream.close();
                                }
                            } catch (Exception e1) {
                                throw new ServiceErrorException(1001, "Failed to saveTokens the file for: " + r9);
                            }
                            return true;
                        } else {
                            keyStore.deleteEntry(r9);
                            throw new ServiceErrorException(1002, "Failed to saveTokens the iv file for: " + r9);
                        }
                    } else {
                        throw new ServiceErrorException(1003, "secret is null on setData: " + r9);

                    }
                } catch (UserNotAuthenticatedException e1) {
                    throw new ServiceErrorException(1004);
                } catch (ServiceErrorException e2) {
                    Log.d("KS", "Key store error", e2);
                    throw e2;
                } catch (Exception e3) {
                    Log.d("KS", "Key store error", e3);
                    throw new ServiceErrorException(1001);
                }
            } catch (Exception e) {
                throw e;
            }
        }
    }

    private static boolean writeBytesToFile(String r3, byte[] r4) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(r3));
            fileOutputStream.write(r4);

            try {
                fileOutputStream.close();
            } catch (IOException e) {
                System.out.println("Error while closing stream: " + e);
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        } catch (IOException e) {
            System.out.println("Exception while writing file " + e);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    System.out.println("Error while closing stream: " + e);
                }
            }
        }
        return false;
    }
}
