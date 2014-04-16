package com.socialchef.service.models;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the user_types database table.
 *
 */
@Entity
@Table(name="user_types")
@NamedQuery(name="UserType.findAll", query="SELECT u FROM UserType u")
public class UserType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USER_TYPES_ID_GENERATOR", sequenceName="USER_TYPES_ID_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_TYPES_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=20, unique=true)
	private String name;

	//bi-directional many-to-one association to UsersUserType
	@OneToMany(mappedBy="userType")
	private Set<UsersUserType> usersUserTypes;

	public UserType() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UsersUserType> getUsersUserTypes() {
		return this.usersUserTypes;
	}

	public void setUsersUserTypes(Set<UsersUserType> usersUserTypes) {
		this.usersUserTypes = usersUserTypes;
	}

	public UsersUserType addUsersUserType(UsersUserType usersUserType) {
		getUsersUserTypes().add(usersUserType);
		usersUserType.setUserType(this);

		return usersUserType;
	}

	public UsersUserType removeUsersUserType(UsersUserType usersUserType) {
		getUsersUserTypes().remove(usersUserType);
		usersUserType.setUserType(null);

		return usersUserType;
	}

	@Override
	public boolean equals(Object obj) {
		UserType ut = (UserType) obj;
		return ut.getName().equalsIgnoreCase(this.name);
	}

}