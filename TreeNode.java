package tree.bst;

public class TreeNode {
	TreeNode left;
	TreeNode right;
	TreeNode parent;
	int val;
	public TreeNode(int x){
		this.val = x;
	}
	
	public int getVal(){
		return this.val;
	}
	
}
