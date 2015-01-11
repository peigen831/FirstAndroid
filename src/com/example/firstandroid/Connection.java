package com.example.firstandroid;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.chord.InvalidInterfaceException;
import com.samsung.android.sdk.chord.Schord;
import com.samsung.android.sdk.chord.SchordChannel;
import com.samsung.android.sdk.chord.SchordManager;

public class Connection {
	private static final String CHORD_SAMPLE_MESSAGE_TYPE = "com.samsung.android.sdk.chord.example.MESSAGE_TYPE";
	private static final String CHORD_HELLO_TEST_CHANNEL = "com.samsung.android.sdk.chord.example.HELLOTESTCHANNEL";
	
	private static Context context;
	private static Connection instance = null;
	public String otherNode;
	public SchordChannel channel;
	public SchordManager chordManager;
	
	
	public Connection(Context ctx){
		context = ctx;
		
		initializeChord();
		
		chordManager = new SchordManager(context);
	
		setupChordManager();
		
		instance = this;
	}
	
	
	
	// Listener for Chord manager events. 
		private SchordManager.StatusListener mManagerListener = new SchordManager.StatusListener () { 
			@Override 
			public void onStarted(String name, int reason) {
				
				if (STARTED_BY_USER == reason) { 
					// Called when Chord is started successfully
					joinChannel();
				}
			}
			
			@Override
			public void onStopped(int reason) {
				if (STOPPED_BY_USER == reason) {
					// Called when Chord is stopped. 
				} 
			} 
		};
	
	
	// Join a desired channel with a given listener. 
	private void joinChannel() { 
		try { 
			channel = chordManager.joinChannel(CHORD_HELLO_TEST_CHANNEL, mChannelListener); 
		} catch (IllegalArgumentException e) {
			e.printStackTrace(); 
		} catch (Exception e) {
			e.printStackTrace(); 
		} 
	}
	
	// Listener for Chord channel events. 
	private SchordChannel.StatusListener mChannelListener = new ChordChannelListenerAdapter() { 
		@Override
		public void onNodeJoined(String fromNode, String fromChannel) {
			byte[][] payload = new byte[1][]; 
			payload[0] = (fromNode + " joined").getBytes();
			otherNode = fromNode;
			channel = chordManager.getJoinedChannel(fromChannel);
			//channel.sendData(fromNode, CHORD_SAMPLE_MESSAGE_TYPE, payload);
		}

		@Override
		public void onDataReceived(String fromNode, String fromChannel, String payloadType, byte[][] payload) {
			
			
			if(payloadType.equals(CHORD_SAMPLE_MESSAGE_TYPE)){
				ChordMessage message = ChordMessage.obtainChatMessage(payload[0]);
				if(message.object instanceof Topic){
					Topic t = (Topic)message.object;
					Log.e("Title", t.getTitle());
				}
				else Log.e("Error", "Can't find datatype");
			}
			
			/*
			if(MainActivity.currentActivity.equals(MainActivity.MAIN_ACTIVITY))
				MainActivity.showMessage(message);
			else if (MainActivity.currentActivity.equals(MainActivity.NEXT_ACTIVITY))
				NextActivity.showMessage1(message);
			*/
		}
		
		@Override
		public void onNodeLeft(String fromNode, String fromChannel) {
			Log.e("DEVICE LEAVE", fromNode + " The device has disconnected to the network");
			String message = fromNode + "leave";
			Toast.makeText(context, message, Toast.LENGTH_LONG).show();
			
		}
	};
	
	private void setupChordManager(){
		List<Integer> interfaceList = chordManager.getAvailableInterfaceTypes(); 
		if (interfaceList.isEmpty()) { 
			// There is no connection. 
			return; 
		} 
		
		try { 
			chordManager.start(interfaceList.get(0).intValue(), mManagerListener);
		} catch (InvalidInterfaceException e) { 
			e.printStackTrace(); 
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	private void initializeChord(){
		Schord chord = new Schord(); 
		try {
			// Initialize an instance of Schord. 
			chord.initialize(context); 
		} catch (SsdkUnsupportedException e) {
			Log.e("NOT SUPPORT", "Device is uncompatible with the API");
		}
	}
	
	public void sendMessage(String message){
		byte[][] payload = new byte[1][0];
		payload[0] = message.getBytes();
		
		for(String node: channel.getJoinedNodeList())
			channel.sendData(node, CHORD_SAMPLE_MESSAGE_TYPE, payload);
	}
	
	public void sendTopic(ChordMessage topic){
		for(String node: channel.getJoinedNodeList())
			channel.sendData(node, CHORD_SAMPLE_MESSAGE_TYPE, new byte[][] { topic.getBytes() });
	}
	
	
	public static Connection getInstance(){
		if(instance == null){
			Log.e("CONNECTION GETINSTANCE", "instance is null");
		}
		return instance;
	}
	
	public static Context getContext(){
		return context;
	}
	
}
