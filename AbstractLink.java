package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;
/**
 * Superclass for other link classes that implements the Crossable interface.
 * @author Brandon Liao
 * 
 */
public abstract class AbstractLink implements Crossable {
	
	public AbstractLink() {
	}
	
	@Override
	/**
	 * Shift the location of the given positionVector to be between the next pair of points. 
	 */
	public void shiftPoints(PositionVector positionVector) {
		// Set the positionVectors point A to the position of the point connected to point B (point B being the point point A was traveling toward).
		positionVector.setPointA(getConnectedPoint(positionVector.getPointB()));
		// Check to see if index of point A is now at a highpoint in the path.
		if (positionVector.getPointA().getPath().getHighpoint()==positionVector.getPointA()) {
			// If point A is at the highpoint in the path, B cannot go higher so set it at the index below point A. 
			positionVector.setPointB(positionVector.getPointA().getPath().getPointByIndex(positionVector.getPointA().getPointIndex()-1));
			// Check to see if the index of point A is now at a lowpoint in the path.
		} else if (positionVector.getPointA().getPath().getLowpoint()==positionVector.getPointA()) {
			// If point A is at the lowpoint in the path, B cannot go lower so set it at the index above point A.
			positionVector.setPointB(positionVector.getPointA().getPath().getPointByIndex(positionVector.getPointA().getPointIndex() + 1));
		}
	}

	@Override
	/**
	 * Gets the point that is connected to the given point by link, returns null if there is no connected point.
	 */
	public Point getConnectedPoint(Point point) {
		return null;
	}
	
	@Override
	/**
	 * This method is called by the simulation in order to indicate that a train has entered the crossing.
	 */
	public void trainEnteredCrossing() {
	}

	@Override
	/**
	 * This method is called by the simulation in order to indicate that a train has exited the crossing.
	 */
	public void trainExitedCrossing() {
	}

	@Override
	/**
	 * Gets the total number of paths connected by a link. 
	 */
	public int getNumPaths() {
		return 0;
	}
	
}
