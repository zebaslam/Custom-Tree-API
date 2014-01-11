import java.util.Scanner;
import java.io.*;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * This class contains the main method. This class reads from a file and stores
 * its contents in an array and the array is used to implement the tree <br>
 * in a helper method called createTree. Another helper method is used to print
 * the menu when appropriate.
 * 
 * @author Zeb Aslam 108041523
 * 
 */

public class TreeDriver {
	/**
	 * This private method takes in the array containing the lines in the file
	 * and uses the trim method on them
	 * 
	 * @param x The array containing the lines of the file
	 * @return An array with string contents trimmed
	 */
	private static String[] trim(String[] x) {
		for (int i = 0; i < x.length; i++) {
			x[i] = x[i].trim();
		}
		return x;
	}

	/**
	 * This method creates the tree based on an array that stores the lines of a
	 * file. <br>
	 * <b>PreCondition: </b> The first three indices of the array cannot be
	 * empty.
	 * 
	 * @param  file the array that stores the contents of the file
	 * @return a Tree with all nodes from the file
	 * @throws GenericError
	 *             thrown when the root node's values do not exist (label,
	 *             prompt etc).
	 */
	private static Tree createTree(String[] file) throws GenericError {
		if (file[0].isEmpty() || file[1].isEmpty() || file[2].isEmpty()) {
			throw new GenericError(
					"\nCannot Create Tree from Empty File or File with no Root Data");
		}
		TreeNode root = new TreeNode();
		Tree tree = new Tree(root, file[0], file[1], file[2]);
		int index = 3;
		while (index < file.length && file[index] != null) {
			// Find the parent label
			String parentLabel = file[index].substring(0,
					file[index].length() - 2);
			// Find the number of children to add to the node
			int child = Character.getNumericValue(file[index].charAt(file[index].length()-1));
			// The next line in the array
			int oldIndex = index + 1;
			// This switch method used to skip to the next line that contains
			// the parent label and number of children
			// based on the number of children to be added to the node
			switch (child) {
			case 1:
				index += 4;
				break;
			case 2:
				index += 7;
				break;
			case 3:
				index += 10;
				break;
			}

			for (int i = 1; i <= child; i++) {
				// add left child
				if (i == 1) {
					tree.addNode(file[oldIndex], file[oldIndex+1], file[oldIndex+2], parentLabel);
					
				}
				// add middle
				if (i == 2) {
					
					tree.addNode(file[oldIndex + 3], file[oldIndex + 4],
							file[oldIndex + 5], parentLabel);
					
				}
				// add right
				if (i == 3) {
					tree.addNode(file[oldIndex + 6], file[oldIndex + 7],
							file[oldIndex + 8], parentLabel);
					
				}
			}
		}
		return tree;
	}

	/**
	 * This helper method is simply used to print the menu for the user at
	 * appropriate times
	 */
	private static void printMenu() {
		System.out.println("Please Enter a Menu Option");
		System.out.println("L- Load a Tree.");
		System.out.println("H- Begin a Help Session.");
		System.out.println("T- Traverse the Tree in Preorder");
		System.out.println("Q- Quit");
	}
/**
 * 
 * The main method displays the menu, gets user input, creates a tree, traverses it in preorder and starts help session
 * @throws FileNotFoundException
 * @throws IOException
 * @throws GenericError
 */

	public static void main(String[] args) throws FileNotFoundException,
			IOException, GenericError {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		BufferedReader br = null;
		printMenu();
		String choice = input.next().toUpperCase();
		// IF THE FILE CONTAINS MORE THAN 100 LINES THIS ARRAY SIZE MUST BE
		// EDITED!!
		String[] tree = new String[100];
		String lineContents;
		// convert to uppercase
		char preference = choice.charAt(0);
		// going to be used to resize the tree
		int counter = 0;
		// Used to see if the tree is loaded, if the user tries an option
		// without loading the tree first, an exception is thrown.
		boolean treeLoaded = false;
		// Used to loop the menu options after each command is completed
		boolean quit = false;
		//the  name of the file
		String name = "";
		//create a null tree
		Tree theTree = null;
		while (quit != true) {
			switch (preference) {
			default:
				throw new GenericError(
						"\nInvalid Menu Option, Program will Terminate");
			case 'Q':
				System.out
						.println("Thank you for using our automated help services!");
				System.exit(0);
				quit = true;
				break;
			case 'L':
				System.out.println("Enter the file name>");
				name = input.next();
				if (name.length() > 20) {
					throw new GenericError(
							"\nThe length of the file name cannot exceed 20 characters");
				}
				br = new BufferedReader(new FileReader(name));
				while ((lineContents = br.readLine()) != null) {
					if (lineContents.trim().length() <= 0)
						continue;
					if (lineContents.length() > 60) {
						throw new GenericError(
								"\nError: Each line in the file must be less than 60 characters");
					}
					{
						tree[0] = lineContents.trim();
						counter++;
					}

					for (int i = 1; i < tree.length; i++) {
						tree[i] = br.readLine();
						if (tree[i] != null)
							counter++;
					}

				}
	
				String[] resizedTree = new String[counter];
				for (int i = 0; i < resizedTree.length; i++) {
					resizedTree[i] = tree[i];
				}
				resizedTree = trim(resizedTree);
				br.close();
				theTree = createTree(resizedTree);
				System.out.println("Successfully created the tree!!!!");
				treeLoaded = true;
				System.out.println("");
				printMenu();
				choice = input.next().toUpperCase();
				preference = choice.charAt(0);
				quit = false;
				counter = 0;
				break;
			case 'T':
				if (treeLoaded == false) {
					throw new GenericError(
							"\nYou must create the tree before you can traverse it!");
				}
				theTree.root.preorderPrint();
				System.out.println("");
				printMenu();
				choice = input.next().toUpperCase();
				preference = choice.charAt(0);
				quit = false;
				break;
			case 'H':
				if (treeLoaded == false) {
					throw new GenericError(
							"\nYou must create the tree before you can have a help session!");
				}
				theTree.beginSession();
				System.out.println("");
				printMenu();
				choice = input.next().toUpperCase();
				preference = choice.charAt(0);
				quit = false;
				break;
			}
		}

	}
}
