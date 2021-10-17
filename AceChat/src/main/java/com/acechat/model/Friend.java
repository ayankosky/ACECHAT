package com.acechat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="friendtable")
public class Friend {
	
	@Id
	@Column
	private int friendtableid;
	@ManyToOne
	@JoinColumn(name="requesterid")
	private User requesterid;
	@ManyToOne
	@JoinColumn(name="requesteeid")
	private User requesteeid;
	@Column
	private String status;
	
	

}
