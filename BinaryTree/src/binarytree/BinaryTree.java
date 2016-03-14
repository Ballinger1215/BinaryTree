package binarytree;

/**
 *
 * @author Mark
 */
public class BinaryTree {

    Node root;
    public int numberOfNodes;
    
    public BinaryTree()
            {
               numberOfNodes = 0; 
            }
    
    public static void main(String[] args) 
    {
        BinaryTree tree = new BinaryTree();
        
        tree.add(51);
        tree.add(22);
        tree.add(753);
        tree.add(12);
        tree.add(33);
        tree.add(65);
        tree.add(67);
        tree.add(81);
        tree.add(76);
        tree.add(79);
        tree.add(1);
        tree.add(125);
        
        //tree.delete(1);
        tree.traverseInOrder();
        //tree.traversePreOrder();
        //tree.traversePostOrder();
        tree.count();
        tree.find(81);
        //tree.clear();
        
        
    }
    
    public void add(int data)
    {
        Node nodeToAdd = new Node(data);
        
        if (root == null)
            root = nodeToAdd;
        
        addNode(root, nodeToAdd);
        numberOfNodes++;
    }   
        private void addNode (Node node, Node nodeToAdd)
        {
             if (nodeToAdd.data < node.data)
            {
                if (node.leftChild == null)
                {
                    nodeToAdd.parent = node;
                    node.leftChild = nodeToAdd;
                }else{
                    addNode(node.leftChild, nodeToAdd);
                     }
            }
             else if (nodeToAdd.data > node.data)
             {
               if (node.rightChild == null)
                {
                    nodeToAdd.parent = node;
                    node.rightChild = nodeToAdd;
                }else{
                    addNode(node.rightChild, nodeToAdd);
                }
             }
        }
    public void traverseInOrder() // in-order traversal 
    {
          
        if (root != null) 
        {
          Node nodeToTraverse = root;
          if(nodeToTraverse.leftChild == null && nodeToTraverse.rightChild == null)
          {
              System.out.println(nodeToTraverse.data);
          }else{
                    if(nodeToTraverse.leftChild != null)
                    {
                        inOrderTraversal(nodeToTraverse.leftChild);
                    }
                    if (nodeToTraverse.rightChild != null)
                    {
                        inOrderTraversal(nodeToTraverse.rightChild);
                    }
                }
        } 
    }
     private void inOrderTraversal(Node node) // in-order traversal
    {
          if(node.leftChild != null)
          {
              inOrderTraversal(node.leftChild);
          }
          System.out.println(node.data);
          
          if(node.rightChild != null)
          {
              inOrderTraversal(node.rightChild);
          }
    } 
     
     public void traversePreOrder() //pre-order traversal
    {
        if (root != null)
        {
          Node nodeToTraverse = root;
          if(nodeToTraverse.leftChild == null && nodeToTraverse.rightChild == null)
          {
              System.out.println(nodeToTraverse.data);
          }else{
                    if(nodeToTraverse.leftChild != null)
                    {
                        preOrderTraversal(nodeToTraverse.leftChild);
                    }
                    if (nodeToTraverse.rightChild != null)
                    {
                        preOrderTraversal(nodeToTraverse.rightChild);
                    }
                }
        } 
    }
     private void preOrderTraversal(Node node) //pre-order traversal
    {
         System.out.println(node.data);
         
          if(node.leftChild != null)
          {
              preOrderTraversal(node.leftChild);
          }

          if(node.rightChild != null)
          {
              preOrderTraversal(node.rightChild);
          }
    }
     
     public void traversePostOrder() // post-order traversal 
    {
        if (root != null)
        {
          Node nodeToTraverse = root;
          if(nodeToTraverse.leftChild == null && nodeToTraverse.rightChild == null)
          {
              System.out.println(nodeToTraverse.data);
          }else{
                    if(nodeToTraverse.leftChild != null)
                    {
                        postOrderTraversal(nodeToTraverse.leftChild);
                    }
                    if (nodeToTraverse.rightChild != null)
                    {
                        postOrderTraversal(nodeToTraverse.rightChild);
                    }
                }
        } 
    }
     private void postOrderTraversal(Node node) // post-order traversal
    {
          if(node.leftChild != null)
          {
              postOrderTraversal(node.leftChild);
          }

          if(node.rightChild != null)
          {
              postOrderTraversal(node.rightChild);
          }
          
          System.out.println(node.data);
    }
     
