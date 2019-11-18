import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileReader;

public class ATM {
	
	//global variables
	String userID_global = "";
	String userPIN_global = "";
	String accBalance_global = "";
	String[] infoInFile = null;
	String[] infoInStock = null;
	
	public ATM() {

	//--------------------------------
	//--------------------------------
	//CALLING METHODS
	//--------------------------------
	//--------------------------------
	
	welcome();
	login();
	}

	//---------------------------------
	//---------------------------------
	//---------WELLCOME MESSAGE--------
	//---------------------------------
	//---------------------------------
	

	public void welcome() {
	
	System.out.println("Wellcome to the NTD Bank");
	}
	
	
	//===============================
	//========== LOGIN ==============
	//===============================
	
	public void login() {
		
	//--------------------------------
	// ASK THE USER FOR THEIR ID
	//--------------------------------
		
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//CREATE VARIABLE FIRST (CHANGE SCOOP)
	
	String input = "";
		
	try {
		
		boolean valid = false;
		
		do {
	      System.out.println("Please enter your ID: ");
	      input = br.readLine();
		
			if(input.matches("[0-9]+")) {
			  	  valid = true;
			} else {
			 	valid = false;
			}
			}while(valid == false);
		
	}catch(Exception e) { System.out.println("Error reading input");}
	
	//--------------------------------------
	//--------------------------------------
	//GET  THE USER PIN
	//--------------------------------------
	//--------------------------------------
	
	BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
	String input2 = "";
	try {
		
		boolean valid2 = false;
		
		do {
	      System.out.println("Please enter your password: ");
		  input2 = br2.readLine();
		
			if(input2.matches("[0-9]+")) {
			  	  valid2 = true;
			} else {
			 	valid2 = false;
			}
		
		
		}while(valid2 == false);
		
	}catch(Exception e) { System.out.println("Error reading input");}
	
	//----------------------------------------
	//----------------------------------------
	//--------FILE NAME INTRODUTION-----------
	//----------------------------------------
	//----------------------------------------
	
	try {
	BufferedReader br3 = new BufferedReader(new FileReader(input + ".txt"));
	
	String userID = br3.readLine();
	String userPIN = br3.readLine();
	String accBalance = br3.readLine();
	
	br3.close ();
	
	//set the global variables
	userID_global = userID;
	userPIN_global = userPIN;
	accBalance_global = accBalance;
	
	
	if (userID.equals(input) && userPIN.equals(input2)) {
		//GOES OK
		menu();
		menuSelect();
		
	} else {
		System.out.println("Login failed, try again");
		login();
		}
	
	}catch(Exception e) { System.out.println(e);
	}
	}
	
	
	//===========================
	//========= MENU ============
	//===========================
	public void menu() {
		System.out.println("1. Account Balance");
		System.out.println("2. Withdraw Money");
		System.out.println("3. Change your password");
		System.out.println("4. Check the latest stock prices");
	}
	
	
	/* ==============================
	=========== MENU SELECT =========
	================================= */
	
	public void menuSelect() {
	BufferedReader br4 = new BufferedReader(new InputStreamReader(System.in));
	String input = "";
	
	//move the input variable to the top
	//so we can see it later after the catch
	
	try {
		boolean valid4 = false;
		do {
			System.out.println("Please enter a number: ");
			input = br4.readLine();
			if(input.matches("[0-9]+")) {
				valid4 = true;
				} else {
					valid4 = false;
					}
			}while(valid4 == false);
		}catch(Exception e) { System.out.println("Error reading input");
		}
		
	if(input.equals("1")) {
		checkAccBalance();
		}
	else if(input.equals("2")) {
		withdrawMoney();
		}
	else if(input.equals("3")) {
		changePIN();
		}
	else if(input.equals("4")) {
		stockPrice();
	}
	//it is the only one isn't print any message for the costumer, because it is a "secret" input
	else if (input.equals("6")) {
		bankSummary();
	}
	}
	
	//===============================
	//========= BACK TO MENU ========
	//===============================
	
	public void backToMenu() {
		BufferedReader br5 = new BufferedReader(new InputStreamReader(System.in));
		String input5 = "";
		
		try {
			boolean valid5 = false;
			do {
				System.out.println("Press Y to return to menu: ");
				System.out.println("Press N to logout: ");
				input5 = br5.readLine();
				if(input5.matches("[a-z]+")) {
					valid5 = true;
					} else {
						valid5 = false;
						}
				}while(valid5 == false);
			}catch(Exception e) { System.out.println("Error reading input");
			}
		
		//send the user off
		
		if(input5.equals("y")) {
			menu();
			menuSelect();
			}
		
		if(input5.equals("n")) {
			logout();
			}
	}
	
	/* ==============================
	 ======= CHECK BALANCE ==========
	 ==============================*/
	
	public void checkAccBalance() {
		System.out.println("Your Balance is: " + accBalance_global);
		backToMenu();
		
		}
	
	//==============================
	//======= WITHDRAW MONEY =======
	//==============================

