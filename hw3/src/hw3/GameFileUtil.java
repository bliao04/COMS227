package hw3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import api.Tile;

/**
 * Utility class with static methods for saving and loading game files.
 * @author Brandon Liao
 */
public class GameFileUtil {
	/**
	 * Saves the current game state to a file at the given file path.
	 * <p>
	 * The format of the file is one line of game data followed by multiple lines of
	 * game grid. The first line contains the: width, height, minimum tile level,
	 * maximum tile level, and score. The grid is represented by tile levels. The
	 * conversion to tile values is 2^level, for example, 1 is 2, 2 is 4, 3 is 8, 4
	 * is 16, etc. The following is an example:
	 * 
	 * <pre>
	 * 5 8 1 4 100
	 * 1 1 2 3 1
	 * 2 3 3 1 3
	 * 3 3 1 2 2
	 * 3 1 1 3 1
	 * 2 1 3 1 2
	 * 2 1 1 3 1
	 * 4 1 3 1 1
	 * 1 3 3 3 3
	 * </pre>
	 * 
	 * @param filePath the path of the file to save
	 * @param game     the game to save
	 */
	public static void save(String filePath, ConnectGame game) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			writer.write(((Integer)game.getGrid().getWidth()).toString());
			writer.write(" ");
			writer.write(((Integer)game.getGrid().getHeight()).toString());
			writer.write(" ");
			writer.write(((Integer)game.getMinTileLevel()).toString());
			writer.write(" ");
			writer.write(((Integer)game.getMaxTileLevel()).toString());
			writer.write(" ");
			writer.write(((Integer)(int)game.getScore()).toString());
			writer.write("\n");
			for(int i = 0; i < game.getGrid().getHeight() - 1; i++) {
				for(int j = 0; j < game.getGrid().getWidth() - 1; j++)
				{
					writer.write(((Integer)game.getGrid().getTile(j, i).getLevel()).toString());
					writer.write(" ");
				}
				writer.write(((Integer)game.getGrid().getTile(game.getGrid().getWidth() - 1, i).getLevel()).toString());
				writer.write("\n");
			}
			for(int j = 0; j < game.getGrid().getWidth() - 1; j++) {
				writer.write(((Integer)game.getGrid().getTile(j, game.getGrid().getHeight() - 1).getLevel()).toString());
				writer.write(" ");
			}
			writer.write(((Integer)game.getGrid().getTile(game.getGrid().getWidth() - 1, game.getGrid().getHeight() - 1).getLevel()).toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads the file at the given file path into the given game object. When the
	 * method returns the game object has been modified to represent the loaded
	 * game.
	 * <p>
	 * See the save() method for the specification of the file format.
	 * 
	 * @param filePath the path of the file to load
	 * @param game     the game to modify
	 * @throws FileNotFoundException 
	 */
	public static void load(String filePath, ConnectGame game) throws FileNotFoundException 
	{
		// Creates a new file variable and a scanner in order to scan the file.
		File gameFile = new File(filePath);
		Scanner scnr = new Scanner(gameFile);
		int width = scnr.nextInt();
		int height = scnr.nextInt();
		game.setMinTileLevel(scnr.nextInt());
		game.setMaxTileLevel(scnr.nextInt());
		game.setScore(scnr.nextLong());
		Grid connectGrid = new Grid(width, height);
		Tile loadTile;
		// Accesses the acid through for loops and iterates through re-loading the game grid.
		for(int i = 0; i < height - 1; i++)
		{
			for(int j = 0; j < width - 1; j++)
			{
				loadTile = new Tile(scnr.nextInt());
				connectGrid.setTile(loadTile, j, i);
			}
		}
		game.setGrid(connectGrid);
	}
}
