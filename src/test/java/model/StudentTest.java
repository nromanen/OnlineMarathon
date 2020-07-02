package model;

import model.Student;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static model.Student.validate;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest{

    @Test
    public void testNullNameExceptionStudent(){
        assertThrows(ConstraintViolationException.class, () -> {Student s = new Student();
            LocalDate d = LocalDate.now();
            d.of(LocalDate.now().getYear()-18, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
            s.setDateOfBirth(d);
            validate(s);});
    }

    @Test
    public void testExceptionMessageStudent(){
        try {
            Student s = new Student();
            LocalDate d = LocalDate.now();
            d = d.of(LocalDate.now().getYear() - 18, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
            s.setDateOfBirth(d);
            s.setName("Eaaaaaaaaaaaaaaaaaaaaa");
            validate(s);
        } catch (ConstraintViolationException e){
            String expected = "Error in field name with value Eaaaaaaaaaaaaaaaaaaaaa: The student name must started from Capitalize letter with length from 1 to 20";
            Set<String> expectedSet = new HashSet<>(Arrays.asList(expected.split("\n")));

            String actual = e.getMessage();
            Set<String> actualSet = new HashSet<>(Arrays.asList(actual.split("\n")));
            assertEquals(expectedSet, actualSet);
        }
    }

    @Test
    public void testNullNumeExceptionMessageStudent(){
        try {
            Student s = new Student();
            LocalDate d = LocalDate.now();
            d = d.of(LocalDate.now().getYear() - 18, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
            s.setDateOfBirth(d);
            validate(s);
        } catch (ConstraintViolationException e){
            String expected =
                    "Error in field name with value null: The student name must not be null";
            Set<String> expectedSet = new HashSet<>(Arrays.asList(expected.split("\n")));

            String actual = e.getMessage();
            Set<String> actualSet = new HashSet<>(Arrays.asList(actual.split("\n")));
            assertEquals(expectedSet, actualSet);
        }
    }

    @Test
    public void testVeryYoungExceptionMessageStudent() {
        LocalDate d = LocalDate.now();
        try {
            Student s = new Student();
            d = d.of(LocalDate.now().getYear() - 10, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
            s.setDateOfBirth(d);
            s.setName("Al");
            validate(s);
        } catch (ConstraintViolationException e) {
            String expected =
                    "Error in field dateOfBirth with value " + d.toString() + ": Age must be in range from 15 to 30";
            Set<String> expectedSet = new HashSet<>(Arrays.asList(expected.split("\n")));

            String actual = e.getMessage();
            Set<String> actualSet = new HashSet<>(Arrays.asList(actual.split("\n")));
            assertEquals(expectedSet, actualSet);
        }
    }

    @Test
    public void testVeryOldExceptionMessageStudent(){
        LocalDate d = LocalDate.now();
        try {
            Student s = new Student();
            d = d.of(LocalDate.now().getYear() - 31, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
            s.setDateOfBirth(d);
            s.setName("Alex");
            validate(s);
        } catch (ConstraintViolationException e){
            String expected =
                    "Error in field dateOfBirth with value "+ d.toString()+": Age must be in range from 15 to 30";
            Set<String> expectedSet = new HashSet<>(Arrays.asList(expected.split("\n")));

            String actual = e.getMessage();
            Set<String> actualSet = new HashSet<>(Arrays.asList(actual.split("\n")));
            assertEquals(expectedSet, actualSet);
        }
    }

    @Test
    public void testNameAndAgeExceptionMessageStudent(){
        LocalDate d = LocalDate.now();
        try {
            Student s = new Student();
            d = d.of(LocalDate.now().getYear() - 2, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
            s.setDateOfBirth(d);
            s.setName("E");
            validate(s);
        } catch (ConstraintViolationException e){
            String expected = "Error in field dateOfBirth with value "+ d.toString()+": Age must be in range from 15 to 30\n" +
                    "Error in field name with value E: The student name must started from Capitalize letter with length from 1 to 20";
            Set<String> expectedSet = new HashSet<>(Arrays.asList(expected.split("\n")));

            String actual = e.getMessage();
            Set<String> actualSet = new HashSet<>(Arrays.asList(actual.split("\n")));
            assertEquals(expectedSet, actualSet);
        }
    }

    @Test
    public void testHappyTest(){
        LocalDate d = null;
        Student s = new Student();
        d = LocalDate.of(LocalDate.now().getYear() - 20, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
        s.setDateOfBirth(d);
        s.setName("Evhen");
        assertTrue(validate(s));
    }
}

