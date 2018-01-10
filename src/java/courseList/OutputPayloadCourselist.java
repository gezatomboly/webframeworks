/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseList;

import at.database.Course;
import java.util.ArrayList;



/**
 *
 * @author gezatomboly
 */
public class OutputPayloadCourselist {
    
    private String message;
    private ArrayList<Course> courseList = new ArrayList<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }
    
    public void addCourse(Course c){
        courseList.add(c);
    }
    
    
    
    
    
    
}
