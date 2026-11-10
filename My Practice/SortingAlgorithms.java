//Sorting algorithms are a very important topic
//Theres a few whose time complexity is O(n log n) and some that are O(n^2)
//Use cases vary based on the size of the data set and whether or not the data is already sorted

class SortingAlgorithms {
    public static void main(String[] args) {
        //UNCOMMENT THE SORTING ALGORITHM YOU WANT TO TEST/SEE DEMONSTRATED
        //Bubble Sort
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Bubble Sort")
        bubbleSort(arr);
        printArray(arr);

        //Selection Sort
        int[] selectArr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Selection Sort")
        selectionSort(selectArr);
        printArray(selectArr)

        //Insertion Sort
        int[] insertionArr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Insertion Sort")
        insertionSort(insertionArr);
        printArray(insertionArr);
    }

    //UTILITY FUNCTION TO PRINT AN ARRAY
    public static void printArray(int[] arr){
        int n = arr.length;
        for(int i = 0; i < n; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    //BUBBLE SORT - O(n^2) TIME COMPLEXITY
    //This algo works by repeatedly swapping adjacent elements if they are in the wrong order
    /**
     * The middle part of this can seem tricky but its simple enough
     * 1. We keep track of the number of passes completed on the array while maintaining a variable for checking for swaps (first i loop)
     * 2. Within another loop we compare the current element against the one next to it, checking if values are unequal (j loop)
     * - NOTE: Within the J loop we see j < n - i - 1. This statement allows us to loop through the unsorted portion of the array
     * - We're checking to see if j is less than all other unsorted elements -1 (the -1 is for indexing as we're comparing to the next element constantly)
     */
    static void bubbleSort(int[] arr){
        //Initializing a variable to hold the length of the array
        int n = arr.length;
        //Initializing various other variables that will be used throughout the program - not necessary but can help
        int i, j, temp;
        //Boolean for marking if a swap has been performed
        boolean swapped;
        //Initial loop for the entire array
        //I is keeping track of passes completed through the array
        for(i = 0; i < n - 1; i++){
            //Set swapped to false as on this iteration - we check if element values and placement are unequal then update this
            swapped = false;
            //Inner loop for comparriosn against 1 item
            //j < n - i - 1 - means j loops through the unsorted portion, stopping one position early
            //It stops one position early (i.e. the -1) because the code compares array[j] and array[j + 1]
            for(j = 0; j < n - i - 1; j++){
                //If the value of the current unsorted part of the array is greater than the next then we swap
                if(arr[j] > arr[j + 1]){
                    //Swap arr[j] and arr[j + 1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            //If no two elements were swapped by inner loop, then break - array is sorted
            if(!swapped){
                break;
            }
        }
        
    }

    //SELECTION SORT - O(n^2) TIME COMPLEXITY
    //This algorithm sorts by repeatedly selecting the smallest (or largest) element from the unsorted portion and swapping it with the first unsorted element.
    //The description of it in action is:
    //1. Find the smallest element and swap it with the first element - get smallest element at correct position
    //2. Find smallest (or second smallest) among the remaining unsorted elements and swap it with the second element
    //3. Repeat until the array is sorted
    public static void selectionSort(int[] arr){
        int n = arr.length;
        for(int i = 0; i < n - 1; i++){
            //Assume the current position holds the minimum element
            int min_index = 1;
            //We then iterate over the unsorted positions to find the actual minimum
            for(int j = i + 1; j < n; j++){
                if(arr[j] > min_index){
                    //Update the minimum index if a smaller element is found
                    min_index = j;
                }
            }

            //Move the minimum element to its correct position
            int temp = arr[i];
            arr[i] = arr[min_index];
            arr[min_index] = temp
        }

    }

    //Insertion Sort
    //Simple sorting algorithm which by iteratively inserting each element of an unsorted list into its correct position in a sorted portion of the list
    //Step by step breakdown is as follows
    //1. Start with 2nd element as the first is assumed to be sorted
    //2. Compare second with first, if its smaller then swap them
    //3. Move to the 3rd element and compare it with the first two and put it in its correct position
    //4. Continue until the entire array is sorted
    public static void insertionSort(int[] arr){
        int n = arr.length;

        for(int i = 1; i < n; i++){
            int key = arr[i];
            int j = i - 1;

            //Move the elements of [0...i-1] that are greater than the key ahead
            while(j >= 0 && arr[j] > key){
                arr[j + 1] = arr[j]
                j = j - 1;
            }

            arr[j + 1] = key;
        }
    }
    
    //QUICK SORT - Divide and Conquer Algorithm
    /**
     * Operates by selecting an element as a pivot and partitioning the given array around the pivot by placing the pivot in its correct position in the sorted array
     * STEPS IN ALGORITHM
     * 1. Select an element from the array - can be random, start, end, median
     * 2. Partition - rearrange the array around the pivot. After partitioning, all elements smaller than the pivot will be on its left, and all elements greater than the pivot will be on its right.
     * 3. Recursively Call - apply the same process to the two partitioned sub arrays
     * 4. Base Case - The recursion stops when there is only one element left in the sub-array, as a single element is already sorted.
     * 
     * Picking a pivot
     * - You could pick either always the first or last element but with this approach it ends up being the worst case if the array is alreadt sorted
     * - Could also choose randomly - this avoids the problem above
     * - Median selection - this is the ideal/prefered or selected approach in terms of time complexity as we can find median in linear time and the partition function will always divide the input array into two halves. 
     * But it takes more time on average as median finding has high constants
     * 
     * PARTITIONING EXPLANATION
     * The partitioning is the most important part of the process and theres 3 methods to do it
     * Naieve - Here we create copy of the array. First put all smaller elements and then all greater. Finally we copy the temporary array back to original array. This requires O(n) extra space
     * Lomuto - Keep track of the index of smaller items and continue swapping, this is the one used here for simplicity
     * Hoares - Fastest of all which traverses the array from both sides, swapping greater element on left side with the smaller on the right while the array isnt partitioned
     * 
     * LUMOTO
     * Works by starting at the leftmost element and keeping track of the smaller or equal elements as i
     * While traversing if we find a smaller element we can swap the current element with arr[i], otherwise we ignore the current element
     * 
     * TIME ANALYSIS
     * Best Case: (Ω(n log n)), Occurs when the pivot element divides the array into two equal halves.
     * Average Case (θ(n log n)), On average, the pivot divides the array into two parts, but not necessarily equal.
     * Worst Case: (O(n²)), Occurs when the smallest or largest element is always chosen as the pivot (e.g., sorted arrays).
     */

    //Chosing the pivot
    public static int partitionLumoto(int[] arr, int low, int high){
        int pivot = arr[high];

        //Index of smaller element and indicates the right position of pivot found so far
        int i = low - 1;

        for(int j = low; j <= high - 1; j++){
            if(arr[j] < pivot){
                i++;
                swap(arr, i, j);
            }
        }

        //Move the pivot after the smaller elements and return its position
        swap(arr, i + 1, high);
        return i + 1;
    }

    //Swap function
    public static void swap(int[] arr, int i, int j){
        temp = arr[i];
        arr[i] = arr[j];
        arr[i] = temp;
    }

    //QUICKSORT FUNCTION
    //arr[] -> array to be sorted
    //low -> Starting index
    //high -> ending index
    public static void quickSort(int[] arr){
        if(low < high){
            //Partition is the partition return index of pivot
            int partition = partitionLumoto(arr, low, high);

            //Recursively calling for smaller elements or greater and equals elements
            quickSort(arr, low, partition - 1);
            quickSort(arr, partition + 1, high);

        }
    }

    //ALTERNATIVE - HOARES PARTITION SCHEME
    /**
     * Works again by initializing 2 indexes that start at 2 ends
     * They move together until an inversion is found (left > right)
     * When inversion is found the values are swapped and the process is repeated
     */

    public static int partitionHoare(int[] arr, int low, int high){
        int pivot = arr[low];
        int i = low - 1, j = high + 1;

        while(true){
            //Find leftmost element greater than the pivot
            do{
                i++;
            }while (arr[i] < pivot);

            //Find rightmost element smaller than or equal to the pivot
            do{
                j--;
            }while(arr[j] > pivot);

            //If the two pointers met
            if(i >= j){
                return j;
            }
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    //MERGE SORT
    /**
     * Known for efficiency and stability - using divide and conquer approach
     * Works by recursively splitting the array into two halves, sorting them and then merging them back together
     * 
     * 1. DIVIDE: Divide the list or array recursively into two halves until it can no more be divided
     * 2. CONQUER: Each subarray is sorted individually using the merge sort algorithm
     * 3. The sorted subarrays are merged back together in sorted order. The process continues until all elements from both subarrays have been merged
     * 
     * Time Complexity:
     * Best Case: O(n log n), When the array is already sorted or nearly sorted.
     * Average Case: O(n log n), When the array is randomly ordered.
     * Worst Case: O(n log n), When the array is sorted in reverse order.
     * 
     * Applications
     * Sorting LinkedLists
     * Can solve problems like the intersection of 2 arrays
     * 
     * Adv
     * Stable, and maintains sorting order
     * Guaranteed worst-case performance: Merge sort has a worst-case time complexity of O(N logN) , which means it performs well even on large datasets
     * Simple to implement
     * 
     * Disadv
     * Slower than QuickSort in general as QuickSort is more cache friendly because it works in-place
     */

    public void mergeSort(int arr[], int l, int r) {
        // code here
        if(l < r){
            //Finding the middle point
            int m = l + (r - l) / 2;
            
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            
            //Merge the 2 halves back together
            merge(arr, l, m, r);
        }
    }
    
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    public void merge(int[] arr, int l, int m, int r){
        
        //Find the sizes of the 2 subarrays needed
        int size1 = m - l + 1;
        int size2 = r - m;
        
        //Creating the new temp arrays
        int L[] = new int[size1];
        int R[] = new int[size2];
        
        //Copy the data into the arrays
        for(int i = 0; i < size1; i++){
            L[i] = arr[l + i];
        }
        for(int j = 0; j < size2; j++){
            R[j] = arr[m + 1 + j];
        }
        
        //Merging the Temp arrays back together
        
        //Initial indices of the first and second sub arrays
        int i = 0, j = 0;
        
        //Initial index of the merged subarray
        int k = l;
        
        while(i < size1 && j < size2){
            if(L[i] < R[j]){
                arr[k] = L[i];
                i++;
            }
            else{
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        
        //Copy remaining elements of L if any
        while(i < size1){
            arr[k] = L[i];
            i++;
            k++;
        }
        
        while(j < size2){
            arr[k] = R[j];
            j++;
            k++;
        }
        
    }

}