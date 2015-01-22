package com.example.firstandroid;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
	
	private String question;
	private ArrayList<Answer> answers;
	
	public Question(String q)
	{
		this.question = q;
	}
	
	public ArrayList<Answer> getAnswers()
	{
		return answers;
	}
	
	public void setAnswers(ArrayList<Answer> ans)
	{
		this.answers = ans;
	}
	
	public String getQuestion()
	{
		return question;
	}
	
	
}
