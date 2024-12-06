package HQLDemo;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

public class HQLOperations 
{
	public static void main(String args[])
	{
		HQLOperations operations = new HQLOperations();
		//operations.addProduct();
		//operations.displayallproductscompleteobject();
		//operations.displayallproductspartialobject();
		//operations.aggregatefunctions();
		//operations.updatepositionalparams();
		//operations.updatenameparams();
		//operations.deletepositionalparams();
		//operations.deletenamedparams();
		//operations.displayproductbyidpositionalparams();
		//operations.displayproductbyidnamedparams();
		//operations.displayproducts();
		operations.paginationdemo();
	}
	//add product using Persistent object (PO)
	
	public void addProduct()
	{
		Configuration configuration = new Configuration();
	    configuration.configure("hibernate.cfg.xml");
	       
	    SessionFactory sf = configuration.buildSessionFactory();
	    Session session = sf.openSession();
	       
	    Transaction t =  session.beginTransaction();
	       
	    Product product = new Product();
	    product.setId(1);
	    product.setCategory("TOOLS");
	    product.setName("BAG");
	    product.setCost(500);
	    product.setStock(10);
	       // please set stock status based on stock value
	    product.setStatus(true);
	       
	    session.persist(product);
	    t.commit();
	    System.out.println("Product Added Successfully");
	    session.close();
	    sf.close();
	}
	
	//complete object()
	
	public void displayallproductscompleteobject()
	{
		Configuration configuration = new Configuration();
	    configuration.configure("hibernate.cfg.xml");
	       
	    SessionFactory sf = configuration.buildSessionFactory();
	    Session session = sf.openSession();
	    
	    String hql= "from Product"; // select * from product_table
	    
	    Query<Product> qry = session.createQuery(hql, Product.class);
	    List<Product> productlist = qry.getResultList();
	    System.out.println("Total Products="+productlist.size());
	    
	    for(Product p:productlist)
	    {
	    	System.out.println("ID="+p.getId());
	    	System.out.println("Category="+p.getCategory());
	    	System.out.println("Name="+p.getName());
	    	System.out.println("Cost="+p.getCost());
	    	System.out.println("Quantity="+p.getStock());
	    	System.out.println("Stock="+p.isStatus());
	    }
	    
	    session.close();
	    sf.close();
	}
	
	public void displayallproductspartialobject() // partial object 
	{
		Configuration configuration = new Configuration();
	    configuration.configure("hibernate.cfg.xml");
	       
	    SessionFactory sf = configuration.buildSessionFactory();
	    Session session = sf.openSession();
	    
	    String hql = "select p.id,p.name,p.cost from Product p";
	    // p is reference object or alias of Product type
	    Query<Object[]> qry = session.createQuery(hql, Object[].class);
	    List<Object[]> productlist = qry.getResultList();
	    
	    System.out.println("Total Products="+productlist.size());
	    
	    for(Object[] obj:productlist)
	    {
	    	System.out.println("ID="+obj[0]);
	    	System.out.println("Name="+obj[1]);
	    	System.out.println("Cost="+obj[2]);
	    }
	    session.close();
	    sf.close();
	}
	
