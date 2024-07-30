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
 * <h1> students_classes </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
@Entity
public class students_classes {
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	private int students_classes_id;
	@ManyToOne
	@JoinColumn(name="student_id", nullable=false)
	private students students;
	@ManyToOne
	@JoinColumn(name="class_id", nullable=false)
	private classes classes;
	@Column
	private java.util.Date reg_date;
	/**
	   * Constructor
	   * 
	   */
	public students_classes() {}
	/**
	   * Constructor
	   * 
	   * @param students parameter to receive the students object related with classes object
	   * @param classes parameter to receive the classes object related with students object
	   * @param reg_date parameter to receive the date of registration of studetns_classes object
	   */
	public students_classes(pojo.students students, pojo.classes classes,
			Date reg_date) {
		super();
		this.students = students;
		this.classes = classes;
		this.reg_date = reg_date;
	}

	/**
	   * Method to get id of students_classes object
	   * 
	   * @return students_classes_id parameter to return the value of students_classes_id of students_classes object
	   */
	public int getStudents_classes_id() {
		return students_classes_id;
	}
	/**
	   * Method to set value of students_classes_id of students_classes object
	   * 
	   * @param students_classes_id parameter to receive the value of students_classes_id of student_classes object
	   */
	public void setStudents_classes_id(int students_classes_id) {
		this.students_classes_id = students_classes_id;
	}
	/**
	   * Method to get students object of students_classes object
	   * 
	   * @return students parameter to return the students object related with classes object
	   */
	public students getStudents() {
		return students;
	}
	/**
	   * Method to set students object of students_classes object
	   * 
	   * @param students parameter to receive the students object related with classes object
	   */
	public void setStudents(students students) {
		this.students = students;
	}
	/**
	   * Method to get the classes object related with students object
	   * 
	   * @return classes parameter to return the classes object related with students object
	   */
	public classes getClasses() {
		return classes;
	}
	/**
	   * Method to set classes object of students_classes object
	   * 
	   * @param classes parameter to receive the classes object related with students object
	   */
	public void setClasses(classes classes) {
		this.classes = classes;
	}
	/**
	   * Method to get registration date of students_classes object
	   * 
	   * @return reg_date parameter to return the registration date of students_classes object
	   */
	public java.util.Date getReg_date() {
		return reg_date;
	}
	/**
	   * Method to set registration date of students_classes object
	   * 
	   * @param reg_date parameter to receive the registration date of students_classes object
	   */
	public void setReg_date(java.util.Date reg_date) {
		this.reg_date = reg_date;
	}
}