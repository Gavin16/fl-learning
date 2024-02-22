package com.fl.demo.millionaire;

import com.fl.demo.utils.EncryptUtil;
import com.fl.demo.utils.NumberUtil;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
//        Alice的财富为i，Bob的财富为j，取值为0~10
//        Alice选择一个随机大整数x
//        Alice和Bob约定使用RSA算法
//        Bob用RSA算法生成公钥和私钥，将公钥发给Alice
//        Alice使用Bob的公钥加密x得C=E(x)，并发送C-i给Bob
//        Bob使用私钥计算Y(u) = D(C-i+u) (1<=u<=10)
//        Bob随机取一个小于x的大整数p，将Y(u) mod p得到Z(u)，验证对所有Z(u)都满足0<Z(u)<p-1。若不满足则更换p重新计算
//        再将Z(u)从第j-1位开始向右均+1得到K(u)，然后将K(u)和p发给Alice
//        Alice将K[i-1]与(x mod p)进行比较，如果相等，则说明i<j，即Alice不如Bob富有；若不相等，则说明i>=j，说明Alice比BOb富有或者和Bob一样富有
        Scanner input = new Scanner(System.in);
        System.out.print("Alice:");
        int i = input.nextInt();

        System.out.print("Bob:");
        int j = input.nextInt();
        int x;
        do {
            x = new Random().nextInt(90) + 10;
        } while (!NumberUtil.is_prime(x));

        System.out.println("随机整数x="+ x);
        int [] pbk, pvk;
        int [][] r = EncryptUtil.create_key();

        pbk = r[0];
        pvk = r[1];

        System.out.println("公钥(n,e)=("+ pbk[0] +","+ pbk[1] +")\n"+
                "私钥(n,d)=("+ pvk[0] +","+ pvk[1] +")");

        int C = EncryptUtil.encrypt(x, pbk);
        System.out.println("Alice发送C-i="+ (C - i) +"给Bob");

        int [] Y = new int[10];
        for(int u = 1; u<11;u++){
            Y[u - 1] = EncryptUtil.decrypt(C-i+u,pvk);
        }
        System.out.println("Y="+ NumberUtil.toS(Y));
        int p = new Random().nextInt(x - 11) + 10;
        int [] Z = new int[10];

        while (true) {
            for(int u = 0; u<10;u++){
                Z[u] = Y[u] % p;
            }
            if(NumberUtil.max(Z) < p -1 &&NumberUtil.min(Z)>0){
                break;
            }
            p = new Random().nextInt(x - 11) + 10;
        }
        System.out.println("p="+String.valueOf(p)+"\nZ="+NumberUtil.toS(Z).toString());
        for(int u=0;u<10;u++){
            if(u>=j-1){
                Z[u] = Z[u] + 1;
            }
        }
        System.out.println("k="+NumberUtil.toS(Z).toString());
        if(Z[i-1] == x % p){
            if (i<j){
                System.out.println("Bob更富有");
            }else {
                System.out.println("验证错误，i应该大于j，Alice可能更富有，也可能和Bob一样富有");
            }
        }else {
            if (i >= j){
                System.out.println("Alice可能更富有，也可能和Bob一样富有");
            }else {
                System.out.println("验证错误，j应该大于i，Bob更富有才对");
            }
        }
    }

}
