package com.example.demo.rest;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.TrainDao;
import com.example.demo.dao.VersionsConfDao;
import com.example.demo.domain.Train;
import com.example.demo.domain.VersionsConf;

@RestController
@Transactional
public class RestApiController {

//	@Autowired
//	private JmsTemplate jmsTemplate;

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

//	@Autowired
//	@Qualifier("jmsTemplateRemote")
//	private JmsTemplate jmsTemplateRemote;

	@RequestMapping(value = "/produce")
	@Transactional(rollbackFor=Exception.class)
	public String produce() throws Exception {
		mensajesConsumer2++;
		VersionsConf versionsconf = new VersionsConf("V" + mensajesConsumer2, "V" + mensajesConsumer2, false,
				new Timestamp(1L), 1);
		VersionsConf versionsResult = versionsConfDao.insertUpdate(versionJdbcTemplate, versionsconf);
		if (mensajesConsumer2 == 2)
			throw new Exception();
		Train train = new Train("101" + mensajesConsumer2, 1L, 2L, false, true, "atp");
		Train trainResult = trainDao.insertUpdate(tpjdbcTemplate, train);
		if (mensajesConsumer2 == 3)
			throw new Exception();
		return "OK";
	}
}