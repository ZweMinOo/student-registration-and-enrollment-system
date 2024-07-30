package pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
/**
 * <h1> payments </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
@Entity
public class payments {
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	private int payment_id;
	@ManyToOne
	@JoinColumn(name="student_id", nullable=false)
	private students students;
	@ManyToOne
	@JoinColumn(name="class_id", nullable=false)
	private classes classes;
	@Column
	private double paid_amount;
	@Column
	private double remaining_amount;
	@Column
	private java.util.Date payment_date;
	/**
	   * Constructor
	   * 
	   */
	public payments() {}
	/**
	   * Constructor
	   * 
	   * @param students parameter to receive the student related with classes
	   * @param classes parameter to received the classes related with students
	   * @param paid_amount parameter to received the paid amount of payments object
	   * @param remaining_amount parameter to received the remaining amount of payments object
	   * @param payment_date parameter to received the payment date of payments object
	   */
	public payments(pojo.students students, pojo.classes classes,
			double paid_amount, double remaining_amount, Date payment_date) {
		super();
		this.students = students;
		this.classes = classes;
		this.paid_amount = paid_amount;
		this.remaining_amount = remaining_amount;
		this.payment_date = payment_date;
	}

	/**
	   * Method to get the payment id of payments object
	   * 
	   * @param payment_id parameter to return the value of payment id of payments object
	   */
	public int getPayment_id() {
		return payment_id;
	}
	/**
	 * Method to set the value of payment id of payments object
	 *
	 * @param payment_id parameter to receive the value of payment id of payments object
	 */

	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	/**
	   * Method to get the students of payments object
	   * 
	   * @return students parameter to return the students object of payments object
	   */
	public students getStudents() {
		return students;
	}
	/**
	 * Method to set the object of students of payments object
	 *
	 * @param students parameter to receive the students object of payments object
	 */
	public void setStudents(students students) {
		this.students = students;
	}
	/**
	   * Method to get the classes of payments object
	   * 
	   * @return classes parameter to return the classes object of payments object
	   */
	public classes getClasses() {
		return classes;
	}
	/**
	 * Method to set the object of classes of payments object
	 *
	 * @param classes parameter to receive the classes object of payments object
	 */
	public void setClasses(classes classes) {
		this.classes = classes;
	}
	/**
	   * Method to get the paid amount of payments object
	   * 
	   * @return paid_amount parameter to return the value of paid amount of payments object
	   */
	public double getPaid_amount() {
		return paid_amount;
	}
	/**
	 * Method to set the value of paid amount of payments object
	 *
	 * @param paid_amount parameter to receive the value of paid amount of payments object
	 */
	public void setPaid_amount(double paid_amount) {
		this.paid_amount = paid_amount;
	}
	/**
	   * Method to get the remaining amount of payments object
	   * 
	   * @return remaining_amount parameter to return the value of remaining amount of payments object
	   */
	public double getRemaining_amount() {
		return remaining_amount;
	}
	/**
	 * Method to set the value of remaining amount of payments object
	 *
	 * @param remaining_amount parameter to receive the value of remaining_amount of payments object
	 */
	public void setRemaining_amount(double remaining_amount) {
		this.remaining_amount = remaining_amount;
	}
	/**
	   * Method to get the payment date of payments object
	   * 
	   * @return payment_date parameter to return the value of payment date of payments object
	   */
	public java.util.Date getPayment_date() {
		return payment_date;
	}
	/**
	 * Method to set the value of payment date of payments object
	 *
	 * @param payment_date parameter to receive the value of payment_date of payments object
	 */
	public void setPayment_date(java.util.Date payment_date) {
		this.payment_date = payment_date;
	}	
}