package dao;

import org.hibernate.cfg.*;
import org.hibernate.*;
/**
 * <h1> HibernateUtils </h1>
 * To process inserting, updating, deleting and selecting classes table from database
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class HibernateUtils {
	private static Configuration myConfig=null;
	private static SessionFactory sessionFactory=null;
	private static String xmlFile = "hibernate.cfg.xml";
	
	/**
	   * Method to configure XML
	   * 
	   * @param myConfig configure object
	   * @param xmlFile file path and name of XML file
	   */
	public static Configuration configure()
	{
		if(myConfig==null){
			myConfig=new Configuration();
			myConfig.configure(xmlFile);
		}
		return myConfig;
	}
	/**
	   * Method to build SessionFactory
	   * 
	   * @param sessoinFactory session object
	   * @param xmlFile file path and name of XML file
	   */
	public static SessionFactory getSessionFactory(){
		
		if(sessionFactory==null)
		{ 
			sessionFactory=new AnnotationConfiguration().configure(xmlFile).buildSessionFactory();
		}
		return sessionFactory;
	}
	
	/**
	   * Method to shutdown session
	   * 
	   * @param sessionFactory session object
	   */
	public static void shutdown(){
		sessionFactory.close();
	}
}	