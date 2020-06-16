package com.ssafy.groupbuying.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;


@Entity
@Table(name="comment")
public @Data class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long comment_id;
	
	@ManyToOne
	@JoinColumn(name= "board")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Board board;
	
	@OneToOne
	@JoinColumn(name= "user")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@NotNull
	@Column(length = 300)
	private String context;
	
}
