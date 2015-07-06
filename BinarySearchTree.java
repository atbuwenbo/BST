package tree.bst;

import java.util.Random;
import java.util.Stack;

public class BinarySearchTree {
	TreeNode root;

	public void inOrderTraversal(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		StringBuilder str = new StringBuilder();
		TreeNode tmp = root;
		while (tmp != null || !stack.isEmpty()) {
			while (tmp != null) {
				stack.push(tmp);
				tmp = tmp.left;
			}
			tmp = stack.pop();
			str.append(tmp.val + " ");
			tmp = tmp.right;
		}
		System.out.println("InOrder Traversal: " + str);
	}

	/**
	 * Randomly generate a binary search tree(BST) with n nodes.
	 * @param n
	 * 		The number of n
	 * @return
	 * 		Tree root
	 */
	public TreeNode genertateTree(int n) {
		int cur = 0;
		while (cur < n) {
			Random rand = new Random();
			insert(new TreeNode(rand.nextInt(50)));
			cur++;
		}
		return root;
	}

	/**
	 * Find the maximum value of a BST given the root
	 * @param node
	 * 		The root of the tree
	 * @return
	 * 		The node with maximum value
	 */
	public TreeNode maximum(TreeNode node) {
		TreeNode result = node;
		while (result.right != null) {
			result = result.right;
		}
		return result;
	}

	/**
	 * Find the minimum value of a BST given the root
	 * @param node
	 * 		The root of the tree
	 * @return
	 * 		The node with minimum value
	 */
	public TreeNode minimum(TreeNode node) {
		TreeNode result = node;
		while (result.left != null) {
			result = result.left;
		}
		return result;
	}

	public TreeNode successor(TreeNode node) {
		TreeNode result = node;
		if (result.right != null) {
			return minimum(result.right);
		}
		result = node.parent;
		while (result != null && node == result.right) {
			node = result;
			result = result.parent;
		}
		return result;
	}

	public TreeNode predecessor(TreeNode node) {
		TreeNode result = node;
		if (result.left != null) {
			return maximum(result.left);
		}
		result = node.parent;
		while (result != null && node == result.left) {
			node = result;
			result = result.parent;
		}
		return result;
	}

	public void printRoot() {
		System.out.println(root.val);
	}

	public void insert(TreeNode node) {
		TreeNode pointer = null;
		TreeNode tmpRoot = root;
		while (tmpRoot != null) {
			pointer = tmpRoot;
			if (node.val < pointer.val) {
				tmpRoot = tmpRoot.left;
			} else {
				tmpRoot = tmpRoot.right;
			}
		}

		node.parent = pointer;
		if (pointer == null) {
			root = node;
		} else if (node.val < pointer.val) {
			pointer.left = node;
		} else {
			pointer.right = node;
		}
		System.out.println("TreeNode " + node.val + " inserted.");
	}

	public void delete(TreeNode node) {
		if (node.left == null) {
			transplant(node, node.right);
		} else if (node.right == null) {
			transplant(node, node.left);
		} else {
			TreeNode successor = successor(node);
			if (successor.parent != node) {
				transplant(successor, successor.right);
				successor.right = node.right;
			}
			transplant(node, successor);
			successor.left = node.left;
			successor.parent = successor;
		}
	}

	private void transplant(TreeNode n1, TreeNode n2) {
		if (n1.parent == null) {
			root = n2;
		} else if (n1 == n1.parent.left) {
			n1.parent.left = n2;
		} else {
			n1.parent.right = n2;
		}
		if (n2 != null) {
			n2.parent = n1.parent;
		}
	}
}
