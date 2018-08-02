**Try using a TreeMap and a HashMap instead of MyHashMap. Are the resulting word frequencies any different? Is the time performance any different? If so, how would you rank the three implementations (in increasing order of time complexity)?** 

With each of the map implementations the word frequencies are not any different . All the words that come out are the same and with the exact same frequencies. The only big difference is that the TreeMap consistently runs slower than the HashMaps. MyHashMap and HashMap were very close in their times, while TreeMap was slower than the both of them. The times were MyHashMap 1276, 1488, 1392, 1392. HashMap 1166, 1338, 1332, 1458. TreeMap 1914, 1801, 1935, 1606. 

**How are % and Math.floorMod different? Which works more reliably for computing a hash table index?**

% returns a number going towards 0, so -1.3 would turn into -1. While Math.floorMod always returns a number that is lower or the floor. So in this case floorMod for -1.3 would return a -2. The floorMod operator would be more reliable because if the second number is positive (which it will always because the table size is positive) so that would mean that the number would always hash to a positive number, while with just the % operator it could hash to a negative number. 

**What is the time complexity of MyHashMap.size(), and how could you make it much more efficient?** 

The time complexity of the size() method is O(n) because it has to check every entry in every list. To make it more efficient a size variable could be maintained and every time something is added into the map it increases by one. This would be O(1) because only one variable would need to be accessed. 

**How does this implementation compare to one where you would directly use your linked Node class from the earlier assignment? Answer briefly in terms of ease of implementation, correctness, reliability, and performance.**

This one is more difficult to implement because is it a data structure within a data structure, however this one would be more reliable and faster because everything would be better sorted and quicker to access anywhere in the structure. 