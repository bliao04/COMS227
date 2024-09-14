package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;

/**
 * Models a fixed link with three paths.
 * The paths A and B run in the same direction and C branches away.
 * @author Brandon Liaof
 *
 */
public class TurnLink extends AbstractLink implements Crossable{
	
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
	 * Creates a new TurnLink. The given endpoints correspond to the paths.
	 * @param endpointA
	 * @param endpointB
	 * @param endpointC
	 */
	public TurnLink (Point endpointA, Point endpointB, Point endpointC) {
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
		if (point.equals(endpointA)) {
			// If the given point is at endpoint A the connected endpoint is endpoint C. 
			return endpointC;
		} else if (point.equals(endpointB)) {
			// If the given point is at endpoint B the connected endpoint is endpoint A.
			return endpointA;
		} else if (point.equals(endpointC)) {
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
		// TurnLink models a link with 3 paths. 
		return 3;
	}

}
