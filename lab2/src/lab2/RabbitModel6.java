package lab2;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModel6
{
  private int rabbitPopulation;
  private int lastPop;
  private int lastlastPop;
  
  /**
   * Constructs a new RabbitModel.
   */
  public RabbitModel6()
  {
    rabbitPopulation = 0;
    lastPop = 1;
    lastlastPop = 0;
  }  
 
  /**
   * Returns the current number of rabbits.
   * @return
   *   current rabbit population
   */
  public int getPopulation()
  {
    // TODO - returns a dummy value so code will compile
    return rabbitPopulation;
  }
  
  /**
   * Updates the population to simulate the
   * passing of one year.
   */
  public void simulateYear()
  {
	rabbitPopulation = lastPop + lastlastPop;
	lastlastPop = lastPop;
	lastPop = rabbitPopulation;
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
    rabbitPopulation = 0;
  }
}
