package com.acechat.model;

import java.sql.Timestamp;

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
@Table(name="chatlogtable")
public class ChatLog {

	@Id
	@Column
	private int chatlogid;
	@ManyToOne
	@JoinColumn(name="userid")
	private User userid;
	@Column
	private String message;
	@Column
	private Timestamp senttime;
	@Column
	private String groupname;
}
