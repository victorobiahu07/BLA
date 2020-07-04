package com.target.barrenlandanalysis;

import java.util.*;


/*
 * This solution uses a modified DFS with a stack structure which counts number of cells in the connected components. 
 * This gives the area of the matrix where given field is divided into cells.
 */

public class BarrenLand {

	private static final int WIDTH = 400;
	private static final int HEIGHT = 600;
	private static final int TILE_SIZE = 1; //size of each square cell

	private static final int X_TILES = WIDTH /TILE_SIZE;
	private static final int Y_TILES = HEIGHT /TILE_SIZE;

	private static LandCoordinate[][] grid = new LandCoordinate[X_TILES][Y_TILES];

	/**
	 * Get list of integer arrays for each string of plot end points
	 * @param gridDimensionsArray
	 * @return Integer array of plot end point
	 */
	private static List<Integer[]> getBarrenLandCoordinates(String[] gridDimensionsArray) {
		List<Integer[]> plotPoints = new ArrayList<>();

		//For each of the set of the grid dimensions, split into array of strings, convert to array of int, add array to list of arrays of plots 
		for (int i = 0; i < gridDimensionsArray.length; i++) {
			String[] strPlotEndpoints = gridDimensionsArray[i].split(" ");
			Integer[] intPlotEndpoints = new Integer[strPlotEndpoints.length];
			for (int j = 0; j < strPlotEndpoints.length; j++) {
				intPlotEndpoints[j] = Integer.parseInt(strPlotEndpoints[j]);
			}
			plotPoints.add(intPlotEndpoints);
		}

		return plotPoints;
	}

	/**
	 * Populate a plot of barren land  with all the coordinates in that space
	 * @param bounds
	 * @return List of coordinates in barren land rectangle
	 */
	private static List<LandCoordinate> getTotalBarrenLand(Integer[] bounds) {

		List<LandCoordinate> allBarrenLandCoordinates = new ArrayList<>();

		//Traverse all end points and create new coordinate for each coordinate within a specified plot of land. 
		//Afterwards add to list of allBarrenLandCoordinates
		for (int i = bounds[0]; i <= bounds[2]; i++) 
		{
			for (int j = bounds[1]; j <= bounds[3]; j++)
			{
				LandCoordinate coordinates = new LandCoordinate(i, j);
				allBarrenLandCoordinates.add(coordinates);
			}
		}
		return allBarrenLandCoordinates;
	}

	/**
	 * Check if coordinate has been visited already and if not switch visited flag to true
	 * @param grid
	 * @param lc
	 * @return boolean flag representing if coordinate lc has been visited or not
	 */
	private static boolean isCoordinateVisited(LandCoordinate[][] grid, LandCoordinate lc) {

		//Check that Coordinate c is not outside bounds of the grid
		if (lc.getX() < 0 || lc.getY() < 0 || lc.getX() >= X_TILES || lc.getY() >= Y_TILES) {
			return false;
		}

		LandCoordinate landCoordinateToCheck = grid[lc.getX()][lc.getY()];

		if (landCoordinateToCheck.isVisited()) {
			return false;
		}

		landCoordinateToCheck.setVisited(true);

		return true;
	}


	/**
	 * Visit all coordinates in the fertile land space and find the area
	 * @param grid
	 * @param x
	 * @param y
	 * @return area of the current fertile land space represented by count variable
	 */
	private static int populateTile(LandCoordinate[][] grid, int x, int y) {
		int count = 0; // Count of square grids being visited

		//this is a dfs helper function.
		Stack<LandCoordinate> stack = new Stack<LandCoordinate>();
		stack.push(new LandCoordinate(x, y));

		while (!stack.isEmpty()) {
			LandCoordinate c = stack.pop();

			//if the coordinate c is unvisited, visit it, increment count by 1, and add neighbors to the stack;
			if(isCoordinateVisited(grid, c)) {     
				count += 1;
				if ( c.getY() -1 >= 0 && !grid[c.getX()][c.getY() - 1].isVisited() ) {
					stack.push(new LandCoordinate(c.getX(), c.getY() - 1));
				}
				if (c.getY() +1 < Y_TILES && !grid[c.getX()][c.getY() + 1].isVisited()) {
					stack.push(new LandCoordinate(c.getX(), c.getY() + 1));
				}
				if ( c.getX() -1 >= 0 && !grid[c.getX() - 1][c.getY()].isVisited()) {
					stack.push(new LandCoordinate(c.getX() - 1, c.getY()));
				}
				if (c.getX() +1 < X_TILES && !grid[c.getX() + 1][c.getY()].isVisited()) {
					stack.push(new LandCoordinate(c.getX() + 1, c.getY()));
				}
			}

		}
		return count;
	}
	
	/**
	 * Traverse the grid, find first unvisited point, populate the fertile area  connected to that plot
	 * and return the total area
	 * @param land
	 * @param xVal
	 * @param yVal
	 * @return List of area of each fertile land plot
	 */
	private static List<Integer> dfs(List<Integer> land, int xVal, int yVal) {

		for (int y = yVal; y < Y_TILES; y++)
		{
			for (int x = xVal; x < X_TILES; x++)
			{
				LandCoordinate tile = grid[x][y];
				if (!tile.isVisited())
				{
					int totalFertileArea = populateTile(grid, x, y);
					land.add(totalFertileArea);
					dfs(land, x, y);
				}
			}
		}
		return land;

	}


	/**
	 * Find sum total of all the fertile land in grid based on String array of the grid dimensions
	 * @param gridDimensionsArray
	 * @return String of all the fertile land area in square meters
	 *  sorted from smallest area to greatest, separated by a space
	 */
	public static String findFertileLand(String[] gridDimensionsArray) {
		List<Integer> fertileLand = new ArrayList<>();

		List<Integer[]> barrenLandEndPoints = getBarrenLandCoordinates(gridDimensionsArray);

		List<LandCoordinate> totalBarrenLand = new ArrayList<>();
		
		//Fill barrenLand list 
		for (Integer[] squareCell : barrenLandEndPoints) {
			totalBarrenLand.addAll(getTotalBarrenLand(squareCell));
		}
		
		//Loop through bounds of the grid filling a 2D array with coordinate points.
		for (int y = 0; y < Y_TILES; y++) 
		{
			for (int x = 0; x < X_TILES; x++) 
			{
				LandCoordinate coord = new LandCoordinate(x, y);
				//      for each coordinate, if it's present in the totalBarrenLand list, mark that coordinate as barren and visited
				for (LandCoordinate lc : totalBarrenLand)
				{
					if (lc.getX() == x && lc.getY() == y)
					{
						coord.setIsBarren(true);
						coord.setVisited(true);
						break;
					}
					else 
					{
						coord.setIsBarren(false);
					}
				}
				grid[x][y] = coord;
			}
		}

		fertileLand = dfs(fertileLand, 0, 0);
		Collections.sort(fertileLand);
		String STDOUT = "";

		if (!fertileLand.isEmpty()) {
			for (Integer land : fertileLand)
			{
				STDOUT += land.toString() + " ";
			}
		} 
		else 
		{
			STDOUT = "No fertile land available.";
		}

		return STDOUT.trim();
	}

	public static void main(String[] args) {
		
		String[] STDIN = {"0 292 399 307"};//
		String[] STDIN_TWO = {"48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547"};

		String STDOUT = findFertileLand(STDIN);

		String STDOUT_TWO = findFertileLand(STDIN_TWO);
		System.out.println(STDOUT);
		System.out.println(STDOUT_TWO);

	}


}

