Name: Victor Obiahu

Project: Target eMIP Technical Case Study # 2 (Barren Land Analysis)

Prompt
A program to solve the following word problem:
You have a farm of 400m by 600m where coordinates of the field are from (0, 0) to (399, 599). A portion of the farm is barren, and all the barren land is in the form of rectangles. 
Due to these rectangles of barren land, the remaining area of fertile land is in no particular shape. An area of fertile land is defined as the largest area of land that is not covered by any of the rectangles of barren land. Read input from STDIN. Print output to STDOUT.
Input
You are given a set of rectangles that contain the barren land. These rectangles are defined in a string, which consists of four integers separated by single spaces, with no additional spaces in the string. The first two integers are the coordinates of the bottom left corner in the given rectangle, and the last two integers are the coordinates of the top right corner.
Output
Output all the fertile land area in square meters, sorted from smallest area to greatest, separated by a space.
Sample Data:
Input: {“0 292 399 307”}
Output: 116800 116800
Input: {“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}
Output: 22816 192608

Approach 
I applied the principle of depth first search implemented using a Stack data structure. This solution makes the barren land a connected graph which I had to traverse. Following this logic I had to split the grid dimensions into a plot of land into 1x1 square cells and check to see what cell was visited and what wasn’t. Ultimately the total sum gives the area of the matrix within the land dimensions passed into the sample input. Time complexity is O(V+E) where V is the vertices and E is the edge. From the case study V and E refer to the upper left and lower right bound serving as the vertices and edges.

Deployment Guidelines 
This barren land analysis project can be run in any Java 1.8 compatible IDE.
Download zip file and import into desired IDE. Run the BarrenLand.java class as a Java application.
The JUnit test class BarrenLandTest is run as a JUnit test application

Testing
I have included 6 other test cases in BarrenLandTest which have sample barren land dimensions as well as edge cases if the entire grid is barren or if a small portion of the grid is barren.
