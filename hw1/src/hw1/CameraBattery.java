package hw1;

/**
 * models a removable and rechargeable camera battery. The battery can be charged both directly by the 
 * camera when connect to a USB port and by an external "wall wart" battery charger. However, 
 * the battery can only be in one place at a time, either connected to the camera, connected to the 
 * external charger, or disconnect for any device. The battery has a maximum capacity to which it 
 * can be charged. The rate of charge when connect to the camera is fixed, while the rate of charge 
 * when connected to the external charger is determined by the charger setting. The charger setting 
 * is a number between 0 inclusive and NUM_CHARGER_SETTINGS exclusive. The charger 
 * setting is set by the user repeatedly pressing a single settings button. Each press the setting 
 * increases by one. However, when the maximum setting is reached the next button press puts it 
 * back to setting 0.
 * @author Brandon Liao
 */
public class CameraBattery 
{
	
	/** 
	 * Number of external charger settings. Settings are number between 0 inclusive and 4 exclusive.
	 */
	public static final int NUM_CHARGER_SETTINGS = 4;
	
	/**
	 * A constant used in calculating the charge rate of the external charger. 
	 */
	public static final double CHARGE_RATE = 2.0;
	
	/**
	 * Default power consumption of the camera at the start of simulation. 
	 */
	public static final double DEFAULT_CAMERA_POWER_CONSUMPTION = 1.0;
	
	/**
	 * Variable holding the power consumption of the camera. 
	 */
	private double CAMERA_POWER_CONSUMPTION = DEFAULT_CAMERA_POWER_CONSUMPTION;
	
	/**
	 * Variable holding the charge of the camera. 
	 */
	private double CAMERA_CHARGE;
	
	/**
	 * Variable holding the charge of the camera battery.
	 */
	private double BATTERY_CHARGE;
	
	/**
	 * Variable representing the max capacity of charge that the camera battery can hold. 
	 */
	private double BATTERY_CAPACITY;
	
	/**
	 * Variable representing the position of the camera battery, if the camera battery is in or out of the camera.
	 */
	private double BATTERY_POSITION;
	
	/**
	 * Variable representing the total amount of drain since the creation or last reset of the camera battery. 
	 */
	private double TOTAL_DRAIN;
	
	/**
	 * Variable representing the current setting of the external charger. 
	 */
	private int CHARGER_SETTING = 0;
		
	
	/**
	 * Constructs a new camera battery simulation. The battery should start disconnected from both the 
	 * camera and the external charger. The starting battery charge and maximum charge capacity are 
	 * given. If the starting charge exceeds the batteries capacity, the batteries charge is set to its 
	 * capacity. The external charger starts at setting 0. 
	 * @param batteryStartingCharge
	 * @param batteryCapacity
	 */
	public CameraBattery(double batteryStartingCharge, double batteryCapacity)
	{
		BATTERY_CHARGE = Math.min(batteryStartingCharge, batteryCapacity);
		BATTERY_CAPACITY = batteryCapacity;
	}
	
	/**
	 * Indicates the user has pressed the setting button one time on the external charger. The charge 
	 * setting increments by one or if already at the maximum setting wraps around to setting 0. 
	 */
	public void buttonPress()
	{
		CHARGER_SETTING += 1;
		CHARGER_SETTING %= 4;
	}
	
	/**
	 * Charges the battery connected to the camera (assuming it is connected) for a given number of 
	 * minutes. The amount of charging in milliamp-hours (mAh) is the number minutes times the 
	 * CHARGE_RATE constant. However, charging cannot exceed the capacity of the battery 
	 * connected to the camera, or no charging if the battery is not connected. The method returns the 
	 * actual amount the battery has been charged.
	 * @param minutes
	 * @return
	 */
	public double cameraCharge(double minutes)
	{;
		double CAMERA_CHARGE_AMOUNT;
		CAMERA_CHARGE_AMOUNT = Math.min(minutes * CHARGE_RATE, BATTERY_CAPACITY-BATTERY_CHARGE) * BATTERY_POSITION;
		CAMERA_CHARGE = Math.min(CAMERA_CHARGE + CAMERA_CHARGE_AMOUNT, BATTERY_CAPACITY);
		BATTERY_CHARGE = Math.min(BATTERY_CHARGE + CAMERA_CHARGE_AMOUNT, BATTERY_CAPACITY);
		return CAMERA_CHARGE_AMOUNT; 
	}
	
