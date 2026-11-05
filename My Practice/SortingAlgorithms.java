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
    static void bubbleSort(int[] arr){
        //Initializing a variable to hold the length of the array
        int n = arr.length;
        int i, j, temp;
        boolean swapped;
        for(i = 0; i < n - 1; i++){
            swapped = false;
            for(j = 0; j < n - i - 1; j++){
                if(arr[j] > arr[j + 1]){
                    //Swap arr[j] and arr[j + 1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            //If no two elements were swapped by inner loop, then break
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
}