package PracticeCRUDOperations;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Controller 
{
	public static void main(String[] args)
	{
		Controller operations = new Controller();
		operations.addemployee();
		//operations.getempbyid();
		//operations.updateemployee();
		//operations.deleteemployee();
	}
	
	public void addemployee()
	{
		Configuration cfg = new Configuration();
		cfg.configure("practicehibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction t = session.beginTransaction();
		
		Scanner sc = new Scanner(System.in);
		Employee e = new Employee();
		System.out.println("Enter Employee ID =");
		int eid = sc.nextInt();
		System.out.println("Enter Employee Name = ");
		String ename = sc.next();
		System.out.println("Enter Employee Email = ");
		String eemail = sc.next();
		System.out.println("Enter Employee Gender = ");
		String egender = sc.next();
		System.out.println("Enter Employee Contact No = ");
		String econtactno = sc.next();
		System.out.println("Enter Employee Salary = ");
		Double esalary = sc.nextDouble();
		
		e.setId(eid);
		e.setName(ename);
		e.setEmail(eemail);
		e.setGender(egender);
		e.setContact(econtactno);
		e.setSalary(esalary);
		
		session.persist(e);
		t.commit();
		
		System.out.println("Employee Data Added Successfully");
		
		sc.close();
		session.close();
		sf.close();
	}
	
	public void getempbyid()
	{
		Configuration cfg = new Configuration();
		cfg.configure("practicehibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Enter Employee ID :");
		int eid = sc1.nextInt();
		Employee emp = session.find(Employee.class, eid);
		if(emp!=null)
		{
			System.out.println("Employee ID = "+emp.getId());
			System.out.println("Employee ID = "+emp.getName());
			System.out.println("Employee ID = "+emp.getGender());
			System.out.println("Employee ID = "+emp.getEmail());
			System.out.println("Employee ID = "+emp.getContact());
			System.out.println("Employee ID = "+emp.getSalary());
		}
		else
		{
			System.out.println("Employee ID Not Found.\n");
		}
		sc1.close();
		session.close();
		sf.close();
	}
	public void updateemployee()
	{
		Configuration cfg = new Configuration();
		cfg.configure("practicehibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction t = session.beginTransaction();
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Enter Employee ID =");
		int eid = sc2.nextInt();
		Employee emp = session.find(Employee.class, eid);
		if(emp!=null)
		{
			System.out.println("Enter Faculty Name:");
			String ename = sc2.next();
			System.out.println("Enter Faculty Salary:");
			double esalary = sc2.nextDouble();
			System.out.println("Enter Faculty Contact No:");
			String econtact = sc2.next();		
			
			emp.setName(ename);
			emp.setSalary(esalary);
			emp.setContact(econtact);
			t.commit();
			System.out.println("Faculty Updated Successfully");
		}
		else
		{
			System.out.println("Employee ID not Found!");
		}
		sc2.close();
		session.close();
		sf.close();
	}
	public void deleteemployee()
	{
		Configuration cfg = new Configuration();
		cfg.configure("practicehibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction t = session.beginTransaction();
		Scanner sc3 = new Scanner(System.in);
		System.out.println("Enter Employee ID =");
		int eid = sc3.nextInt();
		Employee emp = session.find(Employee.class, eid);
		if(emp!=null)
		{
			session.remove(emp);
			t.commit();
			System.out.println("Employee Deleted Successfully");
		}
		else
		{
			System.out.println("Employee Not Found");
		}
		sc3.close();
		session.close();
		sf.close();
	}
}
