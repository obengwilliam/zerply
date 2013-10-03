/*
//     _ +------------------------------+ _
//    /o)|  ZERPLY PROJECT               |(o\
//   / / |       obeng william           | \ \
//  ( (_ |  _                         _  | _) )
// ((\ \)+-/o)-----------------------(o\-+(/ /))
// (\\\ \_/ /                         \ \_/ ///)
//  \      /                           \      /
//   \____/                             \____/
*/






import static java.lang.System.out;
import java.util.*;
import java.io.*;



public class AdvancedZerplyAssignment
{



	public static void main(String[] args) throws IOException
	{
		//Here we begin by calling the UserMenu
		out.println(userMenu());
		

	}

    //declaring static variables;
    static String firstname;
    static String lastname;
    static String emailaddress;
    static String password;
    static String confirmedpassword;


    static String twitterHandle;
	static String githubUsername;
	static String  websiteName;
 	static String universityName;
	static String majorName;
    static String currentCompany;
    static String currentTittle;
    static String phoneNumber;
        		

   

	//this method AT LEAST welcomes the user and takes the initial user input
	static String userMenu( ) throws IOException
	{
       
        

		switch(askUser("welcome. Are you a new user ?(y/n)").toLowerCase())
		{
			case "y":
			    //asking and storing all necessary details in memory
				askuserInfo_("f");
				 
                //returns string : "" when profile does not pass validation
				String userinfo=storeUserInfo();

				//storing user profie info
				if(userinfo!=" ")
					{

						outputInfo(userinfo,"info.csv");
						//storing user details
						outputInfo(storeUser(emailaddress,password,firstname,lastname),"out.csv");
					}
				
                break;

			case "n":
				  askExistingUserInfo();


			      //printing user's profile to screen
				  printUserProfile("info.csv",emailaddress);
		          
	    }

		


		return "Thank you";
	}


    
	//this method takes user provided email, password, and user info and checks it against the data from the user.csv file
    static boolean UserLogin(String email, String password) throws IOException
    {
     
	  if(CheckExistingEmailPassword(emailaddress,password)==false)
	  {
	      return false;
	  }
	    
	  return true;

    }

   
    //validating email
    static boolean validateEmail( String email)
	{
		String user_input=email;


		if(user_input.matches("^([a-zA-Z0-9]+)(\\.[A-Za-z0-9]+)*@([a-zA-Z0-9-]+)(\\.([a-zA-Z]{2,}))+$"))
		{
			return true;

		}
		return false;
	}




	//this method checks to see if the user logging in exists in the input file already (make sure you check for cases where users.csv is empty!)

	static boolean CheckExistingUser(String email) throws IOException
	{

	 	    String [] user_records=readInputFile("out.csv");
			
			for(String record: user_records)
				{
					String user[]=new String [5];
					user=record.split(",");

					if (user[0].equals(email))
					{
					    
						return true;
					}
				}
				
			return false;
	}

	//this method checks if the two user entered passwords are the same
	static boolean validatePassword( String password,String confirmedpassword)
	{
		return password.equals(confirmedpassword);
	}




    //	//this method takes a user provided email and the info.csv file object, pulls the profile info of the user with the provided email
    static void printUserProfile(String filename , String email) throws IOException
   {
	 String [] user_records=readInputFile("info.csv");
		
		for(String record: user_records)
			{
				String user[]=record.split(",");

				if (user[0].equals(email))
				{
				    
					out.println("Twitter :" + user[1]);
					out.println("Github :" + user[2]);
					out.println("website:" + user[3]);
					out.println("University :" + user[4]);
					out.println("Major :" + user[5]);
					out.println("Company:" + user[6]);
					out.println("Title:" + user[7]);
					out.println("Phone Number :" + user[8]);

				
			    }
            }
    }


   //this method reads existing data from user information and users files
 	static String [] readInputFile(String filename)	 throws IOException 
 	{
	
	
		File file=new File(filename);

		Scanner in= new Scanner(file);

		String records="";

		while(in.hasNextLine())
		{
			records=records + in.nextLine();
			
		}
			//separate all the records into an array of individual records by using the .split() method (each record is separated by a semi colon)
	    String recordsArray[]=records.split(";");
		//return the array of individual records

		return recordsArray;
	    }




	//this method takes user input for new users and stores it in a comma separated String
	static String storeUser(String emailaddress,String password,String firstname,String lastname)

	{
		
	    String newUser=String.format("%s,%s,%s,%s;",emailaddress,password,firstname,lastname);
		
		

		return newUser;
	}

	

	//this method asks the user for profile info and then stores it in a comma separated String
	static String  storeUserInfo()
	{
		
   		//this method validates the profile provided by the user
        if(!validateProfileInfo())
       		{ 
       			String newInfo=String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s;",emailaddress,twitterHandle,githubUsername,websiteName,universityName,majorName,currentCompany,currentTittle,phoneNumber);
				return newInfo;
		    }


	    return " ";
		
	}



	//this method writes a String with user info to the users.csv or info.csv (make sure you don’t overwrite your info!)

	static void outputInfo (String record, String fileName) throws IOException
	 {
      	PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
		out.println(record);
		out.close();


	}









    /***************
    *helper methods
	****************
    *
    *
    */
    
    //this method simply for asking the user a question based on the message
	static String askUser(String message)
	{

        out.print(message +" "+":");
		Scanner in=new Scanner(System.in);
		String mess=in.nextLine();
		return mess;
	}

  

