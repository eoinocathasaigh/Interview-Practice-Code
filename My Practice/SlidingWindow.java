//Sliding window is a common problem to get alongside two pointers
//How it works is we basically use a window and slide it along a data structure to perform some basic operation whatever it may be
//Main idea is to use results from the previous window to do computations on the next window
//Commonly used in these problems:
//1. Finding maximum subarrays with a specific sum
//2. Finding longest substring with unique characters
//3. Solving problems that require a fixed window

//The example I'll use is the Maximum subarray of size k
//"Given an array arr[] and an integer k, calculate the maximum sum of a subarray having size exactly k"

//ALGORITHM:
/*
We compute the sum of the first k elements out of n terms using a linear loop and store the sum in variable window_sum
Then we will traverse linearly over the array till it reaches the end and simultaneously keep track of the maximum sum
To get the current sum of a block of k elements just subtract the first element from the previous block and add the last element of the current block
*/
class SlidingWindow{
    public static void main(String[] args){

    }

    public void SlidingWindowMaxSum(int[] arr, int n, int k){
        //n must be greater than k - the number of elements in the array must be larger than our window size
        if(n < k){
            System.out.println("Invalid");
            return -1;
        }

        //Compute the sum of the first window of size k
        int max_sum = 0;
        for(int i = 0; i < k; i++){
            max_sum = arr[i];
        }

        // Compute sums of remaining windows by removing first element of previous window and adding last element of current window.
        //Putting it simply - We use k (window size) to subtract from the current index to get the first element from the previous window
        //We then subtract that value from the window summary and this current index value
        //Then we see which of the instance variable of window sum or the max sum is greater and update the max sum accordingly

        int window_sum = max_sum;
        for(int i = k; i < n; i++){
            window_sum += arr[i] - arr[i - k];
            max_sum = Math.max(max_sum, window_sum);
        }

        return max_sum;
    }
}