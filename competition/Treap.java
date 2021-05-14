package competition;

public class Treap {

}

class Node {
	int key; // ��忡 ����� ����
	int priority; // ����� �켱 ����
	int size; // �� ��带 ��Ʈ�� �ϴ� ����Ʈ���� ũ��
	Node left, right; // ���� �ڽİ� ������ �ڽ�
	
	public Node(int k) { key = k; priority = (int)(Math.random() * 100000); size = 1; }
	
	void setLeft(Node newLeft) { left = newLeft; calcSize(); }
	
	void setRight(Node newRight) { right = newRight; calcSize(); }
	
	void calcSize() {
		size = 1;
		if (left != null) size += left.size;
		if (right != null) size += right.size;
	}
	
	// root�� ��Ʈ�� �ϴ� Ʈ���� key�̸��� ����, key�̻��� ���� ���� �� Ʈ���� �и��Ѵ�
	private static Node[] split(Node root, int key) {
		if (root == null) return new Node[2];
		// root�� key �̸��̸� key���� ���ų� ū ���Ұ� ���� �� �ִ� �������� �ɰ���
		if (root.key < key) {
			Node[] pair = split(root.right, key);
			root.right = pair[0];
			return new Node[] { root, pair[1] };
		}
		// root�� key�̻��̶�� key���� ���� ���Ұ� ���� �� �ִ� ������ �ɰ���
		Node[] pair = split(root.left, key);
		root.left = pair[1];
		return new Node[] { pair[0], root };
	}
	
	// root�� ��Ʈ�� �ϴ� Ʈ���� �� ��� node�� ������ �� ��� Ʈ���� ��Ʈ�� ��ȯ
	static Node insert(Node root, Node node) {
		if (root == null) return node;
		// node�� root�� ��Ʈ�� ��ü�ؾ� �ϴ� ��� node.key�������� ������ �ɰ���
		if (root.priority < node.priority) {
			Node[] pair = split(root, node.key);
			node.setLeft(pair[0]); node.setRight(pair[1]);
			return node;
		} else if (root.key < node.key) {
			root.setLeft(insert(root.left, node));
		} else {
			root.setRight(insert(root.right, node));
		}
		return root;
	}
	
	// max(a) < min(b)�� �� �� ���� ��ģ Ʈ���� ��ȯ
	private static Node merge(Node a, Node b) {
		if (a == null) return b; if (b == null) return a;
		if (a.priority < b.priority) {
			b.setLeft(merge(a, b.left));
			return b;
		}
		a.setRight(merge(a.right, b));
		return a;
	}
	
	// root�� ��Ʈ�� �ϴ� Ʈ������ key�� ����� ��� Ʈ���� ��Ʈ�� ��ȯ
	static Node erase(Node root, int key) {
		if (root == null) return root;
		// root�� ����� �� ����Ʈ���� ��ģ�� ��ȯ
		if (root.key == key) return merge(root.left, root.right);
		if (key < root.key) root.setLeft(erase(root.left, key));
		else root.setRight(erase(root.right, key));
		return root;
	}
	
	// root�� ��Ʈ�� �ϴ� Ʈ������ k��° ���Ҹ� ��ȯ�Ѵ�
	static Node kth(Node root, int k) {
		int leftSize = 0;
		if (root.left != null) leftSize = root.left.size;
		if (k <= leftSize) return kth(root.left, k);
		if (k == leftSize + 1) return root;
		return kth(root.right, k - leftSize - 1);
	}
	
	// root�� ��Ʈ�� �ϴ� Ʈ������ key���� ���� ���� ���� ����� ���� ��ȯ
	static int countLessThan(Node root, int key) {
		if (root == null) return 0;
		if (root.key >= key) return countLessThan(root.left, key);
		int leftSize = root.left == null ? 0 : root.left.size;
		return leftSize + 1 + countLessThan(root.right, key); 
	}
	
}