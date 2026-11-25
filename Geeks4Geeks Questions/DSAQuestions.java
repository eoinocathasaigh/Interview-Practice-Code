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

//Used for questions relating to linked list operations - like merge sort for a linked list
class Node {
    int data;
    Node next;
    Node(int x) {
        data = x;
        next = null;
    }
}

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
        //Both the row and the column of the dp array are +1 because we want to include the 0th row and 0th column to represent the base case of no items and no capacity
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

    //1.3 Optimized DP knapsack for Space complexity
    /**
     * This approach is similar to the one above, with one major exception
     * The space needed to compute the current row of DP values can be majorly reduced. 
     * To compute the current row of the dp[] array, we only need values from the previous row. 
     * Therefore, instead of maintaining the entire 2D dp table, we can optimize space by using just a single 1D array.
     * By traversing the array from right to left we ensure that previously written values are not overwritten before they're used
     */
    public void knapsackProblemDPOP(int[] val, int[] wt, int W){
        //Initializing DP array again
        int[] dp = new int[W + 1]

        //Looping for the first i elements
        for(int i = 1; i <= val.length; i++){
            //Secondary loop, starting at the end of the array and moving backwards
            for(int j = W; j >= wt[i - 1]; j--){
                dp[j] = Math.max(dp[j], dp[j - wt[i - 1] + val[i - 1]]);
            }
        }

        //Ultimately returning the resulting total value
        return dp[W];
    }

    //2. Merge Sort for a linkedList - common enough interview question
    //Merge sort is perfect for linked list
    /**
     * Merge sort works good for Linked List as it does a sequential access for sorting and does not require an auxiliary array or list like we need for sorting an array using Merge Sort.
     * Split the list: Use two pointers, slow and fast, to find the middle of the linked list. Split the list into two smaller lists.
     * Sort both halves: Recursively call mergeSort() on each half until the lists are empty or contain only one node.
     * Merge the sorted halves: Compare the nodes of both sorted lists, link the smaller node first, and append any remaining nodes. Return the head of the merged, sorted list
     * 
     */

    static Node split(Node head){
        Node fast = head;
        Node slow = head;

        //Move slow pointer 1 step and fast pointer 2 steps until fast reaches the end
        while(fast.next != null && fast != null){
            fast = fast.next.next;
            if(fast != null){
                slow = slow.next;
            }
        }

        Node temp = slow.next;
        slow.next = null;
        return temp;
    }

    //Merging the 2 linked lists
    static Node merge(Node first, Node second){
        
        //If either list is empty return the other
        if(first == null) return second;
        if(second == null) return first;

        //Pick the smaller value between the first and second
        if(first.data < second.data){
            //Recursively merge the rest of the lists and link the result to the current node
            frist.next = merge(first.next, second);
            return first;
        }
        else{
            second.next = merge(first, second.next);
            return second;
        }
    }

    //Main merge sort function
    static Node mergeSort(Node head){
        //If list is empty or only has one node then its already sorted
        if(head.next == null || head == null){
            return head;
        }

        //Split the list into 2 values
        Node second = split(head);

        //Recursively sort each half
        head = mergeSort(head);
        second = mergeSort(second);

        //Merge the 2 lists back together
        return merge(head, second);
    }

    //3. MISC QUESTION - PRINTING THE PYRAMID
    /**
     * This is a bit of an easier one where we're tasked with printing a pyramid of stars a certain number of times
     */
    public void printPyramid(int n) {
        // code here
        //Looping over the number of layers
        for(int i = 1; i <= n; i++){
            //Print Spaces - n - i gives us the number of spaces we need to have at the moment
            //e.g. if i = 1 and n = 5, we know for things to be balanced we need 4 spaces
            for(int j = 1; j <= n - i; j++){
                System.out.print(" ");
            }
            
            //Print Stars - (2 * i) - 1 gives us the number of stars to print
            //Since we need the number to be odd constantly, we multiply the current iteration by 2 and just subtract 1 to print that many
            for(int j = 1; j <= (2 * i) - 1; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    //4. SORTING AN ARRAY
    /**
     * This is a simple enough question which asks us to sort an array
     * The easy way to do this is by using Arrays.sort(....)
     * Another ways is commented out in the code below
     */
    void sortArr(int[] arr) {
        // code here
        int i = 0;
        /*
        while(i < arr.length){
            //If element isnt in its correct position, swap it
            if(arr[arr[i] - 1] != arr[i]){
                int temp = arr[i];
                arr[i] = arr[arr[i] - 1];
                arr[temp - 1] = temp;
            }
            else{
                i++;
            }
        }
        */
        Arrays.sort(arr);
    }

    //5. REVERSING A LINKED LIST
    Node reverseList(Node head) {
        // code here
        //STACK METHOD
        Stack<Node> stack = new Stack<>();
        Node temp = head;
        
        //Push all elements onto the stack
        while(temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        
        //Make the last element the new head of the linked list
        if(!stack.isEmpty()){
            head = stack.pop();
            temp = head;
            
            //Pop all elements off the list in reversed order
            while(!stack.isEmpty()){
                
                temp.next = stack.pop();
                
                temp = temp.next;
            }
            
            //Update the pointer of the last node to be null
            temp.next = null;
        }
        
        //Returning the new head
        return head;
    }

    //6. HAS TRIPLET SUM
    //This is a simplistic enough question which asks us to search an array with a target value to determine if a triplet of values within matches the target
    //It's like the 2 sum problem seen previously
    /**
     * The solution for this kind of problem is to use a soted array with the 2 pointer approach 
     * This makes searching for combinations not only easier but more efficient
     */

    public void hasTrippletSum(int[] arr, int target){
        int n = arr.length;
        Arrays.sort(arr);

        //Fix the first element as arr[i]
        //NOTE - we use n - 2 for indexing issues and because we're using the 2 pointers 
        for(int i = 0; i < n - 2; i++){
            //Initializing the left and right pointers
            //i + 1 to be ahead of the element we're checking against
            int l = i + 1; 
            //n - 1 because of indexing
            int r = n - 1;

            int targetSum = target - arr[i];
            while(l < r){
                if(arr[l] + arr[r] == targetSum){
                    return true;
                }
                if(arr[l] + arr[r] < targetSum){
                    l++;
                }
                if(arr[l] + arr[r] > targetSum){
                    r--;
                }
            }
        }

        //If we dont find a solution
        return false;
    }

    //7. SORTINT A STACK
    /**
     * The idea in this problem is that we have a stack and we're tasked with sorting it in ascending order
     * Theres technically 2 approaches where we use an auxillerary stack to keep sorted items
     * Or we can use the approach seen here where we use recursion
     * The idea with this approach is to:
     * 1. Remove the top element of the stack
     * 2. Recursively sort the remaining elements of the stack
     * 3. Insert the removed element back into the stack in its correct position
     * 
     * 2 main methods are seen with this approach
     * sortStack() removes elements from the stack and recursively sorts the remaining elements
     * sortedInsert() inserts a removed element into its correct position in the sorted stack
     * 
     * STEPS IN THE ALGORITHM
     * Remove the top element from the stack and store it in x.
     * Recursively call sortStack() to sort the remaining stack.
     * Once the remaining stack is sorted, call sortedInsert() to place x at its correct position.
     * In sortedInsert(), if the stack is empty or its top element is smaller than or equal to x, push x.
     * Otherwise, temporarily remove the top element and recursively find the correct position for x.
     * After inserting x, push the temporarily removed elements back.
     */

    public void sortedInsert(Stack<Integer> stk, int x){
        //If the stack is empty, or the top element is smaller, push X
        if(stk.isEmpty() || stk.peek() <= x){
            stk.push(x);

            return;
        }

        int top = stk.top;

        //Recursively sort x in ascending order
        sortedInsert(stk, x);
        st.push(top);
    }

    //Sorting the stack recursively
    public void sortStack(Stack<Integer> stk){
        if(stk.isEmpty()){
            return;
        }

        int top = stk.top();
        //Recursively sort the remaining stack
        sortStack(stk);

        sortedInsert(stk, top;)
    }

    //8. BFS OF A GRAPH
    /**
     * Pretty standard question, bfs is where we search all nodes on the current level before going any deeper in our search
     */

    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj){
        //V = Number of vertices
        int V = adj.size();
        //Keeping track of how what nodes we've visited
        boolean[] visited = new boolean[V];
        //A new arrayList for the results of the search
        ArrayList<Integer> res = new ArrayList<>();

        //Setting up the source node
        int src = 0;
        //A queue for us to keep track of what nodes we need to visit
        Queue<Integer> q = new LinkedList<>();
        visited[src] = true;
        q.add(src);

        while(!q.isEmpty()){
            int curr = q.poll();
            //Adding the result to the array list
            res.add(curr);

            //Visit all the unvisited neighbors of the current node
            for(int x : adj.get(curr)){
                if(!visited[x]){
                    visited[x] = true;
                    q.add(x);
                }
            }
        }

        return res;
    }

    //9. CYCLE DETECTION IN UNDIRECTED GRAPHS
    /**
     * Cycle detection is a key part of graph traversal and the searches
     * A cycle is a path that starts and ends on the same vertex
     * Theres 2 main approaches that can be applied to these types of graphs
     * 
     * 1. DFS - An algo where we visit a vertex and then explore its adjacent vertices. If we reach a vertex that has already been visited, it may indicate a cycle.
     * However, in an undirected graph, when DFS moves from u to v, visiting u again from v does not mean that a cycle exists. To handle this, we keep track of the parent of each vertex. The parent is the vertex from which the current vertex was reached during DFS..
     * If during traversal we encounter a node that is already visited and is not the parent of the current node, it means we’ve found a cycle in the graph.
     * 
     * The algorithm for this approach is as follows:
     *  1. Mark the current index as visited and visit all its adjacent vertices
     *  2. If the adjacent vertex isnt visited then continue dfs from that and mark the current vertex as its parent
     *  3. If an adjacent vertex is already visited and isnt the parent of a the current vertex then a cycle exists
     *  4. If all vertices are explored without finding a cycle then one doesnt exist
     * 
     * 2. BFS - Similar to DFS but here we use bfs instead of the recursive approach seen in dfs
     * In BFS, we also maintain a visited array to mark nodes that have been explored and a parent tracker to remember from which node we reached the current one.
     * If during traversal we encounter a node that is already visited and is not the parent of the current node, it means we’ve found a cycle in the graph.
     */

    //9.1 DFS
    public static boolean dfs(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited, int parent){
        //Mark the current node as visited
        visited[v] = true;

        //Recurr for all vertices adjacent to this vertex
        for(int neighbor : adj.get(v)){
            //If an adjacent vertex isnt visited then recur that vertex
            if(!visited[neighbor]){
                if(dfs(neighbor, adj, visited, v)){
                    return true;
                }
            }

            //If the adjacent vertex is visited and isnt the parent of the current node then there exists a cycle in the graph
            else if(neighbor != parent){
                return true;
            }
        }

        return false;
    }

    public boolean isCycle(ArrayList<ArrayList<Integer>> adj){
        int V = adj.size();

        //Mark all the vertices as not visited
        boolean[] visited = new boolean[V];

        //Cycle through the graph to detect any cycles
        for(int i = 0; i < V; i++){
            if(!visited[i]){
                //-1 is to represent the starting vertex
                if(dfs(i, adj, visited, -1)){
                    return true;
                }
            }
        }

        return false;
    }

    //10. Valid Parentheses
    /**
     * This is a question which tasks us with checking whether the parentheses within a string are balanced or not
     * In this case the parentheses being balanced is based on the question of whether or not each opened one has the appropriate closed one
     * Theres 2 main ways to do this
     * 1. With a stack - push elements from one onto the stack, we then check if the next closing one we find matches the element on the top of the stack
     *      If it doesnt then we return false, otherwise true. This approach is O(N) in time but O(n) in space as we create another data structure
     * 2. Without a stack for mutable strings - this approach uses string manipulation to keep track of the elements and parentheses
     * A variable top is used to track the index of the last unmatched opening bracket. 
     * Whenever an opening bracket is found, it is placed at the next top position. 
     * For a closing bracket, we check if it matches the character at top. If it does, we simply decrement top; otherwise, the string is unbalanced. 
     * In the end, if top is -1, all brackets are matched and the string is balanced.
     * 
     * The issue with this approach is that strings are immutable in most Java, JS, Python, C# so the approach wont work in these languages
     */

    public static boolean isBalanced(String str){
        Stack<Character> parStack = new Stack<>();

        for(char c : str.toCharArray()){
            if(c == '(' || c == '[' || c == '{'){
                parStack.push(c);
            }
            else if(c == ')' || c == ']' || c == '}'){
                //If the stack is empty then we automatically return false
                if(parStack.isEmpty()){
                    return false;
                }
                char top = parStack.peek();
                if((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top !=- '{')){
                        return false;
                }

                //Remove the element from the stack if it matches
                parStack.pop();
            }
        }

        return parStack.isEmpty();
    }

    //11. Coin Change
    /**
     * Given an integer array coins[ ] representing different denominations of currency and an integer sum
     * find the number of ways you can make sum by using different combinations from coins[ ]
     * 
     * Theres a few different ways to tackle these kinds of problems. Recursion being one, but as always recursion is a costly process. We're always making a decision on whether or not to include the current coin or not
     * To handle the increased time complexity we can use Memoization 
     * Memoization - an optimization technique primarily used to enhance the performance of algorithms by storing the results of expensive function calls and reusing them when the same inputs occur again
     * - Its particularly helpful for scenarios involving repeated computations like recursion where the same calculation will be performed multiple times
     * 
     * Theres 3 ways we can tackle it from here, through a top down, bottom up DP and finally a space optimized DP
     * TOP DOWN DP 
     * We create a DP table of size n × (sum + 1) to store the results of subproblems based on the number of coins considered and the remaining target sum. This avoids recomputing the same subproblems multiple times.
     * - Each state count(i, sum) stores the number of ways to form the target sum using the first i coins.
     * - If the same subproblem occurs again, we directly use its stored result from the DP table.
     * 
     * BOTTOM UP DP (TABULATION)
     * In the memoization approach we solved each subproblem top-down using recursion, but in the tabulation approach we build the solution in a bottom-up manner by filling a DP table iteratively.
     * We first define the base cases for the DP table.
     * - dp[0][0] = 1, meaning if we have 0 coins and target sum is 0, there is exactly one way — choose nothing.
     * - For dp[0][j] where j > 0, the value is 0 because with zero coins we cannot make a positive sum.
     * - For dp[i][0], the value is 1 for all i because there is only one way to make sum 0 — by not selecting any coin.
     * - After the base initialization, we fill the table iteratively using the same idea.
     * 
     * SPACED OPTIMIZED DP
     * This is the approach im going to higlight the most prominently
     * In previous approach of dynamic programming we have derive the relation between states as given below:
     * - if (sum-coins[i]) is greater than 0, then dp[i][sum] = dp[i][sum-coins[i]] + dp[i+1][sum].
     * - else dp[i][sum] = dp[i+1][sum].
     * - If we observe that for calculating current dp[i][sum] state we only need previous row dp[i-1][sum] or current row dp[i][sum-coins[i]]. 
     * 
     * There is no need to store all the previous states just one previous state is used to compute result.
     */

    public static int count(int[] coins, int sum){
        int n = coins.length;

        //dp[i] will store the possible solutions for value/ combination i
        int[] dp = new int[sum + 1];
        //If we have a zero coins/combinations possible then theres only 1 solution
        dp[0] = 1;

        //Pick all the coins 1 by 1 and update the table[] values after the index greater than or equal to the value of the picked coin
        for(int i = 0; i < n; i++){
            for(int j = coins[i]; j <= sum; j++){
                dp[j] += dp[j - coins[i]];
            }
        }

        return dp[sum];
    }
}