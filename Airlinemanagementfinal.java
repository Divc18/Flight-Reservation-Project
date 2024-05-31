import java.util.*;
import java.lang.Exception;
import java.io.*;
import java.lang.*;
import java.text.SimpleDateFormat;
class A
{
	static Scanner sc=new Scanner(System.in);
	static String n,s,from,to,seatno;
	static int dob;
	double finalprice=0;
	boolean fg1=false;
	void modifyFile(String filename,String newdata)
	{
		try
		{
			File d=new File(filename);
			d.createNewFile();
			FileWriter fout=new FileWriter(d);
			BufferedWriter bout=new BufferedWriter(fout);
			bout.write(newdata);
			bout.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	void modify(String filename,String s1,String t1)
	{
		try
		{
			File d=new File(filename);
			d.createNewFile();
			FileReader fin=new FileReader(d);
			BufferedReader bin=new BufferedReader(fin);
			String olddata="",newdata="";
			String str;
			
			while((str=bin.readLine())!=null)
			{
				olddata=olddata+str+"\n";
			}
			newdata=olddata.replaceAll(s1,t1);

			bin.close();
			modifyFile(filename,newdata);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}
	void Cancel(String filename,String s1,int ch)
	{	
		try
		{
			File d=new File(to);
			d.createNewFile();
			FileReader fin=new FileReader(d);
			BufferedReader bin=new BufferedReader(fin);

			String olddata="";
			String str;
			
			while((str=bin.readLine())!=null)
			{
				if(!str.substring(0,2).equals(s1))
				{
					olddata=olddata+str+"\n";
				}
				else
				{
					fg1=true;
				}

			}
			if(!fg1)
			{
				System.out.print("Record not found");
			}
			else
			{
				System.out.println("\n");
				System.out.println("       TICKET CANCELLED SUCCESSFULLY    ");
				System.out.println("     your money will be deposited soon  ");
				System.out.println("                VISIT AGAIN               ");
			}
			bin.close();
			modifyFile(to,olddata);
			change(ch,filename);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	void disp(String filename)
	{
		try
		{
			File d=new File(filename);
			d.createNewFile();
			FileReader fin=new FileReader(d);
			BufferedReader bin=new BufferedReader(fin);

			String olddata="",newdata="";
			String str;
			System.out.println("\n"+"--------------------------------------------------------------------------");
			while((str=bin.readLine())!=null)
			{
				System.out.println(str);
			}
			System.out.println("--------------------------------------------------------------------------");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	void ticket(String filename)
	{
		long days;
		String str,s="",s1="";
        SimpleDateFormat ob=new SimpleDateFormat("dd-MM-yyyy");
        Scanner sc=new Scanner(System.in);
        try
        {
            File d=new File(filename);
            d.createNewFile();
            FileReader fin=new FileReader(d);
            BufferedReader bin=new BufferedReader(fin);
            
            while((str=bin.readLine())!=null)
            {
                int i1=str.indexOf('-');
                if(i1!=-1 && to.charAt(0)==str.charAt(i1-1))
                {
                    s=str.substring(i1+1,i1+3);
                    i1=str.indexOf("rs-");
                    if(filename.equals("Domestic.txt"))
                    	s1=str.substring(i1+3,i1+8);
                    else
                    	s1=str.substring(i1+3,i1+9);
                }
            }
            int x=Integer.parseInt(s);

            x--;
            bin.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        int bp=Integer.parseInt(s1);
        
        try
        {
            System.out.println("Enter Date Of Booking (DD-MM-YYYY): ");
            Date date1 = ob.parse(sc.nextLine());
            System.out.println("Enter Date Of Going (DD-MM-YYYY): ");
            Date book = ob.parse(sc.nextLine());
            long time=book.getTime()-date1.getTime();
            days=time/(1000*60*60*24)%365;
            if(days<=5)
            {
            	finalprice=(0.30*bp)+bp;
            }
            else if(days<=10)
            {	
            	finalprice=(0.20*bp)+bp;
            }
            else if(days<=20)
            {
            	finalprice=(0.10*bp)+bp;
            }
            else
            {
            	finalprice=bp;
            }
            System.out.println("ticket booked SUCCESSFULLY");
            System.out.println("Final Price :   Rs."+finalprice);
        }
        catch(Exception e)
        {}

        try
		{
			File d=new File(to+".txt");
			d.createNewFile();
			FileWriter fout=new FileWriter(d,true);
			BufferedWriter bout=new BufferedWriter(fout);
			bout.write(seatno+"\t"+n+"\t"+dob+"\t"+from+"\t"+to+"\t"+finalprice);
			bout.newLine();
			bout.close();
		}
		catch(IOException e){}

	}
	void customerdetails(int ch, String filename)
	{
		System.out.println();
		System.out.println("Enter Your Name");
		n=sc.nextLine();
		System.out.println("Enter Your Age");
		dob=sc.nextInt();
		System.out.println("Destination To Get The Flight");
		from=sc.next();
		System.out.println("Destination To Reach");
		to=sc.next();
		System.out.println("Seat No.");
		seatno=sc.next();
		
		change(ch,filename);
	}
	void change(int ch,String filename)
	{
		try
		{
			File d=new File(filename);
			d.createNewFile();
			FileReader fin=new FileReader(d);
			BufferedReader bin=new BufferedReader(fin);

			String str;
			while((str=bin.readLine())!=null)
			{
					
				int i1;
				i1=str.indexOf('-');
				if(i1!=-1 && to.charAt(0)==str.charAt(i1-1))
					s=str.substring(i1+1,i1+3);
			}
			int x=Integer.parseInt(s);
			
			String s1=to.charAt(0)+"-"+x;
			if(ch==2)
				x--;
			else if(ch==3 && fg1)
				x++;
			String t1=to.charAt(0)+"-"+x;
			modify(filename,s1,t1);

			bin.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
}



class Airlinemanagementfinal
{
	public static void main(String args[])
	{
		int p;
		String filename="";
		String sno="";
		Scanner sc=new Scanner(System.in);
		File d=null;
		File i=null;
		int choice,choice1;
		A a1=new A();
		while(true)
		{   System.out.println("\n");
			System.out.println("CHOOSE YOUR FLIGHT TYPE: ");
			System.out.println("1. Domestic ");
			System.out.println("2. International ");
			System.out.println("3. Display Passengers Details");
			System.out.println("4. Exit"+"\n");
			System.out.println("Enter Your Choice ");
			choice=sc.nextInt();
			switch(choice)
			{
			case(1):
				{
					filename="Domestic.txt";
					boolean flag=true;
					while(flag)
					{   System.out.println("\n");
						System.out.println("1. See Flight Details");
						System.out.println("2. Book A Flight ");
						System.out.println("3. Cancel A Ticket ");
						System.out.println("4. Exit"+"\n");
						System.out.println("Enter Your Choice");
						choice1=sc.nextInt();
						switch (choice1)
						{
							case(1):
							{
								try
								{
									d=new File("Domestic.txt");
									d.createNewFile();
									FileReader fin=new FileReader(d);
									BufferedReader bin=new BufferedReader(fin);
									String df;
									System.out.println("\n");
									System.out.println("Flight Details : "+"\n");
									System.out.println("--------------------------------------------------------------------------");
									while((df=bin.readLine())!=null)
									{
										System.out.println(df);
									}
									System.out.println("--------------------------------------------------------------------------");
								}
								catch(Exception e)
								{
									System.out.println(e.getMessage());
								}
								break;
							}
							case(2):
							{
								a1.customerdetails(choice1,filename);
								a1.ticket(filename);
								break;
							}
							case 3:
								System.out.println("Enter Your Seat Number To Be Cancelled");
								sno=sc.next();
								System.out.println("Enter Your Destination ");
								a1.to=sc.next();
								a1.to=a1.to+".txt";
								a1.Cancel(filename,sno,choice1);
								
								break;
							default:
								flag=false;
						}
					}
					break;
				}
			case(2):
				{
					filename="International.txt";
					boolean flag=true;
					while(flag)
					{
						System.out.println("\n");
						System.out.println("1. See Flight Details");
						System.out.println("2. Book A Flight ");
						System.out.println("3. Cancel A Ticket ");
						System.out.println("4. Exit"+"\n");
						System.out.println("Enter Your Choice");
						choice1=sc.nextInt();
						switch (choice1)
						{
							case(1):
							{
								try
								{
									d=new File("International.txt");
									d.createNewFile();
									FileReader fin=new FileReader(d);
									BufferedReader bin=new BufferedReader(fin);
									String df;
									System.out.println("\n"+"--------------------------------------------------------------------------");
									System.out.println("Flight Details : ");
									while((df=bin.readLine())!=null)
									{
										System.out.println(df);
									}
									System.out.println("--------------------------------------------------------------------------"+"\n");
								}
								catch(Exception e)
								{
									System.out.println(e.getMessage());
								}
								break;
							}
							case(2):
							{
								a1.customerdetails(choice1,filename);
								a1.ticket(filename);
								break;
							}
							case 3:
								System.out.println("Enter Your Seat Number To Be Cancelled");
								sno=sc.next();
								System.out.println("Enter Your Destination ");
								a1.to=sc.next();
								a1.to=a1.to+".txt";
								
								a1.Cancel(filename,sno,choice1);
								
								break;
							default:
								flag=false;
						}
					}
					break;
				}
			case 3:
				Scanner sc1=new Scanner(System.in);
				System.out.println("Display The Detail Of File:    ");
				String file=sc1.nextLine();
				file=file+".txt";
				a1.disp(file);
				break;
			case 4:
				System.exit(0);
			}
		}
	}
}