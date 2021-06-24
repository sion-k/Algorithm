package competition;

public class Treap {

}

class Node {
	int key; // 노드에 저장된 원소
	int priority; // 노드의 우선 순위
	int size; // 이 노드를 루트로 하는 서브트리의 크기
	Node left, right; // 왼쪽 자식과 오른쪽 자식
	
	public Node(int k) { key = k; priority = (int)(Math.random() * 100000); size = 1; }
	
	void setLeft(Node newLeft) { left = newLeft; calcSize(); }
	
	void setRight(Node newRight) { right = newRight; calcSize(); }
	
	void calcSize() {
		size = 1;
		if (left != null) size += left.size;
		if (right != null) size += right.size;
	}
	
	// root를 루트로 하는 트리를 key미만의 값과, key이상의 값을 갖는 두 트리로 분리한다
	private static Node[] split(Node root, int key) {
		if (root == null) return new Node[2];
		// root가 key 미만이면 key보다 같거나 큰 원소가 있을 수 있는 오른쪽을 쪼갠다
		if (root.key < key) {
			Node[] pair = split(root.right, key);
			root.right = pair[0];
			return new Node[] { root, pair[1] };
		}
		// root가 key이상이라면 key보다 작은 원소가 있을 수 있는 왼쪽을 쪼갠다
		Node[] pair = split(root.left, key);
		root.left = pair[1];
		return new Node[] { pair[0], root };
	}
	
	// root를 루트로 하는 트리에 새 노드 node를 삽입한 뒤 결과 트리의 루트를 반환
	static Node insert(Node root, Node node) {
		if (root == null) return node;
		// node가 root의 루트를 대체해야 하는 경우 node.key기준으로 반으로 쪼갠다
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
	
	// max(a) < min(b)일 때 이 둘을 합친 트리를 반환
	private static Node merge(Node a, Node b) {
		if (a == null) return b; if (b == null) return a;
		if (a.priority < b.priority) {
			b.setLeft(merge(a, b.left));
			return b;
		}
		a.setRight(merge(a.right, b));
		return a;
	}
	
	// root를 루트로 하는 트리에서 key를 지우고 결과 트리의 루트를 반환
	static Node erase(Node root, int key) {
		if (root == null) return root;
		// root를 지우고 양 서브트리를 합친뒤 반환
		if (root.key == key) return merge(root.left, root.right);
		if (key < root.key) root.setLeft(erase(root.left, key));
		else root.setRight(erase(root.right, key));
		return root;
	}
	
	// root를 루트로 하는 트리에서 k번째 원소를 반환한다
	static Node kth(Node root, int k) {
		int leftSize = 0;
		if (root.left != null) leftSize = root.left.size;
		if (k <= leftSize) return kth(root.left, k);
		if (k == leftSize + 1) return root;
		return kth(root.right, k - leftSize - 1);
	}
	
	// root를 루트로 하는 트리에서 key보다 작은 값을 갖는 노드의 수를 반환
	static int countLessThan(Node root, int key) {
		if (root == null) return 0;
		if (root.key >= key) return countLessThan(root.left, key);
		int leftSize = root.left == null ? 0 : root.left.size;
		return leftSize + 1 + countLessThan(root.right, key); 
	}
	
}