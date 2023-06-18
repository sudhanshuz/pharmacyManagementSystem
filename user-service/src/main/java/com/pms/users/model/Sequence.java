package com.pms.users.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection="sequence")
@Component
public class Sequence {
	@Id
	private String id;
	private int seq;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public Sequence(String id, int seq) {
		super();
		this.id = id;
		this.seq = seq;
	}
	public Sequence() {
		super();
		// TODO Auto-generated constructor stub
	}
}
