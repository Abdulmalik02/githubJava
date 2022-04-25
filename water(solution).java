public class Solution {
    /*
     * @param heights: a list of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.empty() && heights[i] > heights[stack.peek()]) {
                int top = stack.pop();
				// if stack is empty, then it can't trap water.
                if (stack.empty()) {
                    continue;
                }
                int width = i - stack.peek() - 1;
                int height = Math.min(heights[i], heights[stack.peek()]) - heights[top];
                ans += width * height;
            }
            stack.push(i);
        }
        
        return ans;
    }
}