/**
 * A generic Exception handling class used to handle various exceptions within this assignment
 * @author Zeb Aslam 108041523
 *
 */
public class GenericError extends Error {
//used this to satisfy compiler	
private static final long serialVersionUID = -6690154863361458021L;
/**
 * The constructor takes in a string that will be used to generate the error message using the super constructor from the error class. 
 * @param x -The value that the message will have 
 */
public GenericError(String x){	
super(x);
}
}
