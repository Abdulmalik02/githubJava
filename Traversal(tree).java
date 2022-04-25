package org.datastructure.binarytree;

import java.util.Stack;

/**
 * Illustrates the various traversal algorithms available for a binary tree data structure
 * Every traversal is implemented using recursive and iterative (using stack) algorithms
 * @author skanniah
 * 
 */
public class Traversal {
	
	/**
	 * A recursive solution for preorder traversal of a binary tree
	 * @param node Node object
	 * 
	 */
	public void preOrderTraversalRecursive(Node node) {
		if (node == null) {
			return;
		}
		
		System.out.println(node);
		if (node.getLeft() != null) {
			preOrderTraversalIterative(node.getLeft());
		}
		if (node.getRight() != null) {
			preOrderTraversalIterative(node.getRight());
		}
	}
	
	
	
	/**
	 * An iterative solution using stack for preorder traversal of a binary tree.
	 * Logic:- 
	 * 1) Create a stack
	 * 2) Push root node to stack
	 * 3) While stack not null, pop from stack and add right and left nodes to stack if they are present. 
	 * This ensures that when you pop out from the stack, you are processing left node before the right node
	 * @param node Node Object
	 * 
	 */
	public void preOrderTraversalIterative(Node node) {
		Stack<Node> stack = new Stack<Node>();
		stack.push(node);
		
		while (!stack.isEmpty()) {
			Node poppedNode = stack.pop();
			System.out.println(poppedNode);
			if (poppedNode.getRight() != null) {
				stack.add(poppedNode.getRight());
			}
			
			if (poppedNode.getLeft() != null) {
				stack.add(poppedNode.getLeft());
			}
		}
	}
	
	/**
	 * A recursive solution for inorder traversal of a binary tree
	 * For any given node, process the deepest left node before processing its parent and its right companion. 
	 * Repeat the same until all the nodes of a tree are processed.
	 * @param node Node Object
	 * 
	 */
	public void inOrderTraversalRecursive(Node node) {
		if (node == null) {
			return;
		}
		
		if (node.getLeft() != null) {
			inOrderTraversalRecursive(node.getLeft());
		}
		System.out.println(node);
		if (node.getRight() != null) {
			inOrderTraversalRecursive(node.getRight());
		}
	}
	
	
	/**
	 * An iterative solution for inorder traversal of a binary tree
	 * For any given node, process the left descendants before processing its parent and its right descendants. As the left descendants need to be processed first, 
	 * this in turn means, the left most leaf node will be processed first. So navigate deep down to the left node of the left sub-tree and start from there. 
	 * @param node Node Object
	 * 
	 */
	public void inOrderTraversalIterative(Node node) {
		if (node == null) {
			return;
		}

		Stack<Node> stack = new Stack<Node>();
		// Navigate to the left most node of the left sub-tree. During that process, push all the navigating nodes to the stack.
		while (node != null) {
			stack.push(node);
			node = node.getLeft();
		}
		
		while (!stack.isEmpty()) {
			Node currNode = stack.pop();
			System.out.println(currNode);
			
			/* If the current node has a right child, push it to stack and navigate to the left node of that right child if it exists. 
			Remember we need to process the right sub-tree of any parent node. So, add all left nodes of the right child to stack and 
			keep navigating to left till the leaf node */ 
			if (currNode.getRight() != null) {
				Node rightnode = currNode.getRight();
				stack.push(rightnode);
				if (rightnode.getLeft() != null) {
					Node rightsLeftnode = rightnode.getLeft();
					while (rightsLeftnode != null) {
						stack.push(rightsLeftnode);
						rightsLeftnode = rightsLeftnode.getLeft();
					}
				}
			}
		}
		
	}
	
	/**
	 * A recursive solution for postorder traversal of a binary tree
	 * For any given node, process the left descendants before processing the right descendants and then process the parent as the last step. 
	 * Repeat the same until all the nodes of a tree are processed.
	 * @param node Node Object
	 * 
	 */
	public void postOrderTraversalRecursive(Node node) {
		if (node == null) {
			return;
		}
		
		if (node.getLeft() != null) {
			postOrderTraversalRecursive(node.getLeft());
		}
		
		if (node.getRight() != null) {
			postOrderTraversalRecursive(node.getRight());
		}
		System.out.println(node);
	}
	
