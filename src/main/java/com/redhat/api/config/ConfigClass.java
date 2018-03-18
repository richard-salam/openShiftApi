package com.redhat.api.config;
/*  Username: userCWJ Password: TGYMNnYI6iohkPfF Database Name: sampledb Connection URL: mysql://mysql:3306/*/

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.stereotype.Component;


import com.redhat.api.entity.EntityClass;



@Component
@ComponentScan(basePackages="com.redhat.api")
@Configuration
public class ConfigClass {

	@Autowired
	@Bean(name="dataSource")
	public DataSource getMySQLDataSource() {
		DriverManagerDataSource driverMgrDataSource=new DriverManagerDataSource();
		driverMgrDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverMgrDataSource.setUrl("jdbc:mysql://localhost:3306/"); 
		driverMgrDataSource.setSchema("sampledb");
		driverMgrDataSource.setUsername("userCWJ");
		driverMgrDataSource.setPassword("TGYMNnYI6iohkPfF");
		System.out.println("DataBase Connection Established");
		return driverMgrDataSource;
	}

	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory()
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
	
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager hibernateTranMgr=new HibernateTransactionManager(sessionFactory);
		
		System.out.println("Transaction Done");
		
		return hibernateTranMgr;
}
}
