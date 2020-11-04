/*
381. O(1) 时间插入、删除和获取随机元素 - 允许重复

设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
注意: 允许出现重复元素。
insert(val)：向集合中插入元素 val。
remove(val)：当 val 存在时，从集合中移除一个 val。
getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。

示例:
    // 初始化一个空的集合。
    RandomizedCollection collection = new RandomizedCollection();

    // 向集合中插入 1 。返回 true 表示集合不包含 1 。
    collection.insert(1);

    // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
    collection.insert(1);

    // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
    collection.insert(2);

    // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
    collection.getRandom();

    // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
    collection.remove(1);

    // getRandom 应有相同概率返回 1 和 2 。
    collection.getRandom();
 */

import java.util.*;

public class Solution381 {

}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
class RandomizedCollection {

    List<Integer> nums;
    Map<Integer, Set<Integer>> indexs;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        this.nums = new ArrayList<Integer>();
        this.indexs = new HashMap<Integer, Set<Integer>>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        nums.add(val);
        Set<Integer> set = indexs.getOrDefault(val, new HashSet<>());
        set.add(nums.size() - 1);
        indexs.put(val, set);
        return set.size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        // 判断集合中是否有这个数字
        if (!indexs.containsKey(val)) {
            return false;
        }
        // 若有则先在map中找到该数字在list中的位置，再用list中最后一个元素进行覆盖，然后删除最后一个位置上的元素，即实现O(1)删除
        Iterator iter = indexs.get(val).iterator(); // 某数字在nums位置set的迭代器
        int index = (int)iter.next();   // 得到该数字的一个位置
        int lastNum = nums.get(nums.size() - 1);    // nums中的最后一个数字
        nums.set(index, lastNum); // 用最后一个位置的元素覆盖要删除的元素
        indexs.get(val).remove(index);  // 要删除的数字的位置set中删除掉该位置记录
        indexs.get(lastNum).remove((nums.size() - 1));  // 由于覆盖，删除最后一个数字的位置set上的相应位置记录
        if (index != nums.size() - 1) { // 如果要删除的数字不是最后一位上的数字，就将最后一位数字的新位置加入其set
            indexs.get(lastNum).add(index);
        }
        if (indexs.get(val).size() == 0) {  // 如果被删除的数字是nums中最后一个，那么就删除其在map中的set记录
            indexs.remove(val);
        }
        nums.remove(nums.size() - 1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        // Math.random()是令系统随机选取大于等于 0.0 且小于 1.0 的伪随机 double 值，即范围是 [0, 1)
        return nums.get((int)(Math.random() * nums.size()));
    }
}
