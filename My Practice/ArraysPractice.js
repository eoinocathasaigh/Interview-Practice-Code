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