	public void aggregatefunctions()
	{
		Configuration configuration = new Configuration();
	    configuration.configure("hibernate.cfg.xml");
	       
	    SessionFactory sf = configuration.buildSessionFactory();
	    Session session = sf.openSession();
	    
	    String hql1="select count(*) from Product";
	    // u can consider count(property)
	    Query<Long> qry1 = session.createQuery(hql1,Long.class);
	    Long count = qry1.getSingleResult();
	    System.out.println("Total Products Count = "+count);
	    
	    String hql2="select sum(cost) from Product";
	    Query<Double> qry2 = session.createQuery(hql2,Double.class);
	    Double totalsalary = qry2.getSingleResult();
	    System.out.println("Total Cost = "+totalsalary);
	    
	    String hql3="select avg(cost) from Product";
	    Query<Double> qry3 = session.createQuery(hql3,Double.class);
	    Double avgsalary = qry3.getSingleResult();
	    System.out.println("Average Cost = "+avgsalary);
	    
	    String hql4="select min(stock) from Product";
	    Query<Integer> qry4 = session.createQuery(hql4,Integer.class);
	    int minsalary = qry4.getSingleResult();
	    System.out.println("Minimum Cost = "+minsalary);
	    
	    String hql5="select max(stock) from Product";
	    Query<Integer> qry5 = session.createQuery(hql5,Integer.class);
	    int maximumsalary = qry5.getSingleResult();
	    System.out.println("Minimum Cost = "+maximumsalary);
	    
	    session.close();
	    sf.close();
	}
	public void updatepositionalparams()
	{
		Configuration configuration = new Configuration();
	    configuration.configure("hibernate.cfg.xml");
	       
	    SessionFactory sf = configuration.buildSessionFactory();
	    Session session = sf.openSession();
	       
	    Transaction t =  session.beginTransaction();
	    
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter Id : ");
	    int pid = sc.nextInt();
	    System.out.println("Enter Name : ");
	    String pname = sc.next();
	    System.out.println("Enter Cost : ");
	    Double pcost = sc.nextDouble();
	    
	    MutationQuery query = session.createMutationQuery("update Product set name=?1,cost=?2 where id=?3");
	    query.setParameter(1, pname);
	    query.setParameter(2, pcost);
	    query.setParameter(3, pid);
	    
	    int n = query.executeUpdate();
	    t.commit();
	    System.out.println(n+" Product(s) Updated Successfully");
	    
	    session.close();
	    sf.close();
	    sc.close();
	}
	public void deletepositionalparams()
	{
		Configuration configuration = new Configuration();
	    configuration.configure("hibernate.cfg.xml");
	       
	    SessionFactory sf = configuration.buildSessionFactory();
	    Session session = sf.openSession();
	       
	    Transaction t =  session.beginTransaction();
	    
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter Id to Delete : ");
	    int pid = sc.nextInt();
	    
	    MutationQuery query = session.createMutationQuery("delete from Product where id=?1");
	    query.setParameter(1, pid);
	    
	    int n = query.executeUpdate();
	    t.commit();
	    
	    if(n>0) 
	    {
	    	System.out.println("Product Deleted Successfully");
	    }
	    else
	    {
	    	System.out.println("Product Deleted Not Successfully");
	    }
	    session.close();
	    sf.close();
	    sc.close();
	    
	    
	    
	}
	public void updatenameparams()
	{
		Configuration configuration = new Configuration();
	    configuration.configure("hibernate.cfg.xml");
	       
	    SessionFactory sf = configuration.buildSessionFactory();
	    Session session = sf.openSession();
	       
	    Transaction t =  session.beginTransaction();
	    
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter Id : ");
	    int pid = sc.nextInt();
	    System.out.println("Enter Name : ");
	    String pname = sc.next();
	    System.out.println("Enter Cost : ");
	    Double pcost = sc.nextDouble();
	    
	    MutationQuery query = session.createMutationQuery("update Product set name=:V1,cost=:V2 where id=:V3");
	    query.setParameter("V1", pname);
	    query.setParameter("V2", pcost);
	    query.setParameter("V3", pid);
	    
	    int n = query.executeUpdate();
	    t.commit();
	    System.out.println(n+" Product(s) Updated Successfully");
	    
	    session.close();
	    sf.close();
	    sc.close();
	}
	public void deletenamedparams()
	{
		Configuration configuration = new Configuration();
	    configuration.configure("hibernate.cfg.xml");
	       
	    SessionFactory sf = configuration.buildSessionFactory();
	    Session session = sf.openSession();
	       
	    Transaction t =  session.beginTransaction();
	    
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter Id to Delete : ");
	    int pid = sc.nextInt();
	    
	    MutationQuery query = session.createMutationQuery("delete from Product where id=:V");
	    query.setParameter("V", pid);
	    
	    int n = query.executeUpdate();
	    t.commit();
	    
	    if(n>0) 
	    {
	    	System.out.println("Product Deleted Successfully");
	    }
	    else
	    {
	    	System.out.println("Product Deleted Not Successfully");
	    }
	    session.close();
	    sf.close();
	    sc.close();
	}
	
