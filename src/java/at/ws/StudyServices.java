/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ws;

import at.database.Course;
import at.database.HibernateUtil;
import at.database.Person;
import at.database.PersonCourseMembership;
import at.database.PersonCourseMembershipId;
import courseList.InputPayloadCourselist;
import courseList.OutputPayloadCourselist;
import createNewCourse.InputPayloadCreatNewCourse;
import createNewCourse.OutputPayloadCreateNewCourse;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import login.InputPayloadLogin;
import login.OutputPayloadLogin;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author gezatomboly
 */
@WebService(serviceName = "StudyServices")
public class StudyServices {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "login")
    public OutputPayloadLogin login(@WebParam(name = "parameter") InputPayloadLogin parameter) {
        
        OutputPayloadLogin opl = new OutputPayloadLogin();
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            String hql = "FROM Person P WHERE P.username = :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", parameter.getName());
            
            List results = query.list();
            
            Person personFromDB = (Person)results.get(0);
            
            if(personFromDB.getPassword().equals(parameter.getPassword())){
                opl.setFehlerbeschreibung("Success!");
                opl.setUserid(personFromDB.getPersonPk());
                opl.setRole(personFromDB.getRole());
            }else {
                opl.setFehlerbeschreibung("Failure");
            }
            transaction.commit();
            
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }
        
        finally{
            session.close();
        }
        
        
        return opl;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "courseList")
    public OutputPayloadCourselist courseList(@WebParam(name = "parameter") InputPayloadCourselist parameter) {
        
        
        OutputPayloadCourselist opcl = new OutputPayloadCourselist();
        
        
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        
        try{
           transaction = session.beginTransaction();
           String hql = "SELECT C FROM PersonCourseMembership M LEFT JOIN M.course C WHERE M.person.personPk = :id ";
           Query query = session.createQuery(hql);
           query.setParameter("id", parameter.getUserId());
           
           List result = query.list();
           
           //Transaktion 
           transaction.commit();
           
           //KursListe vom DB hinzufügen
           for(int i = 0; i<result.size(); i++){
               
               //Course from DB
               Course courseFromDB = (Course)result.get(i);
               
               //Neus Kursobjekt
               Course course = new Course();
               course.setCoursePk(courseFromDB.getCoursePk());
               course.setTitle(courseFromDB.getTitle());
               course.setDescription(courseFromDB.getDescription());
               
               //Add kurs zu Arraylist im OutputPayloadCourslist
               opcl.addCourse(course);
           }
           opcl.setMessage("Success");
        
        }catch(Exception e){
            opcl.setMessage("Fehler");
            if(transaction != null){
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        
        return opcl;
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addNewCourse")
    public OutputPayloadCreateNewCourse addNewCourse(@WebParam(name = "parameter") InputPayloadCreatNewCourse parameter) {
        
        OutputPayloadCreateNewCourse opcnc = new OutputPayloadCreateNewCourse();
        
        SessionFactory sessionFactroy = HibernateUtil.getSessionFactory();
        Session session = sessionFactroy.openSession();
        Transaction transaction = null;
        
        try{
           
            transaction = session.beginTransaction();
            
           //new course varible für DB
           Course newCourse = new Course();
           
           //set name and description from input
           newCourse.setTitle(parameter.getCourseName());
           newCourse.setDescription(parameter.getCourseDesc());
           
           //save new course primary key
           Integer primarykeyCourse = (Integer)session.save(newCourse);
           
           //save course and lector in personCourseMembership DB
           PersonCourseMembership personCourseMembership = new PersonCourseMembership();
           personCourseMembership.setId(new PersonCourseMembershipId(parameter.getUserid(), primarykeyCourse));
           
           session.save(personCourseMembership);
           
           transaction.commit();
           opcnc.setMessage("Erfolgreiche Speicherung");
           
        }catch(Exception e){
            if(transaction != null){
                opcnc.setMessage("Fehler");
                transaction.rollback();
            }
        }finally{
            session.close();
        }
        
        
        return opcnc;
    }


}
