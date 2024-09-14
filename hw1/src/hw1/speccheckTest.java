package hw1;

public class speccheckTest 
{
	public static void main(String[] Args)
	{
		
		CameraBattery cb2 = new CameraBattery(1000,1500);
		cb2.moveBatteryCamera();
		cb2.drain(100);
		cb2.drain(200);
		System.out.println(cb2.getTotalDrain());
		
	}
}
