package systemUsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import customDatatypes.EvaluationTypes;
import customDatatypes.Marks;
import customDatatypes.NotificationTypes;
import offerings.CourseOffering;
import offerings.ICourseOffering;

public class StudentModel implements IStudentModel{
	
	private String name;
	private String surname;
	private String ID;
	private List<ICourseOffering> coursesAllowed;
	private List<ICourseOffering> coursesEnrolled;
	private Map<ICourseOffering, EvaluationTypes> evaluationEntities;
//	check the comments at the Marks Class this map should contain as many pairs of <CourseOffering, Marks> as course that 
//	the student has enrolled in.
	private Map<ICourseOffering, Marks> perCourseMarks;
	private NotificationTypes notificationType;
//	0 for off, 1 for on
	private int notificationStatus;
	
	public int getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(int notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}
	
	public List<ICourseOffering> getCoursesAllowed() {
		return coursesAllowed;
	}
	
	public void setCoursesAllowed(List<ICourseOffering> coursesAllowed) {
		this.coursesAllowed = coursesAllowed;
	}
	
	public List<ICourseOffering> getCoursesEnrolled() {
		return coursesEnrolled;
	}
	
	public void setCoursesEnrolled(List<ICourseOffering> coursesEnrolled) {
		this.coursesEnrolled = coursesEnrolled;
	}
	
	public Map<ICourseOffering, EvaluationTypes> getEvaluationEntities() {
		return evaluationEntities;
	}
	
	public void setEvaluationEntities(Map<ICourseOffering, EvaluationTypes> evaluationEntities) {
		this.evaluationEntities = evaluationEntities;
	}
	
	public Map<ICourseOffering, Marks> getPerCourseMarks() {
		return perCourseMarks;
	}
	
	public void setPerCourseMarks(Map<ICourseOffering, Marks> perCourseMarks) {
		this.perCourseMarks = perCourseMarks;
	}
	
	public NotificationTypes getNotificationType() {
		return notificationType;
	}
	
	public void setNotificationType(NotificationTypes notificationType) {
		this.notificationType = notificationType;
	}
	
	public boolean enroll(CourseOffering course) {
		List<StudentModel> enrolledStudents = course.getStudentsEnrolled();
		if(checkIfStudentCanTakeCourse(course)) {
			if (coursesEnrolled == null)
				coursesEnrolled = new ArrayList<>();
			enrolledStudents.add(this);
			coursesEnrolled.add(course);
			return true;
		}
		return false;
	}
	
	public boolean checkIfStudentCanTakeCourse(CourseOffering course) {
		List<StudentModel> studentsAllowedToEnroll = course.getStudentsAllowedToEnroll();
		
		if(studentsAllowedToEnroll.contains(this)) {
			if(coursesAllowed.contains(course))
				return true;
		}
		return false;
	}
	
}
