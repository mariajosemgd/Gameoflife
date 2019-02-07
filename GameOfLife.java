import java.util.Scanner;

/**
 * Class to emulate the Game of Life
 * @author Mariajose Gonzalez
 * assumes that even though grid is infinite the only a finite section of this grid will want to be observed
 * assumes that even though the game of life runs indefenitely only a limited number of iterations will be observed
 * assumes that grid is square 
 *
 */


public class GameOfLife {
	// construct a scanner to use inputs
	static Scanner input = new Scanner(System.in);
			
	public static void main(String[] args) {
		
		char tocontinue='x';
		
		//declare gridsize
		int gridsize=10;
		//declare grid type
		int type =5;
		
		
		
		//asks user input to determine grid size
		System.out.println("Please type a number to select a size of grid.");
		gridsize=inputInt();
		
		while(gridsize<1) {
			System.out.println("Please type a positive number to select a size of grid.");
			gridsize=inputInt();
		}
		//generates grid
		int[][] grid=new int[gridsize][gridsize];
		
		//asks user input to determine how to populate the grid
		System.out.println("Please type a number to select a way to populate grid: 1. for block 2. for line 3. for random");
		type=inputInt();
		
		//if invalid input asks for a new input
		while (type<1 || type>3) {
			System.out.println("You did not input 1, 2 or 3.Please type a number to select a way to populate grid: 1. for block 2. for line 3. for random");
			
			type=inputInt();
		}
		
		//populates grid in a block pattern
		if(type==1) {
			System.out.println("Please input lenght of block:");
			int lengthofblock=inputInt();
			
			//check if input is valid and if not ask for it again
			while(lengthofblock<0) {
				System.out.println("Please type a positive number to select length of  block.");
				lengthofblock=inputInt();
			}
			
			grid=generateblockgrid(gridsize, lengthofblock);

		// populates grid in a line pattern
		}else if(type==2) {
			
			System.out.println("Please input lenght of line:");
			int lengthofline=inputInt();
			
			//check if input is valid and if not ask for it again
			while(lengthofline<0) {
				System.out.println("Please type a positive number to select length of  line.");
				lengthofline=inputInt();
			}
			
			grid=generatelinegrid(gridsize, lengthofline);
		
		//populates grid in random pattern	
		}else if (type==3) {
			
			System.out.println("Please input number of alive cells");
			int number=inputInt();
			
			//check if input is valid and if not ask for it again
			while(number<0) {
				System.out.println("Please type a positive number to input number of alive cells.");
				number=inputInt();
			}
			grid=generaterandomgrid(gridsize, number);
		}
		
		
		//print original grid
		System.out.println("This is the original grid:");
		
		printGrid(grid);
		System.out.println();
		
		//infinite loop to continuously evolve the grid
		while(true) {
			//evolves the grid
			grid=evolvegrid(grid);
			
			//prints out the evolved grid
			System.out.println("this is the evolved grid:");
			printGrid(grid);
			
			//asks user if they want to continue evolving
			System.out.println("Do you wish to evolve again? type Y for yes or N for No");
			tocontinue=input.next().charAt(0);
			
			
			while(true) {
				if(tocontinue=='Y' || tocontinue=='N') {
					break;
			//if invalid input asks for a new input
				}else {
					System.out.println("You did not type Y or N. Do you wish to evolve again? type Y for yes or N for No");
					tocontinue=input.next().charAt(0);
				}
				
			}
			//breaks infinite loop if user does not wish to evolve again and finishes program
			if(tocontinue=='N') {
				break;
			}
		}
			
	}

	

	/**gets all total number of neighbours of a specified cell in the grid
	 * @parameters a row of specified cell 
	 * @parameters b collumn of specified cell
	 * @parameters array that represents the grid
	 * @return total number of neighbours 
	 */
	public static int getTotalneighbours(int a, int b, int[][] grid) {
	 //int to represent number of neighbours
		int total=0;
	 //if its the first cell only check the 3 possible neighbours	
	 if(a==0 &&b==0) {
		 for(int i=a; i<a+2; i++) {
			 for(int j=b; j<b+2; j++) {
			 total+= grid[i][j];
			 }
		 }
		 total-= grid[a][b];
		 return total;
	 //if its last cell only check 3 possible neighbours	 
	 }else if(a==grid.length-1 &&b==grid.length-1) {
		 for(int i=a-1; i<a+1; i++) {
			 for(int j=b-1; j<b+1; j++) {
			 total+= grid[i][j];
			 }
		 }
		 total-= grid[a][b];
		 return total;
	 //if its a corner cell only check 3 possible neighbours	
	 }else if(a==0 &&b==grid.length-1 ) {
		 for(int i=a; i<a+2; i++) {
			 for(int j=b-1; j<b+1; j++) {
			 total+= grid[i][j];
			 }
		 }
		 total-= grid[a][b];
		 return total;
		
	 //if its a corner cell only check 3 possible neighbours		 
	 }else if(a==grid.length-1 && b==0) {
		 for(int i=a-1; i<a+1; i++) {
			 for(int j=b; j<b+2; j++) {
			 total+= grid[i][j];
			 }
		 }
		 total-= grid[a][b];
		 return total;
		 
	 //if the cell is on the top side edge do not check neighbours above
	 }else if(a==0 ) {
		 for(int i=a; i<a+2; i++) {
			 for(int j=b-1; j<b+2; j++) {
			 total+= grid[i][j];
			 }
		 }
		 total-= grid[a][b];
		 return total;
		 
	//if the cell is on the left side edge do not check neighbours to the left 
	 }else if(b==0) {
		 for(int i=a-1; i<a+2; i++) {
			 for(int j=b; j<b+2; j++) {
			 total+= grid[i][j];
			 }
		 }
		 total-= grid[a][b];
		 return total;
		 
	//if the cell is on the bottom side edge do not check neighbours bellow 
	 }else if(a==grid.length-1) {
		 for(int i=a-1; i<a+1; i++) {
			 for(int j=b-1; j<b+2; j++) {
			 total+= grid[i][j];
			 }
		 }
		 total-= grid[a][b];
		 return total;
		
	//if the cell is on the right side edge do not check neighbours to the right
	 }else if(b==grid.length-1) {
		 for(int i=a-1; i<a+2; i++) {
			 for(int j=b-1; j<b+1; j++) {
			 total+= grid[i][j];
			 }
		 }
		 total-= grid[a][b];
		 return total;
	 
	//if cell is on the middle of the grid check all possible 8 neighbours 	 
	 }else {
		 //loops through 8 neighbours and adds them
		 for(int i=a-1; i<a+2; i++) {
			 for(int j=b-1; j<b+2; j++) {
			 total+= grid[i][j];
			 }
		 }
		 // subtract checked cell as it has also been looped through 
		 total-= grid[a][b];
		 return total;
	 }
	 	 
	}
	
	
	/**prints out a grid
	 * @parameters  grid to be printed 
	 * @return  
	 */
	public static void printGrid (int[][] grid) {
		//loops throuhg whole array printing out each element
		for(int i=0; i<grid.length; i++) {
			for (int j=0; j<grid.length;j++) {
				System.out.print(grid[i][j]);
			}
			//next row
			System.out.println();
			
		}
	}
	
