import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Justin Hixson
 *
 */
public class SortedDoubleLinkedList_STUDENT_Test {
	SortedDoubleLinkedList<Student> sortedLinkedStudent;
	StudentComparator comparatorStudent;
	
	public Student a = new Student("Aaron", 1, 'A');
	public Student b = new Student("Beth", 2, 'B');
	public Student c = new Student("Chris", 3, 'C');
	public Student d = new Student("David", 4, 'D');
	public Student e = new Student("Evan", 5, 'E');
	public Student f = new Student("Franklin", 6, 'F');
	
	@Before
	public void setUp() {
		comparatorStudent = new StudentComparator();
		sortedLinkedStudent = new SortedDoubleLinkedList<>(comparatorStudent);
		
	}

	@After
	public void tearDown() {
		comparatorStudent = null;
		sortedLinkedStudent = null;
	}

	@Test
	public void testAddToEnd() {
		try {
			sortedLinkedStudent.addToEnd(new Student("Greg", 7, 'G'));
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException exception)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception exception)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddToFront() {
		try {
			sortedLinkedStudent.addToFront(new Student("Greg", 7, 'G'));
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException exception) {
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception exception) {
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testIteratorSuccessfulNext() {
		sortedLinkedStudent.add(a);
		sortedLinkedStudent.add(b);
		sortedLinkedStudent.add(c);
		sortedLinkedStudent.add(d);
		ListIterator<Student> iterator = sortedLinkedStudent.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(a, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(true, iterator.hasNext());
	}
	@Test
	public void testIteratorSuccessfulStudentPrevious() {
		sortedLinkedStudent.add(e);
		sortedLinkedStudent.add(c);
		sortedLinkedStudent.add(b);
		sortedLinkedStudent.add(d);

		ListIterator<Student> iterator = sortedLinkedStudent.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(b, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(d, iterator.next());
		assertEquals(e, iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(e, iterator.previous());
		assertEquals(d, iterator.previous());
		assertEquals(c, iterator.previous());
	}
	
	@Test
	public void testIteratorNoSuchElementException() {
		sortedLinkedStudent.add(e);
		sortedLinkedStudent.add(c);
		sortedLinkedStudent.add(b);
		sortedLinkedStudent.add(d);

		ListIterator<Student> iterator = sortedLinkedStudent.iterator();
		
		assertEquals(true, iterator.hasNext());
		assertEquals(b, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(d, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(e, iterator.next());
		try{
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException exception) {
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception exception) {
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	
	@Test
	public void testIteratorUnsupportedOperationExceptionString() {
		sortedLinkedStudent.add(e);
		sortedLinkedStudent.add(c);
		sortedLinkedStudent.add(b);
		sortedLinkedStudent.add(d);

		ListIterator<Student> iterator = sortedLinkedStudent.iterator();
		
		try{
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException exception) {
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception exception) {
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddStudent() {
		sortedLinkedStudent.add(a);
		sortedLinkedStudent.add(b);
		sortedLinkedStudent.add(c);
		assertEquals(a, sortedLinkedStudent.getFirst());
		assertEquals(c, sortedLinkedStudent.getLast());
		sortedLinkedStudent.add(d);
		sortedLinkedStudent.add(e);
		assertEquals(a, sortedLinkedStudent.getFirst());
		assertEquals(e, sortedLinkedStudent.getLast());

		assertEquals(e,sortedLinkedStudent.retrieveLastElement());
		assertEquals(d, sortedLinkedStudent.getLast());
	}

	@Test
	public void testRemoveFirstStudent() {
		sortedLinkedStudent.add(b);
		sortedLinkedStudent.add(c);
		assertEquals(b, sortedLinkedStudent.getFirst());
		assertEquals(c, sortedLinkedStudent.getLast());
		sortedLinkedStudent.add(a);
		assertEquals(a, sortedLinkedStudent.getFirst());

		sortedLinkedStudent.remove(a, comparatorStudent);
		assertEquals(b, sortedLinkedStudent.getFirst());
	}
	
	@Test
	public void testRemoveEndStudent() {
		sortedLinkedStudent.add(b);
		sortedLinkedStudent.add(f);
		assertEquals(b, sortedLinkedStudent.getFirst());
		assertEquals(f, sortedLinkedStudent.getLast());
		sortedLinkedStudent.add(d);
		assertEquals(f, sortedLinkedStudent.getLast());

		sortedLinkedStudent.remove(d, comparatorStudent);
		assertEquals(f, sortedLinkedStudent.getLast());
	}

	@Test
	public void testRemoveMiddleStudent() {
		sortedLinkedStudent.add(a);
		sortedLinkedStudent.add(b);
		assertEquals(a, sortedLinkedStudent.getFirst());
		assertEquals(b, sortedLinkedStudent.getLast());
		sortedLinkedStudent.add(f);
		assertEquals(a, sortedLinkedStudent.getFirst());
		assertEquals(f, sortedLinkedStudent.getLast());
		assertEquals(3,sortedLinkedStudent.getSize());

		sortedLinkedStudent.remove(a, comparatorStudent);
		assertEquals(b, sortedLinkedStudent.getFirst());
		assertEquals(f, sortedLinkedStudent.getLast());
		assertEquals(2,sortedLinkedStudent.getSize());
	}
	
	private class StudentComparator implements Comparator<Student>
	{
		@Override
		public int compare(Student s1, Student s2) {
			return s1.toString().compareTo(s2.toString());
		}
	}
	
	private class Student {
		String name;
		int id;
		char grade;
		
		public Student (String name, int id, char grade) {
			this.name = name;
			this.id = id;
			this.grade = grade;
		}
		
		public String getName(){
			return name;
		}
		public int getID(){
			return id;
		}
		public int getGrade(){
			return grade;
		}
		
		public String toString() {
			return (getName() + " "+ getID() + " " + getGrade());
		}
	}
}