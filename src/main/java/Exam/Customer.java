package Exam;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_table")
public class Customer
{
	@Id
	@Column(name = "cid")
	int cid;
	@Column(name = "cname",nullable = false,length = 50)
	String cname;
	@Column(name = "cemail",nullable = false,length = 50,unique = true)
	String cemail;
	@Column(name = "cage",nullable = false,length = 50)
	int cage;
	@Column(name = "clocation",nullable = false,length = 50)
	String clocation;
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", cname=" + cname + ", cemail=" + cemail + ", cage=" + cage + ", clocation="
				+ clocation + "]";
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCemail() {
		return cemail;
	}
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	public int getCage() {
		return cage;
	}
	public void setCage(int cage) {
		this.cage = cage;
	}
	public String getClocation() {
		return clocation;
	}
	public void setClocation(String clocation) {
		this.clocation = clocation;
	}
}
