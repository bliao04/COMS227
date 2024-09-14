package hw3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import api.ScoreUpdateListener;
import api.ShowDialogListener;
import api.Tile;

/**
 * Class that models a game.
 * @author Brandon Liao
 */
public class ConnectGame {
	private ShowDialogListener dialogListener;
	private ScoreUpdateListener scoreListener;
	
	/**
	 * Instance variable tracking the grid of the current game. 
	 */
	private Grid connectGrid;
	
	/**
	 * Instance variable tracking the minimum level that tiles can reach. 
	 */
	private int minTile;
	
	/**
	 * Instance variable tracking the max level that tiles can reach.
	 */
	private int maxTile;
	
	/**
	 * Instance variable tracking the current games score. 
	 */
	private long gameScore;
	
	/*
	 * Instance variable used to generate random tiles into the game.
	 */
	private Random randomTile;
	
	/*
	 * Instance variable used for tracking a list of the Tiles that were selected in game. 
	 */
	private ArrayList<Tile> selectedTList;
	
	/**
	 * Constructs a new ConnectGame object with given grid dimensions and minimum
	 * and maximum tile levels.
	 * 
	 * @param width  grid width
	 * @param height grid height
	 * @param min    minimum tile level
	 * @param max    maximum tile level
	 * @param rand   random number generator
	 */
	public ConnectGame(int width, int height, int min, int max, Random rand) {
		connectGrid = new Grid(width, height);
		minTile = min;
		maxTile = max;
		randomTile = rand;
		selectedTList = new ArrayList<>();
	}

	/**
	 * Gets a random tile with level between minimum tile level inclusive and
	 * maximum tile level exclusive. For example, if minimum is 1 and maximum is 4,
	 * the random tile can be either 1, 2, or 3.
	 * <p>
	 * DO NOT RETURN TILES WITH MAXIMUM LEVEL
	 * 
	 * @return a tile with random level between minimum inclusive and maximum
	 *         exclusive
	 */
	public Tile getRandomTile() {
		// Creates a new Tile using a random number between minTile and maxTile. 
		return new Tile(randomTile.nextInt(maxTile - minTile) + minTile);
	}

	/**
	 * Regenerates the grid with all random tiles produced by getRandomTile().
	 */
	public void radomizeTiles() {
		// For loops to access the width and height if the grid. Iterates through the graph setting tiles to random numbers based on max and min tile. 
		for(int i = 0; i < connectGrid.getWidth(); i++) {
			for(int j = 0; j < connectGrid.getHeight(); j++) {
				connectGrid.setTile(getRandomTile(), i, j);
			}
		}
	}

	/**
	 * Determines if two tiles are adjacent to each other. The may be next to each
	 * other horizontally, vertically, or diagonally.
	 * 
	 * @param t1 one of the two tiles
	 * @param t2 one of the two tiles
	 * @return true if they are next to each other horizontally, vertically, or
	 *         diagonally on the grid, false otherwise
	 */
	public boolean isAdjacent(Tile t1, Tile t2) {
		// Uses a form of distance formula, if a tile is adjacent, the difference between their X and Y values should be <= 2.
		return Math.pow((t1.getX() - t2.getX()), 2) + Math.pow((t1.getY() - t2.getY()), 2) <= 2;
	}

	/**
	 * Indicates the user is trying to select (clicked on) a tile to start a new
	 * selection of tiles.
	 * <p>
	 * If a selection of tiles is already in progress, the method should do nothing
	 * and return false.
	 * <p>
	 * If a selection is not already in progress (this is the first tile selected),
	 * then start a new selection of tiles and return true.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return true if this is the first tile selected, otherwise false
	 */
	public boolean tryFirstSelect(int x, int y) {
		Tile selectedTile = connectGrid.getTile(x, y);
		boolean firstSelected = false;
		// Check to see if selection has started or not (list should have a size of 0 if not started). 
		if (selectedTList.size() == 0) {
			selectedTile.setSelect(true);
			selectedTList.add(selectedTile);
			firstSelected = true;
		}
		return firstSelected;
	}

