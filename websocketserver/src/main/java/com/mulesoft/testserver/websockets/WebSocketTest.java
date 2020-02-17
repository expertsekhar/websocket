package com.mulesoft.testserver.websockets;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/websocket")
public class WebSocketTest {

	public void sendMsgs(Session session, long numMsgs, long delay) throws IOException, InterruptedException {
		String response1 = "{\"Sequence\":";
		String response2 = ",\"Event\":{\"EventClass\": \"DeviceMessage\",\"MessageData\":{\"MsgID\":\"9fb4b773-d86e-4c86-a272-d90114b71f4e\",\"EventDtm\": \"2018-04-05T08:55:00Z\",\"AppDtm\": \"2018-04-05T08:55:01Z\"},\"DeviceData\":{\"DeviceID\": \"5149013286639000\",\"GPSLockState\": \"LOCKED\",\"GPSSatelliteCount\":10,\"GPSLastLock\":0,\"GPSLatitude\":40.529514,\"GPSLongitude\":-74.651465,\"GeofenceCode\":null,\"MCC\": \"310\",\"MNC\": \"410\",\"LAC\": \"3987\",\"CID\": \"164012981\",\"RSSI\": \"-77\",\"ExtPower\":true,\"ExtPowerVoltage\":31.8,\"BatteryVoltage\":3.3,\"DeviceTemp\":44,\"RTDLOn\":true},\"ReeferData\":{\"AssetType\": \"Reefer\",\"AssetID\": \"CMCU7781000\",\"OEM\": \"STARCOOL\",\"TAmb\":24,\"TAmbQ\": \"asProvided\",\"TUSDA4\":3276.4,\"TUSDA4Q\": \"openSensor\",\"PctCO2\":25.5,\"PctCO2Q\":null,\"PctCO2Set\":25.5,\"PctCO2SetQ\": \"error\",\"PSuc\":3276.6,\"PSucQ\": \"unavailable\",\"TDis\":3276.6,\"TDisQ\": \"unavailable\",\"FreqComp\":83,\"TSuc\":3276.6,\"TSucQ\": \"unavailable\",\"MCond\":null,\"PCond\":3276.6,\"PCondQ\": \"unavailable\",\"TCond\":46,\"TCondQ\": \"asProvided\",\"MCtrl\": \"Cool\",\"SnCtrl\": \"E001019391\",\"AmpPhA\":8.9,\"AmpPhB\":8.2,\"AmpPhC\":9.1,\"PDis\":3276.6,\"PDisQ\": \"unavailable\",\"PctEconVlv\":255,\"PctEconVlvQ\": \"notUsed\",\"PctExpVlv\":255,\"PctExpVlvQ\": \"notUsed\",\"TEvap\":35.5,\"TEvapQ\": \"asProvided\",\"MCtrl3\": \"Chilled\",\"PctHtr\":255,\"PctHtrQ\": \"notUsed\",\"MEvapFanHS\":null,\"PctGasVlv\":255,\"PctGasVlvQ\": \"notUsed\",\"PctHum\":255,\"PctHumQ\": \"notUsed\",\"PctHumSet\":255,\"PctHumSetQ\": \"unknown\",\"FreqLine\":83,\"FreqLineQ\": \"OOR\",\"VLine1\":1020,\"VLine2\":null,\"VLine3\":null,\"MEvapFanLS\":null,\"PctO2\":25.5,\"PctO2Q\": \"OOR\",\"PctO2Set\":25.5,\"PctO2SetQ\": \"error\",\"MCtrl2\":null,\"TRtn1\":34.9,\"TRtn1Q\": \"asProvided\",\"TRtn2\":3276.6,\"TRtn2Q\": \"unavailable\",\"TSet\":27,\"TSetQ\":null,\"VerSWMajor\": \"12060800\",\"VerSWMinor\":null,\"PctSucVlv\":255,\"PctSucVlvQ\": \"notUsed\",\"TSup1\":34.6,\"TSup1Q\": \"asProvided\",\"TSup2\":33.7,\"TSup2Q\": \"asProvided\",\"AmpTotalDraw\":8.7,\"AmpTotalDrawQ\":null,\"TUSDA1\":3276.4,\"TUSDA1Q\": \"openSensor\",\"TUSDA2\":3276.4,\"TUSDA2Q\": \"openSensor\",\"TUSDA3\":3276.4,\"TUSDA3Q\": \"openSensor\",\"CmhVent\":1275,\"CmhVentQ\": \"unknown\",\"ReeferAlarms\":null}}}";
		for (long ll = 0; ll <= numMsgs; ll++) {
			session.getBasicRemote().sendText(response1 + ll + response2);			
			System.out.println("sent " + ll);
			Thread.sleep(delay);
			
		}	
	}
	
	
	  public void sendMsgsResponse(Session session, String response) throws
	  IOException, InterruptedException {
	  session.getBasicRemote().sendText("reply : " + response);
	  System.out.println("reply : " + response); }
	 

	@OnMessage
	public void onMessage(String message, Session session) throws IOException,
			InterruptedException {		
		
		System.out.println("Received: " + message);
		System.out.println("----session.getId-------- : " + session.getId());
		
		// sendsmall
		// sendsmallclose
		// errorconnect
		// sendlargewaitsendlarge
		// sendlarge
		// sendsmallerror 
		// sendlargedelaysendlargenodelay
		
		if(message.contains("Hello")) { sendMsgsResponse(session,"Hello Friend! How are you ?");  }
		if(message.contains("fine")) { sendMsgsResponse(session,"Great ! I am good here. Please tell me what  can I do for you ?");  }
		if(message.contains("close")) { sendMsgsResponse(session,"I have one request to close my account for now. Please ping me in 10 seconds.");  }
		if(message.contains("update")) { sendMsgsResponse(session,"Yes We have closed your account and please find SR No : SR128"  );  }
		if(message.contains("Thanks")) { sendMsgsResponse(session,"Welcome ! Hoping to see you again!"  );  }
		/*
		 * if (message.contains("sendsmall")) { sendMsgs(session, 10,100); } else if
		 * (message.contains("sendsmallclose")) { sendMsgs(session, 10,100);
		 * //session.close(new CloseReason(CloseCodes.CLOSED_ABNORMALLY,
		 * "abnormal close")); } else if (message.contains("errorconnect")) {
		 * session.close(); } else if (message.contains("sendlargewaitsendlarge")) {
		 * sendMsgs(session, 1000,100); Thread.sleep(5000); sendMsgs(session, 1000,100);
		 * } else if (message.contains("sendlarge")) { sendMsgs(session, 1000,100); }
		 * else if (message.contains("sendsmallerror")) { sendMsgs(session, 10,100);
		 * session.close(new CloseReason(CloseCodes.CLOSED_ABNORMALLY,
		 * "abnormal close")); } else if
		 * (message.contains("sendlargedelaysendlargenodelay")) { sendMsgs(session,
		 * 1000,3000); Thread.sleep(5000); sendMsgs(session, 1000,100); }
		 */
		
	}
	public boolean isNumeric(String s) {  
	    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	} 
	@OnOpen
	public void onOpen(Session session) {
        PushTimeService.initialize();
        PushTimeService.add(session);
		System.out.println("Client connected : " + session.getId());
	}

	@OnClose
	public void onClose() {
		System.out.println("Connection closed");
	}
}
