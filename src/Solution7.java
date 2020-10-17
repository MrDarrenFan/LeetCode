/*
7. 整数反转
给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

示例 1:

输入: 123
输出: 321
 示例 2:

输入: -123
输出: -321
示例 3:

输入: 120
输出: 21
注意:

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */

public class Solution7 {

    /*
    通过循环将数字x的每一位拆开，在计算新值时每一步都判断是否溢出。
        溢出条件有两个，一个是大于整数最大值MAX_VALUE，另一个是小于整数最小值MIN_VALUE，设当前计算结果为 res，下一位为 unit。
        从 res * 10 + unit > MAX_VALUE 这个溢出条件来看
            当出现 res > MAX_VALUE / 10 时，则 res * 10 + unit 一定溢出
            当出现 res == MAX_VALUE / 10 且 unit > 7 时，则 res * 10 + unit 一定溢出（7是2^31 - 1的个位数）
        从 res * 10 + unit < MIN_VALUE 这个溢出条件来看
            当出现 res < MIN_VALUE / 10 时，则 res * 10 + unit 一定溢出
            当出现 res == MIN_VALUE / 10 且 unit < -8 时，则 res * 10 + unit 一定溢出（8是-2^31的个位数）
     */
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int unit = x % 10;
            x = x / 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE && unit > 7)) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE && unit < -8)) {
                return 0;
            }
            res = res * 10 + unit;
        }
        return res;
    }

}
