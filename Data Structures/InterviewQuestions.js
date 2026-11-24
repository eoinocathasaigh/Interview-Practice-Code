//This file will tackle some basic interview questions in JavaScript
//1. HashMap pattern - "Pairs with Target Sum"
//This is a classic question which essentially prompts you to find two numbers in an array that add to a given target sum
//While at first you may think of using a brute force approach (2 loops resulting in O(n^2) time complexity), we can actually solve this problem in O(n) time complexity using a hash map
//How this works is by creating a hash map to store the numbers we've seen so far
//Then from here we check if the complement of the current number (target - current number) exists in the hash map
//If it does, we have found a pair that sums to the target and we can return it

function pairsWithTargetSum(arr, target) {
    const numMap = new Map(); // Create a new hash map to store numbers and their indices
    const result = []; // Array to store the pairs that sum to the target

    for (let i = 0; i < arr.length; i++) {
        const complement = target - arr[i]; // Calculate the complement that would sum to the target

        if (numMap.has(complement)) { // Check if the complement exists in the hash map
            result.push([complement, arr[i]]); // If it does, add the pair to the result array
        }

        numMap.set(arr[i], i); // Store the current number and its index in the hash map
    }

    return result; // Return all pairs that sum to the target
}

// Example usage:
const pairsArr = [2, 7, 11, 15];
const target = 9;
console.log(pairsWithTargetSum(pairsArr, target)); // Output: [[2, 7]]

//2. Sliding window pattern - "Maximum Sum Subarray of Size K"
//This is another classic question which prompts you to find the maximum sum of a subarray of size K
//A brute force approach would involve calculating the sum of every possible subarray of size K, resulting in O(n*k) time complexity
//However, we can optimize this using the sliding window technique, which allows us to calculate the sum in O(n) time complexity

function maxSumSubarrayOfSizeK(arr, k) {
    let n = arr.length;
    if (n < k) return -1;

    // compute sum of first window
    let windowSum = 0;
    for (let i = 0; i < k; i++) {
        windowSum += arr[i];
    }

    let maxSum = windowSum;

    // slide the window
    for (let i = k; i < n; i++) {
        windowSum += arr[i] - arr[i - k];
        maxSum = Math.max(maxSum, windowSum);
    }

    return maxSum;
}

// Example usage:
const arr2 = [2, 1, 5, 1, 3, 2];
const k = 3;
console.log(maxSumSubarrayOfSizeK(arr2, k)); // Output: 9 (subarray [5, 1, 3])

//3. Two Pointers pattern - "Container With Most Water"
//This problem prompts you to find two lines in an array that together with the x-axis form a container that holds the most water
//A brute force approach would involve checking every possible pair of lines, resulting in O(n^2) time complexity
//However, we can optimize this using the two pointers technique, which allows us to find the maximum area in O(n) time complexity

//The main idea here is to use two pointer variables, one at the start of the array and one at the end
//The pointers move towards each other, and at each step we calculate the area formed by the lines at the two pointers
//We keep track of the maximum area found so far, and we move the pointer that points to the shorter line, since moving the taller line would not increase the area
//This process continues until the two pointers meet - this process ensures we just pass through the array once, resulting in O(n) time complexity

function maxArea(arr) {
    let left = 0, right = arr.length - 1;
    let res = 0;
    while (left < right) {
        
        // find the water stored in the container between 
        // arr[left] and arr[right]
        let water = Math.min(arr[left], arr[right]) * (right - left);
        res = Math.max(res, water);
        
        if (arr[left] < arr[right])
            left += 1;
        else
            right -= 1;
    }
  
    return res;
}

// Example usage:
const height = [1, 8, 6, 2, 5, 4, 8, 3, 7];
console.log(maxArea(height)); // Output: 49 (between lines at index 1 and index 8)

//4. Fast and Slow Pointers pattern - "Linked List Cycle"
//This problem prompts you to determine if a linked list has a cycle in it
//A brute force approach would involve using a hash set to store the nodes we've seen so far, resulting in O(n) time complexity and O(n) space complexity
//However, we can optimize this using the fast and slow pointers technique, which allows us to determine if there is a cycle in O(n) time complexity and O(1) space complexity

//The main idea here is to use two pointer variables, one moving at twice the speed of the other
//If there is a cycle in the linked list, the fast pointer will eventually meet the slow pointer
//If there is no cycle, the fast pointer will reach the end of the list

function hasCycle(head) {
    let slow = head;
    let fast = head;

    while (fast !== null && fast.next !== null) {
        slow = slow.next; // move slow pointer by 1
        fast = fast.next.next; // move fast pointer by 2

        if (slow === fast) {
            return true; // cycle detected
        }
    }

    return false; // no cycle
}

