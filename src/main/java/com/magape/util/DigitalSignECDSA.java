package com.magape.util;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class DigitalSignECDSA {
 
    /** Execute digital signature [private key signature]
     * @param data Data to be encrypted
     * @param pKey  Private key
     * @return byte[] Encrypted data
     * @throws Exception
     */
    public static String digitalSign(byte[] data, String pKey) {
        try {
            PrivateKey privateKey = KeyFactory.getInstance("Ed25519")
                    .generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(pKey)));

            Signature signEncode = Signature.getInstance("Ed25519");
            signEncode.initSign(privateKey);
            signEncode.update(data);
            byte[] signedBytes = signEncode.sign();
            return Base64.encodeBase64String(signedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
 
    /** Verify signature [public key verification]
     * @param source Data to be decrypted
     * @param pubKey  Public key
     * @return byte[] Decrypting data
     * @throws Exception
     */
    public static boolean signVerify(String source, String rsaData, String pubKey) {
        try {
            byte[] publicKeyBytes = Base64.decodeBase64(pubKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("Ed25519");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            Signature signDecode = Signature.getInstance("Ed25519");
            signDecode.initVerify(publicKey);
            signDecode.update(source.getBytes(StandardCharsets.UTF_8));
            return signDecode.verify(Base64.decodeBase64(rsaData));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }


    public static void main(String[] args) {
        String publicKey = "MCowBQYDK2VwAyEAMVQdZxF0qAb0hfgdIZBk4XW7lo5WbKDa3LGJ1g1pSrg=";
        String privateKey = "MC4CAQAwBQYDK2VwBCIEIGnhmvBBpn7EhPcJV0tmRPtW/pkNiJsZLiKnShFLJOaA";
        String sign = digitalSign("hello world!".getBytes(), privateKey);
        boolean result = signVerify("hello world!", sign, publicKey);
        System.out.println("sign: " + sign);
        System.out.println("verify: " + result);
    }
}