//Binary search is a search algo that works on sorted or monotonic search space, repeatedly dividing it into halves to find the target value or optimal answer in logarithmic time
//There are two conditions that must be met first before the binary search can take place:
//1. Input list must be sorted
//2. Access to any element of the data structure should take constant time O(1)

/*ALGORITHM FOR BINARY SEARCH
1. Divide list into halves by finding the middle index "mid"
2. Compare the middle index with the 'key'
3. If key is found at middle, process terminates
4. Else choose a half to search through which will be beneficial to the search
- If key is smaller, we move LEFT
- If key is larger, we move RIGHT
5. Search continues until the key is found or the total search space is exhausted
*/
class BinarySearch{
    public static void main(String[] args){

    }

    static void binarySearch(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;
        
        //Begin looping through values
        while(left <= right){
            int mid = left + (right - left) / 2;
            
            //Check if the mid value is the key
            if(arr[mid] == target){
                return arr[mid];
            }

            //If the target is greater than the middle value - update left to be the current middle value and continue the search
            if(arr[mid] < target){
                left = mid + 1;
            }//Otherwise the value has to be less than the middle so we move towards the left - set right to be the middle value
            else{
                right = mid - 1;
            }
        }

        //If not found
        return -1;
    }
}