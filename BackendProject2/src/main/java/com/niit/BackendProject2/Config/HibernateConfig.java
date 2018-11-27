package com.niit.BackendProject2.Config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.BackendProject2.dto.ApplyJob;
import com.niit.BackendProject2.dto.Blog;
import com.niit.BackendProject2.dto.BlogComment;
import com.niit.BackendProject2.dto.Chat;
import com.niit.BackendProject2.dto.Forum;
import com.niit.BackendProject2.dto.ForumComment;
import com.niit.BackendProject2.dto.Friend;
import com.niit.BackendProject2.dto.Job;
import com.niit.BackendProject2.dto.ProfilePicture;
import com.niit.BackendProject2.dto.User;

@Configuration

@EnableTransactionManagement
@ComponentScan("com.niit")

public class HibernateConfig {
	
	@Bean(name="dataSource")
	public DataSource getDatasource () {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
	    dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("project2");
		dataSource.setPassword("project2");
		return dataSource;
		
		
	}
	
	@Bean(name="sessionFactory")
	public SessionFactory sessionFactory() {
		Properties hibernateProperties=new Properties();
			hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
		    hibernateProperties.put("hibernate.dialect","org.hibernate.dialect.OracleDialect");
		    hibernateProperties.put("hibernate.show_sql", "true");
		    
		    LocalSessionFactoryBuilder sessionFactoryBuilder=new LocalSessionFactoryBuilder(getDatasource());
		     sessionFactoryBuilder.addProperties(hibernateProperties);
		     sessionFactoryBuilder.addAnnotatedClass(User.class);
		     sessionFactoryBuilder.addAnnotatedClass(Blog.class);
		     sessionFactoryBuilder.addAnnotatedClass(BlogComment.class);
		     sessionFactoryBuilder.addAnnotatedClass(Forum.class);
		     sessionFactoryBuilder.addAnnotatedClass(ForumComment.class);
		     sessionFactoryBuilder.addAnnotatedClass(Job.class);
		     sessionFactoryBuilder.addAnnotatedClass(ApplyJob.class);
		     sessionFactoryBuilder.addAnnotatedClass(ProfilePicture.class);
		     sessionFactoryBuilder.addAnnotatedClass(Friend.class);
		     sessionFactoryBuilder.addAnnotatedClass(Chat.class);
		    SessionFactory sessionFactory=sessionFactoryBuilder.buildSessionFactory();
		    System.out.println("----Session Factory Object----");
		    
		return sessionFactory;
	
	}
	
	@Bean
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sesionFactory) {
		System.out.println("----Creating Transaction Manager----");
		return new HibernateTransactionManager(sesionFactory);
	}
	}

	