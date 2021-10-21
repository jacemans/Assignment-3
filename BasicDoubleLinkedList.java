import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * This generic double-linked list relies on a head and tail. Both are set to null when the list is empty.
 * Both point to the same element when there is only one element in the list.
 * A node structure has only three fields: data and the prev and next references.
 * The class must only define the following entities:
 * an inner class Node, an inner class that implements ListIterator, head and tail references and an integer representing the list size. 
 * However only the hasNext(), next(), hasPrevious() and previous() methods of ListIterator need to be implemented, 
 * all other methods can throw the UnsupportedOperationException.
 * 
 * @author Justin Hixson
 *
 * @param <T> data element type
 */
public class BasicDoubleLinkedList<T> implements Iterable<T> {
	
	protected class Node {
		protected T data;
		protected Node next;
		protected Node previous;

		protected Node(T data, Node next, Node previous) {
			this.data = data;
			this.next = next;
			this.previous = previous;
		}
	}

	protected int size;
	protected Node first;
	protected Node last;
	
	public BasicDoubleLinkedList() {
		size = 0;
		first = null;
		last = null;
		
	}
	/**
	 * Adds an element to the end of the list. Do not use iterators to implement this method.
	 * @param data the data for the Node within the linked list
	 * @return reference to the current object
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		Node node = new Node(data, null, last);
		
		if (last != null) {
			last.next = node;
		}
		
		last = node;
		
		if (first == null) {
			first = node;
		}
		
		size++;
		return this;
	}
	/**
	 * Adds element to the front of the list. Do not use iterators to implement this method.
	 * @param data the data for the Node within the linked list
	 * @return reference to the current object
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) {
		Node node = new Node(data, first, null);
		if (first != null) {
			first.previous = node;
		}
		first = node;

		if (last == null) {
			last = node;
		}

		size++;
		return this;
	}
	/**
	 * Returns but does not remove the first element from the list. If there are no elements the method returns null. Do not implement this method using iterators.
	 * @return the data element or null
	 */
	public T getFirst() {
		return first.data;
	}
	/**
	 * Returns but does not remove the last element from the list. If there are no elements the method returns null. Do not implement this method using iterators.
	 * @return the data element or null
	 */
	public T getLast() {
		return last.data;
	}
	/**
	 * Notice you must not traverse the list to compute the size. This method just returns the value of the instance variable you use to keep track of size.
	 * @return the size of the linked list
	 */
	public int getSize() {
		return size;
	}
	/**
	 * This method must be implemented using an inner class that implements ListIterator and defines the methods of hasNext(), next(), hasPrevious() and previous().
	 * Remember that we should be able to call the hasNext() method as many times as we want without changing what is considered the next element.
	 * @return  new iterator
	 * @throws UnsupportedOperationException You don't need to implement the ListIterator's remove(), add(), nextIndex() and previousIndex() and set() methods.
	 * @throws NoSuchElementException Your next() method should throw NoSuchElementException if there are no more elements.
	 */
	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException {
		return new Iterator();
	}
	/**
	 * Removes the first instance of the targetData from the list. Notice that you must remove the elements by performing a single traversal over the list.
	 * You may not use any of the other retrieval methods associated with the class in order to complete the removal process.
	 * You must use the provided comparator to find those elements that match the target.
	 * Do not implement this method using iterators.
	 * @param targetData the data element to be removed
	 * @param comparator the comparator to determine equality of data elements
	 * @return data element or null
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, java.util.Comparator<T> comparator) {
		Node prev = null;
		Node current = first;
		
		while (current != null) {
			if (comparator.compare(current.data, targetData) == 0) {
				if (current == first) {
					first = first.next;
					current = first;
				} 
				else if (current == last) {
					current = null;
					last = prev;
					prev.next = null;
				} 
				else {
					prev.next = current.next;
					current = current.next;
				}
				size--;
			} 
			else {
				prev = current;
				current = current.next;
			}
		}
		return this;
	}
	/**
	 * Removes and returns the first element from the list. If there are no elements the method returns null. Do not implement this method using iterators.
	 * @return data element or null
	 */
	public T retrieveFirstElement() {
		if (size == 0) {
			throw new NoSuchElementException("Linked list is empty");
		}
		
		Node node = first;
		first = first.next;
		first.previous = null;
		
		size--;
		return node.data;
	}
	/**
	 * Removes and returns the last element from the list. If there are no elements the method returns null. Do not implement implement this method using iterators.
	 * @return the last element from the list
	 */
	public T retrieveLastElement() {
		if (first == null) {
			throw new NoSuchElementException("Linked list is empty");
		}
		
		Node current = first;
		Node previousNode = null;
		
		while (current != null) {
			if (current.equals(last)) {
				last = previousNode;
				break;
			}
			previousNode = current;
			current = current.next;
		}
		
		size--;
		return current.data;
	}
	/**
	 * Returns an arraylist of the items in the list from head of list to tail of list
	 * @return an arraylist of the items in the list
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> arrayList = new ArrayList<>();
		ListIterator<T> iterator1 = new Iterator();

		while (iterator1.hasNext()) {
			arrayList.add(iterator1.next());
		}
		return arrayList;
	}

	private class Iterator implements ListIterator<T> {

		private Node current;
		private Node last;

		public Iterator() {
			current = first;
			last = null;
		}

		public T next()  throws NoSuchElementException {
			if(current != null) {
				T data = current.data;
				last = current;
				current = current.next;
				if(current != null)
					current.previous = last;

				return data;
			}
			else
				throw new NoSuchElementException();
		}

		public boolean hasNext() {
			return current != null;
		}

		public T previous() {
			if (last != null) {
				current = last;
				last= current.previous;
				return current.data;
			}
			else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void add(T e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		public boolean hasPrevious() {
			return last != null;
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		public void set(T data) {
			throw new UnsupportedOperationException();
		}
	}
}
