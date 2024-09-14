package hw1;

public class CBTest 
{
	public static void main(String[] Args) 
	{
		CameraBattery cb = new CameraBattery(1000,2000);
		System.out.println("Test1: ");
		System.out.println("Battery charge is " + cb.getBatteryCharge() + " expected 1000.0");
		System.out.println("Camera charge is " + cb.getCameraCharge() + " expected 0.0"); 		
		System.out.println(); 
		
		System.out.println("Test 2:"); 
		cb.moveBatteryCamera();
		System.out.println("Battery charge is " + cb.getBatteryCharge() + " expected 1000.0"); 
		System.out.println("Camera charge is " + cb.getCameraCharge() + " expected 1000.0"); 
		
		System.out.println("Total drain is " + cb.getTotalDrain() + " expected 0.0"); 
		System.out.println("Camera power consumption is " + cb.getCameraPowerConsumption() + " expected " + 1.0); 
		double drained = cb.drain(100.0); 
		System.out.println(); 
		System.out.println("Test 4:"); 
		System.out.println("Battery drained by " + drained + " expected 100.0"); 
		System.out.println("Battery charge is " + cb.getBatteryCharge() + " expected 900.0");
		System.out.println("Camera charge is " + cb.getCameraCharge() + " expected 900.0"); 
		System.out.println("Total drain is " + cb.getTotalDrain() + " expected 100.0"); 
 
	    drained = cb.drain(1000.0); 
	    System.out.println(); 
	    System.out.println("Test 5:"); 
	    System.out.println("Battery drained by " + drained + " expected 900.0"); 
	    System.out.println("Battery charge is " + cb.getBatteryCharge() + " expected 0.0"); 
	    System.out.println("Camera charge is " + cb.getCameraCharge() + " expected 0.0"); 
	    System.out.println("Total drain is " + cb.getTotalDrain() + " expected 1000.0"); 
	    
	    double charged = cb.cameraCharge(50.0); 
	    System.out.println(); 
	    System.out.println("Test 6:"); 
	    System.out.println("Battery charged by " + charged + " expected 100.0"); 
	    System.out.println("Battery charge is " + cb.getBatteryCharge() + " expected 100.0"); 
	    System.out.println("Camera charge is " + cb.getCameraCharge() + " expected 100.0");
	    
	    cb.removeBattery(); 
	    drained = cb.drain(50.0); 
	    System.out.println(); 
	    System.out.println("Test 7:"); 
	    System.out.println("Battery drained by " + drained + " expected 0.0"); 
	    System.out.println("Battery charge is " + cb.getBatteryCharge() + " expected 100.0"); 
	    System.out.println("Camera charge is " + cb.getCameraCharge() + " expected 0.0"); 
	    System.out.println("Total drain is " + cb.getTotalDrain() + " expected 1000.0"); 
	   
	    cb.cameraCharge(50.0); 
	    System.out.println(); 
	    System.out.println("Test 8:"); 
	    System.out.println("Battery charge is " + cb.getBatteryCharge() + " expected 100.0"); 
	    System.out.println("Camera charge is " + cb.getCameraCharge() + " expected 0.0"); 
	    System.out.println("Total drain is " + cb.getTotalDrain() + " expected 1000.0"); 
	    
	    cb.moveBatteryExternal(); 
	    cb.externalCharge(50.0); 
	    System.out.println(); 
	    System.out.println("Test 9:"); 
	    System.out.println("External charger setting is " + cb.getChargerSetting() + " exptected 0"); 
	    System.out.println("Battery charge is " + cb.getBatteryCharge() + " expected 100.0"); 
	    System.out.println("Camera charge is " + cb.getCameraCharge() + " expected 0.0"); 
	    System.out.println("Total drain is " + cb.getTotalDrain() + " expected 1000.0");
	}
}