	 // display product based on id using positional params
	public void displayproductbyidpositionalparams()
	{
		Configuration configuration = new Configuration();
	    configuration.configure("hibernate.cfg.xml");
	       
	    SessionFactory sf = configuration.buildSessionFactory();
	    Session session = sf.openSession();
	    
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter Product ID :");
	    int pid = sc.nextInt();
	    
	    String hql = "from Product where id=?1";
	    
	    Query<Product> qry = session.createQuery(hql,Product.class);
	    qry.setParameter(1, pid);
	    
	    Product p = qry.getSingleResultOrNull();
	    if(p!=null)
	    {
	    	// you can print every property in Product Object (p) using getter methods 
	    	System.out.println(p.toString());
	    }
	    else
	    {
	    	System.out.println("Product ID not Found");
	    }
	    
	    sc.close();
	    session.close();
	    sf.close();
	}
	
	 // display product based on id using named params
	
	public void displayproductbyidnamedparams()
	{
		Configuration configuration = new Configuration();
	    configuration.configure("hibernate.cfg.xml");
	       
	    SessionFactory sf = configuration.buildSessionFactory();
	    Session session = sf.openSession();
	    
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter Product ID :");
	    int pid = sc.nextInt();
	    
	    String hql = "from Product where id=:v";
	    
	    Query<Product> qry = session.createQuery(hql,Product.class);
	    qry.setParameter("v", pid);
	    
	    Product p = qry.getSingleResultOrNull();
	    if(p!=null)
	    {
	    	// you can print every property in Product Object (p) using getter methods 
	    	System.out.println(p.toString());
	    }
	    else
	    {
	    	System.out.println("Product ID not Found");
	    }
	    
	    sc.close();
	    session.close();
	    sf.close();
	}
		
	// display products based on category and stock
	public void displayproducts()
	{
		Configuration configuration = new Configuration();
	    configuration.configure("hibernate.cfg.xml");
		       
	    SessionFactory sf = configuration.buildSessionFactory();
	    Session session = sf.openSession();
	    
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter Product Category :");
	    String pcategory = sc.next();
	    System.out.println("Enter Product Stock :");
	    int pstock = sc.nextInt();
		    
	    String hql = "from Product where category=?1 and stock>=?2";
	    
	    Query<Product> qry = session.createQuery(hql,Product.class);
	    qry.setParameter(1, pcategory);
	    qry.setParameter(2,pstock);
	    
	    List<Product> productlist = qry.getResultList();
	    
	    System.out.println("Products Count="+productlist.size());
	    
	    for(Product p:productlist)
	    {
	    	System.out.println("ID="+p.getId());
	    	System.out.println("Category="+p.getCategory());
	    	System.out.println("Name="+p.getName());
	    	System.out.println("Cost="+p.getCost());
	    	System.out.println("Quantity="+p.getStock());
	    	System.out.println("Stock="+p.isStatus());
	    }
	    sc.close();
	    session.close();
	    sf.close();
	}
	
	// pagination
	
	public void paginationdemo()
	{
		Configuration configuration = new Configuration();
	    configuration.configure("hibernate.cfg.xml");
	         
	    SessionFactory sf = configuration.buildSessionFactory();
	    Session session = sf.openSession();
	         
	    String hql = "from Product"; // select * from product_table
	         
	    Query<Product> qry = session.createQuery(hql, Product.class);
	    qry.setFirstResult(1);
	    qry.setMaxResults(3);
	    List<Product> productlist =  qry.getResultList();
	         
	    System.out.println("Total Products="+productlist.size());
	        
	    for( Product p : productlist) 
	    {
	    	System.out.println(p.toString());
	    }
	          
	    session.close();
	    sf.close();
	}
		
}
