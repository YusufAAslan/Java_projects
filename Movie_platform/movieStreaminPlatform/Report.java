public class Report {
	
	private RegularUser reporting_user;
	private RegularUser reported_user;
	private Comment reported_comment;
	private Movie commented_movie;
	
	Report(){
		
	}
	Report(RegularUser reporting_user, RegularUser reported_user, Comment reported_comment){
		this.setReporting_user(reporting_user);
		this.setReported_user(reported_user);
		this.setReported_comment(reported_comment);
	}

	
	/** 
	 * @return RegularUser
	 */
	public RegularUser getReporting_user() {
		return reporting_user;
	}

	
	/** 
	 * @param reporting_user
	 */
	public void setReporting_user(RegularUser reporting_user) {
		this.reporting_user = reporting_user;
	}

	
	/** 
	 * @return RegularUser
	 */
	public RegularUser getReported_user() {
		return reported_user;
	}

	
	/** 
	 * @param reported_user
	 */
	public void setReported_user(RegularUser reported_user) {
		this.reported_user = reported_user;
	}

	
	/** 
	 * @return Comment
	 */
	public Comment getReported_comment() {
		return reported_comment;
	}

	
	/** 
	 * @param reported_comment
	 */
	public void setReported_comment(Comment reported_comment) {
		this.reported_comment = reported_comment;
	}

	
	/** 
	 * @return Movie
	 */
	public Movie get_commented_movie(){
		return commented_movie;
	}

	
	/** 
	 * @param commented_movie
	 */
	public void set_commented_movie(Movie commented_movie){
		this.commented_movie = commented_movie;
	}
}