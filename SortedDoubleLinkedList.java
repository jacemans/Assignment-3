import java.util.Comparator;
/**
 * Implements a generic sorted double list using a provided Comparator. It extends BasicDoubleLinkedList class
 * 
 * @author Justin Hixson
 *
 * @param <T> data element type
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	
	Comparator<T> comparable2;
	/**
	 * Creates an empty list that is associated with the specified comparator
	 * @param comparable2 Comparator to compare data elements
	 */
	public SortedDoubleLinkedList(Comparator<T> comparable2) {
		this.comparable2 = comparable2;
	}
	/**
	 * Inserts the specified element at the correct position in the sorted list. Notice we can insert the same element several times.
	 * Your implementation must traverse the list only once in order to perform the insertion. Do not implement this method using iterators.
	 * Notice that you don't need to call any of the super class methods in order to implement this method.
	 * @param data the data to be added to the list
	 * @return a reference to the current object
	 */
	public SortedDoubleLinkedList<T> add(T data) {
		if (data == null) {
			return this;
		}

		Node node = new Node(data, null, null);
		if (first == null) {
			first = last = new Node(data, null, null);
		}
		else {
			if (comparable2.compare(data, first.data) <= 0) {
				node.next = first;
				first = node;
			} 
			else if (comparable2.compare(data, last.data) >= 0) {
				last.next = node;
				last = node;
			} 
			else {
				Node next = first.next;
				Node previous = first;
				while (comparable2.compare(data, next.data) > 0) {
					previous = next;
					next = next.next;
				}
				previous.next = node;
				node.next = next;
			}
		}
		
		size++;
		return this;
	}
	/**
	 * This operation is invalid for a sorted list. An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
	 * @param data the data for the Node within the linked list
	 * @return reference to the current object
	 * @throws UnsupportedOperationException if method is called
	 */
	@Override
	public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	/**
	 * This operation is invalid for a sorted list. An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
	 * @param data the data for the Node within the linked list
	 * @return reference to the current object
	 * @throws UnsupportedOperationException if method is called
	 */
	@Override
	public BasicDoubleLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	/**
	 * Implements the iterator by calling the super class iterator method.
	 * @return an iterator positioned at the head of the list
	 */
	@Override
	public java.util.ListIterator<T> iterator() {
		return super.iterator();
	}

	/**
	 * This operation is invalid for a sorted list. An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
	 * @param data the data element to be removed
	 * @param comparator the comparator to determine equality of data elements
	 * @return data element or null
	 */
	@Override
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator) {
		super.remove(data, comparable2);

		return this;
	}
}
