package CRUDOperations;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Controller 
{
	public static void main(String args[])
	{
		Controller controller=new Controller();
		//controller.addfaculty();
		//controller.getfacultybyid();
		//controller.updatefaculty();
		controller.deletefaculty();
	}
	public void addfaculty()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction t = session.beginTransaction(); //begins transaction
		Faculty f = new Faculty();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Faculty ID : ");
		int fid = sc.nextInt();
		System.out.println("Enter Faculty NAME : ");
		String fname = sc.next(); 
		System.out.println("Enter Faculty GENDER : ");
		String fgender = sc.next();
		System.out.println("Enter Faculty DEPARTMENT : ");
		String fdepartment = sc.next();
		System.out.println("Enter Faculty SALARY : ");
		Double fsalary = sc.nextDouble();
		System.out.println("Enter Faculty CONTACT : ");
		String fcontactno = sc.next();
		f.setId(fid);
		f.setName(fname);
		f.setGender(fgender);
		f.setDepartment(fdepartment);
		f.setSalary(fsalary);
		f.setContact(fcontactno);
		
		session.persist(f);//insert operation
		
		t.commit();
		System.out.println("Faculty Added Successfully");
		
		sc.close();
		session.close();
		sf.close();
	}
	
	//display / find faculty record by using ID Column 
	public void getfacultybyid()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Faculty ID:");
		int fid = sc.nextInt();
		
		// There is no need to take transaction object because there is no DML Operation
		
		Faculty faculty = session.find(Faculty.class, fid);
		if(faculty!=null)
		{
			System.out.println("Faculty ID="+faculty.getId());
			System.out.println("Faculty ID="+faculty.getName());
			System.out.println("Faculty ID="+faculty.getGender());
			System.out.println("Faculty ID="+faculty.getSalary());
			System.out.println("Faculty ID="+faculty.getDepartment());
			System.out.println("Faculty ID="+faculty.getContact());
		}
		else
		{
			System.out.println("Faculty ID Not Found");
		}
		sc.close();
		session.close();
		sf.close();
	}
	
	public void updatefaculty() 
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction t = session.beginTransaction();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Faculty ID:");
		int fid = sc.nextInt();		
		
		Faculty faculty = session.find(Faculty.class, fid);
		
		
		if(faculty!=null)
		{
			System.out.println("Enter Faculty Name:");
			String fname = sc.next();
			System.out.println("Enter Faculty Salary:");
			double fsalary = sc.nextDouble();
			System.out.println("Enter Faculty Contact No:");
			String fcontact = sc.next();		
			
			faculty.setName(fname);
			faculty.setSalary(fsalary);
			faculty.setContact(fcontact);
			t.commit();
			System.out.println("Faculty Updated Successfully");
		}
		else
		{
			System.out.println("Faculty Object Not Found");
		}
		sc.close();
		session.close();
		sf.close();
	}
	
	public void deletefaculty()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction t = session.beginTransaction();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Faculty ID to Delete:");
		int fid = sc.nextInt();		
		
		Faculty faculty = session.find(Faculty.class, fid);
		
		if(faculty!=null)
		{
			session.remove(faculty);
			t.commit();
			System.out.println("Faculty Deleted Successfully");
		}
		else
		{
			System.out.println("Faculty Not Found");
		}
		sc.close();
		session.close();
		sf.close();
	}
}
