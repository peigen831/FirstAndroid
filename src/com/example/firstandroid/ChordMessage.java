package com.example.firstandroid;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Concrete implementation of Message using the Samsung Chord API. Implements <code>Serializable</code> to allow the message to contain Objects.
 * @author Andrew
 *
 */
public class ChordMessage implements Serializable {

	/**Chord version number?
	 * 
	 */
	private static final long serialVersionUID = 20130219L;
	
	Serializable object;
	
	ChordMessage(Serializable object) {
		this.object = object;
	}

	/**
	 * Returns {@link ChordMessage} in the form of byte array.
	 * @return byte array equivalent of the ChordMessage
	 */
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
	public static ChordMessage obtainChatMessage(byte[] data) {
		final ByteArrayInputStream in = new ByteArrayInputStream(data);
		final ObjectInputStream is;
		ChordMessage message = null;

		try {
			is = new ObjectInputStream(in);
			message = (ChordMessage) is.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return message;
	}

}
