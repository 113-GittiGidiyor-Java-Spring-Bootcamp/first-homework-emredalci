package dev.patika.clients;

import dev.patika.controller.StudentController;
import dev.patika.models.*;
import dev.patika.utils.EntityManagerUtils;
import dev.patika.utils.Messages;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        /*
        if (checkTestData()==0){
            saveTestData();
        }
        */
        saveTestData();

        StudentController studentController = new StudentController();

        //Testing saving new student
        Student student3 = new Student("Ali Battal",LocalDate.of(2001,Month.JANUARY,20),"İzmir",'E');
        //studentController.saveToDatabase(student3);

        //Testing finding all student and courses of the student
        List<Student> studentList = studentController.findAll();
        for(Student student : studentList){
            System.out.println(student.toString());
            System.out.println("Courses of the student : ");
            List<Course> courseList = student.getCourses();
            System.out.println(courseList.size());
            for (Course course: courseList) {
                System.out.println(course.toString());
            }
            System.out.println("-------------");
        }

        System.exit(0);

    }



    private static int checkTestData() {
        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");

        return em.createQuery("from Student",Student.class).getResultList().size();
    }

    private static void saveTestData() {

        Student student1 = new Student("Cihan Akarpınar", LocalDate.of(1990, Month.APRIL,5),"İstanbul",'E');
        Student student2 = new Student("Selin Ak", LocalDate.of(2000, Month.AUGUST,15),"Ankara",'K');

        Course course1 = new Course("Yazılım Mühendisliğine Giriş","CS101",4);
        Course course2 = new Course("Algoirtma Geliştirme","CS150",6);

        Instructor instructor1 = new PermanentInstructor("Koray Güney","İstanbul","+901234567890",100);
        Instructor instructor2 = new VisitingResearcher("Ahmet Mehmet","Ankara","+901234567897",50);
        // Students saves
        student1.getCourses().add(course1);
        student1.getCourses().add(course2);

        student2.getCourses().add(course1);

        course1.getStudents().add(student1);
        course1.getStudents().add(student2);

        course2.getStudents().add(student1);
        // Instructor1 set
        instructor1.getCourses().add(course1);
        course1.setInstructor(instructor1);
        //Instructor2 set
        instructor2.getCourses().add(course2);
        course2.setInstructor(instructor2);

        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");

        try {

            em.getTransaction().begin();

            em.persist(student1);
            em.persist(student2);

            em.persist(instructor1);
            em.persist(instructor2);

            em.persist(course1);
            em.persist(course2);


            em.getTransaction().commit();

            System.out.println("All data persisted");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(Messages.errorMessages);
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }


    }
}
