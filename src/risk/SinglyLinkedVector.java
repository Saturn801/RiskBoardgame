package risk;

public class SinglyLinkedVector implements Vector {
	
	//I have changed the Node class to implement the type <T>
	private Node first=null;  
	private Node temp;
	private Node newNode;
	private int size = 0;
	
	public int size(){ // This just returns our count of size.
		return size;
	}
	public boolean isEmpty(){	// Returns true if empty, or false if not.
		return size==0;
	}
	public int elemAtRank(int rank){
		if(rank+1>size) throw new VectorEmptyException();	// If we try to access a rank that is empty, throw exception.
		int i=0;
		while(i<=rank){	// We cycle through the nodes until we get to the one at the given rank.
			if(i==0)
				temp=first;
			else
				temp=temp.getNext();
			i++;
		}
		return temp.getValue();		// Then we return it's value.
	}
	public int replaceAtRank(int rank, int element){
		if(rank+1>size) throw new VectorEmptyException();	// If we try to access a rank that is empty, throw exception.
		int i=0, j=0;
		int result=first.getValue();
		newNode = new Node(element, null);
		while(i<=rank){
			if(rank==0){
				newNode.setNext(first.getNext());	// If rank is 0, we just replace the first element and update next.
				first=newNode;
			}
			else{
				while(j<=rank){		// Otherwise we cycle through to the element at given rank to get it's values for return and to update the new nodes next.
					if(j==0)
						temp=first;
					else
						temp=temp.getNext();
					j++;
				}
				result = temp.getValue();
				newNode.setNext(temp.getNext());
				j=0;
				while(j<rank){
					if(j==0)
						temp=first;
					else
						temp=temp.getNext();
					j++;
				}
				temp.setNext(newNode);		// We then cycle through to the node before the given rank and update it's next value to the new node.
			}
			i++;
		}
		return result;		// Finally we return result.
	}
	public void insertAtRank(int rank, int element){
		if(rank>size) throw new VectorEmptyException();	// Since we can insert a new node at the same rank as size, the check for exception is slightly different than the others.
		int i=0;
		newNode = new Node(element, null);
		if(size==0){
			first=newNode;	// If the linked list is empty, we just set first to the new node.
		}
		else if(rank==0){
			newNode.setNext(first);	// If the given rank is 0, we update the new node with the current first nodes next value, and then update first to the new node.
			first=newNode;
		}
		else if(size==rank){	// If we want to insert the new node after the final node, we cycle through to
			while(i<rank){		// the last one we currently have, and update its next value to the new node.
				if(i==0)
					temp=first;
				else
					temp=temp.getNext();
				i++;
			}
			temp.setNext(newNode);
		}
		else{					// If there is another node at the rank we wish to insert at, we first cycle 
			while(i<=rank){		// through to that node, and give its next value to our new node.
				if(i==0)
					temp=first;
				else
					temp=temp.getNext();
				i++;
			}
			newNode.setNext(temp);
			i=0;
			while(i<rank){		// After that, we cycle through to the node before it, and update its next
				if(i==0)		// value to our new node.
					temp=first;
				else
					temp=temp.getNext();
				i++;
			}
			temp.setNext(newNode);
		}
		size++;		// We increment size.
	}
	public int removeAtRank(int rank){
		if(rank+1>size) throw new VectorEmptyException();	// If we try to remove a rank that is empty, throw exception.
		int i=0;
		int result=first.getValue();	
		if(size==1){
			first=null;		// If there was only one element in the linked list, we set first to null, as it is
		}					// now empty.
		else if(rank==0){
			first=first.getNext();	// If we wish to remove the first element, we set first to the current firsts next value.
		}
		else{					// Otherwise, we cycle through to the node before the one we wish to remove,
			while(i<rank){		// and we update it's next value to the next value of the node we're removing.
				if(i==0)
					temp=first;
				else
					temp=temp.getNext();
				i++;
			}
			result=temp.getNext().getValue();
			temp.setNext(temp.getNext().getNext());
		}
		size--;			// We decrement size and return the value of the node we removed.
		return result;
	}
	
	public void initialize(int i){	// Initialises the vector with i integers with each value corresponding to the rank to start off with.
		for(int j=0;j<i;j++){		// So if i was 10, the vector would have 10 elements which would be 0,1,2,3,4,5,6,7,8,9.
			insertAtRank(j, j);
		}
	}
	
	public int countOccurences(int check) {
		int count=0;
		if(size==0){
			return 0;
		}
		else{	
			temp=first;
			if(temp.getValue()==check)
				count++;
			while(temp.getNext()!=null){	
				temp=temp.getNext();
				if(temp.getValue()==check)
					count++;
			}
		}
		return count;
	}
	
	public String toString(){
		String output = "";
		output=output+size + ": ";
		int i=0;
		while(i<size){			// We add the value of each node we have to an empty string.
			output= output + elemAtRank(i) + " ";
			i++;
			}
		return output;		// We return the string output.
	}
	
	public static void main(String[] args) { // We use this to check whether the class works properly.

		SinglyLinkedVector vector = new SinglyLinkedVector();
		int i;
		vector.initialize(10);
		vector.insertAtRank(10, 7);
		vector.insertAtRank(11, 7);
		System.out.println(vector);
		i = vector.countOccurences(7);
		System.out.println(i);
	}
}
