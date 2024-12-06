package InheritanceMapping;

import jakarta.persistence.Column;
//import jakarta.persistence.DiscriminatorColumn;
//import jakarta.persistence.DiscriminatorType;
//import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
// no table name

// Single Table Strategy
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "type",discriminatorType = DiscriminatorType.STRING,length = 20)
//@DiscriminatorValue(value = "PERSON")

//Table For Class Strategy
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

// Jointed Table
@Inheritance(strategy = InheritanceType.JOINED)


public class Person 
{
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="pname",length=50)
	private String name;
	@Column(name="page")
	private double age;
	@Column(name="pemail",length=50,unique = true)
	private String email;
	@Column(name="pcontact",length=20,unique = true)
	private String contact;
	@Column(name="plocation",length=50)
	private String location;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAge() {
		return age;
	}
	public void setAge(double age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
