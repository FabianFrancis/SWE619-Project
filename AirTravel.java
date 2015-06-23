/**
   Fabian Francis
   SWE 619
   G#00847710
   
   Description:
   
   This program supports Grandma Robin and is designed to help her organize recommendations for a vacation that her thirteen
   family members have suggested. The program is designed to let the user add a recommendation, display all recommendations
   on record, find the budget recommendation, find the most extravagant recommendation, and to exit the program. If the user 
   chooses to add a recommendation, the program will ask the user if the vacation is by air or by car. If the user chooses by
   air, the program will ask for the family member�s name, phone number, destination for the vacation, a list of activities 
   and their corresponding cost per person, and the cost of the plane ticket.  If the user chooses by car, the program will 
   ask for the family member�s name, phone number, destination for the vacation, a list of activities and their corresponding
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

public class AirTravel extends Vacation {
	public static final double MAX_TICKET_COST = 419.0;
	public static final double MIN_TICKET_COST = 79.0;
	public static int numAirTravel;
	private double ticketCost;
	
	public AirTravel() {
		super();
		this.ticketCost = ticketCost;
		++numAirTravel;
	}

	/**Validates that the plane ticket cost is valid
      
      @return true if the plane ticket cost is between the maximum and minimum ticket cost, inclusive
   */		
	public boolean setTicketCost(double ticketCost) {
		if (ticketCost >= MIN_TICKET_COST && ticketCost <= MAX_TICKET_COST) {
			this.ticketCost = ticketCost;
			return true;
		}
		else {
			return false;
		}
	}
	
	public double getTicketCost() {return ticketCost;} //@return cost of plane ticket
	public static int getNumAirTravel() {return numAirTravel;} //@return number of air travel vacations
   
   public static void invalidAirNumber() {numAirTravel = numAirTravel - 1;} //reduces the number of vacations by air
	
   //@return cost of plane ticket for each family member and the cost of all activities
	public double calculateTotal() {
		return (this.getTicketCost() * MAX_MEMBERS + super.calculateActivityCost());
	}
	
   /*@return a report of family member's name, phone number, destination and activity list and cost, plane ticket cost, and
      total cost of vacation
   */ 	
	public String toString() {
		String output = "";
		output = super.toString() + "\n" +
					"Ticket Cost: " + String.format("$%.2f", this.getTicketCost()) + "\n" +
					"Total Cost of Vacation: " + String.format("$%.2f", this.calculateTotal());
		return output;
	}
}