	public void withdrawMoney() {
		System.out.println("Withdraw Money... How much?");		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		
		//move the input variable to the top
		//so we can see it later after the catch
		
		try {
			boolean valid = false;
			do {
				System.out.println("Please enter a number (20, 50, 100): ");
				input = br.readLine();
				
				if(input.matches("[0-9]+")) {
					valid = true;
					} else {
						valid = false;
						}
				}while(valid == false);
			}catch(Exception e) { System.out.println("Error reading input");
			}
				
		Double answer = Double.parseDouble(accBalance_global) - Double.parseDouble(input);
		accBalance_global = String.valueOf(answer);
		
		System.out.println("New balance: " + accBalance_global);
		
		NewBalance();
		//link to go back to Menu and Menu Select
		//make the code more efficient
		backToMenu();
	}
	
	public void NewBalance() {
		// Load the file and save it in array
		loadingFile();
		// The line will be modify
		int lineToModify = Integer.parseInt("3");
		infoInFile[lineToModify - 1] = accBalance_global;
		// Re-Writing the file
		writingIntoFile();
	}
       
	//============================
	//===== CHANGE PASSWORD ======
	//============================
	
	public void changePIN() {
		// Load the file and save it in array
		loadingFile();
		
		// The line will be modify
		int lineToModify = Integer.parseInt("2");
			
		// Ask the new info
		System.out.println("Please enter your new four-digit password");
		String newValue = askingUser();
		
		
		System.out.println("Please enter again your new four-digit password");
		String newValue2 = askingUser();
		
		if (newValue.equals(newValue2)){
			//Change the info in the array
			infoInFile[lineToModify - 1] = newValue;
			
			// Re-Writing the file
			writingIntoFile();
		}
		else if (newValue != newValue2){
			System.out.println("Your password isn't matching. Please try again.");
		}
		
		backToMenu();
	}
       
        // Printing the new info in the file
	public void writingIntoFile() {
		
		try {
			PrintWriter writer = new PrintWriter(userID_global + ".txt");
			
			for (int i = 0; i < 3 ; i++) {
				writer.println(infoInFile[i]);
				}
			writer.close();
			} 
		
		catch (Exception e) {
				e.printStackTrace();
				}
		}
       
        // Reading input from user 
	public String askingUser() {
		
		BufferedReader br7 = new BufferedReader (new InputStreamReader (System.in));
		String answer7 = "";
		try {
			answer7 = br7.readLine();
			}catch (Exception e) {
				
			}return answer7;
			}
       
        // Loading the file and save it as an array
	public void loadingFile() {
		infoInFile = new String[3];
		try {
			BufferedReader br8 = new BufferedReader (new FileReader(userID_global + ".txt"));
			for (int i = 0; i < 3; i++) {
				infoInFile[i] = br8.readLine();
				
			}
			br8.close();
			} catch (Exception e) {
				System.out.println("I can't find that file");
			}
		}
	
	//===================================
	//========= STOCK PRICE =============
	//===================================

	public void stockPrice() {
		loadingStockFile();
        showingStockInfo();
		backToMenu();
	}
	
	public void loadingStockFile() {
        
        infoInStock = new String[6];
       
        try {
                      BufferedReader br = new BufferedReader (new FileReader("stocks.txt"));
                     
                      for (int i = 0; i < 6; i++) {
                    	  infoInStock[i] = br.readLine();
                                   
                      }
                     
                      br.close();
        } catch (Exception e) {
                      System.out.println("I can't find that file");
        }
       
}
	public void showingStockInfo() {
        for (int i = 0; i < 6; i++) {
                      System.out.println(infoInStock[i]);
        }
}
	//==============================
	//======== LOGOUT ==============
	//==============================
	
	public void logout() {
		System.out.println("Thank you to use the NTD Bank!" );
	}
	
	//===========================
	//===== Bank Summary ========
	//===========================
	public void bankSummary() {
		int files = 100;
	    double some = 0;
		int i = 0;
		int f = 0;
		int g = 0;
		int h = 0;
		while (files < 1000) {
			
			//open files
			try {
				BufferedReader br = new BufferedReader (new FileReader(files + ".txt"));
				
				String userID = br.readLine();
				String userPIN = br.readLine();
				String accBalance = br.readLine();
				br.close ();
				
				//set again the global variables
				userID_global = userID;
				userPIN_global = userPIN;
				accBalance_global = accBalance;
				
				//Do the calc
	        	some = some + Double.parseDouble(accBalance_global);
				
	        }
	        catch (Exception e) {
	                      System.out.println("I can't find that file");
	        }
			//Determined which is the condition
			if (Double.parseDouble(accBalance_global) <= 100){
				i++;
	    	}
	    	if (Double.parseDouble(accBalance_global) > 100 && Double.parseDouble(accBalance_global) <= 200){
	    		f++;
	    		}
	    	if (Double.parseDouble(accBalance_global) > 200 && Double.parseDouble(accBalance_global) <= 300){
				g++;
				}
	    	if (Double.parseDouble(accBalance_global) > 300){
				h++;
				}files = files + 100;
			}
		System.out.println("Total account balance for entire bank is: "+ some);
		System.out.println("Small accounts: " + i);
		System.out.println("Medium accounts: " + f);
		System.out.println("Large accounts: " + g);
		System.out.println("Extra-large accounts: " + h);
	}
				
	public static void main(String[] args) {

		
		
        new ATM();
	}
}
