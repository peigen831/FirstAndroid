package com.example.firstandroid;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Topic implements Serializable {
	
	private String title;
	private ArrayList<Question> questions;
	
	public Topic(String title)
	{
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return title;
	}
	
	
	public byte[] getBytes() {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final ObjectOutputStream os;

		try {
			os = new ObjectOutputStream(out);
			os.writeObject(this);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return out.toByteArray();
	}

	/**
	 * Recreates {@link ChordMessage} from the byte array.
	 * 
	 * @param data
	 *      byte array representing {@link ChordMessage}
	 * @return the original ChordMessage in readable form
	 */
	public static Topic obtainChatMessage(byte[] data) {
		final ByteArrayInputStream in = new ByteArrayInputStream(data);
		final ObjectInputStream is;
		Topic message = null;

		try {
			is = new ObjectInputStream(in);
			message = (Topic) is.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return message;
	}
	

}