	/**
	 * Indicates the user is trying to select (mouse over) a tile to add to the
	 * selected sequence of tiles. The rules of a sequence of tiles are:
	 * 
	 * <pre>
	 * 1. The first two tiles must have the same level.
	 * 2. After the first two, each tile must have the same level or one greater than the level of the previous tile.
	 * </pre>
	 * 
	 * For example, given the sequence: 1, 1, 2, 2, 2, 3. The next selected tile
	 * could be a 3 or a 4. If the use tries to select an invalid tile, the method
	 * should do nothing. If the user selects a valid tile, the tile should be added
	 * to the list of selected tiles.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 */
	public void tryContinueSelect(int x, int y) {
		// Checks to see if the a tile has already be selected first.
		if (selectedTList.size() > 0) {
			Tile selectedTile = connectGrid.getTile(x, y);
			// If a tile has already been selected, checks to see if the tile selected is adjacent to the selected tile. 
			if (isAdjacent(selectedTList.get(selectedTList.size() - 1), selectedTile)) {
				// Checks to see if the first two tiles selected are the same levels IF it is the second tile being selected.
				if (selectedTList.size() == 1) {
					if (selectedTList.get(0).getLevel() == selectedTile.getLevel()) {
						selectedTile.setSelect(true);
						selectedTList.add(selectedTile);
					}
					// If it is not the second tile being selected... 
				} else {
					if (selectedTile.isSelected()) {
						if (selectedTList.get(selectedTList.size() - 2) == selectedTile) {
							selectedTList.get(selectedTList.size() - 1).setSelect(false);
							selectedTList.remove(selectedTList.size() - 1);
						}
					} else {
						if ((selectedTList.get(selectedTList.size() - 1).getLevel() == selectedTile.getLevel()) || (selectedTList.get(selectedTList.size() - 1).getLevel() + 1 == selectedTile.getLevel())) {
							selectedTile.setSelect(true);
							selectedTList.add(selectedTile);
						}
					}
				}
			}
		}
	}

	/**
	 * Indicates the user is trying to finish selecting (click on) a sequence of
	 * tiles. If the method is not called for the last selected tile, it should do
	 * nothing and return false. Otherwise it should do the following:
	 * 
	 * <pre>
	 * 1. When the selection contains only 1 tile reset the selection and make sure all tiles selected is set to false.
	 * 2. When the selection contains more than one block:
	 *     a. Upgrade the last selected tiles with upgradeLastSelectedTile().
	 *     b. Drop all other selected tiles with dropSelected().
	 *     c. Reset the selection and make sure all tiles selected is set to false.
	 * </pre>
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return return false if the tile was not selected, otherwise return true
	 */
	public boolean tryFinishSelection(int x, int y) {
		Tile selectedTile = connectGrid.getTile(x, y);
		boolean selectingFinish = false;
		// Check to see there are tiles selected and the last element in the list is the selected tile.
		if ((selectedTList.size() > 0) && (selectedTList.get(selectedTList.size() - 1) == selectedTile)) {
			// Check to see if there is more than 1 tile selected, otherwise resets the tile selection. 
			if (selectedTList.size() > 1) {
				for (int i = 0; i < selectedTList.size(); i++) {
					gameScore += selectedTList.get(i).getValue();
				}
				scoreListener.updateScore(gameScore);
				upgradeLastSelectedTile();
				dropSelected();
			} else {
				unselect(x, y);
			}
			selectedTList.clear();
			selectingFinish = true;
		}
		return selectingFinish;
	}

	/**
	 * Increases the level of the last selected tile by 1 and removes that tile from
	 * the list of selected tiles. The tile itself should be set to unselected.
	 * <p>
	 * If the upgrade results in a tile that is greater than the current maximum
	 * tile level, both the minimum and maximum tile level are increased by 1. A
	 * message dialog should also be displayed with the message "New block 32,
	 * removing blocks 2". Not that the message shows tile values and not levels.
	 * Display a message is performed with dialogListener.showDialog("Hello,
	 * World!");
	 */
	public void upgradeLastSelectedTile() {
		Tile lastTile = selectedTList.get(selectedTList.size() - 1);
		// Increases the last tile by 1 level and unselects the last tile.
		lastTile.setLevel(lastTile.getLevel() + 1);
		lastTile.setSelect(false);
		// Check to see if the last tile is greater than the previous max tile.
		if (lastTile.getLevel() >= maxTile) {
			maxTile += 1;
			dialogListener.showDialog("New block " + ((int)Math.pow(2, maxTile - 1)) + ", removing blocks " + ((int)Math.pow(2, minTile)));
			minTile += 1;
			dropLevel(minTile - 1);
		}
	}

	/**
	 * Gets the selected tiles in the form of an array. This does not mean selected
	 * tiles must be stored in this class as a array.
	 * 
	 * @return the selected tiles in the form of an array
	 */
	public Tile[] getSelectedAsArray() {
		return selectedTList.toArray(new Tile [selectedTList.size()]);
	}

