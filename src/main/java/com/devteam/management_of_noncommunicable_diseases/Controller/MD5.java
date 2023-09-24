package com.devteam.management_of_noncommunicable_diseases.Controller;
//import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public String encode(String str){
        MessageDigest md;
        String result = "";
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            BigInteger bi = new BigInteger(1, md.digest());

            result = bi.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public static boolean verify(String inputPassword, String hashPassWord) throws NoSuchAlgorithmException {
//        String SELECT_QUERY = "SELECT password FROM accounts WHERE password = ?";
//
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        md.update(inputPassword.getBytes());
//        byte[] digest = md.digest();
//        String myChecksum = DatatypeConverter
//                .printHexBinary(digest).toUpperCase();
//        return hashPassWord.equals(myChecksum);
//    }
}
