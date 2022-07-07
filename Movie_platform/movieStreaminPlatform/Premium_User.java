import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Premium_User extends RegularUser{
    // private List<Movie> downloaded_movies;
    private List<String> downloaded_movies;
    private List<String> fav = new LinkedList<>();
	private AVLTree<String> buffer = new AVLTree<>();
    private List<Comment> comments;
    private SkipList<String> temp;




    public Premium_User(){
        super();
        // downloaded_movies = new ArrayList<Movie>();
        downloaded_movies = new ArrayList<String>();
		temp = new SkipList<>();
        comments = new ArrayList<Comment>();
    }

    public Premium_User(String name, String surname, int age, String email, String password,ArrayList<Movie> data){
        super(name,surname,age,email,password,data);
        // downloaded_movies = new ArrayList<Movie>(); 
        downloaded_movies = new ArrayList<String>();
		temp = new SkipList<>();
        comments = new ArrayList<Comment>();     
    }

    
	/** 
	 * @return List<String>
	 */
	public List<String> get_downloaded_movies(){
        return downloaded_movies;
    }

    
	/** 
	 * @param mov
	 * @return boolean
	 */
	public boolean add_to_downloaded_movies(String mov){
        downloaded_movies.add(mov);
        return true;
    }

    
	/** 
	 * @param mov
	 * @return boolean
	 */
	public boolean remove_from_downloaded_movies(String mov){
        downloaded_movies.remove(mov);
        return true;
    }

    
	/** 
	 * @return List<Comment>
	 */
	public List<Comment> get_comments(){
        return comments;
    }

    
	/** 
	 * @param com
	 * @return boolean
	 */
	public boolean add_to_comments(Comment com){
        comments.add(com);
		return true;
	}

    
	/** 
	 * @param com
	 * @return boolean
	 */
	public boolean remove_from_comments(Comment com){
        return comments.remove(com);
    }

    
	/** 
	 * @param usr
	 * @return boolean
	 */
	public boolean equals(RegularUser usr){
        return usr instanceof Premium_User && super.equals(usr);
    }

	
	/** 
	 * @return SkipList<String>
	 */
	public SkipList<String> get_watchlist()
	{
		return temp;
	}

	
	/** 
	 * @param movie
	 * @return boolean
	 */
	public boolean add_to_watchlist(String movie)
	{   
		// if(movie.equals("null"))
		// throw new NullPointerException();
		temp.insert(movie);
		return true;
	}

	
	/** 
	 * @param movie
	 * @return boolean
	 */
	public boolean remove_from_watchlist(String movie)
	{
		
		temp.delete(movie);
		if(temp.flag)
		{  
			temp.flag = false;
			return true;
		}

		else
		{  
			temp.flag = false;
			return false;
		}
	}

    
	/** 
	 * @param movie
	 * @return AVLTree<String>
	 */
	public AVLTree<String> get_history()
	{   seeHist(buffer.root);
		return buffer;
	}

	
	/** 
	 * @param movie
	 * @return boolean
	 */
	public boolean add_to_history(String movie)
	{
		buffer.add(movie);
		return true;
	}

	
	/** 
	 * @param movie
	 * @return boolean
	 */
	public boolean remove_from_history(String movie)
	{
		if(buffer.remove(movie))
		return true;
		
		return false;
	}

    
	
	/** 
	 * @return List<String>
	 */
	public List<String> get_favourities()
	{
		return fav;
	}
	
	
	/** 
	 * @param movie
	 * @return boolean
	 */
	public boolean add_to_favourities(String movie)
	{
		fav.add(movie);

		return true;
	}

	
	/** 
	 * @param movie
	 * @return boolean
	 */
	boolean remove_from_favourites(String movie){
		return fav.remove(movie);
	}

    
	/** 
	 * @param str
	 * @return boolean
	 */
	public static boolean isNumeric(String str){
        try{
            int num = Integer.parseInt(str);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
    
	/** 
	 * @param phrase
	 * @return boolean
	 */
	public static boolean containsNum(String phrase){

		char [] letters = phrase.toCharArray();
        for(char ch : letters)
            if(Character.isDigit(ch))
                return true;
        return false;
    }


    
	
	/** 
	 * @param root
	 */
	private void seeHist(BinaryTree.Node<String> root)
	{
		if(root == null)
		return;

		seeHist(root.left);
		seeHist(root.right);
		System.out.println(root.toString());
	}


}