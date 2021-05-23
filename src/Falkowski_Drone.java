//Author Name: Andy Falkowski
//Date: 5/15/2021
//Program Name: Falkowski_Drone
//Purpose: Simulation using button, drone movement in x, y, z location

import java.util.InputMismatchException;
import java.util.Scanner;

public class Falkowski_Drone {
	
	public static void main(String[] args) {
		Drone drone = new Drone();
		

		int selection = 0;
		
		while (selection != 8) {
			System.out.println();
			try {
				selection = showMenu();
			} 
			catch (InputMismatchException ex1) {  
				System.out.println("Integer values only");
				selection = 0;
			} 

			switch (selection) {
			case 1:
				drone.moveUpDown(1);
				break;
			case 2:
				drone.moveUpDown(-1);
				break;
			case 3:
				drone.moveFrontBack(1);
				break;
			case 4:
				drone.moveFrontBack(-1);
				break;
			case 5:
				drone.turnLeftRight(-1);
				break;
			case 6:
				drone.turnLeftRight(1);
				break;
			case 7:
				drone.getPosition();
				break;
			case 8:
				break;
			default:
				System.out.println("Please make a valid selection");
				break;
			}
		} 
		System.out.println("Drone navigation has stopped. Thank you.");
	}

	
	// method for menu
	public static int showMenu() {
		int choice = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("Which direction would you like to move the drone?");
		System.out.println("1 - Move Up");
		System.out.println("2 - Move Down");
		System.out.println("3 - Move Forward");
		System.out.println("4 - Move Backward");
		System.out.println("5 - Turn Left");
		System.out.println("6 - Turn Right");
		System.out.println("7 - Display Position");
		System.out.println("8 - Exit Navigation");
		System.out.println();
		choice = input.nextInt();
		input.nextLine();
		return choice;
	}
	
}


class Drone {
	
	private int xPos;
	private int yPos;
	private int zPos;
	private String orientation;
	
	public static final int DEFAULT_X = 0;
	public static final int DEFAULT_Y = 0;
	public static final int DEFAULT_Z = 0;
	public static final String DEFAULT_ORIENTATION = "North";
	
	// constructor
	public Drone () {
		this.xPos = DEFAULT_X;
		this.yPos = DEFAULT_Y;
		this.zPos = DEFAULT_Z;
		this.orientation = DEFAULT_ORIENTATION;
	}
	
	// method for up/down movement
	public void moveUpDown(int z) {
		if (zPos >= 0 && z > 0) {
			zPos += z;
			System.out.println("You have moved up");
		}
		else if (zPos > 0 && z < 0) {
			zPos += z;
			System.out.println("You have moved down");
		}
		else if (zPos == 0 && z < 0)
			System.out.println("Drone is already on the ground. Can't go any lower!");
	}
	
	// method for forward/backward movement
	public void moveFrontBack(int value) {
		String direction = "forward";
		if (value < 0)
			direction = "backward";
		
		if (orientation == "North") {
			yPos += value;
		}
		
		else if (orientation == "South") {
			yPos -= value;
		}
		else if (orientation == "East") {
			xPos += value;
		}
		else if (orientation == "West") {
			xPos -= value;
		}
		System.out.println("You have moved " + direction);
	}
	
	// method for turning left/right
	public void turnLeftRight(int direction) {
		switch (orientation) {
		case "North":
			if (direction == -1)
				orientation = "West";
			else
				orientation = "East";
			break;
		case "East":
			if (direction == -1)
				orientation = "North";
			else
				orientation = "South";
			break;
		case "South":
			if (direction == -1)
				orientation = "East";
			else
				orientation = "West";
			break;
		case "West":
			if (direction == -1)
				orientation = "South";
			else
				orientation = "North";
			break;
		}
		System.out.println("You have turned the drone " + orientation);
	}
	
	// method that retrieves drone's current position
	public void getPosition() {
		System.out.println("[XPos=" + xPos + ", YPos=" + yPos + ", ZPos=" + zPos + ", orientation=" + orientation + "]");
	}
}