	/**
	 * Drains the battery connected to the camera (assuming it is connected) for a given number of 
	 * minutes. The amount of drain in milliamp-hours (mAh) is the number of minutes times the 
	 * cameras power consumption. However, the amount cannot exceed the amount of charge 
	 * contained in the battery assuming it is connected to the camera, or no amount drain if the battery 
	 * is not connected. The method returns the actual amount drain from the battery.
	 * @param minutes
	 * @return
	 */
	public double drain(double minutes)
	{
		double BATTERY_DRAIN_AMOUNT;
		BATTERY_DRAIN_AMOUNT = Math.min(minutes * CAMERA_POWER_CONSUMPTION, CAMERA_CHARGE) * BATTERY_POSITION;
		BATTERY_CHARGE = Math.max(0, BATTERY_CHARGE - BATTERY_DRAIN_AMOUNT);
		CAMERA_CHARGE = Math.max(0, CAMERA_CHARGE - BATTERY_DRAIN_AMOUNT);
		TOTAL_DRAIN += BATTERY_DRAIN_AMOUNT;
		return BATTERY_DRAIN_AMOUNT;
	}
	
	/**
	 * Charges the battery connected to the external charger (assuming it is connected) for a given 
	 * number of minutes. The amount of charging in milliamp-hours (mAh) is the number minutes 
	 * times the charger setting (a number between 0 inclusive and NUM_CHARGER_SETTINGS 
	 * exclusive) the CHARGE_RATE constant. However, charging cannot exceed the capacity of the 
	 * battery connected to the camera, or no charging if the battery is not connected. The method 
	 * returns the actual amount the battery has been charged. 
	 * @param minutes
	 * @return
	 */
	public double externalCharge(double minutes)
	{
		double EXTERNAL_CHARGE_AMOUNT;
		EXTERNAL_CHARGE_AMOUNT = Math.min(minutes * CHARGER_SETTING * CHARGE_RATE, BATTERY_CAPACITY-BATTERY_CHARGE);
		BATTERY_CHARGE = Math.min(BATTERY_CHARGE + EXTERNAL_CHARGE_AMOUNT, BATTERY_CAPACITY);
		return EXTERNAL_CHARGE_AMOUNT;
	}
	
	/**
	 * Reset the battery monitoring system by setting the total battery drain count back to 0. 
	 */
	public void resetBatteryMonitor()
	{
		TOTAL_DRAIN = 0;
	}
	
	/**
	 * Get the battery's capacity. 
	 * @return
	 */
	public double getBatteryCapacity()
	{
		return BATTERY_CAPACITY;
	}
	
	/**
	 * Get the battery's current charge. 
	 * @return
	 */
	public double getBatteryCharge()
	{
		return BATTERY_CHARGE;
	}
	
	/**
	 * Get the current charge of the camera's battery. 
	 * @return
	 */
	public double getCameraCharge()
	{
		return CAMERA_CHARGE;
	}
	
	/**
	 * Get the power consumption of the camera. 
	 * @return
	 */
	public double getCameraPowerConsumption()
	{
		return CAMERA_POWER_CONSUMPTION;
	}
	
	/**
	 * Get the external charger setting. 
	 * @return
	 */
	public int getChargerSetting()
	{
		return CHARGER_SETTING;
	}
	
	/**
	 * Get the total amount of power drained from the battery since the last time the battery monitor 
	 * was started or reset. 
	 * @return
	 */
	public double getTotalDrain() 
	{
		return TOTAL_DRAIN;
	}
	
	/**
	 * Move the battery to the external charger. Updates any variables as needed to represent the move. 
	 */
	public void moveBatteryExternal()
	{
		BATTERY_POSITION = 0;
	}
	
	/**
	 * Move the battery to the camera. Updates any variables as needed to represent the move. 
	 */
	public void moveBatteryCamera()
	{
		BATTERY_POSITION = 1;
		CAMERA_CHARGE = BATTERY_CHARGE;
	}
	
	/**
	 * Remove the battery from either the camera or external charger. Updates any variables as needed 
	 * to represent the removal. 
	 */
	public void removeBattery()
	{
		BATTERY_POSITION = 0;
		CAMERA_CHARGE = 0;
	}
	
	/**
	 * Set the power consumption of the camera. 
	 * @param cameraPowerConsumption
	 */
	public void setCameraPowerConsumption(double cameraPowerConsumption)
	{
		CAMERA_POWER_CONSUMPTION = cameraPowerConsumption;
	}

}
