package lab2;

import plotter.SimulationPlotter;

public class RabbitSimApp {
	
	public static void main(String[] Args) {
		SimulationPlotter plotter = new SimulationPlotter();
		RabbitModel myModel = new RabbitModel();
		plotter.simulate(myModel);
	}

}
