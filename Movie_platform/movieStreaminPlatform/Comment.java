import java.io.Serializable;

/**
 * Comment Class
 * @author mitat
 *
 */
public class Comment implements Serializable {

	private RegularUser user;
	private float rating;
	private String comment_text;
	
	Comment(){
		
	}
	Comment(RegularUser user, float rating, String comment_text){
		this.setUser(user);
		this.setRating(rating);
		this.setComment_text(comment_text);
	}
	
	/** 
	 * @param com
	 * @return boolean
	 */
	boolean equals(Comment com) {
		
		return com.getUser().equals(getUser()) && 
				com.getRating() == getRating() && 
					com.getComment_text().equals(getComment_text());
	}
	
	
	/** 
	 * @return RegularUser
	 */
	public RegularUser getUser() {
		return user;
	}
	
	/** 
	 * @param user
	 */
	public void setUser(RegularUser user) {
		this.user = user;
	}
	
	/** 
	 * @return float
	 */
	public float getRating() {
		return rating;
	}
	
	/** 
	 * @param rating
	 */
	public void setRating(float rating) {
		this.rating = rating;
	}
	
	/** 
	 * @return String
	 */
	public String getComment_text() {
		return comment_text;
	}
	
	/** 
	 * @param comment_text
	 */
	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}
	
	@Override
	public String toString(){
		return getComment_text();
	}
}
