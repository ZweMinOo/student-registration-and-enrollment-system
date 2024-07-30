package pojo;

//import HibernateUtils;
import dao.HibernateUtils;

import org.hibernate.Session;
/**
 * <h1> createTables </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class createTables
{
	public static void main(String[]args)
	{
		Session mySession=HibernateUtils.getSessionFactory().getCurrentSession();
		mySession.beginTransaction();
		
		HibernateUtils.shutdown();
	}

}
