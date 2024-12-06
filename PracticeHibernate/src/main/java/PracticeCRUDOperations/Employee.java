package PracticeCRUDOperations;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_table")
public class Employee 
{
	@Id
	private int id;
	@Column(name = "ename",length = 30,nullable = false)
	private String name;
	@Column(name = "eemail",length = 50,nullable = false)
	private String email;
	@Column(name = "egender",length = 20,nullable = false)
	private String gender;
	@Column(name = "econtact",unique = true,nullable = false,length = 10)
	private String contact;
	@Column(name = "esalary",length = 20,nullable = false)
	private Double salary;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
}
