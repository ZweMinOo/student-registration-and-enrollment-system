package dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojo.students;

/**
 * <h1> studentsDAO </h1>
 * The studentsDAO is to process inserting, updating, deleting and selecting students table from database
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class studentsDAO {
	private Session mySession=null;
	private SessionFactory sessionFactory=null;
	/** Constructor
	 *  @param sessionFactory getSession factory
	 */
	public studentsDAO()
	{
		sessionFactory=HibernateUtils.getSessionFactory();
	}
	/**
	   * Method to insert student details to students table in database
	   * 
	   * @param mySession to process saving students
	   * @param temp object to save
	   * @see pojo.students
	   */
	public void insertStudent(students temp)
	{
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		mySession.save(temp);
		mySession.getTransaction().commit();
	}
	/**
	   * Method to update student details to students table in database
	   * 
	   * @param mySession to process updating students
	   * @param temp object to update
	   * @see pojo.students
	   */
	public void updateStudent(students temp)
	{	
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		students temp1 = new students();
		mySession.load(temp1,temp.getStudent_id());
		temp1.setName(temp.getName());
		temp1.setDate_of_birth(temp.getDate_of_birth());
		temp1.setNrc(temp.getNrc());
		temp1.setPhone(temp.getPhone());
		temp1.setAddress(temp.getAddress());
		temp1.setStudents_classes(temp.getStudents_classes());
		
		mySession.save(temp1);
		mySession.getTransaction().commit();

	}
	/**
	   * Method to delete student details to students table in database
	   * 
	   * @param mySession to process saving students
	   * @param temp object to save
	   * @see pojo.students
	   */
	public void deleteStudent(students temp) 
	{
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		mySession.delete(temp);
		mySession.getTransaction().commit();
		
	}
	/**
	   * Method to get student details from students table in database
	   * 
	   * @param mySession to process executing query
	   * @param query query to execute
	   * @return Result list from executed query
	   * @see pojo.students
	   * @see Session
	   * @see List
	   */
	@SuppressWarnings("unchecked")
	public List<students> getAllStudents(String query) 
	{
		List<students> testList=null;
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		Query q=mySession.createQuery(query);
		testList=q.list();
		mySession.getTransaction().commit();
		return testList;
	}
}