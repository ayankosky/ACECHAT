package com.acechat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name="usertable")
public class User {
	
	
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String name;
	@Column
	private String profilepic;
	@Id
	@Column	
	private int userid;
	

}
