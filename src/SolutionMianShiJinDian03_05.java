/*

（程序员面试金典）
面试题 03.05. 栈排序
栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。

示例1:

 输入：
["SortedStack", "push", "push", "peek", "pop", "peek"]
[[], [1], [2], [], [], []]
 输出：
[null,null,null,1,null,2]
示例2:

 输入：
["SortedStack", "pop", "pop", "push", "pop", "isEmpty"]
[[], [], [], [1], [], []]
 输出：
[null,null,null,null,null,true]
说明:

栈中的元素数目在[0, 5000]范围内。

 */

import java.util.Deque;
import java.util.LinkedList;

public class SolutionMianShiJinDian03_05 {
}

/**
 * Your SortedStack object will be instantiated and called as such:
 * SortedStack obj = new SortedStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.isEmpty();
 */

/*
解题思路：
1、此题需要维护一个有序的栈，即每次push和pop操作前后，栈都应该是有序的。
2、根据题干要求，只允许使用辅助栈，很容易想到，每次push前，将栈中元素比当前小的push到辅助栈中，完成后再push回来；每次pop只需将当前栈顶元素pop出来即可。
3、还有没有继续优化的可能？有！连续多次push时，需要多次把元素在两个栈中传递。想象这样一个极端情况：
连续n次push相同或相近元素，需要来回操作2n x i次，其中i为每次移动的元素数量。
实际上，我们只需要移动2 x i次：先把i个元素移到辅助栈，再把n个元素放入栈，最后将辅助栈中元素移回来即可。
此为惰性更新

惰性更新策略运用很广泛，比如著名的U&F算法、区间树等，就使用了此策略。
 */

class SortedStack {

    // Stack是java早期遗留下来的，不推荐使用；
    // Deque为双端队列，既可以作为栈使用，也可以作为队列使用。可用LinkedList或ArrayQueue实现
    // 基础栈
    Deque<Integer> stack = new LinkedList<>();
    // 辅助栈
    Deque<Integer> tempStack = new LinkedList<>();


    public SortedStack() {

    }

    public void push(int val) {
        int max = stack.isEmpty() ? Integer.MAX_VALUE : stack.peek();
        int min = tempStack.isEmpty() ? Integer.MIN_VALUE : tempStack.peek();
        // 比较基础栈和辅助栈栈顶值，使其满足 辅助栈 <= val <= 基础栈
        while (true) {
            if (val > max) {
                tempStack.push(stack.pop());
                max = stack.isEmpty() ? Integer.MAX_VALUE : stack.peek();
            } else if (val < min) {
                stack.push(tempStack.pop());
                min = tempStack.isEmpty() ? Integer.MIN_VALUE : tempStack.peek();
            } else {
                stack.push(val);
                break;
            }
        }
    }

    public void pop() {
        // 惰性策略，将辅助栈元素push回基础栈
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    public int peek() {
        // 惰性策略，将辅助栈元素push回基础栈
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
        return stack.isEmpty() ? -1 : stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty() && tempStack.isEmpty();
    }
}