package Exam;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import HQLDemo.HQLOperations;
import HQLDemo.Patient;

public class CustomerOpertions
{
	public static void main(String[] args)
	{
		HQLOperations op = new HQLOperations();
	}
	
	public void addCustomer()
	{
		Configuration configuration = new Configuration();
		configuration.configure("practicehibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction t = session.beginTransaction();
		
		Scanner sc = new Scanner(System.in);
			
		Customer customer = new Customer();
		
		System.out.println("Enter Faculty ID: ");
		int cid=sc.nextInt();
		customer.setCid(cid);
		
		System.out.println("Enter Faculty Name: ");
		String cname = sc.next();
		customer.setCname(cname);
		
		System.out.println("Enter Faculty Gender: ");
		int cage = sc.nextInt();
		customer.setCage(cage);
		
		System.out.println("Enter Faculty Department: ");
		String cemail = sc.next();
		customer.setCemail(cemail);
		
		System.out.println("Enter Faculty Salary: ");
		String clocation = sc.next();
		customer.setClocation(clocation);
		
		session.persist(customer);
		t.commit();
		System.out.println("Faculty Added Successfully");
		
		sc.close();
		sf.close();
		session.close();
	}
}
