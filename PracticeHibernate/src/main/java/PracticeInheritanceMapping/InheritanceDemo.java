package PracticeInheritanceMapping;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class InheritanceDemo 
{
  public static void main(String args[])
  {
	  Configuration cfg = new Configuration();
  	  cfg.configure("practicehibernate.cfg.xml");
  	
  	  SessionFactory sf = cfg.buildSessionFactory();
  	  Session session = sf.openSession();
  	
  	  Transaction t = session.beginTransaction();
  	  
  	  Mangement p = new Mangement();

  	  p.setName("CHAITHU");
  	  p.setAge(90);
  	  p.setEmail("chaithu@gmail.com");
  	  p.setContact("90909090");
  	  p.setLocation("MLG");
  	  
  	  Professors teacher = new Professors();
  	  teacher.setDepartment("CSE");
  	  teacher.setQualification("BTECH");
  	  teacher.setDesignation("FACULTY");
  	  teacher.setSalary(30000);
  	  
  	  Student s = new Student();
  	  s.setDepartment("Cyber");
  	  s.setProgram("BTECH");
  	  s.setYear(1);
  	  s.setSemester("ODD");
  	  s.setCgpa(9.6);
  	  s.setBacklogs(0);
  	  
  	  session.persist(p);
  	  session.persist(teacher);
  	  session.persist(s);
  	 
  	  t.commit();
  	  System.out.println("SUCCESS....");
  	
  	session.close();
  	sf.close();
  }
}