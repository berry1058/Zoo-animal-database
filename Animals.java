package animalsInTheZoo;

public class Animals {
	private String animalID = null;
	private String species = null;
	private String typeOfCage = null;
	private String food = null;
	private String name = null;
	private double weight = 0.0;

	
	public Animals(String animalID, String species, String typeOfCage, String food, String name, double weight) {
		super();
		this.animalID = animalID;
		this.species = species;
		this.typeOfCage = typeOfCage;
		this.food = food;
		this.name = name;
		this.weight = weight;
	}


	public Animals() {
		super();
	}


	public String getAnimalID() {
		return animalID;
	}


	public void setAnimalID(String animalID) {
		this.animalID = animalID;
	}


	public String getSpecies() {
		return species;
	}


	public void setSpecies(String species) {
		this.species = species;
	}


	public String getTypeOfCage() {
		return typeOfCage;
	}


	public void setTypeOfCage(String typeOfCage) {
		this.typeOfCage = typeOfCage;
	}


	public String getFood() {
		return food;
	}


	public void setFood(String food) {
		this.food = food;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getWeight() {
		return weight;
	}


	public void setWeight(double weight) {
		this.weight = weight;
	}


	@Override
	public String toString() {
		return "Animals [animalID=" + animalID + ", species=" + species + ", typeOfCage=" + typeOfCage + ", food="
				+ food + ", name=" + name + ", weight=" + weight + "]";
	}
	
	
	
}
