package com.cc.algorithm.backtracking;

/*
 * author:  RainPoetry
 * date:  2019/9/9
 * description:
 *
 *      回溯算法 - 累加器
 *
 *          一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 *
 *
 *      例子：
 *          输入: "112358"
 *          输出: true
 *          解释: 累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 *
 */

public class AdditiveNumber {

    int n;
    String s;

    public boolean isAdditiveNumber(String num) {
        if (num.length() < 3)
            return false;
        // 第一个元素的的位置, 第一个元素不会占据整个 字符的一半
        first:
        for (int i = 0, j = num.length() >> 1; i < j; i++) {
            String first = num.substring(0, i + 1);
//            System.out.println("first: " + first);
            if (unValid(first))
                return false;
            // 第二个 元素 最多占据整个字符的一半
            second:
            for (int m = i + 1, n = ((num.length() - i - 1) >> 1) + m; m < n; m++) {
                String second = num.substring(i + 1, m + 1);
//                System.out.println("second:  " + second);
                if (unValid(second))
                    break second;
                if (verify(first, second, num.substring(m + 1))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean unValid(String element) {
        return element.charAt(0) == '0' && element.length() > 1;
    }


    public static void main(String[] args) {
        AdditiveNumber number = new AdditiveNumber();
        // 199100199
        // 112358
        //
        System.out.println(number.isAdditiveNumber("198019823962"));
        // 199100199
        // "198019823962"
//        System.out.println(number.verify("1", "99","100199"));
//        System.out.println(number.sum("1", "99"));

    }


    // 当前面两个数已经判定
    public boolean verify(String prev, String current, String other) {
        String sum = sum(prev, current);
        if (other.length() < sum.length())
            return false;
        if (other.equalsIgnoreCase(sum))
            return true;
        if (other.substring(0, sum.length()).equalsIgnoreCase(sum))
            return verify(current, sum, other.substring(sum.length()));
        else
            return false;
    }

    // 字符串计算两数之和，解决整数溢出的情况
    public String sum(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        // 表示进位
        int carry = 0;
        int zero = '0';
        int s1Length = s1.length();
        int s2Length = s2.length();
        int max = Math.max(s1.length(), s2.length());
        for (int i = 0; i < max; i++) {
            int n = carry;
            n += i < s1.length() ? s1.charAt(s1Length - i - 1) - zero : 0;
            n += i < s2.length() ? s2.charAt(s2Length - i - 1) - zero : 0;
            carry = n / 10;
            n = n % 10;
            sb.insert(0, n);
        }
        if (carry == 1)
            sb.insert(0, 1);
        return sb.toString();
    }

}
