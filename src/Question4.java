/*

4. 寻找两个正序数组的中位数
给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。

请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。



示例 1:

nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0
示例 2:

nums1 = [1, 2]
nums2 = [3, 4]

则中位数是 (2 + 3)/2 = 2.5

 */

public class Question4 {

    public static void main(String[] args) {

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;    // 数字个数总数
        if (totalLength % 2 == 1) {     // 总数为奇数
            int midIndex = totalLength / 2;     // 中间位置下标
            double median = getKthElement(nums1, nums2, midIndex+1);  // 转化为寻找第k小的数
            return median;
        } else {    // 总数为偶数
            int midIndex1 = totalLength / 2 - 1;    // 中间位置1下标
            int midIndex2 = totalLength / 2;    // 中间位置2下标
            double median = (getKthElement(nums1, nums2, midIndex1+1) + getKthElement(nums1, nums2, midIndex2+1)) / 2.0;
            return median;
        }
    }

    // 寻找第k小的数
    public int getKthElement(int[] nums1, int[] nums2, int k) {
        /*
            主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
            这里的 "/" 表示整除
            nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
            nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
            取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
            这样 pivot 本身最大也只能是第 k-1 小的元素
            如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
            如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
            由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值（k=k−k/2），减去删除的数的个数
         */
        int length1 = nums1.length;
        int length2 = nums2.length;
        int index1 = 0;
        int index2 = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {    // 数组1先比较完毕了
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {    // 数组2先比较完毕了
                return nums1[index1 + k - 1];
            }
            if (k == 1) {   // 当要找第一个最小的数字时，直接比较两数组当前所在位置上的数谁更小
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1];
            int pivot2 = nums2[newIndex2];
            if (pivot1 < pivot2) {
                k -= (newIndex1 - index1 + 1); // k = k - k/2;
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1); // k = k - k/2;
                index2 = newIndex2 + 1;
            }
        }
    }


}