//Example usage:
//Assuming we have a linked list implementation and a way to create a cycle
//const head = new ListNode(1);
//head.next = new ListNode(2);
//head.next.next = new ListNode(3);
//head.next.next.next = head; // create a cycle
//console.log(hasCycle(head)); // Output: true

//5. DFS on Grid - "Number of Islands"
//This is a problem in which we are given a 2D grid of '1's (land) and '0's (water) and we want to count the number of islands
//An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically
//We can solve this problem using Depth First Search (DFS) to explore each island and mark it as visited

// Check if the cell (r, c) is valid for BFS traversal
// It must lie within grid bounds, contain land ('L'), and not be visited yet
function isSafe(grid, r, c, visited) {
    const n = grid.length;
    const m = grid[0].length;
    return (r >= 0 && r < n && c >= 0 && c < m && grid[r][c] === 'L' && !visited[r][c]);
}

// Performs DFS to mark all connected land cells
function dfs(grid, r, c, visited) {
    // Mark current cell as visited
    visited[r][c] = true;

    // All 8 possible directions (vertical, horizontal, diagonal)
    const dr = [-1, -1, -1, 0, 0, 1, 1, 1];
    const dc = [-1, 0, 1, -1, 1, -1, 0, 1];

    // Explore all connected neighbours
    for (let k = 0; k < 8; k++) {
        const nr = r + dr[k];
        const nc = c + dc[k];

        if (isSafe(grid, nr, nc, visited))
            dfs(grid, nr, nc, visited);
    }
}

// finding number of distinct islands in the grid
function countIslands(grid) {
    const n = grid.length;
    const m = grid[0].length;

    // Matrix to track visited cells
    const visited = Array.from({ length: n }, () => Array(m).fill(false));

    let islands = 0;

    // Traverse every cell in the grid
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < m; j++) {
            // Start a new DFS when an unvisited land cell is found
            if (grid[i][j] === 'L' && !visited[i][j]) {
                dfs(grid, i, j, visited);
                islands++;
            }
        }
    }

    return islands;
}

// Example usage:
const grid = [
    ['1', '1', '0', '0', '0'],
    ['1', '1', '0', '0', '0'],
    ['0', '0', '1', '0', '0'],
    ['0', '0', '0', '1', '1']
];
console.log(countIslands(grid)); // Output: 3

//6. BFS on Grid - "Number of Islands"
//This is the same as the problem above, the difference is that we will use Breadth First Search (BFS) instead of Depth First Search (DFS) to explore each island and mark it as visited
//The BFS approach uses a queue to explore all the neighbours of a cell before moving on to the next cell, while DFS explores as far as possible along each branch before backtracking
//It also reuses the same isSafe function to check if a cell is valid for BFS traversal

// Perform BFS to traverse all connected land cells (forming one island)
function bfs(grid, visited, startR, startC) {
    // Possible 8 directions (vertical, horizontal, and diagonal)
    const dRow = [-1, -1, -1, 0, 0, 1, 1, 1];
    const dCol = [-1, 0, 1, -1, 1, -1, 0, 1];

    const q = [];
    q.push([startR, startC]);
    visited[startR][startC] = true;

    // Explore all reachable land cells for this island
    while (q.length > 0) {
        const [r, c] = q.shift();

        // Check all 8 neighbors of the current cell
        for (let k = 0; k < 8; k++) {
            const newR = r + dRow[k];
            const newC = c + dCol[k];
            if (isSafe(grid, newR, newC, visited)) {
                visited[newR][newC] = true;
                q.push([newR, newC]);
            }
        }
    }
}

// Count the total number of islands in the grid
function countIslandsBFS(grid) {
    const n = grid.length;
    const m = grid[0].length;
    const visited = Array.from({ length: n }, () => Array(m).fill(false));
    let islandCount = 0;

    // Traverse every cell in the grid
    for (let r = 0; r < n; r++) {
        for (let c = 0; c < m; c++) {

            // If an unvisited land cell is found, start BFS for that island
            if (grid[r][c] === 'L' && !visited[r][c]) {
                bfs(grid, visited, r, c);
                islandCount++;
            }
        }
    }

    return islandCount;
}

// Example usage:
const gridBFS = [
    ['1', '1', '0', '0', '0'],
    ['1', '1', '0', '0', '0'],
    ['0', '0', '1', '0', '0'],
    ['0', '0', '0', '1', '1']
];
console.log(countIslandsBFS(gridBFS)); // Output: 3

//7. BFS on Graph - "Search in a Graph"
//This problem prompts you to search for a target value in a graph represented as an adjacency list
//We can use Breadth First Search (BFS) to traverse the graph and check if the target value exists
//The BFS approach uses a queue to explore all the neighbours of a node before moving on to the next node

