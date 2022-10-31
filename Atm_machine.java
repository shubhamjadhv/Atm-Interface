package atm;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Atm_machine {
	static Scanner s=new Scanner(System.in);
	static int atmno;
	static int pin1;

	static final String DB_URL = "jdbc:mysql://localhost/atm";
	   static final String USER = "root";
	   static final String PASS = "Shubham@33";
	 //  static final String QUERY = "SELECT cust_no, pin,bal,statement FROM CUSTOMER";
	public  void createAccount() {
		
		 System.out.println("Enter your atm number:");
	      atmno=s.nextInt();
	      System.out.println("\nEnter PIN to  registered");
			 pin1 = s.nextInt();      
				try {
					
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","Shubham@33");
					Statement st=con.createStatement();
					String insertQuery="INSERT INTO  customer(cust_no,pin)" +" VALUES(?,?)";
						PreparedStatement pst = con.prepareStatement(insertQuery);
						pst.setInt(1,atmno);
						pst.setInt(2,pin1);
					    pst.executeUpdate();

					System.out.println("\nYour new account has been successfuly registered!");
				    System.out.println("\nRedirecting to login.............");
			     
		         con.close();
				}
				catch(Exception e)
				{
					System.out.println(e);
					
				}	
				      
	     mainmenu();
		}
	public  void mainmenu() {
		    System.out.println("****************************************");
		    System.out.println("\n Select choice from 1 and 2");
			System.out.println(" 1:Login");
			System.out.println(" 2:Create Account");
			System.out.println("\n****************************************");

			System.out.print("\nChoice: ");
			int choice = s.nextInt();
			switch (choice) {
			case 1:
				getLogin();
				break;
			case 2:createAccount();	
			default:
				System.out.println("\nIncorrect Choice");
			}
		
	}
  void getLogin() {
		
//		System.out.println("Enter you atm number");
//		atmno=s.nextInt();
	 int pinn = 0;
		System.out.println("Enter Your PIN:");
		  pin1=s.nextInt();
		
		 try {
				
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","Shubham@33");
				Statement st=con.createStatement();
					ResultSet rs=st.executeQuery("select cust_no ,pin from customer where pin="+pin1);
				
					while(rs.next())
				    pinn=rs.getInt(2);
					if(pin1==pinn) {
						
					System.out.println("Loging\n");
					}else {
						System.out.println("Incorrect pin ");
					}
			}
			catch(Exception e)
			{
				System.out.println(e);
				
			}
		 
		}
	
	public static void main(String[] args) {
   
		int deposit = 0;
		int AtmNumber;
		int pin3=0;
	   
		 try { 
			  
		Atm_machine at=new Atm_machine();
		at.mainmenu();
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","Shubham@33");
				Statement st=con.createStatement();
				String sql="select cust_no,pin from customer where pin="+pin1;	
				ResultSet rs=st.executeQuery(sql);
				while( rs.next()) {
					 AtmNumber =rs.getInt(1) ;
					 pin3=rs.getInt(2);
					//System.out.println(rs.getString(1)+" "+rs.getString(2));;
			
	        if(pin1==pin3){
	            while(true){ 
	            	System.out.println("\n*************************************************");
	                System.out.println(" \n           W-E-L-C-O-M-E TO A-T-M             \n");
	     
	                System.out.println("*************************************************");
	                System.out.println("-------------------------------------------------");
	                System.out.println("\n 1.Deposite a Amount\n 2.Check Balance\n 3.Withdraw Money  \n 4.Exit");
	               System.out.println("--------------------------------------------------"); 
	               System.out.println("Enter Choice : ");
	                int ch=s.nextInt();
	                if(ch==1){
	                	System.out.println("Enter Amount to Deposite :");
	                    deposit=s.nextInt();
	                   
	                    
	                    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				    	         Statement stmt = conn.createStatement();
				    	      ) {	
	                    	 String QUERY = "select bal from customer where pin= "+pin1;;
					String Sql="update customer set bal =bal+"+deposit+" where pin="+pin1;
					stmt.executeUpdate(Sql);
					rs = stmt.executeQuery(QUERY);
			          
			          while(rs.next()){
			        	  System.out.println("Your money has been deposited successfully");
			        	  System.out.print("\nYour Balance: " + rs.getInt(1));
			          }
			          rs.close();				    
				     
	                    }
				       catch(Exception e)
						{
							System.out.println(e);
							
						}
	                  
	                }
	                else if(ch==2){
	                	 try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    		    	         Statement stmt = conn.createStatement();
	    		    	      ) {	
	                		   
	    			 rs=st.executeQuery("select bal from customer where pin= "+pin1);
	    			while(rs.next())
	    				System.out.println("\nYour Balance:"+rs.getString(1));
                   	       rs.close() ;        	 }
	                	 catch(Exception e)
	         			{
	         				System.out.println(e);
	         				
	         			}
	         		    
	                }
	                else if(ch==3) {

                        System.out.println("Enter amount to withdraw :");
	                    double withdraw=s.nextDouble();
                        
	                    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				    	         Statement stmt = conn.createStatement();
				    	      ) {	 
	           if(withdraw>=500) {
               String query="select bal from customer where pin= "+pin1;
					 sql="update customer set  bal=bal-" +withdraw+"where pin= "+pin1;
					stmt.executeUpdate(sql);
			       
			      rs = stmt.executeQuery(query);
			         while(rs.next()){
			            //Display values
			            System.out.print("\nPlease collect Your Money :" + withdraw);

			    	   System.out.println("\n Your Balance :"+rs.getString(1));
			         }
	                 } 
			         else {
			        	 System.out.println("Please enter the amount in multipal of 500");
			         }
	           rs.close();
			         }
	                    catch(Exception e)
	        			{
	        				System.out.println(e);
	        				
	        			}
	                }
/*	                else if(ch==4){
	                	System.out.println("Enter Old PIN:");
	                        pin3=s.nextInt();
	                   
	                    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				    	         Statement stmt = conn.createStatement();
				    	      ) {	
	                    	       	
	                    String Query="select pin from customer where pin= "+pin3;
	                    System.out.println("Enter new Pin:");
	                    int pin2=s.nextInt();
	   					sql="update customer set  pin="+pin2+"where pin="+pin3;
	   					stmt.executeUpdate(sql);
	   			        rs = stmt.executeQuery(Query);
	   			        while(rs.next()){
	   			    	   System.out.println("Your pin is updated"+pin2);
	   			    	   
	   			       }
			    	 
			       }
	                    catch(Exception e)
	        			{
	        				System.out.println(e);
	        				
	        			}
	        		    
	            }*/
	                else if(ch==4){
	                    System.out.println(" Collect your ATM Card\n Thank you for using ATM Machine!!");
	                    System.exit(0);
	                }
	                else
	                {
	                    System.out.println("Please enter correct choice");
	                }
	            }
	          }
	         
		   }
		 }	
			catch(Exception e)
			{
				System.out.println(e);
				
			} 
		  
	}       
		

}
