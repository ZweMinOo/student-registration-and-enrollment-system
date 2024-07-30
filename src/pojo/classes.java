package pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
/**
 * <h1> classes </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
@Entity
public class classes {
	
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	private int class_id;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="course_id", nullable=false)
	private courses courses;
	@Column
	private String status;
	@Column
	private java.util.Date start_date;
	@Column
	private java.util.Date end_date;	
	@OneToMany(mappedBy="classes")
	private Set<students_classes> students_classes=new HashSet<students_classes>();
	
	/**
	   * Constructor
	   * 
	   */
	public classes(){}
	/**
	   * Constructor to get value of classes
	   * 
	   * @param courses receive object courses
	   * @param status class status
	   * @param start_date class start date
	   * @param end_date class end date
	   * @param students_classes students list related with classes
	   */
	public classes(pojo.courses courses, String status, Date start_date,
			Date end_date, Set<pojo.students_classes> students_classes) {
		super();
		this.courses = courses;
		this.status = status;
		this.start_date = start_date;
		this.end_date = end_date;
		this.students_classes = students_classes;
	}
	/**
	   * Method to get class_id value from classes object
	   * 
	   * @return class_id id of classes object
	   */
	public int getClass_id() {
		return class_id;
	}
	/**
	   * Method to set id to classes object
	   * 
	   * @param class_id value of class_id for classes object
	   */
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	/**
	   * Method to get course related with classes object
	   * 
	   * @return courses courses related with classes object
	   */
	public courses getCourses() {
		return courses;
	}
	/**
	   * Method to set courses to classes object
	   *
	   * @param courses value of courses for classes object
	   */
	public void setCourses(courses courses) {
		this.courses = courses;
	}
	/**
	   * Method to get status value from classes object
	   * 
	   * @return status status of classes object
	   */
	public String getStatus() {
		return status;
	}
	/**
	   * Method to set status value to classes object
	   * 
	   * @param status status of classes object
	   */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	   * Method to get start date value from classes object
	   * 
	   * @return start_date start date of classes object
	   */
	public java.util.Date getStart_date() {
		return start_date;
	}
	/**
	   * Method to set start date to classes object
	   * 
	   * @param start_date start date of classes object
	   */
	public void setStart_date(java.util.Date start_date) {
		this.start_date = start_date;
	}
	/**
	   * Method to get end date from classes
	   * 
	   * @return end_date end date of classes object
	   */
	public java.util.Date getEnd_date() {
		return end_date;
	}
	/**
	   * Method to set end date to classes object
	   * 
	   * @param end_date end date of classes object
	   */
	public void setEnd_date(java.util.Date end_date) {
		this.end_date = end_date;
	}
	/**
	   * Method to get the value of set students_classes from classes
	   * 
	   * @return students_classes the value of set student_related with classes object
	   */
	public Set<students_classes> getStudents_classes() {
		return students_classes;
	}
	/**
	   * Method to set students_classes relative with classes object
	   * 
	   * @param students_classes the value of set students_classes related with classes object
	   */
	public void setStudents_classes(Set<students_classes> students_classes) {
		this.students_classes = students_classes;
	}
}