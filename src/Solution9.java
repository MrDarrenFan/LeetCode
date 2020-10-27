/*
9. 回文数

判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

示例 1:
    输入: 121
    输出: true

示例 2:
    输入: -121
    输出: false
    解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。

示例 3:
    输入: 10
    输出: false
    解释: 从右向左读, 为 01 。因此它不是一个回文数。

进阶:
    你能不将整数转为字符串来解决这个问题吗？
 */

public class Solution9 {

    public static void main(String[] args) {
        Solution9 solution9 = new Solution9();
        System.out.println(solution9.isPalindrome(Integer.MAX_VALUE));
    }

    /*
    直观上来看待回文数的话，就感觉像是将数字进行对折后看能否一一对应。
    所以这个解法的操作就是 取出后半段数字进行翻转。
    这里需要注意的一个点就是由于回文数的位数可奇可偶，所以当它的长度是偶数时，它对折过来应该是相等的；
    当它的长度是奇数时，那么它对折过来后，有一个的长度需要去掉一位数（除以 10 并取整）。

    具体做法如下：
        每次进行取余操作（%10），取出最低的数字：y = x % 10
        将最低的数字加到取出数的末尾：revertedNum = revertedNum * 10 + y
        每取一个最低位数字，x 都要自除以 10
        判断 x 是不是小于 revertedNum ，当它小于的时候，说明数字已经对半或者过半了
        最后，判断奇偶数情况：
            如果是偶数的话，revertedNum 和 x 相等；
            如果是奇数的话，最中间的数字就在revertNum 的最低位上，将它除以 10 以后应该和 x 相等。
     */
    public boolean isPalindrome(int x) {
        // 根据题意，负数不为回文数；
        // 末尾为0时，首位必须也为零，这样的话只有数字0满足
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int revertedNum = 0;
        while (x > revertedNum) {
            revertedNum = revertedNum * 10 + x % 10;
            x = x / 10;
        }
        return x == revertedNum || x == revertedNum / 10;
    }

}
