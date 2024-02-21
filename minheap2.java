import java.util.Arrays;

public class Main {
    public static class MinHeap<T extends Comparable<T>> {
        private Object[] heap;
        private int size;
        
        public MinHeap(int capacity) {
            heap = new Object[capacity];
            size = 0;
        }
        
        public MinHeap(T[] array) {
            size = array.length;
            heap = Arrays.copyOf(array, size);
            for (int i = getParent(size - 1); i >= 0; i--) {
                heapify(i);
            }
        }
        
        public void insert(T value) {
            if (size >= heap.length) {
                resizeHeap();
            }
            heap[size++] = value;
            bubbleUp(size - 1);
        }
        
        public T peek() {
            if (size == 0) {
                throw new IllegalStateException("Heap is empty");
            }
            return (T) heap[0];
        }
        
        public T pop() {
            T min = peek();
            heap[0] = heap[size - 1];
            size--;
            heapify(0);
            return min;
        }
        
        private void heapify(int i) {
            int left = getLeftChild(i);
            int right = getRightChild(i);
            int smallest = i;
            if (left < size && compareValues(left, i) < 0) {
                smallest = left;
            }
            if (right < size && compareValues(right, smallest) < 0) {
                smallest = right;
            }
            if (smallest != i) {
                swapValues(i, smallest);
                heapify(smallest);
            }
        }
        
        private void bubbleUp(int i) {
            while (i != 0 && compareValues(i, getParent(i)) < 0) {
                swapValues(i, getParent(i));
                i = getParent(i);
            }
        }
        
        private void resizeHeap() {
            heap = Arrays.copyOf(heap, size * 2);
        }
        
        private int getParent(int i) {
            return (i - 1) >>> 1;
        }
        
        private int getLeftChild(int i) {
            return (i << 1) + 1;
        }
        
        private int getRightChild(int i) {
            return (i << 1) + 2;
        }
        
        private void swapValues(int i, int j) {
            Object temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
        
        private int compareValues(int i, int j) {
            return ((T) heap[i]).compareTo((T) heap[j]);
        }
    }
    
    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<>(new Integer[]{9, 5, 7, 1, 3});
        System.out.println("Heap: " + Arrays.toString(heap.heap)); // Heap: [1, 3, 7, 5, 9]
        
        heap.insert(2);
        System.out.println("Inserted 2: " + Arrays.toString(heap.heap)); // Inserted 2: [1, 3, 2, 5, 9, 7, null, null, null, null]
        
        System.out.println("Peek: " + heap.peek()); // Peek: 1
        
        System.out.println("Popped: " + heap.pop()); // Popped: 1
        System.out.println("Heap after pop: " + Arrays.toString(heap.heap)); // Heap after pop: [2, 3, 7, 5, 9, 7, null, null, null, null]
    }
}