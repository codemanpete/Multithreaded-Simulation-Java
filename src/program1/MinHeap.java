package program1;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * A min-heap that stores elements of a generic, comparable type
 * @author Lydia McGovern
 * @param <T> the generic, comparable type used in the heap
 */
public class MinHeap<T extends Comparable<T>> {
    private T[] heap;
    private int capacity;
    private int currentSize;
    
/**
 * Constructor for the MinHeap
 * @param type the concrete class for the generic type
 * @param capacity the total capacity of the heap
 */
    public MinHeap(Class<T> type, int capacity) { // Call with MinHeap(ProcessNode.class, 100)
        this.capacity = capacity;
        this.heap = (T[]) Array.newInstance(type, capacity);
        currentSize = 0;
    }
    
/**
 * Recursively re-heap an element upward from its current position
 * @param i the index of the item to be re-heaped
 */
     private void heapUp(int i) {
        if (i == 0) // Check if it's the root node
            return;
        
        int parent = (i - 1) / 2; // The index of the element's parent
        if(heap[parent].compareTo(heap[i]) > 0) { // Swap if smaller than its parent
            swap(parent, i);
            heapUp(parent);
        }
    }
    
/**
 * Recursively re-heap an element downward from its current position
 * @param i the index of the item to be re-heaped
 */
    private void heapDown(int i) {
// The indices of the element's children
        int leftChild = (2 * i) + 1;
        int rightChild = (2 * i) + 2;
	int min = i; // this will be the index of the smallest element of the three
        if(leftChild < currentSize && heap[i].compareTo(heap[leftChild]) > 0) // Check if left child is smaller
            min = leftChild;        
        if (rightChild < currentSize && heap[min].compareTo(heap[rightChild]) > 0) // check if right child is smallest
            min = rightChild;
        
        if (min != i) { // swap if the element is larger than its child
            swap(i, min);
            heapDown(min);
        }
    }
    
/**
 * Swap two elements in the heap
 * @param i the first element to be swapped
 * @param j  the second element to be swapped
 */
    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    
/**
 * Add an element to the heap
 * @param t the element to be added to the heap
 */
    public void add(T t) {
	if (isFull()) // Check if full
	    throw new ArrayIndexOutOfBoundsException("The heap is already full.");
        heap[currentSize] = t; // add to the heap and increase the size
	heapUp(currentSize); // re-heap
	currentSize++;
    }
    
/**
 * Peek at the first element in the heap, but don't remove it
 * @return the first element in the heap
 */
    public T peek() {
	return heap[0];
    }
    
/**
 * Remove the first element from the heap
 * @return the highest-priority element from the heap
 */
    public T remove() {
	if(isEmpty()) // check that the heap isn't empty
	    throw new NoSuchElementException("The heap is empty.");
        T t = heap[0]; // get the first element
	currentSize--;
        heap[0] = heap[currentSize]; // re-heap
        heapDown(0);
        return t;
    }
    
/**
 * Return the number of elements in the heap
 * @return the number of elements in the heap
 */
    public int size() {
        return currentSize;
    }
    
/**
 * Check if the heap is empty
 * @return true if the heap is empty
 */
    public boolean isEmpty() {
        return currentSize == 0;
    }
    
/**
 * Check if the heap is full
 * @return true if the heap is full
 */
    public boolean isFull() {
	return currentSize == capacity;
    }

/**
 * Return the heap as an array (useful for debugging)
 * @return the heap
 */
    public T[] getHeap() {
	return heap;
    }
}