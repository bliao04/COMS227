package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;

/**
 * Models a link between exactly two paths. When the train reaches the endpoint of one of the paths it passes to the endpoint of the other path next.
 * @author Brandon Liao 
 * 
 */
public class CouplingLink extends AbstractLink implements Crossable{
	
	/**
	 * Instance variable tracking one of the endpoints of the path in the link. 
	 */
	private Point endpoint1;
	
	/**
	 * Instance variable tracking the other one of the endpoints of the path in the link. 
	 */
	private Point endpoint2;

	/**
	 * Creates a new CouplingLink that connects the two given endpoints.
	 * @param endpoint1
	 * @param endpoint2
	 */
	public CouplingLink (Point endpoint1, Point endpoint2) {
		this.endpoint1 = endpoint1;
		this.endpoint2 = endpoint2;
		
	}

	@Override
	public void shiftPoints(PositionVector positionVector) {
		super.shiftPoints(positionVector);
	}

	@Override
	public Point getConnectedPoint(Point point) {
		// Coupling link models a link between exactly two paths, if the given point is at one endpoint, the connected point is the other endpoint in the link. 
		if (point.equals(endpoint1)) {
			return endpoint2;
		} else if (point.equals(endpoint2)) {
			return endpoint1;
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
		// Models a link between exactly two paths, so there are only two paths. 
		return 2;
	}

}
