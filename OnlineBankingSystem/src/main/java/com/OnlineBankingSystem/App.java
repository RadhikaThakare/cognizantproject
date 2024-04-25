package com.OnlineBankingSystem;

import java.util.List;
import java.util.Scanner;

import com.Bean.CustomerBean;
import com.Bean.TransactionBean;
import com.DAO.CustomerDao;
import com.DAO.CustomerImplDao;
import com.Exception.AccountException;
import com.Exception.CustomerException;

public class App {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		boolean f=true;
		

		while(f) {
			System.out.println("**********************************");
			System.out.println("Welcome To Online Banking System");
			System.out.println("**********************************"); 
			System.out.println("1. Exit \r\n"
					+ "2. Customer Portal \r\n");
			System.out.println("Choose your option");
			int choice=sc.nextInt();
			
		switch(choice) {
		case 1:
			
			  System.out.println("THANK YOU FOR USING BANKING SYSTEM!!!");
              System.out.println("Exiting System!");
              return;
		case 2:
			System.out.println("LOGIN <<---->> CUSTOMER");
			System.out.println("--------------------------");
			System.out.println("Enter username");
			String username=sc.next();
			System.out.println("Enter Password");
			String password=sc.next();
			System.out.println("Enter Account No");
			int acno=sc.nextInt();
			
			CustomerDao cd=new CustomerImplDao();
			
			try {
				CustomerBean cusb = cd.LoginCustomer(username, password,acno);
				
				System.out.println("Welcome "+cusb.getCname());
				
				boolean m=true;
				
				while(m) {
					System.out.println("-------------------------------\r\n"
							+ "1. View Balance\r\n"
							+ "2. Deposit Money\r\n"
							+ "3. Withdraw Money\r\n"
							+ "4. Transfer Money\r\n"
							+ "5. View Transaction History\r\n"
							+ "6. LOGOUT\r\n"
							+"-------------------------------\r\n");

					
					int x=sc.nextInt();
					
					if(x==1) {
						System.out.println("--------BALANCE----------");
						System.out.println("Current Account Balance");
						System.out.println(cd.viewBalance(acno)); 
						System.out.println("------------------------");
					}
					if(x==2) {
						System.out.println("----------DEPOSIT-----------");
						System.out.println("Enter Amount to Deposit");
						int am=sc.nextInt();
						cd.Deposit(acno, am);
						System.out.println("Your Balance after Deposit");
						System.out.println(cd.viewBalance(acno));
						System.out.println("----------------------------");
					}
					
					if(x==3) {
						System.out.println("----------WITHDRAWL------------");
						System.out.println("Enter Withdrawl amount");
						int wa=sc.nextInt();
						try {
							cd.Withdraw(acno, wa);
							System.out.println("Your Balance after Withdrawl");
							System.out.println(cd.viewBalance(acno));
							System.out.println("-----------------------------");
						}catch(CustomerException e) {
							System.out.println(e.getMessage());
						}
					}
					
					if(x==4) {
						System.out.println("----------AMOUNT TRANSFER-----------");
						System.out.println("Enter Amount to Transfer");
						int t=sc.nextInt();
						System.out.println("Enter Account No. to transfer amount");
						int ac=sc.nextInt();
						
						try {
							cd.Transfer(acno, t, ac);
							System.out.println("Amount transferred Succesfully...");
							System.out.println("-------------------------------");
						}catch(CustomerException e) {
							System.out.println(e.getMessage());
						}
					}
					
					if(x==5) {
						List<TransactionBean> li=null;
						try {
							li= cd.viewTransaction(acno);
						}catch(CustomerException e) {
							System.out.println(e.getMessage());
						}
						System.out.println("------TRANSACTION HISTORY-------");
						System.out.println("Account No.: " + li.get(0).getAccountNo());
						
						for(TransactionBean v : li) {
							System.out.println("----------------------------------------------------");
							if(v.getDeposit()!=0)
								System.out.println("Amount Credit: "+ v.getDeposit());
							if(v.getWithdraw()!=0)
								System.out.println("Amount Debit : "+ v.getWithdraw());
//							System.out.println("Date and Time: "+ v.getTransaction_time());
						}
						
//						li.forEach(v->{
//							System.out.println("----------------------------------------------------");
//							if(v.getDeposit()!=0)
//								System.out.println("Amount Credit: "+ v.getDeposit());
//							if(v.getWithdraw()!=0)
//								System.out.println("Amount Debit : "+ v.getWithdraw());
//							System.out.println("Date and Time: "+ v.getTransaction_time());
//						});
						
					}
					if(x==6) {
						System.out.println("Customer Logged out");
						m=false;
					}
					
					
				}
				break;
				
				
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			
			break;
			
			
			
		}
		
		}
		
		
	}

}
