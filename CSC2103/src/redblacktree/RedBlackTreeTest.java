package redblacktree;


import java.util.Scanner;

/**
 *  Driver class to implement red black tree
 * 
 *
 */

public class RedBlackTreeTest {
    public static void main(String[] args) {
    	RedBlackTree rbt = new RedBlackTree();
    	int node;
    	
	    // To test red-black tree
	    //rbt.insert(2);
	    //rbt.insert(5);
	    //rbt.insert(3);  
    	//rbt.delete(3);
    	
	    rbt.printTree();
	           
	    int choice = 0;
        while (true)    
        { 	
        	System.out.println("===== RED BLACK TREE =====");  
            System.out.println("   1. Insert node");
            System.out.println("   2. Delete node");
            System.out.println("   3. Display tree");
            System.out.println("   4. Clear tree");
            System.out.println("   5. Print tree traversals");
            System.out.println("   6. EXIT");
            System.out.print("Enter option: ");
            
            Scanner scan = new Scanner(System.in);          
            choice = scan.nextInt();
            
            switch (choice)
            {
	            case 1 : 
	                System.out.print("Enter integer element to insert: ");
	                node = scan.nextInt();
	                rbt.insert(node);                     
	                rbt.printTree();
	                break;                          
	            case 2 : 
	                System.out.print("Enter integer element to delete: ");
	                rbt.delete(scan.nextInt());
	                rbt.printTree();
	                break;                                          
	            case 3 : 
	            	rbt.printTree();
	                break;     
	            case 4 : 
	            	rbt.deleteAll();
	            	rbt.printTree();
	                break;     
	            case 5 : 
	            	System.out.print("\n   Preorder  : ");
	            	rbt.preorder();
	            	System.out.print("\n   In-order  : ");
	            	rbt.inorder();
	            	System.out.print("\n   Post-order: ");
	            	rbt.postorder();
	            	System.out.println("\n");
	            	break; 
	            case 6 : 
	            	System.out.println("End of program");
	            	System.exit(0);            
	            default : 
	                System.out.println("Invalid input\n ");
	                break;    
            }                     
        } 
    }
}
