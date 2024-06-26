import java.util.Queue;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private class Node {

		private Key key;
		private Value val;
		private Node left, right;
		private int count;
		private boolean color;

		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		}
	}

	private Node root;

	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null) {
			return new Node(key, val);
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = put(x.left, key, val);
		} else if (cmp > 0) {
			x.right = put(x.right, key, val);
		} else if (cmp == 0) {
			x.val = val;
		}
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		/*if (x == null) {
			return 0;
		}
		return x.count;*/
		return (x == null) ? 0 : x.count;
	}

	public int rank(Key key) {
		return rank(key, root);
	}

	private int rank(Key key, Node x) {
		if (x == null) {
			return 0;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			return rank(key, x.left);
		} else if (cmp > 0) {
			return 1 + size(x.left) + rank(key, x.right);
		} else if (cmp == 0) {
			return size(x.left);
		}
		return 0;
	}

	public Value get(Key key) {
		Node x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0) {
				x = x.left;
			} else if (cmp > 0) {
				x = x.right;
			} else if (cmp == 0) {
				return x.val;
			}
		}
		return null;
	}

	public void delete(Key key) {
		root = delete(root, key);
	}

	private Node delete(Node x, Key key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = delete(x.left, key);
		} else if (cmp > 0) {
			x.right = delete(x.right, key);
		} else {
			if (x.right == null) {
				return x.left;
			}
			if (x.left == null) {
				return x.right;
			}
			Node t = x;
			//x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.count = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node x) {
		if (x.left == null) {
			return x.right;
		}
		x.left = deleteMin(x.left);
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}

	public Key floor(Key key) {
		Node x = floor(root, key);
		/*if (x == null) {
			return null;
		}
		return x.key;*/
		return (x == null) ? null : x.key;
	}

	private Node floor(Node x, Key key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp == 0) {
			return x;
		}
		if (cmp < 0) {
			return floor(x.left, key);
		}
		Node t = floor(x.right, key);
		if (t != null) {
			return t;
		} else {
			return x;
		}
	}

	private boolean isRed(Node x) {
		if (x == null) {
			return false;
		}
		return x.color == RED;
	}

	private Node rotateLeft(Node h) {
		assert isRed(h.right);
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}

	private Node rotateRight(Node h) {
		assert isRed(h.left);
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}

	private void flipColors(Node h) {
		assert !isRed(h);
		assert isRed(h.left);
		assert isRed(h.right);
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}

	public Iterable<Key> iterator() { /* see next slides */

		Queue<Key> q = null;//new Queue<Key>();
		inorder(root, q);
		return q;
	}

	private void inorder(Node x, Queue<Key> q) {
		if (x == null) {
			return;
		}
		inorder(x.left, q);
		//q.enqueue(x.key);
		inorder(x.right, q);
	}

}
