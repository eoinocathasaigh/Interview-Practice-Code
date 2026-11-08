//This file will be used to store the problems solve from Geeks4Geek
//It is to be used for practice as well as a reference for future problems that may be similar to the ones solved here
//I will also use it as a way to gain deeper understanding of the approach to solving problems like these and why certain design decisions were made

//The knapsack problem
/**
 * This is an example of a combinational problem - not too disimilar to the robbing house problem
 * Combinational problems are problems where you have to make a choice between multiple options and the solution is a combination of those choices
 * The knapsack problem states: 
 * Given two arrays, val[] and wt[], where each element represents the value and weight of an item respectively, and an integer W representing the maximum capacity of the knapsack (the total weight it can hold).
 * Put the items into the knapsack such that the total value obtained is maximum without exceeding the capacity W
 * 
 * This put simpler means we have 2 sets of items linked to each other, one denoting value and the other denoting its corresponding weight
 * We need to find a combination of items that maximizes the value while keeping the total weight less than or equal to W
 */

class GeeksStudyQuestions{

    //Main method
    public static void main(String[] args){
        //Test code and method calls will go in here
    }

    //1. The knapsack problem
    //There are a few ways to attack this problem
    //The first one would be a recursion based approach in which we make choices based on current item value and weight and the remaining capacity of the knapsack
    //Using this we recursively call the knapsack function for the next item and the remaining capacity of the knapsack
    //This is repeated until we reach the end of the items or the knapsack is full
    //Although it may sound good this approach is inefficient and will take exponentially longer for larger inputs
    //An example of the recursive approach can be seen in section 1.1

    //1.1 Recursive approach
    public int knapsackRecursive(int[] val, int[] wt, int W, int n){
        //Base case
        if(n == 0 || W == 0){
            return 0;
        }

        int pick = 0;

        //We pick the nth item if it doesnt exceed the capacity of the knapsack
        if(wt[n - 1] <= W){
            pick = val[n - 1] + knapsackRecursive(val, wt, W - wt[n - 1], n - 1);
        }

        //We dont pick the nth item
        int dontPick = knapsackRecursive(val, wt, W, n - 1);

        return Math.max(pick, dontPick);
    }

    //1.2 Dynamic programming approach
    //The dynamic programming examines how in the recursive approach 2 variables are changing, the number of items and the carry capacity of the knapsack
    //Recursion parameters range from 0 to n (n being the number of items) and 0 to W (W being the capacity of the knapsack), so we can create a 2D array of size n+1 (+1 because of arrays indexing (dont want to go out of bounds)) and W+1 to store the results of the subproblems
    //dp[i][j] will represent the maximum value that can be obtained with the first i items and a knapsack capacity of j
    //Recursion is used following this to fill the dp array and the final answer will be in dp[n][W]

    //Much like other dynamic programming problems we approach it by breaking it down into smaller subproblems and solving those
    /**
     * CORE CONCEPT - 2D ARRAY in this problem
     * For this problem we use a 2D array to map out and store the best possible combination usiung the first i items with a knapsack carry capacity of j
     * The first dimension of the array represents the items and the second dimension represents the carry capacity
     * When calculating the value for a cell we look at the previous cells: dp[i -1][j] - aka dont use the current item, what was the best answer using the previous items?
     * We find the best answer using the remaining capacity and the previous items - dp[i - 1][j - [wt[i - 1]]]  (-1 is used for both indexing and previous answer access)
     * Then pick = val[i - 1] + dp[i - 1][j - wt[i - 1]] 
     * The dp table is meant to collect the previously solved problems, the best value instead of the actual combinations
     * To put it a bit clearer, dp[i][j] asks
     * - What is the best value i can get for the first i items with the capacity j
     */

    public void knapsackProblem(int[] val, int[] wt, int W){
        
        //We will use a dynamic programming approach to solve this problem
        //We will create a 2D array to store the maximum value that can be obtained for each weight and item combination
        int n = val.length;
        int[][] dp = new int[n + 1][W + 1];

        //Build the dp array`
        //The outer loop iterates through the items and the inner loop iterates through the weights
        //i = item
        //j = weight/capacity
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= W; j++){
                
                //If theres no items or the knapsack capacity is 0, the maximum value is 0
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }
                else{
                    int pick = 0;

                    //Picking the ith item if it doesnt exceed the capacity of the knapsack
                    if(wt[i - 1] <= j){
                        //Translation of what this means
                        //We pick the ith item and add its value to the maximum value that can be obtained with the remaining capacity
                        //Pick = The value of the ith item + the maximum value that can be obtained with the remaining capacity (j - wt[i - 1]) and the first i-1 items
                        //Val[i - 1] - the value gained from taking the current item -  is used because the array is 0 indexed and we are iterating from 1 to n, this statement is saying we want to pick the ith value properly
                        //j -wt[i - 1] - the capacity remaining after selection
                        //dp[i - 1][...] best value using previous items in that remaining capacity
                        pick = val[i - 1] + dp[i - 1][j - wt[i - 1]];
                    }

                    //The maximum value will be in the bottom right cell of the dp array
                    dp[i][j] = Math.max(pick, dp[i - 1][j]);
                }
            }
        }

        System.out.println("Maximum value in Knapsack = " + dp[n][W]);
    }
}