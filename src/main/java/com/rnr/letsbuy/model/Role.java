package com.rnr.letsbuy.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="ROLES")
public class Role extends BaseModel<Long>{
	
	private String role;
	
	//MappedBy allows us to still link from the table not containing the constraint to the other table.
	@ManyToMany(mappedBy="roles")
	private Set<User> users = new HashSet<>();
	
	private Role(){
		
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Role [role=" + role + ", users=" + users + "]";
	}
}
