//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.psbc;

import com.psbc.exception.SignException;
import com.psbc.exception.VerifyException;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Signature {
    private static final String MD5WITHRSA_ALGORITHM = "MD5withRSA";

    public Signature() {
    }

    private String byteToHex(byte[] byteArray) {
        if (byteArray == null) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();

            for(int i = 0; i < byteArray.length; ++i) {
                String hexString = Integer.toHexString(byteArray[i] & 255);
                if (hexString.length() != 2) {
                    sb.append("0");
                }

                sb.append(hexString);
            }

            return sb.toString();
        }
    }

    private byte[] hexToByte(String hexString) {
        if (hexString != null && hexString.trim().length() != 0) {
            int unit = hexString.length() / 2;
            byte[] byteArray = new byte[unit];

            for(int i = 0; i < hexString.length() / 2; ++i) {
                String hexChar = hexString.substring(i * 2, i * 2 + 2);
                byteArray[i] = (byte)Integer.parseInt(hexChar, 16);
            }

            return byteArray;
        } else {
            return new byte[0];
        }
    }

    public String sign(String plain, String algorithm, PrivateKey privateKey) throws SignException {


        try {
            java.security.Signature sign = java.security.Signature.getInstance(algorithm);
            sign.initSign(privateKey);
            sign.update(plain.getBytes());
            return this.byteToHex(sign.sign());
        } catch (Exception var5) {
            throw new SignException(var5);
        }
    }

    public String sign(String plain, PrivateKey privateKey) throws SignException {
        return this.sign(plain, MD5WITHRSA_ALGORITHM, privateKey);
    }

    public boolean verify(String plain, String signature, String algorithm, PublicKey publicKey) throws VerifyException {

        try {
            java.security.Signature sign = java.security.Signature.getInstance(algorithm);
            sign.initVerify(publicKey);
            sign.update(plain.getBytes());
            byte[] signatureByteArray = this.hexToByte(signature);
            return sign.verify(signatureByteArray);
        } catch (Exception var7) {
            throw new VerifyException(var7);
        }
    }

    public boolean verify(String plain, String signature, PublicKey publicKey) throws VerifyException {
        return this.verify(plain, signature, MD5WITHRSA_ALGORITHM, publicKey);
    }
}
