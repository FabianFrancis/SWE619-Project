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

public abstract class Vacation {
	public static final int MAX_MEMBERS = 13;
	public static final int MAX_ACTIVITY = 5;
	public static final int MIN_ACTIVITY = 1;
	public static final double MAX_COST_PER_PERSON = 25.0;
	private String name;
	private String phoneNumber;
	private String destination;
	private String[] activityList;
	private double[] costPerPerson;
	public static int numPeople;
   private int numActivities;
	private int numCosts;
   
	public Vacation() {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.destination = destination;
		this.activityList = new String[MAX_ACTIVITY];
		this.costPerPerson = new double[MAX_ACTIVITY];
		++numPeople;
	}

	/**Validates that the user input is not an empty name
      
      @return true if the user input is a name
   */	
	public boolean setName(String name) {
		if (!isEmpty(name)) {
			this.name = name;
			return true;
		}
		else {
			return false;
		}
	}

	/**Validates that the user input is not an empty number
      
      @return true if the user input is valid
   */		
	public boolean setPhoneNumber(String phoneNumber) {
		if (!isEmpty(phoneNumber) && isValidNumber(phoneNumber) == true) {
			this.phoneNumber = phoneNumber;
			return true;
		}
		else {
			return false;
		}
	}

	/**Validates that the user input is not an empty destination
      
      @return true if the user input is a destination
   */		
	public boolean setDestination(String destination) {
		if (!isEmpty(destination)) {
			this.destination = destination;
			return true;
		}
		else {
			return false;
		}
	}
	
   /**Validates that the user inputted activity does not go over the limit and is not blank
      Validates that the user inputted cost does not go over the limit and is a valid number
   
      @return if the activity and cost entered do not go over the limit
   */   
	public boolean addActivity(String anActivity, double aCost) {
		if (!isEmpty(anActivity) && numActivities < MAX_ACTIVITY && aCost >= 0 && aCost < MAX_COST_PER_PERSON) {
			this.activityList[numActivities] = anActivity;
			this.costPerPerson[numActivities] = aCost;
         ++numActivities;
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getName() {return name;} //@return family member's name
	public String getPhoneNumber() {return phoneNumber;} //@return family member's phone number
	public String getDestination() {return destination;} //@return family member's destination 
	
   //@return a copy of the list of activities
   public String[] getActivityList() {
		String[] tempArray = new String[this.activityList.length];
		
		for (int i = 0; i < this.activityList.length; i++) {
			tempArray[i] = this.activityList[i];
		}
		
		return tempArray;
	}
	
   //@return copy of the cost per person of each activity
   public double[] getCostPerPerson() {
		double[] tempArray = new double[this.costPerPerson.length];
		
		for (int i = 0; i < this.costPerPerson.length; i++) {
			tempArray[i] = this.costPerPerson[i];
		}
		
		return tempArray;
	}
	
	public static int getNumPeople() {return numPeople;}
   
   public static void invalidNumber() {numPeople = numPeople - 1;}//reduces the number of vacations

   /**Validates that the string entered is not empty
   
      @return a flag that tells whether or not the string's length is zero    
   */	
   public boolean isEmpty(String aString) {
		return (aString.trim().length() == 0);
	}

	/**Validates that the user input is a valid input
      
      @return true if the user input phone number is valid
   */ 	
	public boolean isValidNumber(String phoneNumber) {
		if (phoneNumber.length() != 10) {
			return false;
		}
		else {
			for (int x = 0; x < phoneNumber.length(); x++) {
				if (!Character.isDigit(phoneNumber.charAt(x))) {
					return false;
				}
			}
		}
		return true;
	}
   
   //@return the total cost of activities based on the number of family members
	public double calculateActivityCost() {
		double totalActivityCost = 0;
		for (int i = 0; i < activityList.length; i++) {
			totalActivityCost += (costPerPerson[i] * MAX_MEMBERS);
		}
		return totalActivityCost;
	}
	
   //initiates an abstract method for the subclasses to use
	public abstract double calculateTotal();
   
   //@return a report of the family member's name, phone number, destination and activity list and cost 	
	public String toString() {
		String activityReport = "Activity" + "		   " + "Cost" + "\n";
		for (int i = 0; i < numActivities; i++) {
         activityReport += activityList[i] + "		   " + String.format("$%.2f", costPerPerson[i]);
			activityReport += "\n";
		}
		String output = "";
		output += "Name: " + this.getName() + "\n" + 
					"Phone Number: " + this.getPhoneNumber() + "\n" +
					"Destination: " + this.getDestination() + "\n" +
					activityReport;
		return output;
	}
}