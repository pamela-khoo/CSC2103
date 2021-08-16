package hashing;


import java.util.Scanner;

/**
 *  Driver class to implement hashing functions
 * 
 *
 */

public class HashTableTest {

   public static void main(String[] args){
	   
	   HashTable table = new HashTable(5);  // Set size of table
	   String key;
	   Integer value;
	   
	   // To test the hash table  
	   //table.put("Apple",1);
	   //table.put("Banana",2);
	   //table.put("Cherry",3);
	   //table.put("Lemon",4);
	   //table.put("Lemon",5);
	   
	   // For manual checking if keys are put in the correct index
	   //System.out.println("Hash code for Banana is " + "Banana".hashCode() + " % 5 = " + "Banana".hashCode()%5);
	   //System.out.println("Hash code for Lemon is " + "Lemon".hashCode() + " % 5 = " + "Lemon".hashCode()%5);
      
	   table.list();
      
	   while (true) {
		   System.out.println("\n=============== Hashing ===============");
		   System.out.println("   1. Insert key");
		   System.out.println("   2. Delete key");
		   System.out.println("   3. Search for key");
		   System.out.println("   4. Display contents of hash table");
		   System.out.println("   5. EXIT");
		   System.out.print(" Enter option:  ");
         
		   Scanner scan = new Scanner(System.in);
		   int option = scan.nextInt();
 		
		   switch (option) {
		   case 1: 
			   scan.nextLine();
			   System.out.print("\n   Key = ");
			   key = scan.nextLine();
			   System.out.print("   Value = ");
			   value = scan.nextInt();
			   table.put(key,value);
			   table.list();
			   break;         
		   case 2:
			   scan.nextLine();
			   System.out.print("\n   Key = ");
			   key = scan.nextLine();
			   table.remove(key);
			   table.list();
			   break;       
		   case 3:
			   scan.nextLine();
			   System.out.print("\n   Key = ");
			   key = scan.nextLine();
			   System.out.println("   Table contains key(" + key + ") = " + table.containsKey(key));
			   System.out.println("   Value = " + table.get(key));
			   break;                  
		   case 4:
			   table.list();
			   System.out.print("Hash table has " + table.size() + " item(s)\n");
			   break;
		   case 5:
			   return;         
		   default:
			   System.out.println("   Illegal command.");
			   break;
		   }
	   }
   	}
} 
