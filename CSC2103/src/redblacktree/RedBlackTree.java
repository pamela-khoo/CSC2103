package redblacktree;


/**
 *  Red-Black Tree in Java
 * 
 *
 */
 
// Create a node with parent, left and right child, data and color
class Node {
	Node parent;
	Node left;
	Node right;
	int data;
	char color;
}

public class RedBlackTree {
	private Node root;
	private Node nullNode;

	// Set default values of node  
	public RedBlackTree() {
	    nullNode = new Node();
	    nullNode.color = 'B'; 
	    nullNode.left = null;
	    nullNode.right = null;
	    root = nullNode;
	}

    // Perform left rotation
    public void leftRotate(Node x) {
  		Node y = x.right;
  		x.right = y.left;
    
  		if (y.left != nullNode) {
  			y.left.parent = x;
  		}
  	
  		y.parent = x.parent;
  		
  		if (x.parent == null) { // x is root
  			this.root = y;
  		} else if (x == x.parent.left) { // x is the left child
  			x.parent.left = y;
  		} else { // x is the right child
  			x.parent.right = y;
  		}
  		
  		y.left = x;
  		x.parent = y;
  	}

    // Perform right rotation
    public void rightRotate(Node x) {
    	Node y = x.left;
    	x.left = y.right;
    	if (y.right != nullNode) {
    		y.right.parent = x;
    	}
    	y.parent = x.parent;
    	if (x.parent == null) { // x is root
    		this.root = y;
    	} else if (x == x.parent.right) { // x is the left child
    		x.parent.right = y;
    	} else { // x is the right child
    		x.parent.left = y;
    	}
    	
    	y.right = x;
    	x.parent = y;
    }
    
    // Find node with the minimum key value
 	public Node minimum(Node node) {
 		while (node.left != nullNode) {
 			node = node.left; // leftmost node
 		}
 		return node;
 	}

 	// Find node with the maximum key value
 	public Node maximum(Node node) {
 		while (node.right != nullNode) {
 			node = node.right; // rightmost node
 		}
 		return node;
 	}
 	
 	// Display the tree
 	public void printTree() {
 		System.out.println("");
 		printHelper(this.root, "", true);
 		System.out.println("");
 	}
 	
   	// Function to format the output of the tree  
   	private void printHelper(Node root, String indent, boolean last) {
   		if (root != nullNode) {
   			System.out.print(indent);
   			if (last) {
   				System.out.print("R----");
   				indent += "   ";
   			} else {
   				System.out.print("L----");
   				indent += "|  ";
   			}

   			Ansi ansi = new Ansi();
   			ansi.colorNode(root.color,root.data);
 		
   			printHelper(root.left, indent, false);
   			printHelper(root.right, indent, true);
   		}
   		
   		if (this.root == nullNode) {
 			System.out.println("Tree is empty");
   		}
   	}
   	
  	// Insert into tree 
    public synchronized void insert(int key) {
	    Node node = new Node();
		node.parent = null;
		node.data = key;
		node.left = nullNode;
		node.right = nullNode;
		node.color = 'R'; //set default color as red
	
		Node current = this.root; // start at root
		Node parent = null;
		
		while (current != nullNode) {
			parent = current;
			if (node.data < current.data) { 
		    	current = current.left; // insert as left child
		    } else { 
		    	current = current.right; // insert as right child
		    }
	    }
	
	    node.parent = parent;
	    
	    if (parent == null) {
	    	root = node; 
	    	node.color = 'B'; //set root color to black
	    	return;
	    } else if (node.data < parent.data) {
	    	parent.left = node; 
	    } else {
	    	parent.right = node; 
	    }

	    // If grandparent is null, simply return
	    if (node.parent.parent == null) {
	    	return;
	    }
	    
	    // Balance the tree
	    balanceInsert(node);
	}
   
   
    // Balance node after insertion
    private void balanceInsert(Node node) {
    	Node u;
    	
    	while (node.parent.color == 'R') {
    		
    		// Uncle is the left or right child of grandparent
    		if (node.parent == node.parent.parent.left) {
				u = node.parent.parent.right;
    		} else {
				u = node.parent.parent.left;
			}
    		
    		if (u.color == 'R') { // Case 2.1: Uncle is red
    			
				u.color = 'B';
	            node.parent.color = 'B';
	            node.parent.parent.color = 'R';
	            node = node.parent.parent; 	 
	            
    		} else { // Case 2.2 Uncle is black
    			
    			if (node.parent == node.parent.parent.left) { // left child of grandparent
    				
    				// 2.2.1 right child of parent
    				if (node == node.parent.right) { 
    					node = node.parent;
    					leftRotate(node);
    				} 
    				
    				// 2.2.2 left child of parent
    				node.parent.color = 'B';
    				node.parent.parent.color = 'R'; 
    				rightRotate(node.parent.parent);
    			
    			} else { // right child of grandparent
    				
    				// 2.2.3 left child of parent
    				if (node == node.parent.left) { 
    					node = node.parent;
    					rightRotate(node);
    				}
    				
    				// 2.2.4 right child of parent
    				node.parent.color = 'B';
    				node.parent.parent.color = 'R';
    				leftRotate(node.parent.parent);
    			}
    		}    
    		// Root must be black
    		if (node == root) {
    			break;
            }
    	}
    	root.color = 'B';
    } 
    
