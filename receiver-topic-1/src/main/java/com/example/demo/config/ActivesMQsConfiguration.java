package com.example.demo.config;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import bitronix.tm.resource.jms.PoolingConnectionFactory;

@Configuration
@EnableTransactionManagement
public class ActivesMQsConfiguration {

	@Bean
	public ConnectionFactory jmsConnectionFactoryLocal() {
		PoolingConnectionFactory btmPoolingConnectionFactory = new PoolingConnectionFactory();
		btmPoolingConnectionFactory.setClassName("org.apache.activemq.ActiveMQXAConnectionFactory");
		btmPoolingConnectionFactory.setUniqueName("AMQLocal");
		btmPoolingConnectionFactory.setMinPoolSize(1);
		btmPoolingConnectionFactory.setMaxPoolSize(5);
		btmPoolingConnectionFactory.setAllowLocalTransactions(true);
		btmPoolingConnectionFactory.setUser("admin");
		btmPoolingConnectionFactory.setPassword("admin");
		btmPoolingConnectionFactory.getDriverProperties().setProperty("brokerURL", "tcp://localhost:61616");
		btmPoolingConnectionFactory.init();
		return btmPoolingConnectionFactory;
	}



	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerFactoryLocal(
			@Qualifier("jmsConnectionFactoryLocal") ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(jmsConnectionFactoryLocal());
		factory.setSessionTransacted(true);
		factory.setErrorHandler(t->{
			t.printStackTrace();
		});
		configurer.configure(factory, connectionFactory);
		return factory;
	}

}