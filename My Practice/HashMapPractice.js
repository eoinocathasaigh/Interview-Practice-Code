//WHEN TO USE HASH MAPS? 
//When we need to count how often something appears
//When we need to track whether something has been seen before
//Searching quickly for a value in a collection of data
//When we need to associate data
//To avoid repeatedly searching through an array

//REMOVE DUPLICATES - THIS IS THE BETTER WAY
//KEEPING A COPY OF SEEN ELEMENTS
function removeDupsHash(arr){
    //Map of elements we've seen so far
    //REMEMBER MAP FORMAT IS {KEY: VALUE} - SO IN THIS CASE IT WOULD BE (ELEMENT: TRUE)
    let uniqueMap = {};
    //Array to hold the unique elements
    let uniqueArray = [];

    for(let i = 0; i < arr.length; i++){
        if(!uniqueMap[arr[i]]){
            uniqueMap[arr[i]] = true;
            uniqueArray.push(arr[i]);
        }
    }

    return uniqueArray;
}

//AKA PAIRS WITH TARGET SUM
//TWO SUM - USING HASH MAPS
function twoSum(arr, target){
    //Defining a new map to hold elements we've seen so far
    let map = {};

    //Looping through the array of values
    for(let i = 0; i < arr.length; i++){
        //Finding the complement for the current index to reach the target value
        let complement = target - arr[i];
        //Checking if the complement exists in the map and returning if it does
        if(map[complement] !== undefined){
            return [map[complement], i];
        }
        //Otherwise we add the current element to the map with its index as the value
        map[arr[i]] = i;
    }

    return null;
}

//FINDING THE FIRST NON-REPEATING CHARACTER IN A STRING
function firstNonRepeatingChar(str){
    //Defining a new map to hold characters we've seen so far
    let charMap = {};

    //Looping through the string of characters
    for(let i = 0; i < str.length; i++){
        //If the character is not in the map, we add it with a value of 1
        if(!charMap[str[i]]){
            charMap[str[i]] = 1;
        } else {
            //Otherwise we increment the value by 1
            charMap[str[i]]++;
        }
    }

    //Looping through the string again to find the first character with a value of 1 in the map
    for(let i = 0; i < str.length; i++){
        if(charMap[str[i]] === 1){
            return str[i];
        }
    }

    return null;
}

//FINDING THE MOST FREQUENT ELEMENT IN AN ARRAY
function mostFrequentElement(arr){
    //Defining a new map to hold elements we've seen so far
    let freqMap = {};
    let maxFreq = 0;
    let mostFreqElement = null;

    //Looping through the array of values
    for(let i = 0; i < arr.length; i++){
        //If the element is not in the map, we add it with a value of 1
        if(!freqMap[arr[i]]){
            freqMap[arr[i]] = 1;
        } else {
            //Otherwise we increment the value by 1
            freqMap[arr[i]]++;
        }

        //Checking if the current element's frequency is greater than the max frequency found so far
        if(freqMap[arr[i]] > maxFreq){
            maxFreq = freqMap[arr[i]];
            mostFreqElement = arr[i];
        }
    }

    return mostFreqElement;
}