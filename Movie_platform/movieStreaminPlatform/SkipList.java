import java.io.Serializable;
import java.util.Random;

public class SkipList<T extends Comparable<? super T>> implements SkippableList<T>, Serializable{

    private final SkipNode<T> head = new SkipNode<>(null);
    private final Random rand = new Random();
    protected boolean flag = false;
  
    /** 
     * @param data
     */
    @Override
    public void insert(T data) {
      SkipNode<T> skipNode = new SkipNode<>(data);
      for (int i = 0; i < LEVELS; i++) {
        if (rand.nextInt((int) Math.pow(2, i)) == 0) {
          //insert with prob = 1/(2^i)
          insert(skipNode, i);
        }
      }
    }
  

    /** 
     * @param target
     * @return boolean
     */
    @Override
    public boolean delete(T target) {
      System.out.println("Deleting " + target);
      SkipNode<T> victim = search(target, true);
      if (victim == null) return false;
      victim.data = null;
  
      for (int i = 0; i < LEVELS; i++) {
        head.refreshAfterDelete(i);
      }
  
      System.out.println("deleted...");
      return true;
    }
  
    
    /** 
     * @param data
     * @return SkipNode<T>
     */
    @Override
    public SkipNode<T> search(T data) {
      return search(data, true);
    }
  
    @Override
    public void print() {
      for (int i = LEVELS-1; i >= 0 ; i--) {
        head.print(i);
      }
      System.out.println();
    }
  
    
    /** 
     * @param SkipNode
     * @param level
     */
    private void insert(SkipNode<T> SkipNode, int level) {
      head.insert(SkipNode, level);
    }
  
    
    /** 
     * @param data
     * @param print
     * @return SkipNode<T>
     */
    private SkipNode<T> search(T data, boolean print) {
      SkipNode<T> result = null;
      for (int i = LEVELS-1; i >= 0; i--) {
        if ((result = head.search(data, i, print)) != null) {
          if (print) {
            System.out.println("Found " + data.toString() + " at level " + i + ", so stopped" );
            System.out.println();
            flag = true;
          }
          break;
        }
      }
  
      return result;
    }
  
  
  }