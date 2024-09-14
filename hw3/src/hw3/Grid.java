package hw3;

import api.Tile;

/**
 * Represents the game's grid.
 * @author Brandon Liao 
 */
public class Grid {
	
	/** 
	 * Instance variable that creates a new 2D array containing tile objects, represents the games grid.
	 */
	private Tile[][] gridArray;
	
	/**
	 * Instance variable tracking the grids width.
	 */
	private int gridWidth;
	
	/**
	 * Instance variable tracking the grids height.
	 */
	private int gridHeight;
	
	/**
	 * Creates a new grid.
	 * 
	 * @param width  number of columns
	 * @param height number of rows
	 */
	public Grid(int width, int height) {
		gridWidth = width;
		gridHeight = height; 
		gridArray = new Tile[width][height];
	}

	/**
	 * Get the grid's width.
	 * 
	 * @return width
	 */
	public int getWidth() {
		return gridWidth;
	}

	/**
	 * Get the grid's height.
	 * 
	 * @return height
	 */
	public int getHeight() {
		return gridHeight;
	}

	/**
	 * Gets the tile for the given column and row.
	 * 
	 * @param x the column
	 * @param y the row
	 * @return
	 */
	public Tile getTile(int x, int y) {
		Tile returnTile = gridArray[x][y];
		return returnTile;
	}

	/**
	 * Sets the tile for the given column and row. Calls tile.setLocation().
	 * 
	 * @param tile the tile to set
	 * @param x    the column
	 * @param y    the row
	 */
	public void setTile(Tile tile, int x, int y) {
		gridArray[x][y] = tile;
		tile.setLocation(x,y);
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int y=0; y<getHeight(); y++) {
			if (y > 0) {
				str += "\n";
			}
			str += "[";
			for (int x=0; x<getWidth(); x++) {
				if (x > 0) {
					str += ",";
				}
				str += getTile(x, y);
			}
			str += "]";
		}
		return str;
	}
}
