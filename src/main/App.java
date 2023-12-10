package src.main;

import src.data.CourseData;

import src.models.Course;

public class App {

    public static void main(String[] args) {
        
        Course[] courses = CourseData.setCourseInfo();

        if (courses != null) {
            
            CourseData.setGrade(courses);
            float gpa = CourseData.setGpa(courses);
            CourseData.displayResults(courses, gpa);
        }
    }
}