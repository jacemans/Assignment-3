import static org.junit.Assert.*;

import java.util.ArrayList;
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
public class BasicDoubleLinkedList_STUDENT_Test {
	
	BasicDoubleLinkedList<Student> linkedStudent;
	StudentComparator comparatorStudent;
	
	public Student a = new Student("Aaron", 1, 'A');
	public Student b = new Student("Beth", 2, 'B');
	public Student c = new Student("Chris", 3, 'C');
	public Student d = new Student("David", 4, 'D');
	public Student e = new Student("Evan", 5, 'E');
	public Student f = new Student("Franklin", 6, 'F');

	@Before
	public void setUp() {
		linkedStudent= new BasicDoubleLinkedList<>();
		linkedStudent.addToEnd(b);
		linkedStudent.addToEnd(c);
		comparatorStudent = new StudentComparator();
	}

	@After
	public void tearDown() {
		linkedStudent = null;
		comparatorStudent = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(2, linkedStudent.getSize());
	}
	
	@Test
	public void testAddToEnd() {		
		assertEquals(c, linkedStudent.getLast());
		linkedStudent.addToEnd(d);
		assertEquals(d ,linkedStudent.getLast());
	}
	
	@Test
	public void testAddToFront() {		
		assertEquals(b,linkedStudent.getFirst());
		linkedStudent.addToFront(a);
		assertEquals(a,linkedStudent.getFirst());
	}
	
	@Test
	public void testGetFirst() {
		assertEquals(b,linkedStudent.getFirst());
		linkedStudent.addToFront(c);
		assertEquals(c,linkedStudent.getFirst());
	}

	@Test
	public void testGetLast() {
		assertEquals(c,linkedStudent.getLast());
		linkedStudent.addToEnd(e);
		assertEquals(e,linkedStudent.getLast());
	}
	
	@Test
	public void testToArrayList()
	{
		ArrayList<Student> list;
		linkedStudent.addToFront(a);
		linkedStudent.addToEnd(d);
		list = linkedStudent.toArrayList();
		assertEquals(a,list.get(0));
		assertEquals(b,list.get(1));
		assertEquals(c,list.get(2));
		assertEquals(d,list.get(3));
	}
	
	@Test
	public void testIteratorSuccessfulNext() {linkedStudent.addToFront(a);
		linkedStudent.addToEnd(d);
		ListIterator<Student> iteratorStudent = linkedStudent.iterator();
		assertEquals(true, iteratorStudent.hasNext());
		assertEquals(a, iteratorStudent.next());
		assertEquals(b, iteratorStudent.next());
		assertEquals(c, iteratorStudent.next());
		assertEquals(true, iteratorStudent.hasNext());
		assertEquals(d, iteratorStudent.next());
	}
	
	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedStudent.addToFront(a);
		linkedStudent.addToEnd(d);
		ListIterator<Student> iteratorStudent = linkedStudent.iterator();
		assertEquals(true, iteratorStudent.hasNext());
		assertEquals(a, iteratorStudent.next());
		assertEquals(b, iteratorStudent.next());
		assertEquals(c, iteratorStudent.next());
		assertEquals(d, iteratorStudent.next());
		assertEquals(true, iteratorStudent.hasPrevious());
		assertEquals(d, iteratorStudent.previous());
		assertEquals(c, iteratorStudent.previous());
		assertEquals(b, iteratorStudent.previous());
		assertEquals(a, iteratorStudent.previous());
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedStudent.addToFront(a);
		linkedStudent.addToEnd(d);
		ListIterator<Student> iteratorStudent = linkedStudent.iterator();		
		assertEquals(true, iteratorStudent.hasNext());
		assertEquals(a, iteratorStudent.next());
		assertEquals(b, iteratorStudent.next());
		assertEquals(c, iteratorStudent.next());
		assertEquals(true, iteratorStudent.hasNext());
		assertEquals(d, iteratorStudent.next());
		
		try{
			iteratorStudent.next();
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
	public void testIteratorNoSuchElementExceptionPrevious() {
		linkedStudent.addToFront(a);
		linkedStudent.addToEnd(d);
		ListIterator<Student> iteratorStudent = linkedStudent.iterator();		
		assertEquals(true, iteratorStudent.hasNext());
		assertEquals(a, iteratorStudent.next());
		assertEquals(b, iteratorStudent.next());
		assertEquals(c, iteratorStudent.next());
		assertEquals(d, iteratorStudent.next());
		assertEquals(true, iteratorStudent.hasPrevious());
		assertEquals(d, iteratorStudent.previous());
		assertEquals(c, iteratorStudent.previous());
		assertEquals(b, iteratorStudent.previous());
		assertEquals(a, iteratorStudent.previous());
		
		try{
			iteratorStudent.previous();
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
	public void testIteratorUnsupportedOperationException() {
		linkedStudent.addToFront(a);
		linkedStudent.addToEnd(d);
		ListIterator<Student> iteratorStudent = linkedStudent.iterator();		
		assertEquals(true, iteratorStudent.hasNext());
		assertEquals(a, iteratorStudent.next());
		assertEquals(b, iteratorStudent.next());
		assertEquals(c, iteratorStudent.next());
		assertEquals(true, iteratorStudent.hasNext());
		assertEquals(d, iteratorStudent.next());
		
		try {
			iteratorStudent.remove();
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
	public void testRemove() {
		assertEquals(b, linkedStudent.getFirst());
		assertEquals(c, linkedStudent.getLast());
		linkedStudent.addToFront(a);
		assertEquals(a, linkedStudent.getFirst());
		linkedStudent.remove(a, comparatorStudent);
		assertEquals(b, linkedStudent.getFirst());

		linkedStudent.addToEnd(d);
		assertEquals(d, linkedStudent.getLast());
		linkedStudent.remove(d, comparatorStudent);
		assertEquals(c, linkedStudent.getLast());

		linkedStudent.addToFront(a);
		assertEquals(a, linkedStudent.getFirst());
		assertEquals(c, linkedStudent.getLast());
		linkedStudent.remove(b, comparatorStudent);
		assertEquals(a, linkedStudent.getFirst());
		assertEquals(c, linkedStudent.getLast());
	}

	@Test
	public void testRetrieveFirstElement() {
		assertEquals(b, linkedStudent.getFirst());
		linkedStudent.addToFront(a);
		assertEquals(a, linkedStudent.getFirst());
		assertEquals(a, linkedStudent.retrieveFirstElement());
		assertEquals(b,linkedStudent.getFirst());
		assertEquals(b, linkedStudent.retrieveFirstElement());
		assertEquals(c,linkedStudent.getFirst());
	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals(c, linkedStudent.getLast());
		linkedStudent.addToEnd(d);
		assertEquals(d, linkedStudent.getLast());
		assertEquals(d, linkedStudent.retrieveLastElement());
		assertEquals(c,linkedStudent.getLast());
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