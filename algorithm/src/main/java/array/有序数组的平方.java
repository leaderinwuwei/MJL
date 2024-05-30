package array;

public class 有序数组的平方{
	public static int[] sortedSquares(int[] nums) {
		if (nums == null || nums.length == 1) {
			return nums;
		}
		int left = 0;
		int right = nums.length - 1;
		while (right >= 0) {
			nums[right] = nums[right] * nums[right];
			right--;
		}
		right = nums.length - 1;
		int[] newNums = new int[nums.length];
		int newIndex = right;
		while (right > left) {
			if (nums[left] >= nums[right]) {
				newNums[newIndex] = nums[left];
				left++;
			} else {
				newNums[newIndex] = nums[right];
				right--;
			}
			newIndex--;
		}
		return newNums;
	}

	public static void main(String[] args) {
		sortedSquares(new int[]{-7,-3,2,3,11});
	}
}