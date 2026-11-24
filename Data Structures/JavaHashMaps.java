//Hash Maps are another important area of data structures
//They are a collection of key-value pairs, where each key is unique and maps to a specific value
//Hash maps are implemented using an array and a hash function to compute the index for storing the key-value pairs
//The hash function takes a key and returns an index in the array where the value is stored
//In Java, the HashMap class is a commonly used implementation of a hash map

//Here is an example of how to use a HashMap in Java:
//You'd need to import it first from the java.util package, as shown in the commented import statement at the top of the code.
import java.util.HashMap;

public class JavaHashMaps{
    public static void main(String[] args) {
        //Create a HashMap
        //In this example we see the string is the key and the integer is the value
        HashMap<String, Integer> map = new HashMap<>();

        //Add key-value pairs to the HashMap
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("orange", 3);

        //Retrieve a value using a key
        int value = map.get("banana");
        System.out.println("Value for key 'banana': " + value);

        //Check if a key exists in the HashMap
        boolean hasKey = map.containsKey("apple");
        System.out.println("Does the key 'apple' exist? " + hasKey);

        //Remove a key-value pair from the HashMap
        map.remove("orange");

        //Iterate over the key-value pairs in the HashMap
        for (String key : map.keySet()) {
            System.out.println("Key: " + key + ", Value: " + map.get(key));
        }
    }

    //Aim is to remove any duplicate elements we find from this array
    ///Can be easily achieved with a hash map
    public void removeDuplicates(int[] arr){
        //Declaring a new hashmap to hold values we've seen
        HashMap<Integer, Boolean> seen = new HashMap<>();

        for(int i = 0; i < arr.length; i++){
            //Print the element if its not in the hash map and add it to the hashmap
            if(seen.get(arr[i]) == null){
                seen.put(arr[i], true);
            }
        }
    }
}