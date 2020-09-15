package edu.usml.springboot.MathQuestionBank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="problem")
public class Problem {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pid")
	private Integer Id;
	
	@Column(name="title")
	private String title;
	@Column(name="content")
	private String content;
	
	public Problem() {
		
	}
	

	public Integer getId() {
		return Id;
	}


	public void setId(Integer id) {
		Id = id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Problem(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Problem [pid=" + Id + ", title=" + title + ", content=" + content + "]";
	}
	
	
}
