package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;

/**
 * Models a switchable link with three paths. A boolean turn determines which path trains take. 
 * By default turn is set to false. The following figure shows the three paths labeled A, B, and C.
 * @author Brandon Liao 
 *
 */
public class SwitchLink extends AbstractLink implements Crossable{
	
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
	 * Instance variable tracking if the train is turning or not. 
	 */
    private boolean trainTurning;
    
    /**
     * Instance variable tracking if the train is currently in a crossing or not. 
     */
    private boolean crossing;
	
    /**
     * The given endpoints correspond to the paths
     * @param endpointA
     * @param endpointB
     * @param endpointC
     */
	public SwitchLink(Point endpointA, Point endpointB, Point endpointC) {
		this.endpointA = endpointA;
		this.endpointB = endpointB;
		this.endpointC = endpointC;
		trainTurning = false; 
		crossing = false;
	}
	
	/**
	 * Updates the link to turn or not turn. The turn cannot be modified (do nothing) when the train is currently in (entered but not exited) the crossing.
	 * @param turn
	 */
	public void setTurn(boolean turn) {
		// Check to see if the train is crossing, if not, the train can be set to the given turn boolean. 
		if (!(crossing)) {
			trainTurning = turn;
		}
	}

	@Override
	public void shiftPoints(PositionVector positionVector) {
		super.shiftPoints(positionVector);
	}

	@Override
	public Point getConnectedPoint(Point point) {
		// Check to see if the given point is either of the path endpoints. 
		if (point.equals(endpointA)) {
			// If the train is currently at path A, check to see if it is turning. 
			if (trainTurning) {
				// If the train is turning the train is going to path C. 
				return endpointC;
			} else {
				// If the train is not turning the train going to path B.
				return endpointB;
			} 
		} else if (point.equals(endpointB)) {
			// If the train is at point B, the train is going to path A. 
			return endpointA;
		} else if (point.equals(endpointC)) {
			// If the train is at point C, the train is going to path A. 
			return endpointA;
		} else {
			return null;
		}
	}

	@Override
	public void trainEnteredCrossing() {
		crossing = true;
	}

	@Override
	public void trainExitedCrossing() {
		crossing = false; 
	}

	@Override
	public int getNumPaths() {
		// SwitchLink models a switchable link between three paths. 
		return 3;
	}

}
