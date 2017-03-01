package sda;

import java.util.LinkedList;

import javax.print.attribute.Size2DSyntax;

public class DataStructures {

	public static void main(String[] args) {
		
	}
	
	static {
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		assert stack.pop().equals(4);
		assert stack.pop().equals(3);
		assert stack.pop().equals(2);
		assert stack.pop().equals(1);
		try {
			stack.pop();
			assert false;
		} catch (Exception e) {
			assert true; // musi tutaj wyladowac
		}
	}

	public static class Stack{
	    private Node first = null;
	    public boolean isEmpty(){
	        return first == null;
	    }
	    public Object pop() {
	        if(isEmpty()) 
	            throw new RuntimeException("Can't Pop from an empty Stack.");
	        else{
	            Object temp = first.value;
	            first = first.next;
	            return temp;
	        }
	    }
	    public void push(Object o){
	        first = new Node(o, first);
	    }
	    class Node{
	        public Node next;
	        public Object value;
	        public Node(Object value){
	            this(value, null); 
	        }
	        public Node(Object value, Node next){
	            this.next = next;
	            this.value = value;
	        }
	    }
	}
	
	static {
		Access<Integer> ar = new Arr<Integer>();
		ar.add(1337);
		// dodawanie elementu wymaga alokacji 
		assert ar.getSteps()>=1;
		
		int added=ar.add(new Integer[]{1, 2, 10, 13, 71, 81, 134});
		// Dodawanie listy wymaga alokacji i przypisań
		assert ar.getSteps()>=added*added; // można lepiej?
		
		assert ar.size()==added+1;
		// sprawdzenie jest szybkie
		assert ar.getSteps()==0;

		assert ar.remove(ar.size()-1) == 134;
		// usuwanie ostatniego elementu moze byc szybkie, jesli nie zwalnia pamieci
		assert ar.getSteps()==0;

		ar.add(new Integer[]{12, 14}); ar.getSteps();
		
		assert ar.remove(0) == 1337;
		// jednak usuniecie pierwszego elementu jest kosztowne
		assert ar.getSteps()>=added;
	}

	@SuppressWarnings("unchecked")
	static class Arr<T> implements Access<T> {
		public int steps;
		private T[] data;
		public int getSteps() {
			int r = steps; 
			steps = 0; 
			return r;
		}
		public Arr() {
			this.data = (T[]) new Object[0];
		}
		public T add(T e) {
			T[] data2 = (T[]) new Object[this.data.length+1];
			steps+=this.data.length+1;
			for (int j = 0; j < this.data.length; j++) {
				data2[j] = this.data[j];
				++steps;
			}
			data2[data2.length-1] = e;
			++steps;
			data = data2;
			return data2[data2.length-1];
		}
		public int add(T[] ar) {
			for (T t : ar) {
				this.add(t);
			}
			return ar.length;
		}
		public T get(int n) {
			++steps;
			return data[n];
		}
		public int size() {
			return data.length;
		}
		public T remove(int n) {
			T elem = data[n];
			for (int i = n; i < data.length-2; i++) {
				data[i] = data[i+1];
				++steps;
			}
			return elem;
		}
		
	}
	

	interface Access<T> {
		T add(T e);
		int add(T[] ar);
	
		int size();

		T popFront();
		T popBack();
		T pollFirst();
		T pollLast();

		T get(int n);
		T remove(int n);
		int getSteps();
	}
	
	
	static {
	    Li<Integer> li = new Li<Integer>();
	    li.add(1337);
	    assert li.getSteps()==1; // dodawanie elementu jest szybkie 
	    
	    int added=li.add(new Integer[]{1, 2, 10, 13, 71, 81, 134});
	    // dodawanie wielu - rownież
	    assert li.getSteps()<=added;
	
	    assert li.size()==added+1;
	    // sprawdzenie jest wolne (przechodzi przez wszystkie elementy)
	    assert li.getSteps()>=added;

	    assert li.pollFirst() == 1337;
	    assert li.getSteps() == 1; // uzyskanie pierwszego elementu jest szybkie
	    
	    assert li.pollLast() == 134;
	    assert li.getSteps()==1; // uzyskanie ostatniego - również
	
	    li.add(new Integer[]{12, 14}); li.getSteps(); // dodanie elementu, zerowanie licznika
	    
	    assert li.get(5) == 71;
	    assert li.getSteps()>=5; // dodanie do n-tego elementu - kosztowne

	    assert li.popBack() == 134;
	    assert li.getSteps()==1; // usuwanie pierwszego - też
	    
	    assert li.popFront() == 1337;
	    assert li.getSteps() == 1; // usunięcie pierwszego
	}
	
	static class Li<T> {
		int steps;
		T head, tail;
		
		public int getSteps() {
			int r = steps; 
			steps = 0; 
			return r;
		}
	
		public T add(T e) {
			return e;
			
		}
	
		public int add(T[] ar) {
			for (T t : ar) {
				this.add(t);
			}
			return ar.length;
		}
	
		public T get(int n) {
			return null;
		}
	
		public int size() {
			return 0;
		}
	
		public T remove(int n) {
			return null;
		}

		public T popBack() {
			return null;
		}
		
		public T popFront() {
			return null;
		}

		T pollFirst() {
			return this.head;
		}
		
		T pollLast() {
			return this.head;
		}
	}

}
