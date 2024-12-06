package HQLDemo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class HCQLOperations 
{
	public static void main(String args[])
	{
		HCQLOperations operations = new HCQLOperations();
		
		//operations.restrictionsdemo();
		operations.orderdemo();
		//operations.aggregatefunction();
		
		
	}
	
   public void restrictionsdemo()
    {
	Configuration configuration = new Configuration();
    configuration.configure("practicehibernate.cfg.xml");
		        
	SessionFactory sf = configuration.buildSessionFactory();
	Session session = sf.openSession();
		     
	/*
	 * CriteriaBuilder cb = session.getCriteriaBuilder(); 
	 * CriteriaQuery<Faculty> cq = cb.createQuery(Faculty.class);
	 *  // from Faculty; [Complete Object]
	 * Root<Faculty> root = cq.from(Faculty.class);
	 * System.out.println("*All Faculty Objects*"); 
	 * List<Faculty> faculties = session.createQuery(cq).getResultList();
	 * System.out.println("Faculty Count="+faculties.size()); 
	 * for(Faculty f :faculties)
	 *  {
	 * 
	 * System.out.println(f.toString());
	 *  }
	 */
		        
	/*
	 * CriteriaBuilder cb = session.getCriteriaBuilder();
	 *  CriteriaQuery<Faculty> cq  = cb.createQuery(Faculty.class);
	 *   // from Faculty; [Complete Object]
	 * Root<Faculty> root = cq.from(Faculty.class);
	 * cq.select(root).where(cb.equal(root.get("gender"), "FEMALE"));
	 * System.out.println("*Student Objects with equal criteria*");
	 * List<Faculty> faculties = session.createQuery(cq).getResultList();
	 * System.out.println("Faculty Count="+faculties.size()); 
	 * for(Faculty f : faculties) 
	 * {
	 * 
	 * System.out.println(f.toString()); 
	 * }
	 */
		        
	CriteriaBuilder cb = session.getCriteriaBuilder();
	CriteriaQuery<Patient> cq = cb.createQuery(Patient.class);
	// from Faculty; [Complete Object]
	Root<Patient> root = cq.from(Patient.class);
	
	//cq.select(root).where(cb.lessThan(root.get("salary"),100000));
	//cq.select(root).where(cb.greaterThan(root.get("salary"), 100000));
	//cq.select(root).where(cb.le(root.get("salary"),97000)); 
	//cq.select(root).where(cb.ge(root.get("salary"),97000)); 
	//cq.select(root).where(cb.notEqual(root.get("department"), "CSE")); 
	//cq.select(root).where(cb.like(root.get("department"),"C%")); 
	//cq.select(root).where(cb.like(root.get("department"),"%E")); 
    //cq.select(root).where(cb.like(root.get("gender"),"%MALE%")); 
	//cq.select(root).where(cb.like(root.get("name"),"S__"));  
	//cq.select(root).where(cb.between(root.get("salary"),97000,200000));
	//cq.select(root).where(cb.not(cb.equal(root.get("department"),"CSE")));
	
	List<String> departs = Arrays.asList("CSE","EEE","ARTS");
	cq.select(root).where(root.get("department").in(departs));
	
	
	  System.out.println("*Faculty Objects with different comparision criteria*");
	  List<Patient> faculties = session.createQuery(cq).getResultList();
	  System.out.println("Faculties Count="+faculties.size()); for(Patient f :
	  faculties) {
	  
	  System.out.println(f.toString()); }
	 
	
		        
	 session.close();
	 sf.close();
		        
 }
   public void orderdemo()
   {
	    Configuration configuration = new Configuration();
	    configuration.configure("practicehibernate.cfg.xml");
			        
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
			     
		
		  CriteriaBuilder cb = session.getCriteriaBuilder();
		  CriteriaQuery<Patient> cq = cb.createQuery(Patient.class); 
		  Root<Patient> root = cq.from(Patient.class);
		  
		  //cq.orderBy(cb.asc(root.get("salary")));
		  
		  cq.orderBy(cb.desc(root.get("salary")));
		  
		  System.out.println("*All Faculty Objects*"); 
		  List<Patient> faculties = session.createQuery(cq).getResultList();
		  System.out.println("Faculty Count="+faculties.size()); 
		  for(Patient f : faculties) 
		  {
		  
		  System.out.println(f.toString()); 
		  }
		 
		  session.close();
		  sf.close();
   }
   public void aggregatefunction()
   {
	   	Configuration configuration = new Configuration();
	    configuration.configure("practicehibernate.cfg.xml");
	    SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
			     
		
		CriteriaBuilder cb1 = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq1 = cb1.createQuery(Long.class); 
		Root<Patient> root1 = cq1.from(Patient.class);
		cq1.select(cb1.count(root1.get("name")));
		long totolcount = session.createQuery(cq1).getSingleResult();
		System.out.println("Total Faculty Count = "+totolcount);
		
		CriteriaBuilder cb2 = session.getCriteriaBuilder();
		CriteriaQuery<Double> cq2 = cb2.createQuery(Double.class); 
		Root<Patient> root2 = cq2.from(Patient.class);
		cq2.select(cb2.sum(root2.get("salary")));
		Double totalsum = session.createQuery(cq2).getSingleResult();
		System.out.println("Total Faculty Salary = "+totalsum);
		
		CriteriaBuilder cb3 = session.getCriteriaBuilder();
		CriteriaQuery<Double> cq3 = cb3.createQuery(Double.class); 
		Root<Patient> root3 = cq3.from(Patient.class);
		cq3.select(cb3.avg(root3.get("salary")));
		Double totalavg = session.createQuery(cq3).getSingleResult();
		System.out.println("Average Faculty Salary = "+totalavg);
		
		CriteriaBuilder cb4 = session.getCriteriaBuilder();
		CriteriaQuery<Integer> cq4 = cb4.createQuery(Integer.class); 
		Root<Patient> root4 = cq4.from(Patient.class);
		cq4.select(cb4.min(root4.get("id")));
		int minid = session.createQuery(cq4).getSingleResult();
		System.out.println("Min Faculty ID = "+minid);
		
		CriteriaBuilder cb5 = session.getCriteriaBuilder();
		CriteriaQuery<Integer> cq5 = cb5.createQuery(Integer.class); 
		Root<Patient> root5 = cq5.from(Patient.class);
		cq5.select(cb5.max(root5.get("id")));
		int maxid = session.createQuery(cq5).getSingleResult();
		System.out.println("Max Faculty ID = "+maxid);
		
		CriteriaBuilder cb6 = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq6 = cb6.createQuery(Long.class); 
		Root<Patient> root6 = cq6.from(Patient.class);
		cq6.select(cb6.countDistinct(root6.get("name")));
		long distinctcount = session.createQuery(cq6).getSingleResult();
		System.out.println("Total Faculty Count = "+distinctcount);
		
		session.close();
		sf.close();
		
		
		
   }

}