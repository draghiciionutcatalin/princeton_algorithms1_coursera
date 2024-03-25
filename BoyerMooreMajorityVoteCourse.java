public class BoyerMooreMajorityVoteCourse {

    public static int findMajorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;

        // Find the candidate element
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
                count = 1;
            }
            else if (num == candidate) {
                count++;
            }
            else {
                count--;
            }
            System.out.println("candidate= " + candidate + " count=" + count);
        }

        // Verify if the candidate is the majority element
        count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }

        if (count > nums.length / 2) {
            return candidate;
        }
        else {
            return -1; // No majority element found
        }
    }

    public static void main(String[] args) {
        int[] nums = { 3, 3, 4, 2, 4, 4, 2, 4, 4, 3, 3, 3, 3, 1, 2, 3, 1, 1 }; // Example array
        int majorityElement = findMajorityElement(nums);
        if (majorityElement != -1) {
            System.out.println("Majority element: " + majorityElement);
        }
        else {
            System.out.println("No majority element found.");
        }
    }
}
