package risk;

public interface Vector {
	public int size();
	public boolean isEmpty();
	public int elemAtRank(int rank);
	public int replaceAtRank(int rank, int element);
	public void insertAtRank(int rank, int element);
	public int removeAtRank(int rank);
	public void initialize(int i);
	public int countOccurences(int i);
}