	/** method to evolve a specified grid following the game of life rules
	 * @parameters array to represent grid to be evolved
	 * @return array of evolved grid
	 */
	public static int[][] evolvegrid(int[][] grid) {
		//array to represent evolved grid
		int[][] evolved= new int[grid.length][grid.length];
		//loops through each cell to decide its new value
		for(int i=0; i<grid.length; i++) {
			for (int j=0; j<grid.length;j++) {
				//if cell is empty
				if (grid[i][j]==0) {
					//if it has 3 neighbours new cell is born 
					if (getTotalneighbours(i, j, grid)==3) {
						evolved[i][j]=1;
					//if not cell remains empty
					}else {
						evolved[i][j]=0;
					}
				//if cell is alive	
				}else if(grid[i][j]==1) {
					//if it has 2 or 3 neighbours it continues to live
					if(getTotalneighbours(i, j, grid)==2 || getTotalneighbours(i, j, grid)==3) {
						evolved[i][j]=1;
						
					//if it has less than 2 neighbours it dies of underpopulation if it has more than 3 it dies of over population	
					}else {
						evolved[i][j]=0;
					}
				}
			
			}
		}
		//returns evolved grid
		return evolved;
	}

	/**gets generates a grid populated randomly
	 * @parameters gridsize is size of grid
	 * @parameters numberoflivecells is how many cells will be populated
	 * @return randomly populated array
	 */
	public static int[][] generaterandomgrid(int gridsize,int numberoflivecells){
		
		//declares an array
		int[][] grid = new int[gridsize][gridsize];
		//declares two ints to be used as index for the array
		int random1=0;
		int random2=0;
		//loops as many times as number of cells to be populated
		for(int i=0; i<numberoflivecells; i++) {
			//generates 2 random numbers
			random1= (int)(Math.random() * gridsize );
			random2= (int)(Math.random() * gridsize );
			//populates a cell on a random location in the grid
			grid[random1][random2]=1;
		}
		
		return grid;
	}
	
	
	/**gets generates a grid populated in a block pattern
	 * @parameters gridsize is size of grid
	 * @parameters block size is how long is the grid in cells
	 * @return populated array
	 */
	public static int[][] generateblockgrid(int gridsize,int blocksize){
		int[][] grid = new int[gridsize][gridsize];
		//makes sure the block is not bigger than the grid
		if(blocksize>gridsize) {
			blocksize=gridsize;
		}
		//populates a square 
		for (int i=0; i<blocksize; i++) {
			for(int j=0; j<blocksize; j++)
				grid[i][j]=1;
		}
		
		return grid;
	}
	
	/**gets generates a grid populated in a horizontal straight line
	 * @parameters gridsize is size of grid
	 * @parameters number of cells is how many cells are to be populated
	 * @return populated array
	 */
	public static int[][] generatelinegrid(int gridsize, int numberofcells){
		int[][] grid = new int[gridsize][gridsize];
		//makes sure the line is not bigger than the grid
		if(numberofcells>=gridsize) {
			numberofcells=gridsize;
		}
		// loops from first cell to be populated to last cell to be populated in the middle row of the grid
		for(int i=(gridsize-numberofcells)/2; i<((gridsize-numberofcells)/2)+numberofcells; i++) {
			grid[gridsize/2][i]=1;
		}
		return grid;
		
		
	}

	
	/**gets user input
	 * @return input int
	 */
	public static int inputInt() {
		int in = -1;
		//try catch to stop invalid characters from being used and breaking program
		try {
			//reads input
			in=input.nextInt();
		}catch(Exception e) {
			input.next(); //consumes invalid token
		}
		//returns input
		return in;
	}
	
}
