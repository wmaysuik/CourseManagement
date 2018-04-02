package SystemRun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import authenticationServer.AuthenticationToken;
import loggedInUserFactory.LoggedInUserFactory;
import authenticatedUsers.LoggedInAuthenticatedUser;

public class SystemRun {
	
	public static void main(String args[]) {
		checkLogin();	//Make sure that the user presses L to log in 
		
		String [] userInfo = logInUser(); //Log the user in 
		AuthenticationToken token = new AuthenticationToken(userInfo[3]);
		LoggedInUserFactory factory = new LoggedInUserFactory();
		LoggedInAuthenticatedUser user = factory.createAuthenticatedUser(token);
		//Here I want to print a text file to the console, if not then i wil make a string notifying what operations they can do as this role
		if(user.getAuthenticationToken().getUserType().equals("Admin"))
			printOp("AuthAdminOperations.txt");
		else if(user.getAuthenticationToken().getUserType().equals("Instructor"))
			printOp("AuthInsturctorOperations.txt");
		else if(user.getAuthenticationToken().getUserType().equals("Student"))
			printOp("AuthStudentOperations.txt");
		else
			System.out.println("Error"); //change this to an error text file 
		
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
	               System.out.println("Enter position (Student, Admin, Instructor): ");
	               input = br.readLine();
	               info[3] = input;
	               br.close();
	              
	           
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
	
	
}
