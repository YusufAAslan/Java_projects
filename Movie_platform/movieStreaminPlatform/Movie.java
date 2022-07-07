import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/** A Movie Class for representing Movie.
 * Comments class has to be implemented!!
 * Essential setters and getters methods have been implemented!
 * Set Structure used!
 *  @author Alperen and Kaan
*/

public class Movie implements Serializable{

  private String genre;
  private String title;
  private String budget;
  private float score;
  private int year;

  protected Set<Comment> comments = new HashSet<Comment>();

  public Movie(){

  }

  public Movie(String budget,String genre,String title, float score,int year)
  {
    this.genre = genre;
    this.budget = budget;
    this.score = score;
    this.title = title;
    this.year= year;
  }

  
  /** 
   * @param tp
   */
  public void addMovieComment(Comment tp)
  {
    comments.add(tp);
  }
  
  
  /** 
   * @param m
   * @return boolean
   */
  public boolean equals(Movie m){
      return false;
  }

  
  /** 
   * @param budget
   */
  public void setBudget(String budget)
  {
    this.budget = budget;
  }
  
  /** 
   * @return String
   */
  public String getBudget()
  {
    return budget;

  }

  
  /** 
   * @param score
   */
  public void setScore(float score)
  {
    this.score = score;
  }

  
  /** 
   * @return float
   */
  public float getScore()
  {
    return score;
  }

  
  /** 
   * @param year
   */
  public void setAge(int year)
  {
    this.year = year;
  }

  
  /** 
   * @return int
   */
  public int getAge()
  {
    return year;
  }
  
  /** 
   * @param gn
   */
  public void set_genre(String gn)
  {
    genre = gn;
  }
  
  /** 
   * @return String
   */
  public String get_genre()
  {
    return genre;
  }
  
  /** 
   * @param gn
   */
  public void set_title(String gn)
  {
    title = gn;
  }
  
  /** 
   * @return String
   */
  public String get_title()
  {
    return title;
  }
}