    // Delete a node
    public void delete(int data) {
  		deleteHelper(this.root, data);
  	}
    
  	// Delete all nodes to make the tree logically empty  
    public void deleteAll() {  
        this.root = nullNode;  
    } 
    
    private void deleteHelper(Node node, int key) {
    	
    	// Find the node containing key
        Node z = nullNode;
        Node x, y;
        
        while (node != nullNode) {
        	if (node.data == key) {
        		z = node;
        	}

        	if (node.data <= key) {
        		node = node.right;
        	} else {
        		node = node.left;
        	}
        }

        if (z == nullNode) {
        	System.out.println("Key not found in the tree");
        	return;
        }

        y = z;
        int y_orignal_color = y.color;
        
        if (z.left == nullNode) { // only right child
        	x = z.right;
        	rbTransplant(z, z.right);
        	
        } else if (z.right == nullNode) { // only left child
        	x = z.left;
        	rbTransplant(z, z.left);
        	
        } else { // two children 
        	y = minimum(z.right);
        	y_orignal_color = y.color;
        	x = y.right;
        	
        	if (y.parent == z) { // y is the direct child of z
        		x.parent = y;
        	} else {
        		rbTransplant(y, y.right);
        		y.right = z.right;
        		y.right.parent = y;
        	}

        	rbTransplant(z, y);
        	y.left = z.left;
        	y.left.parent = y;
        	y.color = z.color;
        }
        
        if (y_orignal_color == 'B') {
        	balanceDelete(x);
        }
    }
    
    // Color the nodes
    private void rbTransplant(Node u, Node v) {
    	if (u.parent == null) {
    		root = v;
    	} else if (u == u.parent.left) {
    		u.parent.left = v;
    	} else {
    		u.parent.right = v;
    	}
    	v.parent = u.parent;
    }

    
    // Balance tree after deletion
    private void balanceDelete(Node node) {
    	Node s;
	  
    	// Case 3: Node is black 
    	while (node != root && node.color == 'B') {
		  
    		if (node == node.parent.left) { // left child of parent
    			s = node.parent.right;
			  
    			// 3.1 sibling is red 
    			if (s.color == 'R') { 
    				
    				s.color = 'B';
    				node.parent.color = 'R';
    				leftRotate(node.parent);
    				s = node.parent.right;
    				
    			} 
    			
    			// Sibling is black and...
				// 3.2 ...sibling's children are both black
    			if (s.left.color == 'B' && s.right.color == 'B') { 
    				s.color = 'R';
    				node = node.parent;
    				
    			} else {
    				// 3.3 ...sibling's right child is black
    				if (s.right.color == 'B') { 
    					s.left.color = 'B';
    					s.color = 'R';
    					rightRotate(s);
    					s = node.parent.right;
    				}
    				
    				// 3.4 ...sibling's right child is red.
    				s.color = node.parent.color;
    				node.parent.color = 'B';
    				s.right.color = 'B';
    				leftRotate(node.parent);
    				node = root;
    			}
    			
    		} else { // right child of parent 
    			s = node.parent.left;
    			
    			// Mirror 3.1 
    			if (s.color == 'R') {
    				s.color = 'B';
    				node.parent.color = 'R';
    				rightRotate(node.parent);
    				s = node.parent.left;
    			}

    			if (s.right.color == 'B' && s.right.color == 'B') {
    				s.color = 'R';
    				node = node.parent;
    			} else {
    				if (s.left.color == 'B') {
    					s.right.color = 'B';
    					s.color = 'R';
    					leftRotate(s);
    					s = node.parent.left;
    				}

    				s.color = node.parent.color;
    				node.parent.color = 'B';
    				s.left.color = 'B';
    				rightRotate(node.parent);
    				node = root;
    			}
    		}
    	}
    	node.color = 'B';
    }
  	
    // Pre-order Traversal
	public void preorder() {
    	preOrderHelper(this.root);
    }
	
	// Recursive function to perform pre-order traversal
	private void preOrderHelper(Node node) {
		if ( node == nullNode ) {
			return;
		}
		System.out.print(node.data + " ");
		preOrderHelper(node.left);
		preOrderHelper(node.right);
	}

  	// In-order Traversal
	public void inorder() {
    	inOrderHelper(this.root);
    }
	
	// Recursive function to perform in-order traversal
  	private void inOrderHelper(Node node) {
  		if ( node == nullNode ) {
			return;
		}
		inOrderHelper(node.left);
		System.out.print(node.data + " ");
		inOrderHelper(node.right);
  	}

  	// Post-order Traversal
  	public void postorder() {
  		postOrderHelper(this.root);
  	}
  	
  	// Recursive function to perform post-order traversal
  	private void postOrderHelper(Node node) {
  		if ( node == nullNode ) {
			return;
		}
		postOrderHelper(node.left);
		postOrderHelper(node.right);
		System.out.print(node.data + " ");
  	}
}