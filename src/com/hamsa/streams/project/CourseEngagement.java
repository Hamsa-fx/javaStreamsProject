package com.hamsa.streams.project;

import java.time.LocalDate;
import java.time.Period;

public class CourseEngagement {
	private final Course course;
	private final LocalDate enrollmentDate;	

	private String engagementType;
	private int lastLecture;
	private LocalDate lastActivityDate;
	
	public CourseEngagement(Course course, LocalDate enrollmentDate, String engagementType) {
		this.course = course;
		this.enrollmentDate = enrollmentDate;
		this.lastActivityDate = enrollmentDate;
		this.engagementType = engagementType;
	}

	public String getEngagementType() {
		return engagementType;
	}

	public void setEngagementType(String engagementType) {
		this.engagementType = engagementType;
	}

	public int getLastLecture() {
		return lastLecture;
	}

	public void setLastLecture(int lastLecture) {
		this.lastLecture = lastLecture;
	}

	
	public void setLastActivityDate(LocalDate lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}

	public String getCourseCode() {
		return course.courseCode();
	}

	public int getEnrollmentYear() {
		return enrollmentDate.getYear();
	}
	
	public int getLastActivityYear() {
		return lastActivityDate.getYear();
	}
	
	public String getLastActivityMonth() {
		return "%tb".formatted(lastActivityDate);
	}
	
	public double getPercentComplete() {
		return lastLecture * 100.00 /course.lectureCount();
	}
	
	public int getMonthsSinceActive() {
		LocalDate now = LocalDate.now();
		var months = Period.between(lastActivityDate,now).toTotalMonths();
		return (int) months;
	}
	
	void watchLecture(int lectureNumber,LocalDate currentDate) {
		lastLecture = Math.max(lectureNumber, lectureNumber);
		lastActivityDate = currentDate;
		engagementType = "Lecture" + lastLecture;
	}

	@Override
	public String toString() {
		return "%s: %s %d %s [%d]" .formatted(course.courseCode(),
				                      getLastActivityMonth(),getLastActivityYear(),engagementType,
				                      getMonthsSinceActive());
	}

	
	
	

}
