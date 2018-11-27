package com.niit.Middleware1.Configuration;

import java.nio.charset.StandardCharsets;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.niit.BackendProject2.Config.HibernateConfig;
import javax.servlet.Filter;

/*We are creating this class instead of web.xml , bcz here we dont want to 
use xml for configuration. The latest version of spring 4  supports 
annotations and java code for the configuration*/


public class HelloWorldInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	//Support for Asynchronous Service...
		@Override
		protected void customizeRegistration(javax.servlet.ServletRegistration.Dynamic registration) {
			
			/*Here we have configured the dispatcherServlet by setting its dispatchOptionsRequest 
			 * to true*/
			registration.setInitParameter("dispatchOptionsRequest", "true");
			
			/*DispatcherServlet can handle two types of Request :-
			 * 1. Synchronous :- Synchronous means u will get immediate response
			 * 2. Asynchronous :- Asynchronous means response will take some time .
			 * 
			 * Here , we are our application supports asynchronous request*/
			registration.setAsyncSupported(true);
		}
		
		/*DBConfig.class is the class which we have created in backend . Here we have all the
		 database related configuration.
		 
		 HelloWorldConfiguration is the class which we need to create in frontend ,
		 this class is created to do all the configuration of springs.xml 
		 */
		@Override
		protected Class<?>[] getRootConfigClasses() {
			return new Class[]{HelloWorldConfiguration.class,HibernateConfig.class};
		}

		@Override
		protected Class<?>[] getServletConfigClasses() {
			return new Class[]{WebSocketConfiguration.class};
		}

		@Override
		protected String[] getServletMappings() {
			System.out.println("I m  here");
			//Here we are mapping all the requests with DispatcherServlet
			return new String[]{"/"};
		}
		
		//This function will do the character encoding for messaging
		 @Override
		    protected Filter[] getServletFilters() {
			 CharacterEncodingFilter filter=new CharacterEncodingFilter();
			 filter.setEncoding(StandardCharsets.UTF_8.name());
		        return new Filter[]{filter};
		    }

	}

