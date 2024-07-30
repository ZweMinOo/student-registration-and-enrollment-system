package pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
/**
 * <h1> students </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
@Entity
public class students {
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	private int student_id;
	@Column
	private String name;
	@Column
	private String nrc;
	@Column
	private java.util.Date date_of_birth;
	@Column
	private String phone;
	@Column
	private String address;
	@OneToMany(mappedBy="students")
	private Set<students_classes> students_classes=new HashSet<students_classes>();
	/**
	   * Constructor
	   * 
	   */
	public students() {
		student_id = 0;
	}
	/**
	   * Constructor
	   * 
	   * @param name parameter to receive the name of students object
	   * @param nrc parameter to receive the nrc of students object
	   * @param date_of_birth parameter to receive the date_of_birth of students object
	   * @param phone parameter to receive the phone of students object
	   * @param address parameter to receive the address of students object
	   * @param students_classes parameter to receive the students_classes object related with students object
	   */
	public students(String name, String nrc, Date date_of_birth, String phone,
			String address, Set<pojo.students_classes> students_classes) {
		super();
		this.name = name;
		this.nrc = nrc;
		this.date_of_birth = date_of_birth;
		this.phone = phone;
		this.address = address;
		this.students_classes = students_classes;
	}

	/**
	   * Method to get the student_id of students object
	   * 
	   * @return student_id parameter to return the student_id of students object
	   */
	public int getStudent_id() {
		return student_id;
	}
	/**
	   * Method to set the student_id of students object
	   * 
	   * @param student_id parameter to receive the student_id of students object
	   */
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	/**
	   * Method to get the name of students object
	   * 
	   * @return name parameter to return the name of students object
	   */
	public String getName() {
		return name;
	}
	/**
	   * Method to set the name of students object
	   * 
	   * @param name parameter to receive the name of students object
	   */
	public void setName(String name) {
		this.name = name;
	}
	/**
	   * Method to get the nrc of students object
	   * 
	   * @return nrc parameter to return the nrc of students object
	   */
	public String getNrc() {
		return nrc;
	}
	/**
	   * Method to set the nrc of students object
	   * 
	   * @param nrc parameter to receive the nrc of students object
	   */
	public void setNrc(String nrc) {
		this.nrc = nrc;
	}
	/**
	   * Method to get the date_of_birth of students object
	   * 
	   * @return date_of_birth parameter to return the date_of_birth of students object
	   */
	public java.util.Date getDate_of_birth() {
		return date_of_birth;
	}
	/**
	   * Method to set the date_of_birth of students object
	   * 
	   * @param date_of_birth parameter to receive the date_of_birth of students object
	   */
	public void setDate_of_birth(java.util.Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	/**
	   * Method to get the phone of students object
	   * 
	   * @return phone parameter to return the phone of students object
	   */
	public String getPhone() {
		return phone;
	}
	/**
	   * Method to set the phone of students object
	   * 
	   * @param phone parameter to receive the phone of students object
	   */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	   * Method to get the address of students object
	   * 
	   * @return address parameter to return the address of students object
	   */
	public String getAddress() {
		return address;
	}
	/**
	   * Method to set the address of students object
	   * 
	   * @param address parameter to receive the address of students object
	   */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	   * Method to get the students_classes object related with students object
	   * 
	   * @return students_classes parameter to return the students_classes object related with students object
	   */
	public Set<students_classes> getStudents_classes() {
		return students_classes;
	}
	/**
	   * Method to set the students_classes object related with students object
	   * 
	   * @param students_classes parameter to receive the students_classes object related with students object
	   */
	public void setStudents_classes(Set<students_classes> students_classes) {
		this.students_classes = students_classes;
	}
}