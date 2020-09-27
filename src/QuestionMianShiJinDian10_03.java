/*
面试题 10.03. 搜索旋转数组
搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。

示例1:

 输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
 输出: 8（元素5在该数组中的索引）
示例2:

 输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
 输出：-1 （没有找到）
提示:

arr 长度范围在[1, 1000000]之间
 */

/*
解题思路：二分法。

对于能判断升序区间的情况，根据目标值的大小移动边界。
对于不能判断升序区间的情况，需要逐步清除重复值。

没有重复值的最优情况时间复杂度是O(log N)，全部或几乎全部是重复值的最差情况时间复杂度是O(N)。

                                    nums[left] <= target
                              ┌─  && target <= nums[mid]   ──>  right = mid
                              │   （目标在左边的升序区间中）         （右边界移动到mid）
  ┌─  nums[left] < nums[mid] ─┼
  │     （左边区间升序）         │
  │                           └─    否则目标在右半边          ──>  left = mid + 1
  │                                                             （左边界移动到mid+1）
  │
  │                                 nums[left] <= target
  │                           ┌─  || target <= nums[mid]   ──>  right = mid
  │                           │    （目标在左半边）                （右边界移动到mid）
 ─┼─  nums[left] > nums[mid] ─┼
  │     （左边不是升序）         │
  │                           └─    否则目标在右半边          ──>  left = mid + 1
  │                                                              （左边界移动到mid+1）
  │
  │
  │                           ┌─   nums[left] != target    ──>  left++
  │                           │     （左值不等于目标               （需要逐一清理重复值）
  └─ nums[left] == nums[mid] ─┼         说明还没找到）
      （可能是已经找到了目标      │
        也可能是遇到了重复值）    └─   nums[left] == target    ──>  right = left
                                    （左值等于目标                 （将右边界移动到left，循环结束）
                                      已经找到最左边的目标值）
 */

/*
    相似题目：33、81
 */

public class QuestionMianShiJinDian10_03 {

    public static void main(String[] args) {
//        System.out.println(search(new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}, 5));
    }

    public int search(int[] arr, int target) {
        return -1;
    }

}
