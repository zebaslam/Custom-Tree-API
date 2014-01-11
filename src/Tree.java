/**
 * This class creates the tree using a root and establishes methods to add nodes to the left, middle and right and get node references <br>
 * based on the label of the node. It also has a method that conducts preorder from the root.
 * @author Zeb Aslam Id#108041523
 *
 */
import java.util.Scanner;

public class Tree extends TreeNode {
	// The node representing the root
	protected TreeNode root;
	Scanner input = new Scanner(System.in);

	/**
	 * The constructor initializes all the values for the root node.
	 * 
	 * @param x
	 *            - A node representing the root
	 * @param label
	 *            - Sets the label of the root node to this string
	 * @param prompt
	 *            - Sets the prompt of the root node to this string
	 * @param message
	 *            -sets the message of the root node to this string
	 */
	public Tree(TreeNode x, String label, String prompt, String message) {
		root = x;
		root.setLabel(label);
		root.setPrompt(prompt);
		root.setMessage(message);
	}

	/**
	 * This void method calls the preOrderPrint method in the TreeNode class
	 * using the root.
	 */
	public void PreOrder() {
		if (root == null)
			return;
		root.preorderPrint();
	}

	/**
	 * This overloaded method calls the GetNodeReference method for the given
	 * label using the root
	 * 
	 * @param label
	 *            The label who's node you're trying to find
	 * @return a TreeNode that refers to the label youre searching for
	 */
	public TreeNode getNodeReference(String label) {
		return getNodeReference(label, root);
	}

	/**
	 * This method searches the tree for a node that refers to the particular
	 * label
	 * 
	 * @param label
	 *            The label of the node you are searching for
	 * @param node
	 *            The node you will begin searching the tree from (this will be
	 *            done using the overloaded method using the root)
	 * @return A tree node representing the node that label refers to
	 */
	public TreeNode getNodeReference(String label, TreeNode node) {
		if (node != null) {
			if (node.getLabel().equals(label))
				return node;
			else {

				TreeNode temp;
				temp = getNodeReference(label, node.getLeft());
				if (temp != null)
					return temp;


				if (node != null)
					temp = getNodeReference(label, node.getMiddle());
				if (temp != null)
					return temp;


				if (node != null)
					temp = getNodeReference(label, node.getRight());
				if (temp != null)
					return temp;

				return null;
			}

		} else {
			return null;
		}
	}

	/**
	 * This method is used to add a node to the tree, adding to the left first
	 * then the middle and then the right
	 * 
	 * @param label
	 *            The label of the node youre trying to add
	 * @param prompt
	 *            The prompt of the node youre trying to add
	 * @param message
	 *            The message of the node youre trying to add
	 * @param parentLabel
	 *            The parent label is going to be used in the getNodeReference
	 *            to find parent node
	 * @return
	 */
	public boolean addNode(String label, String prompt, String message,
			String parentLabel) {
		boolean addition = false;
		TreeNode node = new TreeNode();
		node.setLabel(label);
		node.setPrompt(prompt);
		node.setMessage(message);
		TreeNode parent = getNodeReference(parentLabel);
		if (parent.getLeft() == null) {
			parent.setLeft(node);
			addition = true;
		} else if (parent.getMiddle() == null) {
			parent.setMiddle(node);
			addition = true;
		} else {
			parent.setRight(node);
			addition = true;
		}
		return addition;

	}

	/**
	 * This method is used to construct a menu of options using the labels of
	 * the left, middle and right child. <br>
	 * This method also takes in user input as an integer from the scanner class
	 * 
	 * @param left
	 *            So long as this is not null, print it as an option
	 * @param middle
	 *            So long as this is not null, print it as an option
	 * @param right
	 *            So long as this is not null, print it as an option
	 * @return an integer value representing the choice of the user
	 */
	private int helper(String left, String middle, String right) {
		if (left != null && !left.isEmpty()) {
			System.out.println("1) " + left);
		}
		if (middle != null && !middle.isEmpty()) {
			System.out.println("2) " + middle);
		}
		if (right != null && !right.isEmpty()) {
			System.out.println("3) " + right);
		}
		System.out.println("0) Exit Session");
		int num = input.nextInt();
		return num;
	}

	/**
	 * This method creates the help session for the user based on the tree. It
	 * creates a node that begins at the root and traverses the tree <br>
	 * and prints out valid message for the user. It also uses the
	 * getNodeReference to get the label that refers to the parent <br>
	 * and loops so long as it doesn't equal null. A switch statement determines
	 * which node will be referred to next based on the user's choice.
	 */
	public void beginSession() {
		TreeNode node = root;
		String leftLabel = null;
		String middleLabel = null;
		String rightLabel = null;
		String leftPrompt = null;
		String middlePrompt = null;
		String rightPrompt = null;
		boolean flag = true;
		while (node != null && flag == true) {
			if (node.isLeaf() == true) {
				System.out.println(node.getMessage());
				break;
			} else {
				System.out.println(node.getMessage());
			}
			if (node.getLeft() != null) {
				leftLabel = node.getLeft().getLabel();
				leftPrompt = node.getLeft().getPrompt();
			}
			else{
				leftPrompt=null;
			}
			if (node.getMiddle() != null) {
				middleLabel = node.getMiddle().getLabel();
				middlePrompt = node.getMiddle().getPrompt();
			}
			else{
				middlePrompt=null;
			}
			if (node.getRight() != null) {
				rightLabel = node.getRight().getLabel();
				rightPrompt = node.getRight().getPrompt();
			}
			else{
				rightPrompt=null;
			}
			int choice = helper(leftPrompt, middlePrompt, rightPrompt);
			switch (choice) {
			case 0:
				System.out.println("Terminating");
				return;
			case 1:
				node = getNodeReference(leftLabel);
				flag = true;
				break;
			case 2:
				node = getNodeReference(middleLabel);
				flag = true;
				break;
			case 3:
				node = getNodeReference(rightLabel);
				flag = true;
				break;
			default:
				throw new GenericError(
						"This is not a valid menu option. The program will terminate.");
			}

		}
	}
}
