package com.redhat.api.config;
/*  Username: userCWJ Password: TGYMNnYI6iohkPfF Database Name: sampledb Connection URL: mysql://mysql:3306/*/

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.BeanDefinitionParsingException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.stereotype.Component;


import com.redhat.api.entity.EntityClass;



@Component
@ComponentScan(basePackages="com.redhat.api.controller")
@Configuration
public class ConfigClass {

/*	@Autowired
	@Bean(name="dataSource")
	public DataSource getMySQLDataSource() {
		DriverManagerDataSource driverMgrDataSource=new DriverManagerDataSource();
		driverMgrDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverMgrDataSource.setUrl("jdbc:mysql://mysql:3306/sampledb"); 
		driverMgrDataSource.setUsername("userCWJ");
		driverMgrDataSource.setPassword("TGYMNnYI6iohkPfF");
		System.out.println("DataBase Connection Established");
		return driverMgrDataSource;
	}*/
	
	
	@Autowired
	@Bean(name="dataSource")
	public DataSource getMySQLDataSource() throws BeanInstantiationException, BeanDefinitionParsingException {
		
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/api");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("open");
		
		System.out.println("DataBase Connection Established");
		
		return driverManagerDataSource;
	}

	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory() throws BeanInstantiationException, BeanDefinitionParsingException
	{
		Properties hibernateProperties=new Properties();
		hibernateProperties.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.format_sql", "true");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto","update");


		
		LocalSessionFactoryBuilder localSessionFacBuilder=new LocalSessionFactoryBuilder(getMySQLDataSource());
		localSessionFacBuilder.addProperties(hibernateProperties);
		
		localSessionFacBuilder.addAnnotatedClass(EntityClass.class);

		SessionFactory sessionFactory=localSessionFacBuilder.buildSessionFactory();
		System.out.println("SessionFactory  Created");
		return sessionFactory;
	}
	
	@Autowired
	@Bean(name="transactionManager")
	
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory) throws BeanInstantiationException, BeanDefinitionParsingException
	{
		HibernateTransactionManager hibernateTranMgr=new HibernateTransactionManager(sessionFactory);
		
		System.out.println("Transaction Done");
		
		return hibernateTranMgr;
}
}
