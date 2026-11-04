//REMOVE DUPLICATES - THIS IS THE BETTER WAY
//KEEPING A COPY OF SEEN ELEMENTS
function removeDupsHash(arr){
    let uniqueMap = {};
    let uniqueArray = [];

    for(let i = 0; i < arr.length; i++){
        if(!uniqueMap[arr[i]]){
            uniqueMap[arr[i]] = true;
            uniqueArray.push(arr[i]);
        }
    }

    return uniqueArray;
}