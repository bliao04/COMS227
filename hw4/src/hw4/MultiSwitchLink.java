package hw4;

import api.Crossable;
import api.Point;
import api.PointPair;
import api.PositionVector;

/**
 * Models a link with a minimum of 2 to a maximum of 6 paths.
 * The connections are provided to the constructor as an array of 2 to 6 point pairs. Each point pair describes the two endpoints where the train comes from and goes to.
 * The turn cannot be modified when the train is in the crossing.
 * @author Brandon Liao 
 *
 */
public class MultiSwitchLink extends AbstractLink implements Crossable{
	
	/**
	 * Array of PointPair objects, each PointPair object indicates where the train comes from and goes to and also describes the connection. 
	 */
	private PointPair[] connectArr;
	
	/**
	 * Instance variable tracking if the train is currently in a crossing or not. 
	 */
	private boolean crossing;
	
	/**
	 * Creates a new MultiSwitchLink. The given array of point pairs describes the connection. Each PointPair indicates where the train comes from and goes to.
	 * @param connections
	 */
	public MultiSwitchLink(PointPair[] connections) {
		connectArr = connections;
	}
	
	/**
	 * Updates the connection point pair at the given index. 
	 * The connection cannot be modified (method does nothing) when the train is currently in (entered but not exited) the crossing.
	 * @param pointPair
	 * @param index
	 */
	public void switchConnection(PointPair pointPair, int index) {
		// Check to see if the train is crossing if not update the index of the PointPair array. 
		if (!(crossing)) {
			connectArr[index] = pointPair;
		}
	}

	@Override
	public void shiftPoints(PositionVector positionVector) {
		super.shiftPoints(positionVector);
	}

	@Override
	public Point getConnectedPoint(Point point) {
		// Iterates through the array until it finds the given point in the array. 
		for (int i = 0; i < connectArr.length; i++) {
			// Variables tracking the current iterated point A and point B in the point pair in the array.
			Point currentPointA = connectArr[i].getPointA();
			Point currentPointB = connectArr[i].getPointB();
			// Check to see if the given point is equal to the current point A iteration or current point B iteration.
			if (currentPointA.equals(point)) {
				// If the given point is equal to the current iterated point A, the connected point must be the current point B.
				return connectArr[i].getPointB();
			} else if (currentPointB.equals(point)) {
				// If the given point is equal to the current iterated point B, the connect point must be the current point A. 
				return connectArr[i].getPointA();
			}
		}
		return null;
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
		int numPaths = 0;
		// Iterates through the PointPair arrays length, the amount of PointPairs is the same amount of paths. 
		for (int i = 0; i < connectArr.length; i++) {
			numPaths += 1;
		}
		return numPaths;
	}

}
