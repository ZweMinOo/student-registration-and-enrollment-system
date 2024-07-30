package dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojo.users;

/**
 * <h1> usersDAO </h1>
 * The usersDAO is to process inserting, updating, deleting and selecting users table from database
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class usersDAO {
	private Session mySession=null;
	private SessionFactory sessionFactory=null;
	
	/** Constructor
	 *  @param sessionFactory getSession factory
	 */
	public usersDAO()
	{
		sessionFactory=HibernateUtils.getSessionFactory();
	}
	/**
	   * Method to insert user details to users table in database
	   * 
	   * @param mySession to process saving users
	   * @param temp object to save
	   * @see pojo.users
	   */
	public void insertUser(users temp)
	{
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		mySession.save(temp);
		mySession.getTransaction().commit();
	}
	/**
	   * Method to update user details to users table in database
	   * 
	   * @param mySession to process updating users
	   * @param temp object to update
	   * @see pojo.users
	   */
	public void updateUser(users temp)
	{	
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		users temp1 = new users();
		mySession.load(temp1,temp.getUser_id());
		temp1.setUsername(temp.getUsername());
		temp1.setPassword(temp.getPassword());
		
		mySession.save(temp1);
		mySession.getTransaction().commit();

	}
	/**
	   * Method to delete user details to users table in database
	   * 
	   * @param mySession to process saving users
	   * @param temp object to save
	   * @see pojo.users
	   */
	public void deleteUser(users temp) 
	{
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		mySession.delete(temp);
		mySession.getTransaction().commit();
		
	}
	/**
	   * Method to get user details from users table in database
	   * 
	   * @param mySession to process executing query
	   * @param query query to execute
	   * @return Result list from executed query
	   * @see pojo.users
	   * @see Session
	   * @see List
	   */
	@SuppressWarnings("unchecked")
	public List<users> getAllUsers(String query) 
	{
		List<users> testList=null;
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		Query q=mySession.createQuery(query);
		testList=q.list();
		mySession.getTransaction().commit();
		return testList;
	}
}
