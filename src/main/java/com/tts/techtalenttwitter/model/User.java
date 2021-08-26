package com.tts.techtalenttwitter.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity //Tell Springboot this is going to be stored in a DATABASE.
public class User {

	@Id //Tell Springboot that this field is going to be the database table's
	    //primary key.
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;
	
	private String email;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int active;
	
	@CreationTimestamp //This will tell the JPA to autopopulate this when an object
	                   //is created.
	private Date createdAt;
	
	//This set of roles needs to be generated VIA a relationship in the relationship in the
	//database to work with the JPA....we have to add annotations to define that relationship
	//and then the JPA can fill out this value.
	
	@ManyToMany(cascade = CascadeType.ALL) //Specify the Users and roles have a many to many
	                                       //db relationship
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name="role_id"))				
	private Set<Role> roles; //This will contain all the security roles the user has.
	                         //However there is generally no way to store "compound" or 
	                         //complicated data structures in a database...... So
	                         //we need to get this Set of Roles from somewhere..
	
}
