package GeneratorClassDemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class Demo 
{
	public static void main(String[] args)
	{
		Demo d = new Demo();
		//d.addEmployee();
		d.displayallemps();
	}
	public void addEmployee()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction t = session.beginTransaction();
		
		Employee emp = new Employee();
		// id will be auto assigned[auto increment]
		emp.setName("MSWD");
		emp.setGender("MALE");
		emp.setSalary(12500.50);
		emp.setContact("7890789097");
		
		session.persist(emp);
		t.commit();
		System.out.println("Employee Added Successfully");
		
		session.close();
		sf.close();
		
	}
	public void displayallemps()
	{
		Configuration configuration = new Configuration();
	    configuration.configure("hibernate.cfg.xml");
	       
	    SessionFactory sf = configuration.buildSessionFactory();
	    Session session = sf.openSession();
	    
	    String hql= "from Employee"; 
	    
	    Query<Employee> qry = session.createQuery(hql, Employee.class);
	    List<Employee> emplist = qry.getResultList();
	    System.out.println("Total Employees ="+emplist.size());
	    
	    for(Employee e:emplist)
	    {
	    	System.out.println("ID="+e.getId());
	    	System.out.println("Name="+e.getName());
	    	System.out.println("Gender="+e.getGender());
	    	System.out.println("Salary="+e.getSalary());
	    	System.out.println("Contact="+e.getContact());
	    }
	    
	    session.close();
	    sf.close();
	}
}
