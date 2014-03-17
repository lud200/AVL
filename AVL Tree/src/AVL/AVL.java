package AVL;

public class AVL {
	public static void main(String args[]){
		tree t=new tree();
		t.insert(3);
		t.insert(2);
		t.insert(5);
		t.insert(6);
		t.insert(1);
		t.inorder();
	}
}
class Node{
	int data, height;
	Node left, right;
	public Node(){
		data=0;
		height=0;
		left=null;
		right=null;
	}
	public Node(int n){
		left=null;
		right=null;
		data=n;
		height=0;
	}
}
class tree{
	Node root;
	public tree(){
		root=null;
	}
	
	public boolean isEmpty(){
		return root==null;
	}
	
	public void makeEmpty(){
		root=null;
	}
	
	public int height(Node n){
		return n==null?-1:n.height;
	}
	public void insert(int data){
		root=insert(data, root);
	}
	
	public Node insert(int x, Node t){
		if(t==null)
			return new Node(x);
		else if(x<t.data){
			t.left=insert(x, t.left);
			if(height(t.left)-height(t.right)==2)
			{
				if(x<t.left.data)
					t=rotateWithLeft(t);
				else
					t=doublerotatewithleft(t);
			}
			else if(x>t.data){
				t.right=insert(x, t.right);
				if(height(t.right)-height(t.left)==2){
					if(x>t.right.data)
						t=rotateWithRight(t);
					else
						t=doublerotatewithright(t);
				}
			}
			else ;
			t.height=max(height(t.left), height(t.right))+1;	
		}
		return t;		
	}
	public Node rotateWithRight(Node n){
		Node n1=n.right;
		n.right=n1.left;
		n1.left=n;
		n.height=max(height(n.left), height(n.right))+1;
		n1.height=max(height(n1.right), height(n1.left))+1;
		return n1;
	}
	public Node rotateWithLeft(Node n){
		Node n2=n.left;
		n.left=n2.right;
		n2.right=n;
		n.height=max(height(n.left), height(n.right))+1;
		n2.height=max(height(n2.left), n.height)+1;
		return n2;		
	}
	public Node doublerotatewithleft(Node n){
		n.left=rotateWithRight(n.left);
		return rotateWithLeft(n);
	}
	public Node doublerotatewithright(Node n){
		n.right=rotateWithLeft(n.right);
		return rotateWithRight(n);
	}

	private int max(int l, int r) {
		return l>r?l:r;
	}
	
	public int countNodes(){
		return countNodes(root);
	}
	
	private int countNodes(Node n){
		if(n==null)
			return 0;
		else{
			int l=1;
			l=l+countNodes(n.left);
			l=l+countNodes(n.right);
			return l;
		}
	}
	
	public void inorder(){
		inorder(root);
	}
	
	private void inorder(Node n){
		if(n!=null)
		{
			inorder(n.left);
			System.out.println(n.data);
			inorder(n.right);
		}
	}
}