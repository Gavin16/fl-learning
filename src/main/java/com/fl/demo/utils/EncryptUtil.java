package com.fl.demo.utils;

import java.math.BigInteger;
import java.util.Random;

/**
 * RSA 加密工具类
 * (1) 生成密钥对
 * (2) 对整数进行加密
 * (3) 对加密结果进行解密
 */
public class EncryptUtil {

    // 创建RSA密钥对
    public static int [][] create_key() throws Exception {
        //创建公钥和私钥
        // N = p * q (两个质数相乘)
        int p, q, n, s;
        do {
            p = new Random().nextInt(90) + 10;
        } while (!NumberUtil.is_prime(p));
        do {
            q = new Random().nextInt(90) + 10;
        } while (!NumberUtil.is_prime(q) || q == p);

        n = p * q;
        // 欧拉函数 phi(n) = phi(p) * phi(q) = (p-1) * (q-1)
        s = (p - 1)*(q - 1);

        int d,e;
        System.out.println("n="+ n +",s="+ s);
        // 随机得到整数 e, 并找出模反元素 d
        while (true) {
            e = new Random().nextInt(s - 3) + 2;
            if (NumberUtil.gcd(e, s) == 1){
                d = NumberUtil.get_inverse(e,s)[0];
                if (d>0){
                    break;
                }
            }
        }
        // 返回 公钥(n,e) 和 私钥(n, d)
        return new int[][]{{n,e},{n,d}};
    }

    public static int encrypt(int content, int [] pbKey){
        BigInteger c = new BigInteger(String.valueOf(content));
        BigInteger p0 = new BigInteger(String.valueOf(pbKey[0]));
        return (c.pow(pbKey[1]).mod(p0)).intValue();
    }

    public static int decrypt(int encrypt_content, int [] pvKey){
        BigInteger c = new BigInteger(String.valueOf(encrypt_content));
        BigInteger p0 = new BigInteger(String.valueOf(pvKey[0]));
        return (c.pow(pvKey[1]).mod(p0)).intValue();
    }

}
