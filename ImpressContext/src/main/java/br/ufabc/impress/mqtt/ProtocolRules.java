package br.ufabc.impress.mqtt;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.poi.ss.formula.functions.Count;

import br.ufabc.impress.Main;
import br.ufabc.impress.Param;
import br.ufabc.impress.esper.Esper;
import br.ufabc.impress.facade.EvalSdpFacade;
import br.ufabc.impress.facade.ResourceFacade;
import br.ufabc.impress.facade.ResourceLogFacade;
import br.ufabc.impress.model.EvalSdp;
import br.ufabc.impress.model.Resource;
import br.ufabc.impress.model.ResourceLog;
import br.ufabc.impress.util.NumberUtil;

/*
 * 
 * Describe the protocol and converting in code programming
 * */
public class ProtocolRules {

	public static final String del = ";";

	private ResourceFacade resourceFacade;
	private ResourceLogFacade resourceLogFacade;

	public ProtocolRules(String message) {
		this.message = message;
	}

	private String message;

	public boolean applyProtocolsRules() {

		if (message.equalsIgnoreCase("START")) {
			
			if (Param.is_eval) {
				Main.startTime = System.currentTimeMillis();
			
				// eval time
				EvalSdp eval = new EvalSdp();
				eval.setStart(new Timestamp(Main.startTime));
				eval.setName(Param.name_experiment);
				eval.setModule("START TIME");
				eval.setDescription("START TIME");
				new EvalSdpFacade().create(eval);
				//System.out.println(Param.module_rule_status);
			}

		} else if (message.equalsIgnoreCase("FINISH")) {
			
			if (Param.is_eval) {
				
				Main.finishTime = System.currentTimeMillis();
			
				// eval time
				EvalSdp eval = new EvalSdp();
				eval.setStart(new Timestamp(Main.startTime));
				eval.setFinish(new Timestamp(Main.finishTime));
				eval.setDuration(new Timestamp(Main.finishTime-Main.startTime));
				eval.setDurationMil(Main.finishTime-Main.startTime);
				eval.setName(Param.name_experiment);
				eval.setModule("FINISH TIME");
				eval.setDescription("FINISH TIME");
				new EvalSdpFacade().create(eval);
				//System.out.println(Param.module_rule_status);
				
				System.exit(0);
			}
			
			

		} else {

			String m[] = message.split(del);

			NumberUtil nu = new NumberUtil();

			String r = null;
			// if m[0] is a number
			if (nu.isNumeric(m[0])) {
				// change status of demo
				if (m[0].trim().equalsIgnoreCase("4")) {

					ResourceLog lr = new ResourceLog();
					lr.setCreationDate(new Timestamp(new Date().getTime()));

					if (m[1].trim().equalsIgnoreCase("0")) {

						// lr.setLogResourceValue("0");
						lr.setResourceLogValue("0");

					} else if (m[1].trim().equalsIgnoreCase("1")) {
						// ON
						// r = "ON";
						// lr.setLogResourceValue("1");
						lr.setResourceLogValue("1");

					}

					else if (m[1].trim().equalsIgnoreCase("2")) {
						// AUTO
						// lr.setLogResourceValue("2");
						lr.setResourceLogValue("2");
					} else {
						// ERROR
					}

					// Resource res = new Resource();
					// res.setId(Param.sensor_android);
					Resource res = getResourceFacade().find(
							Param.sensor_android);
					lr.setResource(res);

					this.getLogResourceFacade().create(lr);

					Esper.addEventEsper(lr);
				}
				// send message to mobile
				else if (m[0].trim().equalsIgnoreCase("3")) {

				}
				// actuator
				else if (m[0].trim().equalsIgnoreCase("2")) {

				}
				// sensor
				else if (m[0].trim().equalsIgnoreCase("1")) {
					String idResource = m[1];
					String value = m[2];

					ResourceLog lr = new ResourceLog();
					// lr.setLogResourceValue(value);
					lr.setResourceLogValue(value);
					lr.setCreationDate(new Timestamp(new Date().getTime()));

					// Resource res = new Resource();
					// res.setId(Integer.parseInt(idResource));

					// LogResourceFacade logResourceFacade = new
					// LogResourceFacade();
					// logResourceFacade.create(lr);

					Resource res = getResourceFacade().find(
							Integer.parseInt(idResource));
					lr.setResource(res);

					this.getLogResourceFacade().create(lr);

					Esper.addEventEsper(lr);
					System.out.println("FUSION CREATE --> " + m[1] + " --> "
							+ m[2]);

				}

				else {
					// This option is not valid
				}
			}
		}

		return true;
	}

	public void sendMessage(String msg) {
		try {
			// MqttPublish pub = new MqttPublish(Param.uri, Param.port,
			// Param.clientId+11, "impress/action", "1");
			MqttPublish pub = new MqttPublish(Param.address, "impre" + 11,
					Param.topic, msg);
			// pub.publish(Param.uri, Param.port, Param.clientId+11,
			// "impress/temperature", "1");
			Thread thread = new Thread(pub);
			thread.start();
		} catch (Throwable th) {
			new Throwable("Drools Error Request Repository", th);
		}
	}

	private ResourceFacade getResourceFacade() {
		if (resourceFacade == null) {
			resourceFacade = new ResourceFacade();
		}
		return resourceFacade;
	}

	private ResourceLogFacade getLogResourceFacade() {
		if (resourceLogFacade == null) {
			resourceLogFacade = new ResourceLogFacade();
		}
		return resourceLogFacade;
	}

}
