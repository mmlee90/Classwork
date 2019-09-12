import java.io.*;
import java.util.Scanner;
/**
 * Creates a binary tree which holds AnyType objects. All walks are from right to left. Each node keeps 
 * track of lastChild and leftSibling.
 */
class BinTreeNode<AnyType>{
  private AnyType element;
  private BinTreeNode lastChild;
  private BinTreeNode leftSibling;
 /**
   * Constructor for the BinTreeNode with a given node (element),lastChild, and leftSibling.
   * 
   * @param element represents values or elements of type AnyType
   * @param lastChild right most child
   * @param leftSibling sibling to the left of the lastChild
   * @return BinTreeNode
   */
  public BinTreeNode(AnyType element, BinTreeNode<AnyType> lastChild, BinTreeNode<AnyType> leftSibling){
    this.element = element;
    this.lastChild = lastChild;
    this.leftSibling = leftSibling;  
  }
  /**
   * Constructor for the BinTreeNode with a given node.
   * 
   * @param el the node
   * @return BinTreeNode
   */
  public BinTreeNode(AnyType el){
    this.element = el;
  }
  /**
   * Returns the height of the tree rooted at t.
   * 
   * @param t root of the tree
   * @return the heignt
   */
  public static int height(BinTreeNode<?> t){
    int depth = 0;
    if(t == null)
      return -1;
    
    return -1;
  }
  /**
   * Returns the size of the tree rooted at t.
   * 
   * @param t the root
   * @return size of the tree
   */
  public static int size(BinTreeNode<?> t){
    return 0;
  }
  /**
   * Returns a String representation of a pre-order walk on a binary tree rooted at this node.
   * 
   * @return string
   */
  public String toStringPreOrder(){
    return "";
  }
  /**
   * Returns a String representation of a post-order walk on a binary tree rooted at this node.
   * 
   * @return string
   */
  public String toStringPostOrder(){
    return "";
  }
  /**
   * Returns a String representation of level-order walk on the binary tree rooted at this node.
   * 
   * @return string
   */
  public String toStringLevelOrder(){
    return "";
  }
  /**
   * Returns a String representation of a pre-order walk on the m-ary tree rooted at this node.
   * 
   * @return string
   */
  public String toStringPreOrderMTree(){
    return "";
  }
  /**
   * Returns a String representation of a post-order walk on the m-ary tree rooted at this node.
   * 
   * @return string
   */
  public String toStringPostOrderMTree(){
    return "";
  }
  /**
   * Returns a String representation of a level-order walk on the m-ary tree rooted at this node.
   * 
   * @return string
   */
  public String toStringLevelOrderMTree(){
    return "";
  }
   /**
   * Removes the node at the given element only if the node is a leaf.
   * 
   * @param root the root of the tree with the leaf to be removed
   * @param valueToRemove element to be removed
   * @return true if node is removed successfully and false if removal not possible
   */
  public static <AnyType extends Comparable<AnyType>> boolean leafRemove(BinTreeNode<AnyType> root, AnyType valueToRemove){
  
    return false;
  }
  
  
  



























}