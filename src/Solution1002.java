/*
1002. 查找常用字符

给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。

你可以按任意顺序返回答案。

示例 1：
    输入：["bella","label","roller"]
    输出：["e","l","l"]
示例 2：
    输入：["cool","lock","cook"]
    输出：["c","o"]
提示：
    1 <= A.length <= 100
    1 <= A[i].length <= 100
    A[i][j] 是小写字母
 */

import java.util.*;

public class Solution1002 {

    public static void main(String[] args) {
        Solution1002 solution1002 = new Solution1002();
        System.out.println(solution1002.commonChars(new String[]{"bella","label","roller"}));
    }

    public List<String> commonChars(String[] A) {
        List<String> resultList = new ArrayList<>();

        if (A == null || A.length == 0) {   // 异常值
            return resultList;
        }

        int[] countOfCommonChars = new int[26];   // 记录每个字符串都显示的字符（注释中记位结果数组）
        int[] currentCount = new int[26];     // 记录每个字符串中出现的字符，出现次数
        for (int i = 0; i < A.length; i++) {
            Arrays.fill(currentCount, 0);   // 对每个字符串中字符记录前，清零
            String str = A[i];  // 当前字符串
            if (i == 0) {   // 第一次记录（记录的是第一个字符串中的字符）
                for (int j = 0; j < str.length(); j++) {
                    countOfCommonChars[str.charAt(j) - 'a']++;
                }
            } else {    // 遍历非第一个字符串时
                // 记录当前字符串每个字符出现次数
                for (int j = 0; j < str.length(); j++) {
                    currentCount[str.charAt(j) - 'a']++;
                }
                // 将 每个字符串中出现的字符与次数 与 结果数组 进行比较
                for (int j = 0; j < countOfCommonChars.length; j++) {
                    if (countOfCommonChars[j] > 0) {    // 只比较结果数组中已有的字符
                        if (currentCount[j] > 0) {  // 若该字符也在当前字符串中出现
                            if (countOfCommonChars[j] > currentCount[j]) {  // 若该字符 在结果数组中的次数 大于 当前字符串中出现的次数(记为N)
                                countOfCommonChars[j] = currentCount[j];    // 则结果数组中该字符最多出现N次
                            }
                        } else {    // 若该字符不在当前字符串中出现，则从结果数组中删去（即=0）
                            countOfCommonChars[j] = 0;
                        }
                    }
                }
            }
        }

        // 最后的结果数组即为 每个字符串中都出现的字符，放入结果list中返回
        List<String> result = new ArrayList<>();
        for (int i = 0; i < countOfCommonChars.length; i++) {
            if (countOfCommonChars[i] > 0) {
                for (int j = 0; j < countOfCommonChars[i]; j++) {
                    result.add(String.valueOf((char)(i + 'a')));
                }
            }
        }
        return result;
    }

}
