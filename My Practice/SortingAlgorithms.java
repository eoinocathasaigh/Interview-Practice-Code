//Sorting algorithms are a very important topic
//Theres a few whose time complexity is O(n log n) and some that are O(n^2)
//Use cases vary based on the size of the data set and whether or not the data is already sorted

class SortingAlgorithms {
    public static void main(String[] args) {
        //UNCOMMENT THE SORTING ALGORITHM YOU WANT TO TEST/SEE DEMONSTRATED
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
}