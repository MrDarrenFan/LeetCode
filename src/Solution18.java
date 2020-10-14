/*
18. 四数之和
给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

注意：

答案中不可以包含重复的四元组。

示例：

给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

满足要求的四元组集合为：
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
 */

/*
方法一：排序 + 双指针
思路与算法

最朴素的方法是使用四重循环枚举所有的四元组，然后使用哈希表进行去重操作，得到不包含重复四元组的最终答案。
假设数组的长度是 n，则该方法中，枚举的时间复杂度为 O(n^4)，去重操作的时间复杂度和空间复杂度也很高，因此需要换一种思路。

为了避免枚举到重复四元组，则需要保证每一重循环枚举到的元素不小于其上一重循环枚举到的元素，且在同一重循环中不能多次枚举到相同的元素。

为了实现上述要求，可以对数组进行排序，并且在循环过程中遵循以下两点：
    每一种循环枚举到的下标必须大于上一重循环枚举到的下标；
    同一重循环中，如果当前元素与上一个元素相同，则跳过当前元素。
使用上述方法，可以避免枚举到重复四元组，但是由于仍使用四重循环，时间复杂度仍是 O(n^4)。
注意到数组已经被排序，因此可以使用双指针的方法去掉一重循环。

使用两重循环分别枚举前两个数，然后在两重循环枚举到的数之后使用双指针枚举剩下的两个数。
假设两重循环枚举到的前两个数分别位于下标 i 和 j，其中 i<j。
初始时，左右指针分别指向下标 j+1 和下标 n-1。每次计算四个数的和，并进行如下操作：
    如果和等于target，则将枚举到的四个数加到答案中，然后将左指针右移直到遇到不同的数，将右指针左移直到遇到不同的数；
    如果和小于target，则将左指针右移一位；
    如果和大于target，则将右指针左移一位。
使用双指针枚举剩下的两个数的时间复杂度是 O(n)，因此总时间复杂度是 O(n^3)，低于 O(n^4)。

具体实现时，还可以进行一些剪枝操作：（注意nums要提前排序）
    1.在确定第一个数之后，如果nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target，说明此时剩下的三个数无论取什么值，四数之和一定大于target，因此退出第一重循环；
    2.在确定第一个数之后，如果nums[i] + nums[n-3] + nums[n-2] + nums[n-1] < target，说明此时剩下的三个数无论取什么值，四数之和一定小于target，因此第一重循环直接进入下一轮，枚举nums[i+1]；
    3.在确定前两个数之后，如果nums[i] + nums[j] + nums[j+1] + nums[j+2] > target，说明此时剩下的两个数无论取什么值，四数之和一定大于target，因此退出第二重循环；
    4.在确定前两个数之后，如果nums[i] + nums[j] + nums[n-2] + nums[n-1] < target，说明此时剩下的两个数无论取什么值，四数之和一定小于target，因此第二重循环直接进入下一轮，枚举nums[j+1]。
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution18 {

    public static void main(String[] args) {

    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>(); // 存放结果

        if (nums == null || nums.length < 4) {
            return result;
        }

        Arrays.sort(nums);  // 排序

        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {  // 第一层循环：寻找第一个数
            if (i > 0 && nums[i] == nums[i - 1]) {  // 避免重复数字
                continue;
            }
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {   // 剪枝情况1
                break;
            }
            if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {    // 剪枝情况2
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {  // 第二层循环：寻找第二个数
                if (j > i + 1 && nums[j] == nums[j - 1]) {  // 避免重复数字
                    continue;
                }
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {   // 剪枝情况3
                    break;
                }
                if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) { // 剪枝情况4
                    continue;
                }
                // 双指针寻找第三、四个数11
                int left = j + 1;
                int right = length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {    // 和与目标值相等，则放入结果集，同时移动左右两指针
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {  // 在合适的范围内，避开重复数字
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) { // 在合适的范围内，避开重复数字
                            right--;
                        }
                        right--;
                    } else if (sum < target) {  // 和比目标值小，则第三个数稍大一点
                        left++;
                    } else {    // 和比目标值大，则第四个数稍小一点
                        right--;
                    }
                }
            }
        }
        return result;
    }

}
