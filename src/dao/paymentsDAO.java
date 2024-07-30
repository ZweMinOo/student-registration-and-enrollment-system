package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojo.classes;

import pojo.payments;

/**
 * <h1> paymentsDAO </h1>
 * The paymentsDAO is to process inserting, updating, deleting and selecting payments table from database
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class paymentsDAO {
	private Session mySession=null;
	private SessionFactory sessionFactory=null;
	
	/** Constructor
	 *  @param sessionFactory getSession factory
	 */
	public paymentsDAO()
	{
		sessionFactory=HibernateUtils.getSessionFactory();
	}
	/**
	   * Method to insert payment details to payments table in database
	   * 
	   * @param mySession to process saving payments
	   * @param temp object to save
	   * @see pojo.payments
	   */
	public void insertPayment(payments temp)
	{
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		mySession.save(temp);
		mySession.getTransaction().commit();
	}
	/**
	   * Method to update payment details to payments table in database
	   * 
	   * @param mySession to process updating payments
	   * @param temp object to update
	   * @see pojo.payments
	   */
	public void updatePayment(payments temp)
	{		
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		payments temp1 = new payments();
		mySession.load(temp1,temp.getPayment_id());
		temp1.setClasses(temp.getClasses());
		temp1.setStudents(temp.getStudents());
		temp1.setPaid_amount(temp.getPaid_amount());
		temp1.setRemaining_amount(temp.getRemaining_amount());
		temp1.setPayment_date(temp.getPayment_date());
		
		mySession.save(temp1);
		mySession.getTransaction().commit();

	}
	/**
	   * Method to delete payment details to payments table in database
	   * 
	   * @param mySession to process saving payments
	   * @param temp object to save
	   * @see pojo.payments
	   */
	public void deletePayment(payments temp) 
	{
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		mySession.delete(temp);
		mySession.getTransaction().commit();
		
	}
	/**
	   * Method to get payment details from payments table in database
	   * 
	   * @param mySession to process executing query
	   * @param query query to execute
	   * @return Result list from executed query
	   * @see pojo.payments
	   * @see Session
	   * @see List
	   */
	@SuppressWarnings("unchecked")
	public List<payments> getAllPayments(String query) 
	{
		List<payments> testList=null;
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		Query q=mySession.createQuery(query);
		testList=q.list();
		mySession.getTransaction().commit();
		return testList;
	}
}
