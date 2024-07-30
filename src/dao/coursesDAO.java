package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojo.courses;

/**
 * <h1> CoursesDAO </h1>
 * To process inserting, updating, deleting and selecting courses table from database
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class coursesDAO {
	private Session mySession=null;
	private SessionFactory sessionFactory=null;
	
	/** Constructor
	 *  @param sessionFactory getSession factory
	 */
	public coursesDAO()
	{
		sessionFactory=HibernateUtils.getSessionFactory();
	}
	/**
	   * Method to insert courses details to courses table in database
	   * 
	   * @param mySession to process saving courses
	   * @param temp object to save
	   * @see pojo.courses
	   */
	public void insertCourse(courses temp)
	{
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		mySession.save(temp);
		mySession.getTransaction().commit();
	}
	
	/**
	   * Method to update courses details to courses table in database
	   * 
	   * @param mySession to process updating courses
	   * @param temp object to update
	   * @see pojo.courses
	   */
	public void updateCourse(courses temp)
	{	
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		courses temp1 = new courses();
		mySession.load(temp1,temp.getCourse_id());
		temp1.setClasses(temp.getClasses());
		temp1.setTitle(temp.getTitle());
		temp1.setType(temp.getType());
		temp1.setFee(temp.getFee());
		temp1.setDuration(temp.getDuration());
		
		mySession.save(temp1);
		mySession.getTransaction().commit();

	}
	/**
	   * Method to delete courses details to courses table in database
	   * 
	   * @param mySession to process saving courses
	   * @param temp object to save
	   * @see pojo.courses
	   */
	public void deleteCourse(courses temp) 
	{
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		mySession.delete(temp);
		mySession.getTransaction().commit();
		
	}
	/**
	   * Method to get courses details from courses table in database
	   * 
	   * @param mySession to process executing query
	   * @param query query to execute
	   * @return Result list from executed query
	   * @see pojo.courses
	   * @see Session
	   * @see List
	   */
	@SuppressWarnings("unchecked")
	public List<courses> getAllCourses(String query) 
	{
		List<courses> testList=null;
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		Query q=mySession.createQuery(query);
		testList=q.list();
		mySession.getTransaction().commit();
		return testList;
	}
}