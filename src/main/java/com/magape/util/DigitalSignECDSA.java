package com.magape.util;


import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;
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
    //公钥
    private static final String PUBLIC_KEY = "ECDSAPublicKey";
    //私钥
    private static final String PRIVATE_KEY = "ECDSAPrivateKey";
 
    /** Initialize key pairs
     * @return Map Key pair Map
     * @throws Exception exception
     */
    public static Map<String, Object> initKey() {
        try {
            // Instantiating key pair generator
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            // Initialize key pair generator with a key size of 256 bits
            keyPairGenerator.initialize(256, new SecureRandom());
            // Generate key pairs
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // Store key pairs in Map
            Map<String, Object> keyMap = new HashMap<String, Object>(2);
            keyMap.put(PUBLIC_KEY, (ECPublicKey) keyPair.getPublic());
            keyMap.put(PRIVATE_KEY, (ECPrivateKey) keyPair.getPrivate());
            return keyMap;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
 
    /** Execute digital signature [private key signature]
     * @param data Data to be encrypted
     * @param privKey  Private key
     * @return byte[] Encrypted data
     * @throws Exception
     */
    public static String digitalSign(byte[] data, String privKey) {
        try {
            PrivateKey privateKey = (PrivateKey) KeyFactory.getInstance("EC")
                    .generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(privKey)));
            Signature signature = Signature.getInstance("SHA256WithECDSA");
            signature.initSign(privateKey);
            signature.update(data);
            return Base64.encodeBase64String(signature.sign());
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
            PublicKey publicKey = (PublicKey) KeyFactory.getInstance("EC")
                    .generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(pubKey)));
            Signature signature = Signature.getInstance("SHA256WithECDSA");
            signature.initVerify(publicKey);
            signature.update(source.getBytes(Charset.defaultCharset()));
            return signature.verify(Base64.decodeBase64(rsaData));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
 
    /** Obtain private key
     * @param keyMap Key Map
     * @return byte[] private key
     * @throws Exception
     */
    public static byte[] getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }
 
    /** Obtain public key
     * @param keyMap Key Map
     * @return byte[] public key
     * @throws Exception
     */
    public static byte[] getPublicKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }


    public static void main(String[] args) {
        //公钥
        byte[] publicKey;
        //私钥
        byte[] privateKey;

        //初始化密钥
        //生成密钥对
        Map<String, Object> keyMap = DigitalSignECDSA.initKey();
        publicKey = DigitalSignECDSA.getPublicKey(keyMap);
        privateKey = DigitalSignECDSA.getPrivateKey(keyMap);
        System.out.println("ECDSAPublicKey:\n" + Base64.encodeBase64String(publicKey));
        System.out.println("ECDSAPrivateKey:\n" + Base64.encodeBase64String(privateKey));

        System.out.println();
        String msgA2B = "What can I do for you?";
        //Execute digital signature【Private key signature】
        String encodeMsgA2B = DigitalSignECDSA.digitalSign(msgA2B.getBytes(), Base64.encodeBase64String(privateKey));
        System.out.println("JDK ECDSA SIGNATURE：:\n" + encodeMsgA2B);
//        验证签名【公钥验证】
        boolean bool = DigitalSignECDSA.signVerify(msgA2B, encodeMsgA2B, Base64.encodeBase64String(publicKey));
//        boolean bool = DigitalSignECDSA.signVerify("hello world!!!".getBytes(), Base64.decodeBase64("MEQCIH+yDdVGy2H+FSwlSVsZjK2kiwjeBVlIHWagv1uvBHCIAiByPBppWt1aiuKwksiWzcmStA+EyXcFoiTlNJnEHAZYtA=="), Base64.decodeBase64("MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEHgRVIEh4L77hloN8ZpUbZujPET7eBFP/XFD+B9iEjs8xdhhJS79FroRXyOWIcJDwe5fSCjJXOqinbGwfSfN4XA=="));



        System.out.println("Is the digital signature valid？:\n" + bool);
    }
}