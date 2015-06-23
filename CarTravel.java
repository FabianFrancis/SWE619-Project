/**
   Fabian Francis
   SWE 619
   G#00847710
   
   Description:
   
   This program supports Grandma Robin and is designed to help her organize recommendations for a vacation that her thirteen
   family members have suggested. The program is designed to let the user add a recommendation, display all recommendations
   on record, find the budget recommendation, find the most extravagant recommendation, and to exit the program. If the user 
   chooses to add a recommendation, the program will ask the user if the vacation is by air or by car. If the user chooses by
   air, the program will ask for the family member’s name, phone number, destination for the vacation, a list of activities 
   and their corresponding cost per person, and the cost of the plane ticket.  If the user chooses by car, the program will 
   ask for the family member’s name, phone number, destination for the vacation, a list of activities and their corresponding
   cost per person, the number of miles of the trip, and a list of cars being taken. If the user chooses to display all 
   recommendations on record, the program will display the name, phone number, destination name, list of activities and their
   cost per person, the plane ticket cost if the vacation is by air, the number of miles and the list of cars taken if the 
   vacation is by car, and finally, the total cost of the vacation. If the user chooses to find the budget recommendation, 
   the program will display all the information about a recommendation that has the lowest total vacation cost. Likewise, if 
   the user chooses to find the most extravagant recommendation, the program will display all the information about a 
   recommendation that has the highest total vacation cost. If the user chooses to quit the application, the program will 
   display a message that counts the number of vacations by car and by air. Additionally, the program will display if a 
   vacation by car has a BMW under the car list. 
   
   The user is required to input:
      name
      phoneNumber
      destination
      activity
      cost
      planeTicketCost
      miles
      car

   For the menu option Print All Recommendations, the output will be:
      name
      phone
      destination
      activityList
      costPerPerson
      planeTicketCost
      miles
      carList
      total
      
   For the menu option Print Budget Recommendation, the output will be:
      name
      phone
      destination
      activityList
      costPerPerson
      planeTicketCost
      miles
      carList
      total
         
   For the menu option Print Extravagant Recommendation, the output will be:
      name
      phone
      destination
      activityList
      costPerPerson
      planeTicketCost
      miles
      carList
      total   
   
   For the menu option Quit, the output will be:
      numAirTravel
      numCarTravel
      
*/

public class CarTravel extends Vacation {
	public static final int MAX_MILES = 425;
	public static final double TAX_RATE = .575;
	public static int numCarTravel;
	private int miles;
	private String[] carList;
   private int numCars;
	
	public CarTravel() {
		super();
		this.miles = miles;
		this.carList = new String[MAX_MEMBERS];
		++numCarTravel;
	}

	/**Validates that the user input miles is valid
      
      @return true if the miles amount is less than the maximum amount of miles
   */		
	public boolean setMiles(int miles) {
		if (miles > 0 && miles <= MAX_MILES) {
			this.miles = miles;
			return true;
		} 
		else {
			return false;
		}
	}
	
   /**Validates that the user inputted car does not go over the limit and is not blank
   
      @return if the car entered does not go over the limit
   */     
	public boolean addCar(String aCar) {
		if (!super.isEmpty(aCar) && numCars < MAX_MEMBERS) {
			this.carList[numCars] = aCar;
			++numCars;
			return true;
		}
		else {
			return false;
		}
	}
	
	public int getMiles() {return miles;} //@return amount of miles for trip
	
   //@return copy of list of cars
   public String[] getCarList() {
		String[] tempArray = new String[carList.length];
		for (int i = 0; i < carList.length; i++) {
			tempArray[i] = carList[i];
		}
		return tempArray;
	}
	
	public static int getNumCarTravel() {return numCarTravel;} //@return number of vacations by car
	
   //@return cost of miles for trip times standard tax rate and the total cost of activities
	public double calculateTotal() {
		return (((this.getMiles() * TAX_RATE) * numCars)  + super.calculateActivityCost());
	}
	
   //@return list of cars taken
   public String carListToString() {
		String carReport = "";
		for (int i = 0; i < numCars; i++) {
         carReport += carList[i]; 
			carReport += "\n";          
		}
      return carReport;   
   }
   
   public static void invalidCarNumber() {numCarTravel = numCarTravel - 1;} //reduces the number of vacations by car
   
   /*@return a report of family member's name, phone number, destination and activity list and cost, miles for trip, list of
         cars, and total cost of vacation
   */
	public String toString() {
		String output = "";
		output = super.toString() + "\n" +
					"Number of Miles: " + this.getMiles() + "\n" +
					"Cars Taken: " + "\n" + this.carListToString() + "\n" + 
					"Total Cost of Vacation: " + String.format("$%.2f", this.calculateTotal());
      return output;
	}
}