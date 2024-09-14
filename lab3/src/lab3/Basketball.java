package lab3;

public class Basketball 
{
	private double basketballDiamater;
	
	private boolean isInflated;
	
	public Basketball(double givenDiamater)
	{
		basketballDiamater = givenDiamater;
	}
	
	public boolean isDribbleable()
	{
		return isInflated;
	}
	
	public double getDiameter()
	{
		return basketballDiamater;
	}
	
	public double getCircumfrence()
	{
		return 0;
	}
	
	public void inflate()
	{
		isInflated = true; 
	}
}
