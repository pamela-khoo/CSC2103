package hashing;


import java.util.LinkedList;

/**
 *  Hashing in Java using separate chaining
 *  
 * 
 */

public class HashTable {

    public static class ListNode {
        String key;
        Integer value;
    }

    private int count;
    private LinkedList<ListNode>[] table; // Hash table stored as an array of linked lists
    
    // Create a new hash table to set the initial size
    public HashTable(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("Illegal table size");
		}
    	table =  new LinkedList[capacity];	
    }
   	
    // Number of nodes in the table
 	public int size() {
 		return count;
 	}
 	
    // Calculate hash code based on length of table
 	private int hash(String key) {
 		if (key == null) {
			throw new NullPointerException("Key cannot be null");
		}
 		return (Math.abs(key.hashCode())) % table.length;
 	}

    // List nodes in each location of the table
 	public void list() {
 		System.out.println();
 		for (int i = 0; i < table.length; i++) {
 			System.out.print(i + ":");
 			
 			LinkedList<ListNode> list = table[i];
 			
 			if (list != null) { 
				for(ListNode item : list) {
		        	System.out.print("  (" + item.key + "," + item.value + ")"); 
		        	
		        	if (item.equals(list.getLast())) {
		        		break;
		        	}
		        	System.out.print("  -->");
		        }
 			}
 			System.out.println();
 		}
 	} 
    
 	// Insert items into list
    public void put(String key, Integer value) {
        int index = hash(key);
        LinkedList<ListNode> list = table[index];

        if (list == null) {
   			
   			// Create a new LinkedList and add item to it
            list = new LinkedList<ListNode>();

            ListNode item = new ListNode();
            item.key = key;
            item.value = value;

            list.add(item);
            count++;
            table[index] = list;
        }
        
        // If key exists, replace value of key
        for(ListNode item : list) {
            if(item.key.equals(key)) {
                item.value = value;
                return;
            }
        }
         
        // Add new item into list
        ListNode item = new ListNode();
        item.key = key;
        item.value = value;

        list.add(item);
        count++;
    }
 	
    // Delete item from table
    public void remove(String key) {
        int index = hash(key);
        LinkedList<ListNode> list = table[index];

        if (list == null) {
        	System.out.println("   Key not found in table");
        	return;
        }
        
        for(ListNode item : list) {
            if (item.key.equals(key)) {
                list.remove(item);
                count--;
                System.out.println("   Key deleted succesfully");
                return;
            }
        }
    }
    
    // Search for item by key
  	public Integer get(String key) {
     	 int index = hash(key);
          LinkedList<ListNode> list = table[index];

          if (list == null) {
         	 return null;
          }

          for(ListNode item : list) {
              if (item.key.equals(key)) {
             	 return item.value;
              }
          }
          return null;
     }
  	
    // Search whether item exists in the list 
   	public boolean containsKey(String key) {
   		int index = hash(key);  
   		LinkedList<ListNode> list = table[index];  
   		
   		if (list == null) {
   			return false;
        }
   		
        for(ListNode item : list) {
            if (item.key.equals(key)) {
                return true;
            }
        }
   		return false;
   	}
}
