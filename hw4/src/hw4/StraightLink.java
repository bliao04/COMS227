package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;

/**
 * Models a fixed link with three paths.
 * The paths A and B run in the same direction and C branches away.
 * @author Brandon Liao 
 *
 */
public class StraightLink extends AbstractLink implements Crossable{
	
	/**
	 * Instance variable tracking the endpoint of path A. 
	 */
	private Point endpointA;
	
	/**
	 * Instance variable tracking the endpoint of path B.
	 */
	private Point endpointB;
	
	/**
	 * Instance variable tracking the endpoint of path C. 
	 */
	private Point endpointC;

    /**
     * Creates a new StraightLink. The given endpoints correspond to the paths. 
     * @param endpointA the endpoint of path A
     * @param endpointB the endpoint of path B
     * @param endpointC the endpoint of path C
     */
    public StraightLink(Point endpointA, Point endpointB, Point endpointC) {
    	this.endpointA = endpointA;
    	this.endpointB = endpointB;
    	this.endpointC = endpointC; 
    }
    
    @Override
    public void shiftPoints(PositionVector positionVector) {
        super.shiftPoints(positionVector);
    }

    @Override
    public Point getConnectedPoint(Point point) {
    	// Check to see if the given point equals any of the endpoints of the paths. 
    	if (endpointA.equals(point)) {
    		// If the given point is at endpoint A the connected endpoint is endpoint B.
            return endpointB;
        } else if (endpointB.equals(point)) {
        	// If the given point is at endpoint B the connected endpoint is endpoint A. 
            return endpointA;
        } else if (endpointC.equals(point)) {
        	// If the given point is at endpoint C the connected endpoint is endpoint A. 
            return endpointA;
        } else {
            return null;
        }
    }

    @Override
    public void trainEnteredCrossing() {
    	// Does nothing. 
    }

    @Override
    public void trainExitedCrossing() {
    	// Does nothing. 
    }
    
    @Override
    public int getNumPaths() {
    	// StraightLink models a link with three paths. 
        return 3;
    }


}
