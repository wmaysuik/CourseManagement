package SystemRun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import SystemState.System_State;
import authenticationServer.AuthenticationToken;
import loggedInUserFactory.LoggedInUserFactory;
import authenticatedUsers.LoggedInAuthenticatedUser;
import authenticatedUsers.LoggedInInstructor;
import authenticatedUsers.LoggedInStudent;
import authenticatedUsers.LoggedInAdmin;

public class SystemRun {
	
	public static void main(String args[]) {
		checkLogin();	//Make sure that the user presses L to log in
		while(System_State.state == 1) {
			String [] userInfo = logInUser(); //Log the user in 
			if(userInfo[3].equals("invalid")) {
				System.out.println("Invalid ID");
				break;
			}
			AuthenticationToken token = new AuthenticationToken(userInfo[3]);
			LoggedInUserFactory factory = new LoggedInUserFactory();
			LoggedInAuthenticatedUser user = factory.createAuthenticatedUser(token);
			user.setName(userInfo[0]);
			user.setSurname(userInfo[1]);
			user.setID(userInfo[2]);
			//Here I want to print a text file to the console, if not then i wil make a string notifying what operations they can do as this role
			if(user.getID().charAt(0) == '0') {
				printOp("AuthAdminOperations.txt");
				LoggedInAdmin authenticatedAdmin = (LoggedInAdmin) user;
				adminOperations(authenticatedAdmin); //function which perform the admin operations 
			}
			else if(user.getID().charAt(0) == '1') {
				printOp("AuthInsturctorOperations.txt");
				LoggedInInstructor authenticatedInstructor = (LoggedInInstructor) user;
				break;
				//use instructor operations
			}
			else if(user.getID().charAt(0) == ('2')) {
				printOp("AuthStudentOperations.txt");
				LoggedInStudent authenticatedStudent = (LoggedInStudent) user;
				break;
				//use student operations 
			}
			else 
				System.out.println("Error"); //change this to an error text file 
		}
		
		exitMessage();
	}
	
	
	
	private static String[] logInUser() {
		String [] info = new String[4];
		 BufferedReader br = null;
	
	       try {
	
	           br = new BufferedReader(new InputStreamReader(System.in));
	           	System.out.println("Enter first name: ");
	               String input = br.readLine();
	               info[0] = input;
	               System.out.println("Enter last name: ");
	               input = br.readLine();
	               info[1] = input;
	               System.out.println("Enter ID: ");
	               input = br.readLine();
	               info[2] = input;
	               
	            switch(info[2].charAt(0)) {
		       		case '0':
		       			info[3] = "Admin";
		       			break;
		       		case '1':
		       			info[3] = "Instructor";
		       			break;
		       		case '2': 
		       			info[3] = "Student";
		       			break;
		       		default: 
		       			info[3] = "invalid";
		       			break;
	       		}
	              
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	       return info;
	}
	
	
	private static void checkLogin() {
		BufferedReader br = null;
		boolean log = false;
		 try {
			 br = new BufferedReader(new InputStreamReader(System.in));
			 	while(!log){
			 		System.out.println("To begin login, press 'L'");
			 		String input = br.readLine();
			 		if(input.equals("L"))
			 			log = true;
			 		else
			 			System.out.println("Invalid key, please try again.");
			 	}
		                
	     } catch (IOException e) {
	           e.printStackTrace();
	       }
	}
	
	private static void printOp(String file) {
		try {
			System.out.println(new String(Files.readAllBytes(Paths.get(file))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void adminOperations(LoggedInAdmin user) {
		boolean login = true;
		while(login && System_State.state == 1) { //while the user is logged in, and the system is in a running state, they are able to pe
			BufferedReader br = null; 	
			try {
				 br = new BufferedReader(new InputStreamReader(System.in));
				 String input = br.readLine();
				 switch(input) {
				 case "1":
					 user.StartSystem();
					 break;
				 
				 case "2": //put a print statement at the end of the main function, once 
					 user.StopSystem();
					 break;
					 
				 case "3": 
					 user.readCourseFiles();
					 System.out.println("Course file updated");
					 break;
				 case "Logout":
					 login = false;
					 System.out.println("You have been logged out");
					 break;
				 default: 
					 System.out.println("Invalid key entry, please try again");
					 break;
				 }	
			}
			catch (IOException e) {
		           e.printStackTrace();
		       }
			
		}
	}
	
	private static void exitMessage() { //writes an exit method, given how it reaches the end of the 
		if(System_State.state == 0)
			System.out.println("Session terminated");
		else
			System.out.println("Logged out");
	}
	
	
}
