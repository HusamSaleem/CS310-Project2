package driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import mapDataStructure.Map;

public class StudentDictionary {
	Map<String, String> map;
	
	Scanner scnr = new Scanner(System.in);

	public StudentDictionary() {
		map = new Map<String, String>();
		readFromFile("records.txt");
		showMenu();
	}

	public static void main(String[] args) {
		StudentDictionary dictionary = new StudentDictionary();
	}
	
	public void showMenu() {
		System.out.println("\n------------------Main Menu-------------------");
		System.out.println("Please enter one of the following options...");
		System.out.println("0: Exit");
		System.out.println("1: Search for a student by redID");
		System.out.println("2: Search for a student by Name");
		System.out.println("3: Delete a student");
		
		int choice = 0;
		try {
			choice = Integer.parseInt(scnr.next());
		} catch (NumberFormatException e) {
			System.out.println("There has been an error...");
			System.out.println("Returning to main menu");
			showMenu();
		}
		if (choice > 3 || choice < 0) {
			System.out.println("Please enter a valid choice");
			showMenu();
		}
		
		if (choice == 0) {
			System.out.println("Exiting...");
			System.exit(0);
		} else if (choice == 1) {
			System.out.println("Please enter the redID of the student");
			String id = scnr.next();
			
			boolean success = searchByKey(id);
			
			if (success)
				System.out.println("Found student by the name of: " + map.getValue(id));
			else
				System.out.println("No student found by that ID...\nReturning to the main menu\n");
			
			showMenu();
		} else if (choice == 2) {
			System.out.println("Please enter the name of the student (Example: firstname lastname)");
			
			String name = null;
		
			String firstName = scnr.next();
			String lastName = scnr.next();
			
			name = firstName + " " + lastName;
			name = name.toLowerCase();
			
			boolean success = searchByValue(name);
			
			if (success)
				System.out.println("Found student by the red id of: " + map.getKey(name));
			else
				System.out.println("No student found by that name...\nReturning to the main menu\n");
			
			showMenu();
		} else {
			System.out.println("Please enter the redID of the student");
			String id = scnr.next();
			
			boolean success = deleteStudent(id);
			
			if (success)
				System.out.println("Successfully deleted the student, the new dictionary is as follows:\n" + map.toString());
			else
				System.out.println("Failed to delete the student. Wrong ID entered most likely...\nReturning to the main menu");
			
			showMenu();
		}
	}

	// Read any text file
	public void readFromFile(String fileName) {
		File file;
		Scanner scan = null;

		try {
			file = new File("testingInputs/" + fileName);
			scan = new Scanner(file);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			String name = line.substring(0, line.indexOf(',')).toLowerCase();
			String redID = line.substring(line.indexOf(',') + 2);
			
			addStudent(redID, name);
		}
		
		System.out.print("----------Current student dictionary----------\n" + map.toString());
		System.out.println("----------------------------------------------");
		scan.close();
	}
	
	private void addStudent(String id, String name) {
		map.add(id, name);
	}
	
	private boolean searchByKey(String id) {
		if (map.contains(id))
			return true;
		else
			return false;
	}
	
	private boolean searchByValue(String name) {
		if (map.contains(map.getKey(name)))
			return true;
		else
			return false;
	}
	
	private boolean deleteStudent(String id) {
		
		if (map.contains(id)) {
			map.delete(id);
			return true;
		} 
		
		return false;
	}

}