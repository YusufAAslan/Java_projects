import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class RegularUser implements Serializable{

    private String name;
    private String surname;
    private int age;
    private String email;
    private String password;

	private SkipList<String> temp = new SkipList<>();
	public ArrayList<Movie> data = new ArrayList<>();
	private List<String> fav = new LinkedList<>();
	private AVLTree<String> buffer = new AVLTree<>();

	public RegularUser()
	{
		name = "NoNameYet";
		surname= "NoSurNameYet";
		age = -1;
		email = "NoEmailYet";
		password = "NoPasswordYet";
	}

	public RegularUser(String name,String surname,int age,String email,String password,ArrayList<Movie> data)
	{
		this.name = name;
		this.data = data;
		this.surname = surname;
		this.age = age;
		this.email = email;
		this.password = password;

		temp = new SkipList<>();
		data = new ArrayList<>();
		fav = new LinkedList<>();
		buffer = new AVLTree<>();
	}


    
	/** 
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/** 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/** 
	 * @return String
	 */
	public String getSurname() {
		return surname;
	}
	
	/** 
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	/** 
	 * @return int
	 */
	public int getAge() {
		return age;
	}
	
	/** 
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/** 
	 * @return String
	 */
	public String getEmail() {
		return email;
	}
	
	/** 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/** 
	 * @return String
	 */
	public String getPassword() {
		return password;
	}
	
	/** 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
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
		if(movie.equals("null"))
		throw new NullPointerException();
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
	 * @return ArrayList<Movie>
	 */
	public ArrayList<Movie> getData()
	{
		return data;
	}

	
	/** 
	 * @param temper
	 */
	public void setData(ArrayList<Movie> temper)
	{	
		data = temper;
	}
	
	
	/** 
	 * @param movie
	 * @return AVLTree<String>
	 */
	public AVLTree<String> get_history()
	{	seeHist(buffer.root);
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

}