	/**
	 * An iterative solution for postorder traversal of a binary tree.
	 * 
	 * On a broad view, there are 2 cases that need to be covered in this algorithm
	 * 1)	If the current node is the parent of the previously processed node (the previously processed node could either be a 
	 * 		left or right child) then it means we are going up the tree. That in turn means we have already processed the child 
	 *		sub-trees of the current node. So, it’s time to process the parent node and go for the next element in the stack.
	 * 2)	The other situations could be: 
	 *		a)	The current node can be the left or right child of the previously processed node. In this case, we are going down the tree. 
	 *		b)	The current node is the root node. 
	 *		c)	The current node can be the right companion of the previously processed node. 
	 *		For all the above usecases, we call the special private method “navigateAndProcessNodesInPostOrder” method. 
	 *		navigateAndProcessNodesInPostOrder  method takes care of pushing the current node, left child and right child (in that order) 
	 *		to stack if the nodes exist. After pushing the nodes, it navigates to the left child (if it exists) because precedence is given 
	 *		to the left node. If not it tries to navigate to the right child. If there are no child nodes then this method just processes 
	 *		it without the need of adding that to stack because it’s a leaf node. “NodeWrapper” object is used as a wrapper object to hold 
	 *		the previous processed node, the current node and the stack.
	 * @param node Node Object
	 * 
	 */
	public void postOrderTraversalIterative(Node node) {
		if (node == null) {
			return;
		}
		
		Stack<Node> stack = new Stack<Node>();
		stack.push(node);
		Node prevNode = null;
		Node currNode = null;
		
		while (!stack.isEmpty()) {
			currNode = stack.pop();
			
			if (currNode.getLeft() == prevNode || currNode.getRight() == prevNode) {
				// If the current node is the parent of previously processed node. Navigating up the tree. So its enough just to process the node
				System.out.println(currNode);
				prevNode = currNode;
			} else {
				// If previously processed node was the parent of current node. i.e currently processing either the left or right child (navigating down).
				// If the node is the root node.
				// If the current node is the leaf node of a sub-tree. In this case previously processed node and current node are same.
				// If the current node being processed is the right companion of the previously processed node
				
				NodeWrapper nodeWrapper = new NodeWrapper(prevNode, currNode, stack);
				nodeWrapper = navigateAndProcessNodesInPostOrder(nodeWrapper);
				prevNode = nodeWrapper.getPrevNode();
				currNode = nodeWrapper.getCurrNode();
				stack = nodeWrapper.getStack();
			}
		}
	}
	
	/**
	 * A helper method for post-order traversal - if given a node, it traverses and processes the sub-tree
	 * NodeWrapper object is used to exchange prev, curr nodes and stack between the calling and called method
	 * @param nodeWrapper
	 * @return
	 */
	private NodeWrapper navigateAndProcessNodesInPostOrder(NodeWrapper nodeWrapper) {
		Node currNode = nodeWrapper.getCurrNode();
		Node prevNode = nodeWrapper.getPrevNode();
		Stack<Node> stack = nodeWrapper.getStack();
		
		Node leftNode = currNode.getLeft();
		Node rightNode = currNode.getRight();
		if (leftNode != null  || rightNode != null) {
			// current node (if its a parent) is the last to be processed and so it has to be pushed to stack first
			stack.push(currNode);
			if (rightNode != null) {
				// If there is a right node, push it to stack
				stack.push(rightNode);
			}
			// If there is a left node, push it to stack
			if (leftNode != null) {
				stack.push(leftNode);
			}
			prevNode = currNode;
			
			// Left takes precedence. If there is a left node, go left else go right 
			if (leftNode != null) {
				currNode = currNode.getLeft();
			} else if (rightNode != null) {
				currNode = currNode.getRight();
			}
		} else { // its a leaf node, so process it
			System.out.println(currNode);
			prevNode = currNode;
		}
		nodeWrapper.setPrevNode(prevNode);
		nodeWrapper.setCurrNode(currNode);
		return nodeWrapper;
	}
	
	 /**
	 * @author skanniah
	 * An inner class NodeWrapper to encompass prevNode, currNode and stack for post-order traversal
	 *
	 */
	class NodeWrapper {
		private Node prevNode;
		private Node currNode;
		private Stack<Node> stack;
		
		NodeWrapper(Node prevNode, Node currNode, Stack<Node> stack) {
			this.prevNode = prevNode;
			this.currNode = currNode;
			this.stack = stack;
		}

		private Node getPrevNode() {
			return prevNode;
		}

		private Node getCurrNode() {
			return currNode;
		}

		private Stack<Node> getStack() {
			return stack;
		}

		private void setPrevNode(Node prevNode) {
			this.prevNode = prevNode;
		}

		private void setCurrNode(Node currNode) {
			this.currNode = currNode;
		}

		private void setStack(Stack<Node> stack) {
			this.stack = stack;
		}
		
	}
	
	public static void main(String[] args) {
		Node root = BinaryTree.createBinaryTree();
		Traversal traversal = new Traversal();
		System.out.println("## Preorder Traversal Recursive ##");
		traversal.preOrderTraversalRecursive(root);
		System.out.println("## Preorder Traversal Iterative ##");
		traversal.preOrderTraversalIterative(root);
		System.out.println("## Inorder Traversal Recursive ##");
		traversal.inOrderTraversalRecursive(root);
		System.out.println("## Inorder Traversal Iterative ##");
		traversal.inOrderTraversalIterative(root);
		System.out.println("## Postorder Traversal Recursive ##");
		traversal.postOrderTraversalRecursive(root);
		System.out.println("## Postorder Traversal Iterative ##");
		traversal.postOrderTraversalIterative(root);
	}
}