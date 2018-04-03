/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdatabasethrift;


import com.duong.domain.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 
 * @author hoang
 *
 */

public class SessionProvider {

	private static Session session;
	private static Configuration configuration;
	
	private SessionProvider() { }
	
	static {
		
		configuration = new Configuration()
	    
		.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
	    .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
	    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mysqljdbc")
	    .setProperty("hibernate.connection.username", "hibernate")
	    .setProperty("hibernate.connection.password", "Hibernate1@#")
	    .setProperty("hibernate.show_sql", "true")
	    .setProperty("hibernate.hbm2ddl.auto", "update")
	    .addAnnotatedClass(Skill.class);
	}
	
	
	public static Session getSession() {
	
		SessionFactory factory = configuration.buildSessionFactory();
		session = factory.openSession();
		return session;
	}
	
}