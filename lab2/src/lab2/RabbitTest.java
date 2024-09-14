package lab2;

public class RabbitTest {
	
	public static void main (String[] Args)
	{
		RabbitModel model = new RabbitModel();
		System.out.println(model.getPopulation());
	    System.out.println("Expected 500");
	    model.simulateYear();
	    System.out.println(model.getPopulation());
	    System.out.println("Expected 250");
	    model.simulateYear();
	    System.out.println(model.getPopulation());
	    System.out.println("Expected 125");
	    model.simulateYear();
	    System.out.println(model.getPopulation());
	    model.simulateYear();
	    System.out.println(model.getPopulation());
	    model.simulateYear();
	    System.out.println(model.getPopulation());

	}

}
