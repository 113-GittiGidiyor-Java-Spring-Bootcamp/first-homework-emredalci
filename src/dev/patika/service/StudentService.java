package dev.patika.service;

import dev.patika.models.Course;
import dev.patika.models.Student;
import dev.patika.repository.CrudRepository;
import dev.patika.repository.StudentRepository;
import dev.patika.utils.EntityManagerUtils;
import dev.patika.utils.Messages;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentService implements CrudRepository<Student>, StudentRepository {

    EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");

    @Override
    public List<Student> findAll() {
        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");
        return em.createQuery("from Student",Student.class).getResultList();

    }

    @Override
    public Student findById(int id) {
        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");
        return em.find(Student.class,id);
    }

    @Override
    public void saveToDatabase(Student student) {
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(Messages.errorMessages);

        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }

    }

    @Override
    public void deleteFromDatabase(Student student) {
        try {
            em.getTransaction().begin();
            Student foundStudent = em.createQuery("select s from Student s WHERE s.id =:id",Student.class).setParameter("id",student.getId()).getSingleResult();
            em.remove(foundStudent);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(Messages.errorMessages);
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }

    }

    @Override
    public void updateOnDatabase(Student student, int id) {
        try {
            em.getTransaction().begin();
            Student foundStudent = em.find(Student.class,id);
            foundStudent.setAddress(student.getAddress());
            foundStudent.setBirthDate(student.getBirthDate());
            foundStudent.setName(student.getName());
            foundStudent.setGender(student.getGender());
            foundStudent.setCourses(student.getCourses());
            em.merge(foundStudent);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(Messages.errorMessages);
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }


    }

    @Override
    public void deleteStudentFromDatabase(int id) {
        try {
            em.getTransaction().begin();
            Student foundStudent = em.find(Student.class,id);
            em.remove(foundStudent);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(Messages.errorMessages);
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }



}
