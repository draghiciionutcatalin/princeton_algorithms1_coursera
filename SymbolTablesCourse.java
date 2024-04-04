public class SymbolTablesCourse<Key extends Comparable<Key>, Value> {


	void put(Key key, Value val) {
		//put key-value pair into the table
		//(remove key from table if value is null)
	}

	Value get(Key key) {
		//value paired with key
		//(null if key is absent)
		return null;
	}

	void delete(Key key) {
		//remove key (and its value)from table
	}

	boolean contains(Key key) {
		//is there a value paired with key?
		return false;
	}

	boolean isEmpty() {
		//is the table empty?
		return false;
	}

	int size() {
		//number of key-value pairs
		return 0;
	}

	Key min() {
		//smallest key
		return null;
	}

	Key max() {
		//largest key
		return null;
	}

	Key floor(Key key) {
		//largest key less than or equal to key
		return null;
	}

	Key ceiling(Key key) {
		//smallest key greater than or equal to key
		return null;
	}

	int rank(Key key) {
		//number of keys less than key
		return 0;
	}

	Key select(int k) {
		//key of rank k
		return null;
	}

	void deleteMin() {
		//delete smallest key
	}

	void deleteMax() {
		//delete largest key
	}

	int size(Key lo, Key hi) {
		//number of keys in[lo..hi]
		return 0;
	}

	Iterable<Key> keys(Key lo, Key hi) {
		//keys in[lo ..hi], in sorted order
		return null;
	}

	Iterable<Key> keys() {
		//all keys in the table,in sorted order
		return null;
	}
}
