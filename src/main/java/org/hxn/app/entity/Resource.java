package org.hxn.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "resource")
public class Resource implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6714942431985959141L;
	@Id
	@GeneratedValue(generator = "resourceGenerator")
	@GenericGenerator(name = "resourceGenerator", strategy = "uuid.hex")
	@Column(name = "resource_id", length = 32)
	private String id;
	@Column(name="name",length=20,unique=true,nullable=false)
	private String name;
	@Column(name="permission",length=20,unique=true)
	private String permission;
	@Column(name="url",length=20,unique=true,nullable=false)
	private String url;
	

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

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", name=" + name + ", permission=" + permission + ", url=" + url + "]";
	}

}
