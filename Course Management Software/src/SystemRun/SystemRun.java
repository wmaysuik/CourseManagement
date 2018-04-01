package SystemRun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import authenticationServer.AuthenticationToken;
import loggedInUserFactory.LoggedInUserFactory;
import authenticatedUsers.LoggedInAdmin;
import authenticatedUsers.LoggedInInstructor;
import authenticatedUsers.LoggedInStudent;
import authenticatedUsers.LoggedInAuthenticatedUser;

public class SystemRun {
	
	public static void main(String args[]) {
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
               System.out.println("Enter type: ");
               input = br.readLine();
               info[3] = input;
               br.close();
               
               AuthenticationToken token = new AuthenticationToken(info[3]);
               LoggedInUserFactory factory = new LoggedInUserFactory();
               LoggedInAuthenticatedUser user = factory.createAuthenticatedUser(token);
               System.out.println(user.getAuthenticationToken().getUserType());
               
               //System.out.println(token.getUserType());
              
           

       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           if (br != null) {
               try {
                   br.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
}
	
}
