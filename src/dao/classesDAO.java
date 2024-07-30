package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojo.classes;

/**
 * <h1> classesDAO </h1>
 * The classesDAO is to process inserting, updating, deleting and selecting classes table from database
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class classesDAO {
	private Session mySession=null;
	private SessionFactory sessionFactory=null;
	
	/** Constructor
	 *  @param sessionFactory getSession factory
	 */
	public classesDAO()
	{
		sessionFactory=HibernateUtils.getSessionFactory();
	}
	
	 /**
	   * Method to insert class details to classes table in database
	   * 
	   * @param mySession to process saving class
	   * @param temp object to save
	   * @see pojo.classes
	   */
	public void insertClass(classes temp)
	{
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		mySession.save(temp);
		mySession.getTransaction().commit();
	}
	
	/**
	   * Method to update class details to classes table in database
	   * 
	   * @param mySession to process updating class
	   * @param temp object to update
	   * @see pojo.classes
	   */
	public void updateClass(classes temp)
	{
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		classes temp1 = new classes();
		mySession.load(temp1,temp.getClass_id());
		temp1.setCourses(temp.getCourses());
		temp1.setStatus(temp.getStatus());
		temp1.setStart_date(temp.getStart_date());
		temp1.setEnd_date(temp.getEnd_date());
		
		mySession.save(mySession.merge(temp1));
		mySession.getTransaction().commit();
	}
	
	/**
	   * Method to delete class details to classes table in database
	   * 
	   * @param mySession to process saving class
	   * @param temp object to save
	   * @see pojo.classes
	   */
	public void deleteClass(classes temp) 
	{
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		mySession.delete(temp);
		mySession.getTransaction().commit();
		
	}
	
	/**
	   * Method to get class details from classes table in database
	   * 
	   * @param mySession to process executing query
	   * @param query query to execute
	   * @return Result list from executed query
	   * @see pojo.classes
	   * @see Session
	   * @see List
	   */
	@SuppressWarnings("unchecked")
	public List<classes> getAllClasses(String query) 
	{
		List<classes> testList=null;
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		Query q=mySession.createQuery(query);
		testList=q.list();
		mySession.getTransaction().commit();
		return testList;
	}
}