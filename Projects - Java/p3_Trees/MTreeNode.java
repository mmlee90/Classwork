import java.io.*;
import java.util.Scanner;
/**
 * Creates a m-ary tree which holds AnyType objects. All walks are from right to left.
 * 
 */
class MTreeNode<AnyType>{
  private AnyType element;
  private int m;
  private ArrayList<MTreeNode> children = new ArrayList<MTreeNode>();
  /**
   * Constructor for the MTreeNode with a given branching factor and an array to hold m or less children.
   * 
   * @param element represents values or elements of type AnyType
   * @param m the branching factor which is 3 in 3-ary trees and 4 in 4-ary trees
   * @param children an array containint a total of m or less children
   */
  public MTreeNode(AnyType element, int m, ArrayList<MTreeNode> children){
    this.element = element;
    this.m = m;
    this.children = children;
  }
  /**
   * Constructor for the MTreeNode with a given branching factor.
   * 
   * @param el represents values or elements of type AnyType
   * @param m the brancing factor which is a 3 in 3-ary trees and a 4 in 4-ary trees
   */
  @SuppressWarnings("unchecked")
  public MTreeNode(AnyType el, int m){
    this.element = el;
    this.m = m;
    this.children = new ArrayList(m);
  }
  /**
   * Returns the height of the tree rooted at t.
   * 
   * @param t root of the tree
   * @return height of tree or -1 if null
   */
  public static int height(MTreeNode<?> t){
    int depth = 0;
    if(t == null)
      return -1;
    if(t.children.size == 0)
        return 0;
    else{
      for(int i = 0; i < t.m; i++){
        if(t.children.get(i) != null){
          if(height(t.children.get(i)) > depth)
            depth = height(t.children.get(i));
        }
      }
      return depth + 1;
    }
  }
  /**
   * Returns the size of the tree rooted at t
   * 
   * @param t root of the tree
   * @return size of tree or 0 if null
   */
  public static int size(MTreeNode<?> t){
    int numNodes = 0;
    if(t == null)
      return 0;
    numNodes++;//node isn't null so there is at least one node
    if(t.children.size == 0)
      return 0;
    else{
      for(int i = 0; i < t.m; i++){
        numNodes = numNodes + size(t.children.get(i));
      }
    }
    return numNodes;
  }
  /**
   * Adds the child to the list of children. 
   * 
   * @param child to be added
   * @return true if child is added, false if the array is full and no more children can be added
   */
  public <AnyType> boolean addChild(MTreeNode<AnyType> child){
    if(this.children.size < m){
      this.children.add(child);
      return true;
    }
    else
      return false;
  }
  /**
   * Returns a String representation of a pre-order walk on the m-ary tree rooted at this node.
   * 
   * @return string representation of pre-order walk
   */
  @SuppressWarnings("unchecked")
  public String toStringPreOrder(){
    ArrayList<MTreeNode> pre = new ArrayList();
    if(this == null)
      return "";
    int nodeCounter = this.children.size;
    pre.add(this);//add root
    nodeCounter--;
    //while(nodeCounter != 0){
      //this.children.get(m-1)
      
    
    //}
    
    
    
    return "";
  }
  /**
   * Returns a String representation of a post-order walk on the m-ary tree rooted at this node.
   * 
   * @return string representation of post-order walk
   */
  public String toStringPostOrder(){
    if(this == null)
      return "";
    ArrayList<MTreeNode> post = new ArrayList<MTreeNode>();
    post.add(this);
    return "";
  }
  /**
   * Returns a String representation of a level-order walk on the m-ary tree rooted at this node.
   * 
   * @return string representation of level-order walk
   */
  public String toStringLevelOrder(){
    return "";
    
  }
  /**
   * ArrayList for type MTreeNode. Contains constructors to create an ArrayList and get() and add().
   * 
   */
  public class ArrayList<MTreeNode>{
    private Object[] arr;
    private int size;
    
    public ArrayList(){
      arr =  new Object[m];
      size = 0;
    }
    public ArrayList(int m){
      arr = new Object[m];
      size = 0;
    }
    @SuppressWarnings("unchecked")
    public MTreeNode get(int index){
      if(index > size)
        throw new ArrayIndexOutOfBoundsException();
      MTreeNode x = (MTreeNode)arr[index];
        return x;  
    }
    public void add(MTreeNode n){
      if(size < arr.length)
        arr[size++] = n;
    }
  }
  /**
   * Accepts a file name and returns the root node of the m-ary tree. Throws an IOException if the file is not found.
   * 
   * @param fileName file that is to be read
   */
  public static MTreeNode<String> createMTree(String fileName) throws IOException{
    
    try{
      BufferedReader b = new BufferedReader(new FileReader(new File(fileName)));
      StringBuilder sb = new StringBuilder();
      String s = b.readLine();
      while(s != null){
        sb.append(s);
        s = b.readLine();
      }
      String string = sb.toString();
      String[] split = string.split(" ");//split each individual sequence of chars into an array
      int i = 0;
      int mCounter = 0;
      int m = 0;
      Object element;
      while(i < split.length){
        if(i == 0){//m
          m = Integer.parseInt(split[i]);
          i++;
          continue;
        }
        if(i == 1){//root
          element = split[i];
          i++;
          continue;
        }
        //ArrayList<MTreeNode> c = MTreeNode((AnyType)element, m);
        while(mCounter < m){
          //c.add(split[i]);
          i++;
        }
      }
      
    }
    catch(IOException e){
      throw new IOException();
    }
    //MTreeNode<AnyType> tree = new MTreeNode<AnyType>(element, m);
    return null;
  }
  /**
   * Accepts a BinTreeNode which is the root of the binary tree and returns the root of the m-ary tree created.
   * 
   * @param broot the root of the binary tree
   * @return root of the m-ary tree
   */
  public static <AnyType> BinTreeNode<AnyType> createBinTree(MTreeNode<AnyType> mroot){
    return null;
  }
  /**
   * Removes the node at the given element only if the node is a leaf.
   * 
   * @param root the root of the tree with the leaf to be removed
   * @param valueToRemove element to be removed
   * @return true if node is removed successfully and false if removal not possible
   */
  public static <AnyType extends Comparable<AnyType>> boolean leafRemove(MTreeNode<AnyType> root, AnyType valueToRemove){
   
    return false;
  }

}