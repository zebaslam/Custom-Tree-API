/**
 * This class is used to create the treenode class and has getters, setters, <br>
 * and methods to order nodes in a preorder fashion. 
 * @author Zeb Aslam Id#108041523
 *
 */
public class TreeNode {
	//The left treenode
	private TreeNode left;
	//the middle treenode
	private TreeNode middle;
	//the right treenode
	private TreeNode right;
	//the message string
	private String message;
	//the prompt string
	private String prompt;
	//the label string
	private String label;
	
/**
 * This method is used to print out the preorder traversal of the tree <br>
 * It checks to see if the left, middle and right nodes are null and if not, 
 * it makes a recursive call to each and prints those nodes
 */
	public void preorderPrint()
	{
	   System.out.println("Label: "+label+"\n"+"Prompt: "+prompt+ "\n"+"Message: "+message+"\n");
	   if (left != null)
	      left.preorderPrint( );  
	   if(middle!= null)
		   middle.preorderPrint();
	   if (right != null)
	      right.preorderPrint( );
	    
	} 
	
/**
 * This method returns the value of message 	
 * @return the value of message
 */
	public String getMessage() {
		return message;
	}
/**
 * This method is used to set the value of message
 * @param message the message value that is to be set
 */
	public void setMessage(String message) {
		this.message = message;
	}
/**
 * This method returns the value of prompt
 * @return the value of prompt
 */
	public String getPrompt() {
		return prompt;
	}
/**
 * Used to set the value of prompt
 * @param prompt- the prompt that is to be set
 */
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
/**
 * Constructor for the class that initializes all values to null
 */
	public TreeNode() {
		left = null;
		middle = null;
		right = null;
		prompt=null;
		message=null;
		label=null;
	}
/**
 * Used to return the label of the node
 * @return the value of label
 */
	public String getLabel() {
		return label;
	}
/**
 * This method is used to set the label for a node
 * @param label the string that is to be label is to be set to
 */
	public void setLabel(String label) {
		this.label = label;
	}
/**
 * Used to get the left node
 * @return	the left node
 */
	public TreeNode getLeft() {
		return left;
	}
/**
 * Set the left node
 * @param left take in a node to set as the left node
 */
	public void setLeft(TreeNode left) {
		this.left = left;
	}
/**
 * Return's the middle node
 * @return middle node
 */
	public TreeNode getMiddle() {
		return middle;
	}
/**
 * Used to set the middle node
 * @param middle node to be set as middle node
 */
	public void setMiddle(TreeNode middle) {
		this.middle = middle;
	}
/**
 * Gets the right node
 * @return the right node 
 */
	public TreeNode getRight() {
		return right;
	}
/**
 * Sets the right node
 * @param right	sets the right node
 */
	public void setRight(TreeNode right) {
		this.right = right;
	}
/**
 * Sets the label to x
 * @param x new value of label
 * @return string representing new label
 */
	private String label(String x) {
		label = x;
		return label;
	}

	/**
	 * This private method determines whether a node is a leaf
	 * 
	 * @return a boolean statement indicating whether the node is a leaf
	 */
	public boolean isLeaf() {
		return (left == null) && (middle == null) && (right == null);
	}

	/**
	 * Used to return the value of message
	 * @return a string representing message
	 */
	private String message() {
		message=getMessage();
		if (isLeaf() == true) {
			return message;
		} else {
			message = prompt();
			return message;
		}

	}
/**
 * used to return the prompt
 * @return value representing prompt
 */
	private String prompt() {
		if (left != null) {
			prompt += left.getPrompt() + "\n";
		}
		if (right != null) {
			prompt += right.getPrompt() + "\n";
		}
		if (middle != null) {
			prompt += middle.getPrompt() + "\n";
		}
		return prompt;
	}

}
