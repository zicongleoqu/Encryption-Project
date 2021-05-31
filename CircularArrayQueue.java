/**
	 * This class represents a Queue implementation using a circular array as the underlying data structure.
	 * @author Zicong Qu
	 */
public class CircularArrayQueue<T> implements QueueADT<T> {
	private int front, rear, count;
	private T[] queue;
	private int DEFAULT_CAPACITY = 20;

	/**
	 * Constructor initialize front, rear, count, and capacity of the queue array.
	 */
	public CircularArrayQueue () {
		front = 1;
		rear = DEFAULT_CAPACITY;
		count = 0;
		queue = (T[])(new Object[DEFAULT_CAPACITY]);
	}

	/**
	 * Same as the first constructor described above, except that this one takes in 
	 * an integer parameter for the initial capacity rather than using the default capacity.
	 * 
	 * @param  size the size of the queue array
	 */
	public CircularArrayQueue (int size) {
		front = 1;
		rear = size;
		count = 0;
		queue = (T[])(new Object[size]);
	}
	
	/** Takes in an element of the generic type and adds that element to the rear of the queue
	 * 
     *  @param element the element needs to be pushed to to the rear of the queue.
     */
	public void enqueue(T element) {
		if (count == queue.length) {
			expandCapacity();
		}
		rear = (rear + 1) % queue.length;
		queue[rear] = element;
		count ++;
	}
	
	/** Throw EmptyCollectionException if the array is empty. Remove and return the item at the front of the stack.
	 * 
     *  @return the item at the font of the stack
     */
	public T dequeue() throws EmptyCollectionException{
		if (isEmpty()) {
			throw new EmptyCollectionException("queue");
		}
		T element = queue[front];
		front = (front + 1) % queue.length;		
		count --;
		return element;
	}
	
	/** Throws an EmptyCollectionException if the queue is empty. 
	 * Otherwise return the item at the front of the queue without removing it
	 * 
     *  @return the item at the font of the stack
     */
	public T first() throws EmptyCollectionException{
		if (isEmpty()) {
			throw new EmptyCollectionException("queue");
		}
		T element = queue[front];
		return element;
	}
	
	/** Returns true if the queue is empty, and false otherwise.
	 * 
     *  @return True or false indicating if the queue is empty
     */
	public boolean isEmpty() {
		if (count == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	/** Returns the number of items on the queue.
	 * 
     *  @return the number of items on the queue.
	*/
	public int size() {
		return count;
	}
	
	/** Returns the front index value 
	 * 
     *  @return the front index value 
	*/
	public int getFront() {
		return front;
	}
	
	/** Returns the rear index value 
	 * 
     *  @return the rear index value 
	*/
	public int getRear() {
		return rear;
	}
	
	/** Returns the current length (capacity) of the array 
	 * 
     *  @return the current length (capacity) of the array 
	*/
	public int getLength() {
		return queue.length;
	}
	
	/** Returns the string containing "QUEUE: " followed by each of the queue's items 
	 * in order from front to rear with ", " between each of the items and a period at the end of the last item. 
	 * If the queue is empty then print "The queue is empty" instead.
	 * 
     *  @return the string representation of the queue
	*/
	public String toString() {
		String result;
		if (isEmpty()) {
			result = "The queue is empty";
		}else {
			result = "QUEUE: ";
			for (int i = 1; i <= count; i ++) {
				result += queue[i];
				if (i != count) {
					result += ", ";
				}else {
					result += ".";
				}
			}
		}
		return result;
	}
	
	/** Create a new array that has 20 more slots than the current array has.
	*/
	private void expandCapacity() {
		int new_size = getLength() + 20;
		T[] expanded_queue = (T[])(new Object[new_size]);
		for (int i = front; i <= count; i ++) {
			expanded_queue[i] = queue[front];
			front = (front + 1) % queue.length;
		}
		queue = expanded_queue;
		front = 1;
		rear = count;
	}
}
