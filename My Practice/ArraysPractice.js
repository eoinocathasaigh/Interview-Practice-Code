//Reverse an Array
function reverseArr(arr){
    let left = 0;
    //So its not out of bounds
    let right = arr.length -1;

    while (left < right){
        let temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;

        //MOVE POINTERS EOIN
        left++;
        right--;
    }

    return arr;
}

//FIND MAX VALUE IN ARRAY
function findMax(arr){
    //ERROR HANDLING
    if(arr.length == 0){
        return null;
    }
    let currMax = arr[0];

    for(let i = 1; i < arr.length; i++){
        if(arr[i] > currMax)
            currMax = arr[i]
    }

    return currMax;
}

//REMOVE DUPLICATES
//NOTE THAT THIS IS THE SLOWER METHOD - DO NOT DO THIS UNLESS EXPLICITLY ASKED TO
//THE EASIER AND BETTER PROGRAMMING WAY IS TO DO THIS USING A HASH MAP
function removeDups(arr){
    let uniqueArray = [];

    //LOOP 1 - LOOPING OVER ALL ELEMENTS IN THE 1ST ARRAY
    for(let i = 0; i < arr.length; i++){
        let isDuplicate = false;
        for(let j = 0; j < uniqueArray.length; j++){
            if(arr[i] === uniqueArray[j]){
                isDuplicate = true;
                break;
            }
        }
        if(!isDuplicate){
            uniqueArray.push(arr[i])
        }
    }

    return uniqueArray;
}

//Arrays are equal - without using built in methods
function arraysEqual(arr1, arr2){
    if(arr1.length != arr2.length){
        return false;
    }

    for(let i = 0; i < arr1.length; i++){
        if(arr1[i] != arr2.length[i])
            return false;
    }

    return true;
}

//Binary Search - O(log n) time complexity
//Question prompts us with an array and a value we're meant to find within it
//Array is sorted in ascending order
//Binary search is a divide and conquer algo that works by dividing the array in half and checking if the value is greater than or less than the middle value
function binarySearch(arr, target){
    //Setting up our pointers similar to the reverse array function - like two pointers
    let left = 0;
    let right = arr.length - 1;

    while(left <= right){
        //Finding the middle index of the array
        let mid = Math.floor((left + right) / 2);

        if(arr[mid] == target){
            return mid;
        }
        else if(arr[mid] < target){
            //We move the left pointer to the right of the middle index because we know at this point the target value is greater than the middle value
            left = mid + 1;
        }
        else{
            //We're moving the right pointer to the left of the mid index because we know at this point the target value is less than the middle value
            right = mid -1;
        }
    }
    //We return -1 if the value isnt found within the array
    return -1;
}

//PRODUCT OF ARRAY EXCEPT SELF
//Another common one where we're asked to find the product of all elements in an array except for the current index
function productExceptSelf(arr){
    let n = arr.length;
    //We create a new output array and fill it with 1s so we can easily multiply all values 
    let outputArray = new Array(n).fill(1);

    //CALCULATING LEFT PRODUCTS - looping through and calculating product for all variables left of the curent index
    let leftProduct = 1;
    for(let i = 0; i < n; i++){
        //Setting the current index of the output array to the left product value
        outputArray[i] = leftProduct;
        //Then we iteratively update the product value by multiplying it with the current index of the input array
        leftProduct *= arr[i];
    }

    //CALCULATING RIGHT PRODUCTS - Same as before just with the right side
    let rightProduct = 1;
    for(let i = n -1; i >= 0; i--){
        //Multiplying the current index of the output array with the right product value
        outputArray[i] *= rightProduct;
        //Same update as seen before with the left product just with the right product
        rightProduct *= arr[i];
    }

    return outputArray;
}

//FINDING SECOND LARGEST ELEMENT IN AN ARRAY
function secondLargest(arr){
    //Error handling
    if(arr.length < 2){
        return null;
    }

    const n = arr.length;
    let largest = -1;
    let secondLargest = -1;

    //Initializing the loop to find the largest and second largest elements in the array
    for(let i = 0; i < n; i++){
        if(arr[i] > largest){
            secondLargest = largest;
            largest = arr[i];
        }
        else if(arr[i] > secondLargest && arr[i] != largest){
            secondLargest = arr[i];
        }
    }
    return secondLargest;
}

//CHECKING PALINDROME - TWO POINTERS
function isPalindromeTwoPoint(string){
    let left = 0;
    let right = string.length - 1;

    //Continue to loop while the pointers arent crossed
    while(left < right){
        if(string[left] != string[right]){
            return false;
        }

        //Move the pointers
        left++;
        right--;
    }

    //If we reach this point then no mismatch has been found so we return true
    return true;
}

//PALINDROME - RECURSION 
//This method of approaching the palindrome problem just using recursion
function isPalindromeUtil(string, left, right){
    if(left >= right)
        return true;

    //If characters at the current positions arent equal then return false
    if(string[left] != string[right]){
        return false
    }

    //If not then move the pointers slightly 
    return isPalindromeUtil(string, left+ 1, right -1);
}

// Function to check if a string is a palindrome
function isPalindrome(s) {
    let left = 0;
    let right = s.length - 1;
    return isPalindromeUtil(s, left, right);
}