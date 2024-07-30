package dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojo.students_classes;
/**
 * <h1> students_classesDAO </h1>
 * The students_classesDAO is to process inserting, updating, deleting and selecting students_classes table from database
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class students_classesDAO {
	private Session mySession=null;
	private SessionFactory sessionFactory=null;
	
	/** Constructor
	 *  @param sessionFactory getSession factory
	 */
	public students_classesDAO()
	{
		sessionFactory=HibernateUtils.getSessionFactory();
	}
	/**
	   * Method to insert students_classes details to students_classes table in database
	   * 
	   * @param mySession to process saving students_classes
	   * @param temp object to save
	   * @see pojo.students_classes
	   */
	public void insertStudents_Classes(students_classes temp)
	{
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		mySession.save(temp);
		mySession.getTransaction().commit();
	}
	/**
	   * Method to update students_classes details to students_classes table in database
	   * 
	   * @param mySession to process updating students_classes
	   * @param temp object to update
	   * @see pojo.students_classes
	   */
	public void updateStudents_Classes(students_classes temp)
	{
		
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		students_classes temp1 = new students_classes();
		mySession.load(temp1,temp.getStudents_classes_id());
		temp1.setClasses(temp.getClasses());
		temp1.setStudents(temp.getStudents());
		temp1.setReg_date(temp.getReg_date());
		
		mySession.save(temp1);
		mySession.getTransaction().commit();

	}
	/**
	   * Method to delete students_classes details to students_classes table in database
	   * 
	   * @param mySession to process saving students_classes
	   * @param temp object to save
	   * @see pojo.students_classes
	   */
	public void deleteStudents_Classes(students_classes temp) 
	{
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		mySession.delete(temp);
		mySession.getTransaction().commit();
		
	}
	/**
	   * Method to get students_classes details from students_classes table in database
	   * 
	   * @param mySession to process executing query
	   * @param query query to execute
	   * @return Result list from executed query
	   * @see pojo.students_classes
	   * @see Session
	   * @see List
	   */
	@SuppressWarnings("unchecked")
	public List<students_classes> getAllStudents_Classes(String query) 
	{
		List<students_classes> testList=null;
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		Query q=mySession.createQuery(query);
		testList=q.list();
		mySession.getTransaction().commit();
		return testList;
	}
}
