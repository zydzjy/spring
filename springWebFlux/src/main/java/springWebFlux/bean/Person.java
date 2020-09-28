package springWebFlux.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;

@Scope(scopeName = "request")
public class Person {

	@Id
	private Integer id;
	private String firstname;
	private String lastname;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}