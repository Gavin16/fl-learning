package com.fl.demo.millionaire;

import com.fl.demo.utils.EncryptUtil;

public class RSATest {

    // 测试验证RSA对整数进行加解密
    public static void main(String[] args) throws Exception {

        int a = 5, b = 7;

        int[][] key = EncryptUtil.create_key();

        int[] pubKeys = key[0];
        int[] prvKeys = key[1];

        int encrypt = EncryptUtil.encrypt(a, pubKeys);
        System.out.println("RSA加密公钥:" + encrypt);

        int decrypt = EncryptUtil.decrypt(encrypt, prvKeys);

        System.out.println("RAS私钥解密:"+ decrypt);

    }
}
