package hs.util;

import java.io.UnsupportedEncodingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;
//import org.apache.commons.;



public final class HsEncrypter {

    private static HsEncrypter instance;

    private HsEncrypter() {
    }


    public synchronized String encrypt(String plaintext) throws NoSuchAlgorithmException,
                                                                UnsupportedEncodingException {
        MessageDigest md = null;
        md = MessageDigest.getInstance("SHA");
        md.update(plaintext.getBytes("UTF-8"));

        byte raw[] = md.digest();

        String hash = (new BASE64Encoder()).encode(raw);
        //String hash = new Base64().encodeToString(raw);
        return hash;
    }


    public static synchronized HsEncrypter getInstance() {
        if (instance == null) {
            instance = new HsEncrypter();
        }
        return instance;
    }

}
