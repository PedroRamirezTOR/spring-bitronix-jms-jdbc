package com.example.demo.jms;

import java.sql.Timestamp;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.TrainDao;
import com.example.demo.dao.VersionsConfDao;
import com.example.demo.domain.Train;
import com.example.demo.domain.VersionsConf;

@Component
@Transactional
public class ContactTransactionReceiver {


	private int mensajesConsumer2 = 0;

	@Autowired
	@Qualifier("versionJdbcTemplate")
	private JdbcTemplate versionJdbcTemplate;
	
	@Autowired
	@Qualifier("tpJdbcTemplate")
	private JdbcTemplate tpjdbcTemplate;

	@Autowired
	private VersionsConfDao versionsConfDao;
	
	@Autowired
	private TrainDao trainDao;
	

	@Transactional(rollbackFor=Exception.class)
	@JmsListener(destination = "Consumer.consumer2.VirtualTopic.TopicPrueba")
	public void receiveMessageFromContacts2(Message message) throws Exception {
		mensajesConsumer2++;
		TextMessage txtMessage = (TextMessage) message;
		System.out.println("Segundo consumer:" + txtMessage.getText() + " recibidos:" + mensajesConsumer2);
		
		
		VersionsConf versionsconf = new VersionsConf("V" + mensajesConsumer2, "V" + mensajesConsumer2, false,new Timestamp(1L), 1);
		VersionsConf versionsResult = versionsConfDao.insertUpdate(versionJdbcTemplate, versionsconf);
		
		if (mensajesConsumer2 == 2) {
			throw new Exception();
		}
		
		Train train = new Train("101"+mensajesConsumer2, 1L, 2L, false, true, "atp");
	    Train trainResult = trainDao.insertUpdate(tpjdbcTemplate, train);
		
		if (mensajesConsumer2 == 3) {
			throw new Exception();
		}
	}

}