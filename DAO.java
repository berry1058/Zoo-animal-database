package animalsInTheZoo;

import java.sql.*;

import java.util.ArrayList;
import java.util.Scanner;





public class DAO {
	static final String DB_URL = "jdbc:mysql://localhost:3306/?user=root&autoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "sesame";
	static Scanner keyboard = new Scanner(System.in);
	
	
	static Connection CONN = null;
	static Statement STMT = null;
	static PreparedStatement PREP_STMT = null;
	static ResultSet RES_SET = null;

	public static void ConnToDB(){
		try {
			System.out.println("Trying to connect to the database");
			CONN = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Database connection failed");
			e.printStackTrace();
		}
		System.out.println("Connected to the Database");
	}

	public static void readFromDB(){
		ConnToDB();
		
		ArrayList<Animals> ourAnimals = new ArrayList<>();
		try {
			STMT = CONN.createStatement();
			RES_SET = STMT.executeQuery("SELECT * FROM students.animasl_at_the_zoo;");
			
			while(RES_SET.next()){
				Animals animalsInDB = new Animals();
				
				animalsInDB.setAnimalID(RES_SET.getString("animal_id"));
				animalsInDB.setSpecies(RES_SET.getString("species"));
				animalsInDB.setTypeOfCage(RES_SET.getString("type_of_cage"));
				animalsInDB.setFood(RES_SET.getString("food"));
				animalsInDB.setName(RES_SET.getString("name"));
				animalsInDB.setWeight(RES_SET.getDouble("weight"));
				
				ourAnimals.add(animalsInDB);
			}
			
			for(Animals animal : ourAnimals){
				System.out.println(animal.toString());
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void writeToDB() {
		Animals animalToAdd = new Animals();
		animalToAdd = aboutTheAnimals();
		ConnToDB();

		try {
			PREP_STMT = CONN.prepareStatement(insertToDB);
			PREP_STMT.setString(1, animalToAdd.getSpecies());
			PREP_STMT.setString(2, animalToAdd.getTypeOfCage());
			PREP_STMT.setString(3, animalToAdd.getFood());
			PREP_STMT.setString(4, animalToAdd.getName());
			PREP_STMT.setDouble(5, animalToAdd.getWeight());
			
			PREP_STMT.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static String insertToDB = "INSERT INTO `students`.`animasl_at_the_zoo`"
			+ "(species, type_of_cage, food, name, weight)" + "VALUES" + "(?,?,?,?,?)";

	public static Animals aboutTheAnimals() {
		Animals animalToAdd = new Animals();

		System.out.println("What is the name of the species?");
		animalToAdd.setSpecies(keyboard.nextLine());

		System.out.println("What is the type of cage or enclosure will the animal be in?");
		animalToAdd.setTypeOfCage(keyboard.nextLine());

		System.out.println("What will be the main source of food for this animal?");
		animalToAdd.setFood(keyboard.nextLine());
		
		System.out.println("What is the name of this animal?");
		animalToAdd.setName(keyboard.nextLine());
		
		System.out.println("What is the current weight of this animal?");
		animalToAdd.setWeight(Double.parseDouble(keyboard.nextLine()));

		return animalToAdd;
	}
	
	public static Animals deleteTheAnimals() {
		Animals animalToDelete = new Animals();

		System.out.println("What is the name of the species you want to delete?");
		animalToDelete.setSpecies(keyboard.nextLine());

		

		return animalToDelete;
	}
	
	public static void deleteFromDB() {
		Animals animalToDelete = new Animals();
		animalToDelete = deleteTheAnimals();
		ConnToDB();

		try {
			PREP_STMT = CONN.prepareStatement("DELETE FROM `students`.`animasl_at_the_zoo` WHERE species = ?");
			PREP_STMT.setString(1,animalToDelete.getSpecies());
			PREP_STMT.executeUpdate(); 
			

	
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
//	private static String deleteFromDB = "DELETE FROM Table WHERE species = ?"
	//		+ "(species)" + "VALUES" + "(?)";
	public static void updateToDB() {
		Animals animalToUpdate = new Animals();
		animalToUpdate = updateTheAnimals();
		ConnToDB();

		try {
			PREP_STMT = CONN.prepareStatement(updateInDB);
			
			PREP_STMT.setString(1, animalToUpdate.getTypeOfCage());
			PREP_STMT.setString(2, animalToUpdate.getFood());
			PREP_STMT.setString(3, animalToUpdate.getName());
			PREP_STMT.setDouble(4, animalToUpdate.getWeight());
			PREP_STMT.setString(5, animalToUpdate.getSpecies());
			
			PREP_STMT.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static String updateInDB = "UPDATE `students`.`animasl_at_the_zoo`"
			+ "SET type_of_cage =?, food =?, name=?, weight=? WHERE species = ?";

	public static Animals updateTheAnimals() {
		Animals animalToUpdate = new Animals();

//		System.out.println("What will the species be updated to?");
	//	animalToUpdate.setSpecies(keyboard.nextLine());

		System.out.println("What will the type of cage or enclosure be updated to?");
		animalToUpdate.setTypeOfCage(keyboard.nextLine());

		System.out.println("What will the main source of food be updated to?");
		animalToUpdate.setFood(keyboard.nextLine());
		
		System.out.println("What will the name of this animal be updated to?");
		animalToUpdate.setName(keyboard.nextLine());
		
		System.out.println("What will the current weight of this animal be updated to?");
		animalToUpdate.setWeight(Double.parseDouble(keyboard.nextLine()));
		
		System.out.println("What animal will be updated?");
		animalToUpdate.setSpecies(keyboard.nextLine());

		return animalToUpdate;
	}

}
