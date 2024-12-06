package HCQLDemo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class HCQLOperations 
{
	public static void main(String[] args)
	{
		HCQLOperations operations = new HCQLOperations();
		//operations.addStudent();
		//operations.restrictionsdemo();
		//operations.oerderdemo();
		//operations.aggregatefunction();
		//operations.hcqldemo();
	}
	
	// add Student using Persistent Object (PO)
	public void addStudent()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction t = session.beginTransaction();
		
		Student student = new Student();
		student.setId(104);
		student.setName("PFSD");
		student.setGender("FEMALE");
		student.setAge(34);
		student.setDepartment("CSEIT");
		student.setEmail("pfsd@gmail.com");
		student.setContact("8908908918");
		
		session.persist(student);
		t.commit();
		System.out.println("Student Added Successfully");
		session.close();
		sf.close();
	}
	
	public void restrictionsdemo()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		/*CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Student> cq = cb.createQuery(Student.class);
		// from Student [Complete Object]
		Root<Student> root = cq.from(Student.class);
		
		System.out.println("****All Student Object****");		
		List<Student> students = session.createQuery(cq).getResultList();
		System.out.println("Total Students = "+students.size());
		for(Student s:students)
		{
			// you can also you getter methods of Student Object(s) 
			System.out.println(s.toString());
		}*/
		
		
		
		
		/*
		 * CriteriaBuilder cb = session.getCriteriaBuilder(); CriteriaQuery<Student> cq
		 * = cb.createQuery(Student.class); // from Student [Complete Object]
		 * Root<Student> root = cq.from(Student.class);
		 * 
		 * cq.select(root).where(cb.equal(root.get("department"),"CSE"));
		 * 
		 * System.out.println("****Student Objects with equal Criteria****");
		 * List<Student> students = session.createQuery(cq).getResultList(); 
		 * System.out.println("Total Students = "+students.size());
		 * for(Student s:students) { 
		 * // you can also you getter methods of Student Object(s)
		 * System.out.println(s.toString()); 
		 * }
		 */
		
		
		
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Student> cq = cb.createQuery(Student.class);
		// from Student [Complete Object]
		Root<Student> root = cq.from(Student.class);
		
		//cq.select(root).where(cb.lessThan(root.get("age"), 50));
		//cq.select(root).where(cb.greaterThan(root.get("age"), 45));
		//cq.select(root).where(cb.le(root.get("age"), 40)); // less than or equal to
		//cq.select(root).where(cb.ge(root.get("age"), 40));
		//cq.select(root).where(cb.notEqual(root.get("department"),"CSE")); //not equal
		//cq.select(root).where(cb.like(root.get("department"), "C%")); // starts with C
		//cq.select(root).where(cb.like(root.get("department"),"%E")); // ends with E
		//cq.select(root).where(cb.like(root.get("department"),"%EI%"));//SEI as substring
		//cq.select(root).where(cb.like(root.get("email"),"%gmail%"));//gmail as substring
		//cq.select(root).where(cb.like(root.get("name"),"___D"));//ends with D and length should be 4
		//cq.select(root).where(cb.between(root.get("age"),30,50));// age between 30 and 50
		
		// you can apply not criteria for all above comparison criteria 
		//cq.select(root).where(cb.not(cb.equal(root.get("department"),"CSE")));
		List<String> depts = Arrays.asList("CSE","ECE","ME");
		cq.select(root).where(root.get("department").in(depts));
		
		
		System.out.println("****Student Objects with comparision Criteria****");		
		List<Student> students = session.createQuery(cq).getResultList();
		System.out.println("Total Students = "+students.size());
		for(Student s:students)
		{
			// you can also you getter methods of Student Object(s) 
			System.out.println(s.toString());
		}
		
		
		session.close();
		sf.close();
	}
	public void oerderdemo() // ascending / descending
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Student> cq = cb.createQuery(Student.class);
		Root<Student> root = cq.from(Student.class);
		
		// ascending order based on name 
		//cq.orderBy(cb.asc(root.get("name")));
		cq.orderBy(cb.desc(root.get("name")));
		
		System.out.println("****Order By Demo****");		
		List<Student> students = session.createQuery(cq).getResultList();
		System.out.println("Students Count= "+students.size());
		for(Student s:students)
		{
			System.out.println(s.toString());
		}
		session.close();
		sf.close();
		
	}
	public void aggregatefunction()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		CriteriaBuilder cb1 = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq1 = cb1.createQuery(Long.class);
		Root<Student> root1 = cq1.from(Student.class);
		cq1.select(cb1.count(root1.get("name")));
		Long count = session.createQuery(cq1).getSingleResult();
		System.out.println("Total Student Count = "+count);
		
		CriteriaBuilder cb2 = session.getCriteriaBuilder();
		CriteriaQuery<Double> cq2 = cb2.createQuery(Double.class);
		Root<Student> root2 = cq2.from(Student.class);
		cq2.select(cb2.sum(root2.get("age")));
		double totalage = session.createQuery(cq2).getSingleResult();
		System.out.println("Sum Student Age = "+totalage);
		
		CriteriaBuilder cb3= session.getCriteriaBuilder();
	    CriteriaQuery<Double> cq3= cb3.createQuery(Double.class);
	    Root<Student> root3= cq3.from(Student.class);
	    cq3.select(cb3.avg(root3.get("age")));
	    double avgage = session.createQuery(cq3).getSingleResult();
	    System.out.println("Average Students Age="+avgage);
	      
	    CriteriaBuilder cb4= session.getCriteriaBuilder();
	    CriteriaQuery<Integer> cq4= cb4.createQuery(Integer.class);
	    Root<Student> root4= cq4.from(Student.class);
	    cq4.select(cb4.min(root4.get("id")));
	    int minsid = session.createQuery(cq4).getSingleResult();
	    System.out.println("Minimum Students ID="+minsid);
	      

	    CriteriaBuilder cb5= session.getCriteriaBuilder();
	    CriteriaQuery<Integer> cq5= cb5.createQuery(Integer.class);
	    Root<Student> root5= cq5.from(Student.class);
	    cq5.select(cb5.max(root5.get("id")));
	    int maxsid = session.createQuery(cq5).getSingleResult();
	    System.out.println("Maximum Students ID="+maxsid);

		
		CriteriaBuilder cb6 = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq6 = cb6.createQuery(Long.class);
		Root<Student> root6 = cq6.from(Student.class);
		cq6.select(cb6.countDistinct(root6.get("department")));
		long distinctcount = session.createQuery(cq6).getSingleResult();
		System.out.println("Maximum Student ID = "+distinctcount);
		
		session.close();
		sf.close();
	}
	
	// display students of CSE Department based on age in descending order
	public void hcqldemo() //usecase
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		 CriteriaBuilder cb = session.getCriteriaBuilder(); CriteriaQuery<Student> 
		 cq = cb.createQuery(Student.class); // from Student [Complete Object]
		 Root<Student> root = cq.from(Student.class);
		 
		 cq.select(root).where(cb.equal(root.get("department"),"CSE"));
		 cq.orderBy(cb.desc(root.get("name")));
		 
		 System.out.println("****Student Objects with equal Criteria****");
		 List<Student> students = session.createQuery(cq).getResultList(); 
		 System.out.println("Students Count = "+students.size());
		 for(Student s:students) { 
			 System.out.println(s.toString()); 
		 }
		session.close();
		sf.close();
	}
}
