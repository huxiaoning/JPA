package org.hxn.app.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "role")
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2816187024268701102L;

	@Id
	@GeneratedValue(generator = "roleGenerator")
	@GenericGenerator(name = "roleGenerator", strategy = "uuid.hex")
	@Column(name = "role_id", length = 32)
	private String id;

	@Column(name = "name", length = 20, unique = true, nullable = false)
	private String name;
	
	@Column(name = "sn", length = 20, unique = true, nullable = false)
	private String sn;
	

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "role_resource", joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "resource_id") })
	private Set<Resource> resources;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}


}
