package br.ufabc.impress.mqtt;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttSubscribe implements MqttCallback, Runnable {

	private String uri;
	private String clientId;
	private String topic;
	
	private MqttClient client;
	private MqttConnectOptions options;

	public MqttSubscribe(String uri, String clientId, String topic) {
		this.clientId = clientId;
		this.uri = uri;
		this.topic = topic;
	}

	@Override
	public void run() {

		this.subscribe(uri, clientId, topic);
	}

	private void subscribe(String uri, String clientId, String topic) {

		System.out.println("Subscribing topic " + topic);

		try {

			Random random = new Random();
			String id = clientId+random.nextInt(2000);
			client = new MqttClient(uri, id);
			client.setCallback(this);

			options = new MqttConnectOptions();
			options.setUserName("admin");
			options.setPassword("password".toCharArray());

			client.connect(options);

			// client.subscribe("impress/temperature");
			client.subscribe(topic);

			try {
				System.in.read();

			} catch (IOException e) {
				// If we can't read we'll just exit
				System.out.println("Error subscribing ....");
				new Throwable("Error subscribing receive message", e);

			}

			client.disconnect();

		} catch (MqttException e) {
			// e.printStackTrace();
			System.out.println("Error subscribing MQTT" + e.getMessage() + " "
					+ e.getLocalizedMessage());
			new Throwable("Error subscribing MQTT", e);
		}

	}

	public void connectionLost(Throwable msg) {
		System.out.println("Connection Lost " + clientId + " "+ msg.getStackTrace().toString());
		//new Throwable("Connection Lost", msg);
		this.subscribe(uri, clientId, topic);
	}

	public void deliveryComplete(IMqttDeliveryToken arg0) {

	}

	public void messageArrived(String topic, MqttMessage message)
			throws Exception {
	
		ProtocolRules pr = new ProtocolRules(new String(message.getPayload()));
		pr.applyProtocolsRules();

	}

}
