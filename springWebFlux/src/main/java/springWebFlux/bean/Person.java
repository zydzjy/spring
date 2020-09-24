<<<<<<< HEAD
package springWebFlux.bean;

import org.springframework.data.annotation.Id;

public class Person {

	  @Id
	  private Long id;
	  private String firstname;
	  private String lastname;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
=======
package springWebFlux.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;

@Scope(scopeName = "request")
public class Person {

	@Id
	private Long id;
	private String firstname;
	private String lastname;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
>>>>>>> 49f8c4445bdd3ac99b29b4e622b9cb17970d9fcd
