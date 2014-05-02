package com.socialchef.service.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the users_user_types database table.
 * 
 */
@Entity
@Table(name="users_user_types")
@NamedQuery(name="UsersUserType.findAll", query="SELECT u FROM UsersUserType u")
public class UsersUserType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USERS_USER_TYPES_ID_GENERATOR",
		sequenceName="USERS_USER_TYPES_ID_SEQUENCE", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERS_USER_TYPES_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to UserType
	@ManyToOne
	@JoinColumn(name="user_type_id", nullable=false)
	private UserType userType;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;

	public UsersUserType() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}