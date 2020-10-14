/*
33. 搜索旋转排序数组
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是 O(log n) 级别。

示例 1:

输入: nums = [4,5,6,7,0,1,2], target = 0
输出: 4
示例 2:

输入: nums = [4,5,6,7,0,1,2], target = 3
输出: -1
 */

public class Solution33 {

    public static void main(String[] args) {

    }

    public int search(int[] arr, int target) {
        // 将数组一分为二，其中一定有一个是有序的，另一个可能是有序，也能是部分有序。此时有序部分用二分法查找。
        // 无序部分再一分为二，其中一个一定有序，另一个可能有序，可能无序。
        // 这样即是使用了二分查找，可以使时间复杂度最低为O(logn)
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) >> 1;  // 如果乘除是2的幂，用位运算更快
            // 如果mid位是target，则返回
            if (arr[mid] == target) {
                return mid;
            }
            // 因为旋转过且原数组有序，后面的连续序列一定比前面的连续序列第一位数要小
            // 所以通过第一位和mid位比较。得出前段还是后段有序
            if (arr[left] <= arr[mid]) { // 代表前段有序
                // 再看target是否在当前段，若在则正常进行二分查找，若不在则指针转换为另外一段
                if (target >= arr[left] && target < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {    // 代表后段有序
                // 再看target是否在当前段，若在则正常进行二分查找，若不在则指针转换为另外一段
                if (target > arr[mid] && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

}
