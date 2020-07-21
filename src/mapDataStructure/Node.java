package mapDataStructure;

public class Node<K extends Comparable<K>, V> {
	private K key;
	private V value;
	private Node<K, V> leftChild;
	private Node<K, V> rightChild;	

	public Node(K key, V value) {
		this.key = key;
		this.value = value;
		this.leftChild = null;
		this.rightChild = null;
	}	

	@Override
	public String toString() {
		String returnString = "Key:" + this.key + "; Value:" + this.value;
		return String.valueOf(returnString);
	}

	public int compareTo(Node<K,V> n) {
		return this.key.compareTo(n.getKey());
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public Node<K, V> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node<K, V> leftChild) {
		this.leftChild = leftChild;
	}

	public Node<K, V> getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node<K, V> rightChild) {
		this.rightChild = rightChild;
	}
	
}
