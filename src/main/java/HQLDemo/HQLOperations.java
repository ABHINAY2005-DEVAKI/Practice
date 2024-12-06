package HQLDemo;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

public class HQLOperations {


public static void main(String args[]) 
{
	HQLOperations operations= new HQLOperations();
	operations.addpatient();
	//operations.displayallpatientcompleteobject();
	//operations.displayallpatientpartialobject();
	//operations.aggregatefunctions();
	//operations.updatepositionalparams();
	//operations.deletepositionalparams();
	//operations.updatenamedparams();
	//operations.deletenamedparams();
	//operations.displaypatientbyidpositionalparams();
	//operations.displaypatientbyidnamedparams();
	//operations.displaypatient();
}
public void addpatient() {
	
	Configuration configuration = new Configuration();
	configuration.configure("practicehibernate.cfg.xml");
	
	SessionFactory sf = configuration.buildSessionFactory();
	Session session = sf.openSession();
	
	Transaction t = session.beginTransaction();
	
	Scanner sc = new Scanner(System.in);
		
	Patient faculty = new Patient();
	
	System.out.println("Enter Faculty ID: ");
	int fid=sc.nextInt();
	faculty.setId(fid);
	
	System.out.println("Enter Faculty Name: ");
	String fname = sc.next();
	faculty.setName(fname);
	
	System.out.println("Enter Faculty Gender: ");
	String fgender = sc.next();
	faculty.setGender(fgender);
	
	System.out.println("Enter Faculty Department: ");
	String fdepartment = sc.next();
	faculty.setDepartment(fdepartment);
	
	System.out.println("Enter Faculty Salary: ");
	double fsalary = sc.nextDouble();
	faculty.setSalary(fsalary);
	
	System.out.println("Enter Faculty ContactNumber :");
	String fcontactnumber = sc.next();
	faculty.setContactnumber(fcontactnumber);
	
	session.persist(faculty);
	t.commit();
	System.out.println("Faculty Added Successfully");
	
	sc.close();
	sf.close();
	session.close();
}
public void displayallpatientcompleteobject() {
	Configuration configuration = new Configuration();
	configuration.configure("practicehibernate.cfg.xml");
	
	SessionFactory sf = configuration.buildSessionFactory();
	Session session = sf.openSession();
	
	String hql ="from Faculty";
	
	Query<Patient> qry = session.createQuery(hql,Patient.class);
	List<Patient> facultylist = qry.getResultList();
	
	System.out.println("Faculty Details:"+facultylist.size());
	
	for(Patient f : facultylist )
	{
		System.out.println("Faulty ID: "+f.getId());
		System.out.println("Faculty Name: "+f.getName());
		System.out.println("Faculty Gender: "+f.getGender());
		System.out.println("Faculty Department :"+f.getDepartment());
		System.out.println("Faculty Salary :"+f.getSalary());
		System.out.println("Faculty ContactNumber :"+f.getContactnumber());
	}
	session.close();
	sf.close();
}

public void displayallpatientpartialobject() {
	Configuration configuration = new Configuration();
	configuration.configure("practicehibernate.cfg.xml");
	
	SessionFactory sf = configuration.buildSessionFactory();
	Session session = sf.openSession();
	
	String hql = "select f.id, f.name,f.department from Faculty f";
	
	Query<Object[]> qry = session.createQuery(hql, Object[].class);
	List<Object[]> facultylist = qry.getResultList();
	
	System.out.println("Faculty Details:"+facultylist.size());
	
	for(Object[] obj: facultylist) {
		System.out.println("Faculty ID:"+obj[0]);
		System.out.println("Faculty NAME:"+obj[1]);
		System.out.println("Faculty DEPARTMENT:"+obj[2]);
		
	}
	session.close();
	sf.close();
	
	
}
public void aggregatefunctions()
{
	Configuration configuration = new Configuration();
	configuration.configure("practicehibernate.cfg.xml");
	
	SessionFactory sf = configuration.buildSessionFactory();
	Session session = sf.openSession();
	
	String hql1 = "select count(*) from Faculty";
	Query<Long> qry1=session.createQuery(hql1, Long.class);
	Long count = qry1.getSingleResult();
	System.out.println("Total Faculty="+count);
	
	String hql2 = "select sum(salary) from Faculty";
	Query<Double> qry2=session.createQuery(hql2, Double.class);
	double totalsalary = qry2.getSingleResult();
	System.out.println("Total Salary="+totalsalary);
	
	String hql3 = "select avg(salary) from Faculty";
	Query<Double> qry3=session.createQuery(hql3, Double.class);
	double avgsalary = qry3.getSingleResult();
	System.out.println("Average Salary="+avgsalary);
	
    String hql4 = "select min(salary) from Faculty";
	Query<Double> qry4=session.createQuery(hql4, Double.class);
	double minsalary = qry4.getSingleResult();
	System.out.println("Minimum Salary="+minsalary);
	
	String hql5 = "select max(salary) from Faculty";
	Query<Double> qry5=session.createQuery(hql5, Double.class);
	double maxsalary = qry5.getSingleResult();
	System.out.println("Maximum Salary="+maxsalary);
	
	session.close();
	sf.close();
}
public void updatepositionalparams()
{
	Configuration configuration = new Configuration();
     configuration.configure("practicehibernate.cfg.xml");

     SessionFactory sf = configuration.buildSessionFactory();
     Session session = sf.openSession();

     Transaction t = session.beginTransaction();
     
     Scanner sc = new Scanner(System.in);
     
     System.out.println("Enter Faculty ID:");
     int fid=sc.nextInt();
     
     System.out.println("Enter Faculty Name: ");
     String fname=sc.next();
     
     System.out.println("Enter Product Salary:");
     double fsalary=sc.nextDouble();
     
     String hql = "update Faculty set name=?1,salary=?2 where id=?3";
     MutationQuery qry = session.createMutationQuery(hql);
     
     qry.setParameter(1,fname);
     qry.setParameter(2,fsalary);
     qry.setParameter(3,fid);
     
     
     int n = qry.executeUpdate();
     
     t.commit();
     System.out.println(n+" Faculty(s) Updated Successfully");
     
     sc.close();
     session.close();
     sf.close();
}
public void deletepositionalparams()
{
	Configuration configuration = new Configuration();
     configuration.configure("practicehibernate.cfg.xml");

     SessionFactory sf = configuration.buildSessionFactory();
     Session session = sf.openSession();

     Transaction t = session.beginTransaction();
     
     Scanner sc = new Scanner(System.in);
     
     System.out.println("Enter Faculty ID:");
     int fid=sc.nextInt();
    
     String hql = "delete Faculty set where id=?1";
     MutationQuery qry = session.createMutationQuery(hql);
     
     qry.setParameter(1,fid);
     
     int n = qry.executeUpdate();
     
     t.commit();
   
     if(n>0)	
     {
    	 System.out.println("Faculty Deleted Successfully");
     }
     else
     {
    	 System.out.println("Faculty ID Not Found");
     }
     sc.close();
     session.close();
     sf.close();
}
public void updatenamedparams()
{
	 Configuration configuration = new Configuration();
     configuration.configure("practicehibernate.cfg.xml");

     SessionFactory sf = configuration.buildSessionFactory();
     Session session = sf.openSession();

     Transaction t = session.beginTransaction();
     
     Scanner sc = new Scanner(System.in);
     
     System.out.println("Enter Faculty ID:");
     int fid=sc.nextInt();
     
     System.out.println("Enter Faculty Name: ");
     String fname=sc.next();
     
     System.out.println("Enter Faculty Salary:");
     double fsalary=sc.nextDouble();
     
     String hql = "update Faculty set name=:v1,salary=:v2 where id=:v3";
     MutationQuery qry = session.createMutationQuery(hql);
     
     qry.setParameter("v1",fname);
     qry.setParameter("v2",fsalary);
     qry.setParameter("v3",fid);
     
     
     int n = qry.executeUpdate();
     
     t.commit();
     System.out.println(n+" Faculty(s) Updated Successfully");
     
     sc.close();
     session.close();
     sf.close();

}
public void deletenamedparams()
{
	Configuration configuration = new Configuration();
     configuration.configure("practicehibernate.cfg.xml");

     SessionFactory sf = configuration.buildSessionFactory();
     Session session = sf.openSession();

     Transaction t = session.beginTransaction();
     
     Scanner sc = new Scanner(System.in);
     
     System.out.println("Enter Faculty ID:");
     int fid=sc.nextInt();
    
     String hql = "delete Faculty set where id=:v";
     MutationQuery qry = session.createMutationQuery(hql);
     
     qry.setParameter("v",fid);
     
     int n = qry.executeUpdate();
     
     t.commit();
   
     if(n>0)	
     {
    	 System.out.println("Faculty Deleted Successfully");
     }
     else
     {
    	 System.out.println("Faculty ID Not Found");
     }
     sc.close();
     session.close();
     sf.close();
}
public void displaypatientbyidpositionalparams()
{
	  Configuration configuration = new Configuration();
	  configuration.configure("practicehibernate.cfg.xml");
	    
	  SessionFactory sf = configuration.buildSessionFactory();
	  Session session = sf.openSession();
	  
	  Scanner sc = new Scanner(System.in);
	  System.out.println("Enter Faculty ID:");
	  int fid = sc.nextInt();
	  
	  String hql = "from Faculty where id=?1"; 
	  org.hibernate.query.Query<Patient> qry = session.createQuery(hql, Patient.class);
	  qry.setParameter(1, fid);
	  
	  
	  
	  Patient f = qry.getSingleResultOrNull();
	  
	  if(f!=null)
	  {
	  
	   System.out.println(f.toString());
	   
	  }
	  
	  else
	  {
	   System.out.println("Faculty ID Not Found");
	  }
	  sc.close();
	  session.close();
	  sf.close();

}
public void displaypatientbyidnamedparams()
{
	Configuration configuration = new Configuration();
	configuration.configure("practicehibernate.cfg.xml");
	
	SessionFactory sf = configuration.buildSessionFactory();
	Session session = sf.openSession();
	
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter Faculty ID:");
	int fid = sc.nextInt();
	
	String hql = "from Faculty where id=:v";
	
	Query<Patient> qry = session.createQuery(hql,Patient.class);
	qry.setParameter("v", fid);
	
	Patient f = qry.getSingleResultOrNull();
	
	if(f!=null)
	{
	
		System.out.println(f.toString());
		
	}
	else
	{
		System.out.println("Faculty ID Not Found");
	}
	sc.close();
	session.close();
	sf.close();
}
public void displaypatient()
{
	Configuration configuration = new Configuration();
	configuration.configure("practicehibernate.cfg.xml");
	
	SessionFactory sf = configuration.buildSessionFactory();
	Session session = sf.openSession();
	
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter Faculty contactnumber:");
	String fcontactnumber = sc.next();
	System.out.println("Enter Faculty ID:");
	int fid = sc.nextInt();
	
	String hql = "from Faculty where contactnumber=?1 and id=?2"; 
	
	Query<Patient> qry = session.createQuery(hql,Patient.class);
	qry.setParameter(1,fcontactnumber);
	qry.setParameter(2, fid);
	List<Patient> facultylist = qry.getResultList();
	System.out.println("Total Faculty="+facultylist.size());
	
	for(Patient f : facultylist)
	{
		System.out.println("ID:"+f.getId());
		System.out.println("NAME:"+f.getName());
		System.out.println("GENDER:"+f.getGender());
		System.out.println("SALARY:"+f.getSalary());
		System.out.println("DEPARTMENT:"+f.getDepartment());
		System.out.println("CONTACTNUMBER:"+f.getContactnumber());
		
	}
	sc.close();
	session.close();
	sf.close();
}

}