	/**
	 * Removes all tiles of a particular level from the grid. When a tile is
	 * removed, the tiles above it drop down one spot and a new random tile is
	 * placed at the top of the grid.
	 * 
	 * @param level the level of tile to remove
	 */
	public void dropLevel(int level) {
		// Access all the tiles in the grid width and height through for loops. 
		for (int i = 0; i < connectGrid.getWidth(); i++) {
			for (int j = 0; j < connectGrid.getHeight(); j++) {
				Tile currentTile = connectGrid.getTile(i, j);
				// Check if the current tile is the same level as the drop level parameter. 
				if (currentTile.getLevel() == level) {
					unselect(currentTile.getX(), currentTile.getY());
					// Sets k to the current height level and iterates through the length of the current height level.
					for (int k = j; k > 0; k--) {
						connectGrid.getTile(i, k).setLevel(connectGrid.getTile(i, k - 1).getLevel());
					}
					connectGrid.setTile(getRandomTile(), i, 0);
					connectGrid.getTile(i, 0).setLocation(i, 0);
				}
			}
		}
	}

	/**
	 * Removes all selected tiles from the grid. When a tile is removed, the tiles
	 * above it drop down one spot and a new random tile is placed at the top of the
	 * grid.
	 */
	public void dropSelected() {
		// For loop runs for the length of selected tiles, accesses the tiles in the selected tiles list.
		for (int i = 0; i < selectedTList.size(); i++) {
			Tile dropTile = connectGrid.getTile(selectedTList.get(i).getX(), selectedTList.get(i).getY());
			dropTile.setSelect(false);
			// Accesses the height of the tile and iterates through the length, dropping each tile level. 
			for (int j = dropTile.getY(); j > 0; j--) {
				connectGrid.getTile(dropTile.getX(), j).setLevel(connectGrid.getTile(dropTile.getX(), j - 1).getLevel());
			}
			connectGrid.setTile(getRandomTile(), dropTile.getX(), 0);
			connectGrid.getTile(dropTile.getX(), 0).setLocation(dropTile.getX(), 0);
		}
	}

	/**
	 * Remove the tile from the selected tiles.
	 * 
	 * @param x column of the tile
	 * @param y row of the tile
	 */
	public void unselect(int x, int y) {
		Tile unselectTile = connectGrid.getTile(x, y);
		unselectTile.setSelect(false);
		// Iterates through the selected tiles list. 
		for (int i = 0; i < selectedTList.size(); i++) {
			// Check to see that the given tile is the correct tile (width and height).
			if ((unselectTile.getX() == selectedTList.get(i).getX()) && (unselectTile.getY() == selectedTList.get(i).getY())) {
				selectedTList.remove(i);
				break;
			}
		}
	}

	/**
	 * Gets the player's score.
	 * 
	 * @return the score
	 */
	public long getScore() {
		return gameScore;
	}

	/**
	 * Gets the game grid.
	 * 
	 * @return the grid
	 */
	public Grid getGrid() {
		return connectGrid;
	}

	/**
	 * Gets the minimum tile level.
	 * 
	 * @return the minimum tile level
	 */
	public int getMinTileLevel() {
		return minTile;
	}

	/**
	 * Gets the maximum tile level.
	 * 
	 * @return the maximum tile level
	 */
	public int getMaxTileLevel() {
		return maxTile;
	}

	/**
	 * Sets the player's score.
	 * 
	 * @param score number of points
	 */
	public void setScore(long score) {
		gameScore = score;
	}

	/**
	 * Sets the game's grid.
	 * 
	 * @param grid game's grid
	 */
	public void setGrid(Grid grid) {
		connectGrid = grid;
	}

	/**
	 * Sets the minimum tile level.
	 * 
	 * @param minTileLevel the lowest level tile
	 */
	public void setMinTileLevel(int minTileLevel) {
		minTile = minTileLevel;
	}

	/**
	 * Sets the maximum tile level.
	 * 
	 * @param maxTileLevel the highest level tile
	 */
	public void setMaxTileLevel(int maxTileLevel) {
		maxTile = maxTileLevel;
	}

	/**
	 * Sets callback listeners for game events.
	 * 
	 * @param dialogListener listener for creating a user dialog
	 * @param scoreListener  listener for updating the player's score
	 */
	public void setListeners(ShowDialogListener dialogListener, ScoreUpdateListener scoreListener) {
		this.dialogListener = dialogListener;
		this.scoreListener = scoreListener;
	}

	/**
	 * Save the game to the given file path.
	 * 
	 * @param filePath location of file to save
	 */
	public void save(String filePath) {
		GameFileUtil.save(filePath, this);
	}

	/**
	 * Load the game from the given file path
	 * 
	 * @param filePath location of file to load
	 */
	public void load(String filePath) throws FileNotFoundException{
		GameFileUtil.load(filePath, this);
	}
}
