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
@Table(name="blockusertable")
public class Block {

	@Id
	@Column
	private int blockusertableid;
	@ManyToOne
	@JoinColumn(name="blockerid")
	private User blockerid;
	@ManyToOne
	@JoinColumn(name="blockeeid")
	private User blockeeid;
	
}
