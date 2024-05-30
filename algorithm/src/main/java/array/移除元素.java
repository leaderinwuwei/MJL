package array;

public class 移除元素 {
	public static int removeElement(int[] nums, int val) {
		//目标就是所有需要移除的元素替换到数组末尾
		if (nums == null) {
			return 0;
		}
		int length = nums.length;
		int tailIndex = length - 1;
		for (int i = 0; i < length; i++) {
			tailIndex = length - 1;
			for (int j = length - 1; j >= 0; j--) {
				if (nums[j] != val) {
					tailIndex = j;
					break;
				}
			}
			if (nums[i] != val) {
				continue;
			}
			if (i > tailIndex) {
				continue;
			}
			if (nums[i] == val) {
				nums[i] = nums[tailIndex];
				nums[tailIndex] = val;
			}
		}
		if (tailIndex == length - 1) {
			return tailIndex;
		} else {
			return tailIndex - 1;
		}

	}

	public static void main(String[] args) {
		removeElement(new int[]{2}, 3);
	}
}