     public boolean delete(int val)
     {
         //single node: node has no children
         //one child: node has one child
         //two children: node has two children
         Node nodeToBeDeleted = find(val);
         
         if(nodeToBeDeleted != null)
         {
             if(nodeToBeDeleted.leftChild == null && nodeToBeDeleted.rightChild == null) //single node: node has no children
             {
             deleteSingleNode(nodeToBeDeleted);
             }
         }
         else if (nodeToBeDeleted.leftChild != null && nodeToBeDeleted.rightChild != null) //two children: node has two children
         {
             deleteTwoChildren(nodeToBeDeleted);
         }
         else if(nodeToBeDeleted.leftChild != null) //one child: where left child should be deleted
                 {
                     deleteOneChild(nodeToBeDeleted);
                 }
         else if(nodeToBeDeleted.rightChild != null) //one child: where right child should be deleted
                 {
                     deleteOneChild(nodeToBeDeleted);
                 }
         return false;
     }
     
     private void deleteOneChild(Node nodeToBeDeleted) //one child: node has one child
     {
        if(nodeToBeDeleted.parent.leftChild == nodeToBeDeleted)
        {
            if(nodeToBeDeleted.leftChild != null)
            {
                nodeToBeDeleted.parent.leftChild = nodeToBeDeleted.leftChild;
            }
            else if(nodeToBeDeleted.rightChild != null)
            {
                nodeToBeDeleted.parent.leftChild = nodeToBeDeleted.rightChild;
            }
            numberOfNodes--;
        }
        else if(nodeToBeDeleted.parent.rightChild == nodeToBeDeleted)
        {
             if(nodeToBeDeleted.leftChild != null)
            {
                nodeToBeDeleted.parent.rightChild = nodeToBeDeleted.leftChild;
            }
            else if(nodeToBeDeleted.rightChild != null)
            {
                nodeToBeDeleted.parent.rightChild = nodeToBeDeleted.rightChild;
            }
             numberOfNodes--;
        }
     }
     
     private void deleteTwoChildren(Node nodeToBeDeleted) //two children: node has two children
     {
         Node minNode = minLeftTraversal(nodeToBeDeleted.rightChild);
         
         deleteOneChild(minNode);
         
         minNode.parent = nodeToBeDeleted.parent;
         minNode.leftChild = nodeToBeDeleted.leftChild;
         minNode.rightChild = nodeToBeDeleted.rightChild;
         
         if (nodeToBeDeleted.parent == null)
         {
             root = minNode;
         }
         else
         {
            if(nodeToBeDeleted.parent.leftChild == nodeToBeDeleted)
            {
                nodeToBeDeleted.parent.leftChild = minNode;
            }
            else if(nodeToBeDeleted.parent.rightChild == nodeToBeDeleted)
            {
                nodeToBeDeleted.parent.rightChild = minNode;
            }
            numberOfNodes--;
         }
     }
     
     private Node minLeftTraversal(Node node)
     {
         if(node.leftChild == null)
         {
             return node;
         }
         return minLeftTraversal(node.leftChild);
     }
     
     private void deleteSingleNode(Node nodeToBeDeleted) //single node: node has no children
     {
         if(nodeToBeDeleted.leftChild == null && nodeToBeDeleted.rightChild == null)
             {
                 //check if the node to be deleted is the left or right CHILD of the PARENT of the node
                 if(nodeToBeDeleted.parent.leftChild == nodeToBeDeleted)
                 {
                     nodeToBeDeleted.parent.leftChild = null;
                 }
                 else if(nodeToBeDeleted.parent.rightChild == nodeToBeDeleted)
                 {
                     nodeToBeDeleted.parent.rightChild = null;
                 }
                 numberOfNodes--;
             }
     }
     
     public Node find(int val)
     {
         if(root != null)
         {
             return findNode(root, new Node(val));
         }
         return null;
     }
     
     private Node findNode(Node search, Node node)
     {
         if(search == null)
             return null;
         
         if(search.data == node.data)
         {
             System.out.println("Node you are refering to for search or deletion is: " + node.data);
             return search;
         }
         else
         {
             Node returnNode = findNode(search.leftChild, node);
             
             if(returnNode == null)
             {
                 returnNode = findNode(search.rightChild, node);
             }
             //System.out.println(returnNode);
             return returnNode;
         }
     }
      
      public int count() {

        System.out.println("number of nodes in the Binary Tree are: " + numberOfNodes);
        return numberOfNodes;
      }

      public void clear(){
        if(numberOfNodes > 0)
        {
            root = null;
        }
    }
}