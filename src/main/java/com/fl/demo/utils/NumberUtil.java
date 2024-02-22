package com.fl.demo.utils;

public class NumberUtil {

    public static int[] get_inverse(int a, int b){
//        a*x = 1 mod b
//        b*y = 1 mod a
//        已知a、b，返回x, y
        if (b == 0) {
            return new int[]{1, 0};
        } else {
            int [] r = get_inverse(b, a % b);
            int x1, x2, y1, y2;
            x2 = r[0];
            y2 = r[1];
            x1 = y2;
            y1 = x2 - ((int)a / b) * y2;
            return new int[]{x1, y1};
        }
    }



    // 判断是否是素数
    public static boolean is_prime(int x) throws Exception {
        if (x <= 1) {
            throw new Exception("0和1既不是素数也不是合数，x应为大于1的正整数");
        }
        for(int i = 2; i < (int) (Math.sqrt(x) + 1); i++) {
            if(x % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 辗转相除法求最大公约数
    public static int gcd(int a,int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    // 求最大值
    public static int max(int []d){
        int m = d[0];
        for (int i = 1; i<d.length;i++){
            if(m<d[i]){
                m = d[i];
            }
        }
        return m;
    }

    public static int min(int []d){
        int m = d[0];
        for (int i = 1; i<d.length;i++){
            if(m>d[i]){
                m = d[i];
            }
        }
        return m;
    }


    public static StringBuffer toS(int []d){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[ ");
        for (int j : d) {
            stringBuffer.append(j);
            stringBuffer.append(" ");
        }
        stringBuffer.append(" ]");
        return stringBuffer;
    }

}
