package com.acechat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="chattable")
public class Chat {

	@Id
	@Column
	private int chatid;
	@ManyToOne
	@JoinColumn(name="userid")
	private User userid;
	@Column
	private String groupname;
	@Column
	private String status;
	
}
