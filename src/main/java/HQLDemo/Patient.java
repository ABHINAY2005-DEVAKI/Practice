package HQLDemo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="patient_table")
public class Patient 
{
	@Id
	@Column(name="fid")
	private int id;
	@Column(name="fname",length = 50,nullable = false)
	private String name;
	@Column(name="fgender",length=10,nullable = false)
	private String gender;
	@Column(name="fdepertment",length=10,nullable = false)
	private String department;
	@Column(name="fsalary",length=10,nullable=false)
	private double salary;
	@Column(name="fcontactno",length=20,nullable=false,unique = true)
	private String contactnumber;
	
	@Override
	public String toString() {
		return "Faculty [id=" + id + ", name=" + name + ", gender=" + gender + ", department=" + department
				+ ", salary=" + salary + ", contactnumber=" + contactnumber + "]";
	}
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}
	

}