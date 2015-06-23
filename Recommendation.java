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

import javax.swing.JOptionPane;
public class Recommendation {
	public static void main(String[] args) {
		int LIMIT = 13;
		Vacation[] memberArray = new Vacation[LIMIT];
		int choice = getChoice();
		
		while (choice != 6) {
			switch(choice) {
				case 1:
               //Adds a recommendation
					addInfo(memberArray);
					break;
				case 2:
               //Prints a report of all recommendations
					printAll(memberArray);
					break;
				case 3:
               //Prints recommendation with the lowest total vacation cost
					printLowest(memberArray);
					break;
				case 4:
				//Prints recommendation with the highest total vacation cost
					printHighest(memberArray);
					break;
				case 5:
				//Prints number of air and car vacations as well as if there is a BMW on record, then quits program
					quit(memberArray);
               choice = 6;
					break;
			}
         if (choice == 6) {
            break;
         }
         else {
            choice = getChoice();
		   }
      }				
	}
	
	private static void addInfo(Vacation[] memberArray) {
		int position = Vacation.getNumPeople();
		
		if (position < memberArray.length) {
			Object[] options = {"Air Travel", "Car Travel"};
			Vacation tempVacation;
			
			//Displays a menu with option buttons that the user can click on, either Air Travel or Car Travel
			int reply = JOptionPane.showOptionDialog(null,
    		"What type of vacation would you like?","A New Recommendation",
    		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
    		null,options,options[0]);
			
			if (reply == JOptionPane.YES_OPTION) {
            tempVacation = new AirTravel();
				AirTravel aVacation = (AirTravel)tempVacation; 
				
				//Continuously validates that the name the user input is a valid input (i.e. not blank)
				String memberName = "";
				do {
					memberName = JOptionPane.showInputDialog("Enter the name of the family member.");
					if (!aVacation.setName(memberName)) {
						JOptionPane.showMessageDialog(null, "Error! Please enter a valid name.");
					}
				} while(!aVacation.setName(memberName));
				
            boolean valid = true;	
				String memberNumber = "";
				do {
					memberNumber = JOptionPane.showInputDialog(
					"Enter a phone number for " + aVacation.getName() + " in the format XXXXXXXXXX");
               //Validates that the phone number is in the correct format and is not blank
					if (!aVacation.setPhoneNumber(memberNumber)) {
					   JOptionPane.showMessageDialog(null, "Error! Please enter a phone number in the right format.");
					}
               else {
                  //Validates that the phone number entered by the user does not match another phone number on record
                  for (int i = 0; i < Vacation.getNumPeople(); i++) {
                     if (memberArray[i] != null) {
                        String keyword = memberArray[i].getPhoneNumber();
                        if (memberNumber.equals(keyword)) {
                           JOptionPane.showMessageDialog(null, "Error! This phone number is already being used.");
							      JOptionPane.showMessageDialog(null, "Application Rejected.");
							      valid = false;
                           break;  
                        }                     
                     }
                  }
                  if (valid == false) {
                     Vacation.invalidNumber();
                     AirTravel.invalidAirNumber();
                     return;
                  }                  
               }
				} while (!aVacation.setPhoneNumber(memberNumber));
				
				//Continuously validates that the destination the user input is a valid input (i.e. not blank)
				String memberDestination = "";
				do {
					memberDestination = JOptionPane.showInputDialog("Enter the name of the recommended destination: ");
					if (!aVacation.setDestination(memberDestination)) {
						JOptionPane.showMessageDialog(null, "Error! Please enter a valid name.");
					}
				} while (!aVacation.setDestination(memberDestination));
				
				//Prompts the user to enter an activity and its corresponding cost per person
				String memberActivity = "";
				double memberCost = 0;
				do {
					memberActivity = JOptionPane.showInputDialog("Enter the name of the activity: ");
					try {
					   memberCost = Double.parseDouble(JOptionPane.showInputDialog("Enter the cost per person of the activity: "));
					}
					catch (NumberFormatException e){}
					if (!aVacation.addActivity(memberActivity, memberCost)) {
					   JOptionPane.showMessageDialog(null, "Invalid activity!");
					}						
				} while (JOptionPane.showConfirmDialog(null, "Enter another activity?") == JOptionPane.YES_OPTION);
				
				//Continuously validates that the user entered cost is a valid input (i.e. not a negative number or a character)
				double planeTicketCost = 0;
				do {
					try {
						planeTicketCost = Double.parseDouble(JOptionPane.showInputDialog("Enter the cost of the plane ticket: "));
					}
					catch (NumberFormatException e) {
						planeTicketCost = -1;
					}
					if (!aVacation.setTicketCost(planeTicketCost)) {
						JOptionPane.showMessageDialog(null, "Error! Please enter a valid cost.");
					}
				} while (!aVacation.setTicketCost(planeTicketCost));
				
				JOptionPane.showMessageDialog(null, "Recommendation Approved");
				memberArray[position] = aVacation;
			}
			else if (reply == JOptionPane.NO_OPTION) {
            tempVacation = new CarTravel();
				CarTravel aVacation = (CarTravel)tempVacation;
				
				//Continuously validates that the name the user input is a valid input (i.e. not blank)
				String memberName = "";
				do {
					memberName = JOptionPane.showInputDialog("Enter the name of the family member.");
					if (!aVacation.setName(memberName)) {
						JOptionPane.showMessageDialog(null, "Error! Please enter a valid name.");
					}
				} while(!aVacation.setName(memberName));
				
            boolean valid = true;
				String memberNumber = "";
				do {
					memberNumber = JOptionPane.showInputDialog(
					"Enter a phone number for " + aVacation.getName() + " in the format XXXXXXXXXX");
               //Validates that the phone number is in the correct format and is not blank
					if (!aVacation.setPhoneNumber(memberNumber)) {
					   JOptionPane.showMessageDialog(null, "Error! Please enter a phone number in the right format.");
					}
               else {
                  //Validates that the phone number entered by the user does not match another phone number on record
                  for (int i = 0; i < Vacation.getNumPeople(); i++) {
                     if (memberArray[i] != null) {
                        String keyword = memberArray[i].getPhoneNumber();
                        if (memberNumber.equals(keyword)) {
                           JOptionPane.showMessageDialog(null, "Error! This phone number is already being used.");
							      JOptionPane.showMessageDialog(null, "Application Rejected.");
							      valid = false;
                           break;  
                        }                     
                     }
                  }
                  if (valid == false) {
                     Vacation.invalidNumber();
                     CarTravel.invalidCarNumber();
                     return;
                  }                  
               }
				} while (!aVacation.setPhoneNumber(memberNumber));

				//Continuously validates that the destination the user input is a valid input (i.e. not blank)
				String memberDestination = "";
				do {
					memberDestination = JOptionPane.showInputDialog("Enter the name of the recommended destination: ");
					if (!aVacation.setDestination(memberDestination)) {
						JOptionPane.showMessageDialog(null, "Error! Please enter a valid name.");
					}
				} while (!aVacation.setDestination(memberDestination));
            	
				//Prompts the user to enter an activity and its corresponding cost per person
				String memberActivity = "";
				double memberCost = 0;
				do {
					memberActivity = JOptionPane.showInputDialog("Enter the name of the activity: ");
					try {
					   memberCost = Double.parseDouble(JOptionPane.showInputDialog("Enter the cost per person of the activity: "));
					}
					catch (NumberFormatException e){}
					if (!aVacation.addActivity(memberActivity, memberCost)) {
					   JOptionPane.showMessageDialog(null, "Invalid activity!");
					}						
				} while (JOptionPane.showConfirmDialog(null, "Enter another activity?") == JOptionPane.YES_OPTION);
				
				//Continuously validates that the user entered miles are valid (i.e. not a negative number or an invalid character)
				int memberMiles = 0;
				do {
					try {
						memberMiles = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of miles for the trip: "));
					}
					catch (NumberFormatException e) {
						memberMiles = -1;
					}
					if (!aVacation.setMiles(memberMiles)) {
						JOptionPane.showMessageDialog(null, "Error! Please enter a valid number.");
					}
				} while (!aVacation.setMiles(memberMiles));
				
				//Prompts for a list of cars taken for the trip
				String carMake = "";
				do {
					carMake = JOptionPane.showInputDialog("Enter the make of the car (i.e. Honda, Ford, etc.)");
					//Continuously validates that the car entered is a valid input (i.e. not blank)
					if (!aVacation.addCar(carMake)) {
						JOptionPane.showMessageDialog(null, "Invalid Entry!");
					}
				} while (JOptionPane.showConfirmDialog(null, "Enter another car?") == JOptionPane.YES_OPTION);
				
				JOptionPane.showMessageDialog(null, "Recommendation Approved");
				memberArray[position] = aVacation;				
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Error! The number of recommendations allowed has been reached.");
		}
	}
	
	private static void printAll(Vacation[] memberArray) {
		String output = "";
		if (Vacation.getNumPeople() > 0) {
			for (int i = 0; i < Vacation.getNumPeople(); i++) {
				if (memberArray[i] != null) {
					output += (i + 1) + ". " + memberArray[i] + "\n\n";
				}
			}
			JOptionPane.showMessageDialog(null, output);
		}
		else {
			JOptionPane.showMessageDialog(null, "Error! There are no recommendations on record! Please enter a recommendation first.");
		}
	}
	
	private static void printLowest(Vacation[] memberArray) {
		String output = "";
      if (Vacation.getNumPeople() > 0) {
			double lowest = 9999;
			//Determines which vacation has the lowest total cost
			for (int i = 0; i < Vacation.getNumPeople(); i++) {
				Vacation temp = memberArray[i];
				if (temp instanceof AirTravel) {
					AirTravel tempAir = (AirTravel)temp;
					if (tempAir.calculateTotal() < lowest) {
						lowest = tempAir.calculateTotal();
						output = tempAir.toString();
					}
				}
				else if (temp instanceof CarTravel) {
					CarTravel tempCar = (CarTravel)temp;
					if (tempCar.calculateTotal() < lowest) {
						lowest = tempCar.calculateTotal();
						output = tempCar.toString();
					}
				}
			}
			JOptionPane.showMessageDialog(null, output);
		}
		else {
			JOptionPane.showMessageDialog(null, "Error! There are no recommendations on record! Please enter a recommendation first.");
		}
	}
	
	private static void printHighest(Vacation[] memberArray) {
		String output = "";
      if (Vacation.getNumPeople() > 0) {
			double highest = -1;
			//Determines which vacation has the highest total cost
			for (int i = 0; i < Vacation.getNumPeople(); i++) {	
				Vacation temp = memberArray[i];
				if (temp instanceof AirTravel) {
					AirTravel tempAir = (AirTravel)temp;
					if (tempAir.calculateTotal() > highest) {
						highest = tempAir.calculateTotal();
						output = tempAir.toString();
					}
				}
				else if (temp instanceof CarTravel) {
					CarTravel tempCar = (CarTravel)temp;
					if (tempCar.calculateTotal() > highest) {
						highest = tempCar.calculateTotal();
						output = tempCar.toString();
					}
				}
			}
         JOptionPane.showMessageDialog(null, output);				
		}
		else {
			JOptionPane.showMessageDialog(null, "Error! There are no recommendations on record! Please enter a recommendation first.");
		}		
	}
	
	private static void quit(Vacation[] memberArray) {
		String output = "";
		if (Vacation.getNumPeople() > 0) {
			output = "Number of Vacations by Air: " + AirTravel.getNumAirTravel() + "\n" +
						"Number of Vacations by Car: " + CarTravel.getNumCarTravel() + "\n\n";
			//Determines if a vacation by car has a BMW as one of the cars
			for (int i = 0; i < Vacation.getNumPeople(); i++) {
				Vacation temp = memberArray[i];
				if (temp instanceof CarTravel) {
					CarTravel tempCar = (CarTravel)temp;
               String keyword = "BMW";
				   if (tempCar.carListToString().indexOf(keyword) != -1) {
					   output += "**A car vacation has a BMW as one of the cars.**";
				   }
				}
			}
         JOptionPane.showMessageDialog(null, output);
		}
		else {
			JOptionPane.showMessageDialog(null, "No recommendations were made.");
		}
	}
	
	private static int getChoice() {
		int choice;
		
      //Continuously validates that the choice that the user entered is a valid input (i.e. not a negative number or character)
		do {
			try {
				choice = Integer.parseInt(JOptionPane.showInputDialog(
					"What would you like to do?"
					+ "\n 1: Add Recommendation"
					+ "\n 2: Print All Recommendations"
					+ "\n 3: Print Budget Recommendation"
					+ "\n 4: Print Extravagant Recommendation"
					+ "\n 5: Quit"));
			}
			catch (NumberFormatException e) {
				choice = 0;
			}
			if (choice < 1 || choice > 5) {
				JOptionPane.showMessageDialog(null, "Error! Please enter a valid menu option.");
			}
		} while (choice < 1 || choice > 5);
		
		return choice;
	}
}