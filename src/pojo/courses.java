package pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
/**
 * <h1> courses </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
@Entity
public class courses {
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	private int course_id;
	@Column
	private String title;
	@Column
	private double fee;
	@Column
	private String type;
	@Column
	private int duration;
	@OneToMany(mappedBy="courses", fetch=FetchType.EAGER)
	private Set<classes> classes =new HashSet<classes>();
	/**
	   * Constructor
	   * 
	   */
	public courses() {}
	/**
	   * Constructor
	   * 
	   * @param title parameter to receive title of courses object
	   * @param fee parameter to receive fee of courses object
	   * @param type parameter to receive type of courses object
	   * @param duration parameter to receive duration(hour) of courses object
	   * @param classes parameter to receive classes related with courses object
	   */
	public courses(String title, double fee, String type, int duration,
			Set<pojo.classes> classes) {
		super();
		this.title = title;
		this.fee = fee;
		this.type = type;
		this.duration = duration;
		this.classes = classes;
	}

	/**
	  * Method to get course id of courses object
	  * 
	  * @return course_id the course id of courses object
	  */
	public int getCourse_id() {
		return course_id;
	}
	/**
	  * Method to set course id to courses object
	  * 
	  * @param course_id parameter to receive course id of courses object
	  */
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	/**
	  * Method to get title of courses object
	  * 
	  * @return course_id parameter to return the title of courses object
	  */
	public String getTitle() {
		return title;
	}
	/**
	  * Method to set title to courses object
	  * 
	  * @param title parameter to receive the title of courses object
	  */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	  * Method to get fee(MMK) of courses object
	  * 
	  * @return fee parameter to return the fee(MMK) of courses object
	  */
	public double getFee() {
		return fee;
	}
	/**
	  * Method to set fee(MMK) to courses object
	  * 
	  * @param fee parameter to receive the fee(MMK) of courses object
	  */
	public void setFee(double fee) {
		this.fee = fee;
	}
	/**
	  * Method to get type of courses object
	  * 
	  * @return type parameter to return the type of courses object
	  */
	public String getType() {
		return type;
	}
	/**
	  * Method to set type to courses object
	  * 
	  * @param type parameter to receive the type of courses object
	  */
	public void setType(String type) {
		this.type = type;
	}
	/**
	  * Method to get duration(hour) of courses object
	  * 
	  * @return duration parameter to return the duration(hour) of courses object
	  */
	public int getDuration() {
		return duration;
	}
	/**
	  * Method to set duration(hour) to courses object
	  * 
	  * @param duration parameter to receive duration(hour) of courses object
	  */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	/**
	  * Method to get classes related with courses object
	  * 
	  * @return classes parameter to return the classes list related with courses object
	  */
	public Set<classes> getClasses() {
		return classes;
	}
	/**
	  * Method to set classes list related with courses object
	  * 
	  * @param classes parameter to receive classes list related with courses object
	  */
	public void setClasses(Set<classes> classes) {
		this.classes = classes;
	}
}