 //this method is responsible for asking details from  existing  users

  static void askExistingUserInfo() throws IOException
			  {
			  
			  	while(true)
			  	{
			  		  emailaddress=askUser("Please enter your email address");
					  password=askUser("Please enter your password");

					  if(validateEmail(emailaddress)==false)
					  {
							out.println("Incorrect email please retry");
							continue;
					  }
					 
			          if(!UserLogin(emailaddress,password))
			          {
			          		out.println( "Wrong details please try again");
			          		continue;
			          }
			          else
			          	break;



			  	}
			

			  }
				 

	// this method is responsible for asking for user info
    static void askuserInfo_(String label) throws IOException
    {
    	switch(label)
    	{
    		case "f":
    			firstname=askUser("Please enter your firstname");
	    		if(!validateFirstLastname(firstname))
	    		{
					out.println( "Firstname problem : please try again");
					askuserInfo_("f");
					break;
				}
				
    		case "l":
    			lastname=askUser("Please enter your lastname");
	 			if(!validateFirstLastname(lastname))
	 			{
					out.println("lastname problem : please try again");
					askuserInfo_("l");
					break;
				
				}
				
    		case "e":
    			emailaddress=askUser("Please enter your email address");
				if(validateEmail(emailaddress)==false)
				{
				  	out.println( "Incorrect Email: Please retry");
				  	askuserInfo_("e");
				  	break;
				}
				if(CheckExistingUser(emailaddress)==true)
				{
				 	out.println("Email exist already");
				 	askuserInfo_("e");
				 	break;
				}
				

    		case "p":
    			password=askUser("Please enter your password");
            	confirmedpassword=askUser("Please confirm  password");
				if(validatePassword(password,confirmedpassword)==false)
				{
					out.println("Password dont match");
					askuserInfo_("p");
					break;

				}
				BCrypt bcrypt=new BCrypt();
				password = bcrypt.hashpw(password, BCrypt.gensalt());

				
  
    	}
    }

   


    //validating user's profile info
     
    static boolean validateProfileInfo()
    {
    
    	boolean invalid=true;

       
        while(invalid)
        {
            twitterHandle=askUser("Please enter your twitter Handle");
	    	if(twitterHandle.matches("[a-zA-Z0-9]+"))
	    	{
	    	 break;
	    		
	    	}
	    	else
	    		 out.println("Twitter handle must be alphabets and numbers");

	    		
		   
		    
		}


		 while(invalid)
        {
        	githubUsername=askUser("Please enter your Github Username");
	    	if(githubUsername.matches("[a-zA-Z0-9]+"))
	    		break;
	    	else

		    	out.println("Github name must be alphabets and numbers");
		  
		   
		}


		 while(invalid)
        {
        
	    	
	    	websiteName=askUser("Please enter the name of your website ");	
	 
	    	if(websiteName.matches("^www\\.([a-z0-9-]+)\\.[a-z]{2,3}$"))
	    		
		    	break;
		    else
		       
		    	out.println("Website must follow the following format: www.google.com");

			  
		}


		 while(invalid)
        {
        
	    	universityName=askUser("Please enter the name of the University you attended");
	    	if(universityName.matches("[\\s a-zA-Z0-9]+"))
	    		
		    		break;
		    else
			       
		     		
		    	out.println("University name must contain alphabets,numbers and whitespaces");
		       
			   
		}



    	
	    

	    while(invalid)
	    {   
			majorName=askUser("Please enter your Major:");
	        if(majorName.matches("^[\\s a-zA-Z0-9]+"))
		        break;
		    else	
			      
		    
		    	out.println("Company name must contain alphabets, numbers and whitespaces");
		}
       
      
 		while(invalid)
 		{
	        currentCompany=askUser("Please enter of your Current Company");
	    	if(currentCompany.matches("^[\\s a-zA-Z0-9]+"))
		       break;
		   	else
		    	out.println("Company name must contain alphabets, numbers and whitespaces");
			      
		}


        
		while(invalid)
		{
	        currentTittle=askUser("Please enter your current title");
	    	if(currentTittle.matches("[\\s a-zA-Z0-9]+"))
		           break;
		    else

		    	 out.println("Title must contain only alphabets,numbers and whitespaces ");
			      

	   
	    }



        while(invalid)
        {
	        phoneNumber=askUser("Please enter your phone number");
	    	if(phoneNumber.matches("[0-9]{3}-[0-9]{3}-[0-9]{4}"))
	    		
	    		 break;
	    	else

		    	out.println("Please the phone number must be in the following format: 556-567-6789");
		}

   
	
		return !invalid;


    }



    //this method is responsible for checking bothe email and password of user
   
	 static boolean CheckExistingEmailPassword(String emailaddress, String password) throws IOException
	 {

	 	   
	 	    String [] user_records=readInputFile("out.csv");
			
			for(String record: user_records)
				{
					String user[]=new String [5];
					user=record.split(",");
   
					if ( user[0].equals(emailaddress)&& BCrypt.checkpw(password, user[1]) )
					{
					    
						return true;
					}
					
				
				}
				
			return false;
	 }


	


    //this method is responsible for validating bothe firstname and lastname
    static boolean validateFirstLastname( String name)
	{
		String user_input=name;


		if(user_input.matches("[a-zA-Z]{2,}"))
		{
			return true;

		}
		return false;
	}



}