function bfsGraph(adj){
    const V = adj.length;
    const visited = new Array(V).fill(false);
    const res = [];

    const q = new Denque();

    let src = 0;
    visited[src] = true;
    q.push(src);

    while (!q.isEmpty()) {
        const curr = q.shift();
        res.push(curr);

        // visit all the unvisited
        // neighbours of current node
        for (const x of adj[curr]) {
            if (!visited[x]) {
                visited[x] = true;
                q.push(x);
            }
        }
    }

    return res;
}

// Example usage:
const adjList = [
    [1, 2],    // Neighbors of vertex 0
    [0, 3, 4], // Neighbors of vertex 1
    [0],       // Neighbors of vertex 2
    [1],       // Neighbors of vertex 3
    [1]        // Neighbors of vertex 4
];
console.log(bfsGraph(adjList)); // Output: [0, 1, 2, 3, 4]

//8. Basic DP - "Robbing House"
//DP - Dynamic Programming is a method for solving complex problems by breaking them down into simpler subproblems
//This problem prompts you to find the maximum amount of money you can rob from a series of houses, where adjacent houses cannot be robbed on the same night
//We can use dynamic programming to solve this problem by keeping track of the maximum amount that can be robbed up to each house
//The instinct might be to use recursion, but this would lead to an exponential time complexity due to overlapping subproblems
//Instead, we can use an iterative approach to build up the solution in O(n) time complexity and O(1) space complexity

//EXAMPLE OF THE BAD RECURSIVE APPROACH
// Calculate the maximum stolen value recursively
function findMaxSumRec(arr, n) {
	
    // If no houses are left, return 0.
    if (n <= 0)  return 0;
  
  	// If only 1 house is left, rob it. 
    if (n === 1)  return arr[0];

    // Two Choices: Rob the nth house and do not rob the nth house 
    let pick = arr[n - 1] + findMaxSumRec(arr, n - 2);
    let notPick = findMaxSumRec(arr, n - 1);

    // Return the max of two choices
    return Math.max(pick, notPick);
}

// Function to calculate the maximum stolen value
function findMaxSum(arr) {
    let n = arr.length;
    
    // Call the recursive function for n houses
  	return findMaxSumRec(arr, n);
}

let robbingArr = [6, 7, 1, 3, 8, 2, 4];
console.log(findMaxSum(robbingArr));

//Expexted Optimized Iterative Approach
function findMaxSum(arr) {
    //Set the length of the array
    let n = arr.length;

    //If theres no houses, return 0, if theres only one house, return the value of that house
    if (n === 0)
        return 0;
    if (n === 1)
        return arr[0];

    //Set previous 2 values
    let secondLast = 0, last = arr[0];

    //Compute current value using previous two values
    //The final current value would be our result
    let res;
    for (let i = 1; i < n; i++) {
        res = Math.max(arr[i] + secondLast, last);
        secondLast = last;
        last = res;
    }
    return res;
}
//Driver Code
let optimizedRobbingArr = [6, 7, 1, 3, 8, 2, 4];
console.log(findMaxSum(optimizedRobbingArr));

//9. Monotonic Stack - "Next Greater Element"
//This problem prompts you to find the next greater element for each element in an array
//We can use a monotonic stack to solve this problem in O(n) time complexity
//This problem basically asks to find how far is the current index from the index of next greater temperature to the temperature at the current index. The most optimal way to solve this problem is by making use of a stack. Below are the steps:

//1. Iterate over the everyday temperature of the given array arr[] using the current index.
//2. If the stack is empty, push the current index to the stack.
//3. If the stack is not empty then do the following: 

//If the temperature at the current index is lesser than the temperature of the index at top of the stack, push the current index.
//If the temperature at the current index is greater than the temperature of the index at top of the stack, then update the no of days to wait for warmer temperature as:

//current index – index at top of the stack

//Pop the stack once the number of days has been updated in the output list.
//4. Repeat the above steps for all the indices in the stack that are lesser than the temperature at the current index.

function dailyTemperatures(T)
{
    var n = T.length;

    //To store the answer
    var daysOfWait = Array(n).fill(-1);
    var s = [];

    //Traverse all the temperatures
    for (var i = 0; i < n; i++) {

        //Check if current index is the next warmer temperature of any previous indexes
        while (s.length!=0
               && T[s[s.length-1]] < T[i]) {

            daysOfWait[s[s.length-1]]
                = i - s[s.length-1];

            //Pop the element
            s.pop();
        }

        //Push the current index
        s.push(i);
    }

    //Print waiting days
    for (var i = 0; i < n; i++) {
        document.write( daysOfWait[i] + " ");
    }
}

//Driver Code
//Given temperatures
var arr = [73, 74, 75, 71,
        69, 72, 76, 73 ];
dailyTemperatures(arr);// Output: 1 1 4 2 1 1 0 0