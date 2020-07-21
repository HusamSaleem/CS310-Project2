package mapDataStructure;

public class Map<K extends Comparable<K>, V> implements MapInterface<K, V> {

	private Node<K, V> root;
	private int size;

	public Map() {
		root = null;
	}

//	public static void main(String[] args) {
//		Map<Integer, Integer> map = new Map<Integer, Integer>();
//
//		System.out.println(map.add(5, 10));
//		System.out.println(map.add(3, 11));
//		System.out.println(map.add(8, 14));
//		System.out.println(map.add(1, 15));
//		System.out.println(map.contains(5));
//		System.out.println(map.contains(1));
//		// System.out.println(map.getKey(14));
//
//		System.out.println(map.toString());
//		System.out.println();
//		System.out.println(map.delete(8));
//
//		System.out.println(map.toString());
//
//	}

	@Override
	public boolean contains(K key) {
		if (root == null || key == null)
			return false;

		Node<K, V> curNode = root;

		while (curNode != null && curNode.getKey().compareTo(key) != 0) {
			if (curNode.getKey().compareTo(key) > 0) {
				curNode = curNode.getLeftChild();
			} else {
				curNode = curNode.getRightChild();
			}
		}

		// Found key
		if (curNode == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean add(K key, V value) {
		// Duplicate key, won't add
		if (this.contains(key))
			return false;

		Node<K, V> newNode = new Node<K, V>(key, value);
		Node<K, V> curNode = root;

		if (root == null) {
			root = newNode;
			size++;
			return true;
		}

		while (true) {
			if (curNode.compareTo(newNode) > 0) {
				if (curNode.getLeftChild() == null) {
					curNode.setLeftChild(newNode);
					size++;
					return true;
				}

				curNode = curNode.getLeftChild();

			} else {
				if (curNode.getRightChild() == null) {
					curNode.setRightChild(newNode);
					size++;
					return true;
				}

				curNode = curNode.getRightChild();
			}
		}
	}

	@Override
	public V delete(K key) {
		if (root == null)
			return null;

		if (!this.contains(key))
			return null;

		// prev is the parent of the curNode
		Node<K, V> prev = null;
		Node<K, V> curNode = root;
		
		V val = null;

		// Find the node
		while (curNode != null && curNode.getKey().compareTo(key) != 0) {
			prev = curNode;
			if (curNode.getKey().compareTo(key) > 0) {
				curNode = curNode.getLeftChild();
			} else {
				curNode = curNode.getRightChild();
			}
		}

		// Case 1: No Children
		if (curNode.getLeftChild() == null && curNode.getRightChild() == null) {
			if (curNode != root && prev.getLeftChild().getKey().compareTo(key) == 0) {
				val = curNode.getValue();
				prev.setLeftChild(null);
			} else if (curNode != root && prev.getRightChild().getKey().compareTo(key) == 0) {
				val = curNode.getValue();
				prev.setRightChild(null);
			} else {
				root = null;
			}
		}
		// Case 2: 2 Children
		else if (curNode.getLeftChild() != null && curNode.getRightChild() != null) {
			// Find the in-order successor and delete it
			val = curNode.getValue();
			Node<K, V> temp = minValue(curNode.getRightChild());
			delete(temp.getKey());

			curNode.setKey(temp.getKey());
			curNode.setValue(temp.getValue());
		}
		// Case 3: 1 Child
		else {
			if (curNode != root && curNode.getLeftChild() != null) {
				val = curNode.getValue();
				prev.setLeftChild(curNode.getLeftChild());
			} else if (curNode != root && curNode.getRightChild() != null) {
				val = curNode.getValue();
				prev.setRightChild(curNode.getRightChild());
			} else {
				val = curNode.getValue();
				root = (curNode.getLeftChild() != null) ? curNode.getLeftChild() : curNode.getRightChild();
			}
		}

		return val;
	}

	// Find the smallest node by key
	private Node<K, V> minValue(Node<K, V> node) {
		Node<K, V> minv = node;
		while (node.getLeftChild() != null) {
			minv = node.getLeftChild();
			node = node.getLeftChild();
		}
		return minv;
	}

	private String printInOrder(Node<K, V> node, String str) {
		if (node != null) {
			str = printInOrder(node.getLeftChild(), str);
			str += node.toString() + "\n";
			str = printInOrder(node.getRightChild(), str);
		}

		return str;
	}

	@Override
	public V getValue(K key) {
		Node<K, V> curNode = root;
		V val;

		while (curNode != null && curNode.getKey().compareTo(key) != 0) {
			if (curNode.getKey().compareTo(key) > 0) {
				curNode = curNode.getLeftChild();
			} else {
				curNode = curNode.getRightChild();
			}
		}

		if (curNode != null) {
			val = curNode.getValue();
			return val;
		}

		return null;
	}

	K keyTemp;

	@Override
	public K getKey(V value) {
		getKey(value, this.root);

		return keyTemp;
	}

	public void getKey(V value, Node<K, V> node) {
		if (node != null) {
			getKey(value, node.getLeftChild());

			if (node.getValue().equals(value)) {
				keyTemp = node.getKey();
			}

			getKey(value, node.getRightChild());
		}

		return;
	}

	@Override
	public String toString() {
		return this.printInOrder(this.root, new String());
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return (this.size == 0);
	}

	@Override
	public void clear() {
		this.root = null;
	}
}