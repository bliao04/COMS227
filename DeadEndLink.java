package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;
/**
 * Models a link that connects a single path to nothing. getConnectedPoint() returns null and shiftPoints() does nothing.
 * @author Brandon Liao 
 *
 */
public class DeadEndLink extends AbstractLink implements Crossable {
	
	public DeadEndLink() {
	}

	@Override
	public void shiftPoints(PositionVector positionVector) {
		// Does nothing.
	}

	@Override
	public Point getConnectedPoint(Point point) {
		// Does nothing. 
		return null;
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
		// Models a link between an endpoint and a path, so there is only one path. 
		return 1;
	}

	
}
