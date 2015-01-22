package com.example.firstandroid;


import java.io.Serializable;

public class Answer implements Serializable {
	
	private String answer;
	private boolean isCorrect;
	
	public Answer(String answer, boolean isCorrect)
	{
		this.answer = answer;
		this.isCorrect = isCorrect;
	}
	
	public boolean isCorrect()
	{
		return isCorrect;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return answer;
	}

}
