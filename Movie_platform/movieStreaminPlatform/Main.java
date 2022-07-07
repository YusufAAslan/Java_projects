import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * Main class to test all the methods of the system 
 * @author Alperen Gülçimen, Hasan Mutlu, Eda Çanakcı, Kaan Uslu, Hacı Hasan Savan  
 * @since 14.06.2022
 */
public class Main {

    protected static ArrayList<RegularUser> listOfRegularUsers;
    protected static ArrayList<Premium_User> listOfPremiumUsers;
	public static ArrayList<Movie> film = new ArrayList<>();
	private static final String USERDB = "userDB.dat";
	private static final String PREUSERDB = "preuserDB.dat";
	private static final String MOVIEDB = "movieDB.dat";
	private static final String REPORTDB = "reportDB.dat";

	private static ArrayList<RegularUser> userDB;
	private static ArrayList<Movie> movieDB;
	private static ArrayList<Report> reports;

	private static PriorityQueue<String> recommendedMovies;

	private static Scanner sc;

	private static int preusr = -1;
	private static int Reg_index = -1;

/**
	 * Main method to start the Movie Streaming Platform 
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		sc = new Scanner(System.in);
		System.out.println("-------------------------------------------");
        System.out.println("    Welcome to Movie Streaming Platform");
        System.out.println("-------------------------------------------");
		initialize();
		update_movieDB();
		oku();
		sortByScore(film);
		openPage();
		
	}

	/**
	 * Method to open page with the options to log in as admin , as user, to create a new account and to terminate
	 * the system. User can select these again and again until he/she choose to exit. Right before terminating the system,
	 * all the changes are saved to their relevant files. 
	 */
	private static void openPage(){
		int select;
		do{
			
			System.out.println("1. Admin Login");
			System.out.println("2. User Login");
			System.out.println("3. Don't Have an Account? Sign Up!");
			System.out.println("4. Exit");
			System.out.println("-------------------------------------------");
			System.out.print("> Select: ");
			select = readInput();
			switch(select){
				case 1:
					cleanScrean();
					adminLogin();
					return;
				case 2:
                	logIn();
					break;
				case 3: 
					cleanScrean();
                	signIn();
					break;
				case 4:
					update_UserDB();
					update_movieDB();
					update_reportDB();
					update_PreUserDB();
					System.out.println("> See you!");
					return;
				default:
					System.out.print("> You have pressed a wrong button. Select again: ");

			}
		}while(select != 4);
	}
	
	/**
	 * Method use to log in the system as admin.
	 * username : admin
	 * password : admin
	 */

	private static void adminLogin(){
		String username, passwrd;
		while(true){
			// System.out.println("\n---------------------- ADMIN MENU -------------------------");
			System.out.print("> Enter username: ");
			sc.nextLine();
			username = sc.nextLine();
			System.out.print("> Enter password: ");
			passwrd = sc.nextLine();
			if(username.equals("admin") && passwrd.equals("admin")){
				cleanScrean();
				adminMenu();
				return;
			}
			System.out.println("\n> Wrong username/password. Try again.\n");
			//System.out.println("\b\b\b\b\b\b\b");
		}


	}
	/**
	 * Method to represent admin menu.
	 * Admin can review reports, add movie to database, delete movie from database, 
	 * edit movie from database and log out. 
	 * Admin can see current movies in the database.
	 * 
	 */
	private static void adminMenu(){
		while(true){
			System.out.println("\n---------------------- ADMIN MENU -------------------------");
			System.out.println("1. Review Reports");
			System.out.println("2. Add Movie to Database");
			System.out.println("3. Delete Movie from Database");
			System.out.println("4. Edit Movie from Database");
			System.out.println("5. Logout");
			System.out.println("> Current movies:");
			// for(int i = 0; i < movieDB.size(); i++){
			// 	System.out.println(movieDB.get(i).get_title());
			// }
			System.out.print("> Selection: ");
			int selection = readInput();
			switch(selection){
				case 1:
					reviewReports();
					break;
				case 2:
					addMovie();
					break;
				case 3:
					deleteMovie();
					break;
				case 4:
					editMovie();
					break;
				case 5:
					return;
			}
		}	
	}

	/**
	 * Method to review reports. That is accessed by admin.  
	 */

	private static void editMovie(){
        Movie mov = new Movie();
        String s = new String();
        int ind = 0;
        System.out.print("Enter the name of the movie: ");
        sc.nextLine();
        s = sc.nextLine();
        int flag = 0;
        for(int i = 0; i < film.size(); i++){
            if(film.get(i).get_title().contains(s)){
                ind = i;
                flag = 1;
                break;
            }
        }

        if(flag == 0){
            System.out.println("Movie not found.");
            return;
        }

        System.out.print("Enter Movie Name: ");
        sc.nextLine();mov.set_title(sc.nextLine());
        System.out.print("Enter Movie Budget: ");
        mov.setBudget(sc.nextLine());
        System.out.print("Enter Movie's Genre: ");
        mov.set_genre(sc.nextLine());
        System.out.print("Enter Movie's Score: ");
        mov.setScore(sc.nextFloat());
        System.out.print("Enter Movie's Release date: ");
        mov.setAge(sc.nextInt());

        film.get(ind).set_title(mov.get_title());
        film.get(ind).setBudget(mov.getBudget());
        film.get(ind).set_genre(mov.get_genre());
        film.get(ind).setScore(mov.getScore());
        film.get(ind).setAge(mov.getAge());

    }
	private static void reviewReports(){
		System.out.println("Here are the list of reports: ");
		for(int i = 0; i < reports.size(); i++){
			System.out.println("Report " + i+1);
			printReportData(reports.get(i));
		}
		System.out.println("Select Report ID: ");
		System.out.println("Press 0 to return to menu: ");
		int select = readInput();
		if(select == 0){
			return;
		}
		if(select < 0 || select >= reports.size()){
			System.out.println("Invalid selection");
			return;
		}
		System.out.println("Select action");
		System.out.println("1. Ban User");
		System.out.println("2. Delete Comment");
		System.out.println("3. Dismiss");
		System.out.print("Select: ");
		select = readInput();
		switch(select){
			case 1:
				userDB.remove(reports.get(select - 1).getReported_user());
				reports.remove(reports.get(select - 1));
				// update_UserDB();
				// update_movieDB();
				// update_reportDB();
				System.out.println("User is banned. Press any key to continue.");
				sc.nextLine();
				break;
			case 2:
				Movie m = reports.get(select - 1).get_commented_movie();
				for(int i = 0; i < movieDB.size(); i++){
					if(movieDB.get(i).equals(m)){
						movieDB.remove(i);
						break;
					}
				}
				reports.remove(reports.get(select - 1));
				// update_movieDB();
				// update_reportDB();
				System.out.println("Comment is deleted. Press any key to continue.");
				sc.nextLine();
				break;
			case 3:
				reports.remove(reports.get(select - 1));
				// update_reportDB();
				System.out.println("Report is dismissed. Press any key to continue.");
				sc.nextLine();
				break;
		}
	}

	/**
	 * Method to add movies to database. This can be access by admin.
	 * It is required to enter the name, budget, genre, score and release date of the movie.
	 */
	private static void addMovie(){
		Movie mov = new Movie();
		
        System.out.print("Enter Movie Name: ");
        sc.nextLine();mov.set_title(sc.nextLine());
        System.out.print("Enter Movie Budget: ");
        mov.setBudget(sc.nextLine());
        System.out.print("Enter Movie's Genre: ");
        mov.set_genre(sc.nextLine());
        System.out.print("Enter Movie's Score: ");
        mov.setScore(sc.nextFloat());
        System.out.print("Enter Movie's Release date: ");
        mov.setAge(sc.nextInt());

		movieDB.add(mov);
		// update_movieDB();

		System.out.println("The movie is added to database");
		printMovieData(mov);
		System.out.println("Press any key to return to Admin menu.");
		sc.nextLine();sc.nextLine();
	}
/**
	 * Method to delete movies from database. This can be accessed by admin.
	 */
	private static void deleteMovie(){
		String s;
		System.out.print("Enter the name of the movie: ");
		sc.nextLine();
		s = sc.nextLine();
		for(int i = 0; i < movieDB.size(); i++){
			if(movieDB.get(i).get_title().equals(s)){
				movieDB.remove(i);
				//
				System.out.println("Movie is deleted. Press any key to continue.");
				sc.nextLine();
				break;
			}
		}
		
	}

	
/**
	 * Method to write details of a movie
	 * @param mov Movie paramater whose informations is going to be written 
	 */
	private static void printMovieData(Movie mov){

        System.out.println("Name: " + mov.get_title());
        System.out.println("IMDB Score: " + mov.getScore());
        System.out.println("Release Data: " + mov.getAge());
        System.out.println("Genre: " + mov.get_genre());
        System.out.println("Budget: "+ mov.getBudget());

	}

	
	/**
	 * Prints informations about a report.
	 * @param rep Report that is reported
	 */
	private static void printReportData(Report rep){
		System.out.println("Reporting: " + rep.getReported_user().getName() + " " + rep.getReported_user().getSurname());
		System.out.println("Reported by:" + rep.getReporting_user().getName() + " " + rep.getReporting_user().getSurname());
		System.out.println("Commented Movie: " + rep.get_commented_movie().get_title());
		System.out.println("Comment: " + rep.getReported_comment().getComment_text());
	}

	
	/** read input function in order to take input from user.
	 * @return input or 0 
	 */
	private static int readInput(){
        int input;
        try {
            input = sc.nextInt();
            return input;
        } 
        catch (Exception InputMismatchException) {
            sc.nextLine();
            return 0;
        }
    }
	/** initialize function to start program.
	 */
	@SuppressWarnings("unchecked")
	private static void initialize(){
		listOfRegularUsers = new ArrayList<RegularUser>();
		listOfPremiumUsers = new ArrayList<Premium_User>();
		movieDB = new ArrayList<Movie>();
		reports = new ArrayList<Report>();

		//Movie
		try{
			FileInputStream fis = new FileInputStream(MOVIEDB);
			ObjectInputStream ois = new ObjectInputStream(fis);

            movieDB = (ArrayList<Movie>) ois.readObject();
            ois.close();
		}
		catch(IOException ex){
			try{
				FileOutputStream fos = new FileOutputStream(MOVIEDB);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(movieDB);
				oos.close();
			}
			catch(IOException e){
				System.out.println("Error: Movie Database File Write");
				return;
			}
		}
		catch(ClassNotFoundException ex){
		}
		//Report
		try{
			FileInputStream fis = new FileInputStream(REPORTDB);
			ObjectInputStream ois = new ObjectInputStream(fis);

            reports = (ArrayList<Report>) ois.readObject();
            ois.close();
		}
		catch(IOException ex){
			try{
				FileOutputStream fos = new FileOutputStream(REPORTDB);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(reports);
				oos.close();
			}
			catch(IOException e){
				System.out.println("Error: Report Database File Write");
				return;
			}
		}
		catch(ClassNotFoundException ex){
		}
		//User
		try{
			FileInputStream fis = new FileInputStream(USERDB);
			ObjectInputStream ois = new ObjectInputStream(fis);

            listOfRegularUsers = (ArrayList<RegularUser>) ois.readObject();
            ois.close();
		}
		catch(IOException ex){
			try{
				FileOutputStream fos = new FileOutputStream(USERDB);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(listOfRegularUsers);
				oos.close();
			}
			catch(IOException e){
				System.out.println("Error: Movie Database File Write");
				return;
			}
		}
		catch(ClassNotFoundException ex){
		}
		//Premium
		try{
			FileInputStream fis = new FileInputStream(PREUSERDB);
			ObjectInputStream ois = new ObjectInputStream(fis);

            listOfPremiumUsers = (ArrayList<Premium_User>) ois.readObject();
            ois.close();
		}
		catch(IOException ex){
			try{
				FileOutputStream fos = new FileOutputStream(PREUSERDB);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(listOfPremiumUsers);
				oos.close();
			}
			catch(IOException e){
				System.out.println("Error: Movie Database File Write");
				return;
			}
		}
		catch(ClassNotFoundException ex){
		}
	}
/**Updates user DB with corresponding Regular User Objects
	*
	*/
	private static void update_UserDB(){
		try{
			FileOutputStream fos = new FileOutputStream(USERDB);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(listOfRegularUsers);
			oos.close();
		}
		catch(IOException e){
			System.out.println("Error: Movie Database File Write");
			return;
		}
	}
	/**Updates user DB with corresponding Premium User Objects
	*
	*/
	private static void update_PreUserDB(){
		try{
			FileOutputStream fos = new FileOutputStream(PREUSERDB);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(listOfPremiumUsers);
			oos.close();
		}
		catch(IOException e){
			e.printStackTrace();
			System.out.println("Error: Movie Database File Write");
			return;
		}
	}
/**Updates Movie DB with corresponding Movie Objects
	*
	*/
	private static void update_movieDB(){
		try{
			FileOutputStream fos = new FileOutputStream(MOVIEDB);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(movieDB);
			oos.close();
		}
		catch(IOException e){
			e.printStackTrace();
			System.out.println("Error: Movie Database File Write");
			return;
		}
	}
/**Updates report DB with corresponding Report Objects
	*
	*/
	private static void update_reportDB(){
		try{
			FileOutputStream fos = new FileOutputStream(REPORTDB);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(reports);
			oos.close();
		}
		catch(IOException e){
			System.out.println("Error: Report Database File Write");
			return;
		}
	}

/**Sign in function which helps user to sign in 
	*
	*/
    private static void signIn(){
    
        String membership, name_, surname_ , email_, password_, ageTemp_ = null;
        int age_;
        // Scanner sc = new Scanner(System.in);
		System.out.println("\n------------------------- Create an Account --------------------------\n");
        System.out.println("> Which one do you prefer?\n1- Premium version\n2- Free Version\n3- Convert Premium");
		System.out.println("--------------------------\n");
		sc.nextLine();
        do{
            System.out.print("> Select: ");
            membership = sc.nextLine();
        }while(!(membership.equals("1") || membership.equals("2") || membership.equals("3")));

		if(membership.equals("3"))
		{
			convertPremium();
			membership = "1";
		}

		
        System.out.print("\nEnter your name");
        do{
            System.out.print(": ");         
            name_ = sc.nextLine();
        }while(RegularUser.containsNum(name_));

        System.out.print("Enter your surname ");
        do{
            System.out.print(": ");
            surname_ = sc.nextLine();
        }while(RegularUser.containsNum(surname_));

        System.out.print("Enter your age");
        do{
            System.out.print(": ");
            ageTemp_ = sc.nextLine();                        
        }while(!RegularUser.isNumeric(ageTemp_));
        age_ = Integer.parseInt(ageTemp_);

        System.out.print("Enter your email: ");
        email_ = sc.nextLine();

        System.out.print("Enter your password: ");
        password_ = sc.nextLine();

        switch(membership){
            case "1": 
				cleanScrean();
                System.out.println("You are now premium user");
                Premium_User newPremium = new Premium_User(name_, surname_, age_, email_, password_,film);
				preusr++;
                listOfPremiumUsers.add(newPremium);
				// update_PreUserDB();
                break;  

            default : 
				cleanScrean();
                System.out.println("You are a free user");
                RegularUser newRegular = new RegularUser(name_, surname_, age_, email_, password_, film);
				Reg_index++;
                listOfRegularUsers.add(newRegular);
				// update_UserDB();
			}

    }
 /**Log in function which helps user to log in and watch movie, see hist. etc.
	*
	*/
    
    private static void logIn(){   

        String mailInfo, passwordInfo, temp;
        int flag = 0, subchoice = 1;
        // Scanner sc = new Scanner(System.in);

        do{
		sc.nextLine();
        flag = 0;
        System.out.print("> Enter your email: ");
        mailInfo = sc.nextLine();   

        System.out.print("> Enter your password: ");
        passwordInfo = sc.nextLine();   

        for(RegularUser reg : listOfRegularUsers){
            if(reg.getEmail().equals(mailInfo) && reg.getPassword().equals(passwordInfo)){
                flag = 1;
                System.out.println("> Welcome, " + reg.getName() + " to platform for free.");
                break;
            }
        }

        for(int i = 0; i < listOfPremiumUsers.size(); i++){
            if(listOfPremiumUsers.get(i).getEmail().equals(mailInfo) && listOfPremiumUsers.get(i).getPassword().equals(passwordInfo)){
                flag = 2;
                System.out.println("> Welcome, " + listOfPremiumUsers.get(i).getName() + " to platform as premium.");
                break;
            }
        }

        if(flag == 0)   
            System.out.println("> Invalid email address or password.");
        else if(flag == 1){
			cleanScrean();
        	regularUserMenu();
			break;
		}
        else{
			cleanScrean();
        	premiumUserMenu(mailInfo);
			break;
		}
    
        System.out.println("> Would you like to try again to log in (1) or sign in a new account (2) ?");
        temp = sc.nextLine();
        subchoice = Integer.parseInt(temp);
        if(subchoice == 2){
            signIn();
            break;
        }

        }while(subchoice == 1 && flag == 0);   
        // sc.close();
    }

 /**Regular User Menu function
      * It shows a menu and gives user options.
	*
	*/
    public static void regularUserMenu()
    {
        int ch ;
        Scanner scan = new Scanner(System.in);
        do{
            System.out.printf("\n1-Watch film\n2-Add favourities\n3-Add Watchlist\n4-See history\n5-Exit\nChoice: ");
             ch = scan.nextInt();
             switch(ch)
             {

                case 1:
                System.out.printf("Watching New Film\nPlease insert the Name of the film: ");
				scan.nextLine();
                listOfRegularUsers.get(Reg_index).add_to_history(scan.nextLine());
				// update_UserDB();
                break;

                case 2:
                System.out.printf("Adding favourities\nPlease Name of the film: ");
				scan.nextLine();
                listOfRegularUsers.get(Reg_index).add_to_favourities(scan.nextLine());
				// update_UserDB();
                break;
                
				case 3:
				scan.nextLine();
                System.out.printf("Add WatchList\nName of the film: ");
                listOfRegularUsers.get(Reg_index).add_to_watchlist(scan.nextLine());
				// update_UserDB();
                break;

                case 4:
                System.out.println("See History\n"); 
                listOfRegularUsers.get(Reg_index).get_history().toString();
				// update_UserDB();
                break;
                case 5:
                System.out.println("-------- > Quit < ------");
				
				// update_UserDB();
				break;
            
             }

        }while(ch != 5);
		
    }

    
	/** premiumUser menu is calls searchMovie, it is premium users menu
	 * @param mailInfo is mail of corresponding user.
	 */
	public static void premiumUserMenu(String mailInfo)
    {
		searchMovie(mailInfo);
    }

	
	/** Menu for premium users, it gives user options to choose.
	 * @param mailInfo is mail of corresponding user.
	 */
	public static void searchMovie(String mailInfo){
		String sMovie="", s;
		Movie m = new Movie();
		Comment c = new Comment();
		int flag = 0,ct = 0;

		for(int d=0;d<listOfPremiumUsers.size();d++){
			if(listOfPremiumUsers.get(d).getEmail().equals(mailInfo)){
				preusr=d;
				break;
			}
		}

		do{
			
			flag = 0;
			
			System.out.println("----------------------------- Premium Menu ----------------------------------");
			System.out.println("1. Watch Movie");
			System.out.println("2. Comment");
			System.out.println("3. Download");
			System.out.println("4. See upcomings");
			System.out.println("5. Add WatchList");
			System.out.println("6. Add Favourities");
			System.out.println("7. See " + listOfPremiumUsers.get(preusr).getName() + " ' s History");
			System.out.println("8. See " + listOfPremiumUsers.get(preusr).getName() + " ' s Downloaded movies");
			System.out.println("9. See " + listOfPremiumUsers.get(preusr).getName() + " ' s Watchlist");
			System.out.println("10. See " + listOfPremiumUsers.get(preusr).getName() + " ' s Comments");
			System.out.println("11. See " + listOfPremiumUsers.get(preusr).getName() + " ' s Favorites");
			System.out.println("12. Return to Menu");
			System.out.println("-----------------------------------------------------------------------------");
			System.out.print("> Select: ");

			ct = 0;
			s = sc.nextLine();
			if(s.equals("12"))	return;

			if(s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4") || s.equals("5")
			|| s.equals("6")){
				if(!s.equals("4")){
					System.out.print("> Enter the name of the movie: ");
					sMovie = sc.nextLine();
				}			

				for(int i = 0; i < movieDB.size(); i++){
					if(movieDB.get(i).get_title().contains(sMovie)){
						flag = 1;
						System.out.println("\n> Movie Found! ");
						m = movieDB.get(i);
						ct++;
						
						printMovieData(m);
						
						switch(s){
							case "1":
								System.out.println("\n> You are watching the movie.");
								m.get_title();
								listOfPremiumUsers.get(preusr).add_to_history(m.get_title());
								System.out.println("> You have watched the movie " + m.get_title()+"\n\n");
								// update_PreUserDB();
								break;

							case "2":
								System.out.print("\n> Enter your rating: ");
								String tp = sc.nextLine();
								float efm = Float.parseFloat(tp);
								c.setRating(efm);
								System.out.println("> Enter your comment:");
								c.setComment_text(sc.nextLine());
								c.setUser(listOfPremiumUsers.get(preusr));
								m.addMovieComment(c);
								listOfPremiumUsers.get(preusr).add_to_comments(c);
								System.out.println("\n");
								break;

							case "3":
							System.out.println("\n> You are downloading the movie.");
							listOfPremiumUsers.get(preusr).add_to_downloaded_movies(m.get_title());
							System.out.println("> The movie " + m.get_title() + " is downloaded."+"\n\n");
							// update_PreUserDB();
							break;

							case "4":
							System.out.println("\n> Seeing upcoming movies:\n");
							fillRecommended(m);
							break;

							case "5":
							System.out.printf("\n> Adding to watchlist\n\n\n");
							listOfPremiumUsers.get(preusr).add_to_watchlist(m.get_title());
							// update_PreUserDB();
							break;
							case "6":
						System.out.printf("\n> Adding to favourities\n\n\n");
						listOfPremiumUsers.get(preusr).add_to_favourities(m.get_title());
						// update_PreUserDB();
						break;
						}
						
						if(flag == 0)
							System.out.println("Movie not found"+"\n\n");
						if(ct == 1)
							break;
						}
					}
				}
				else{
					switch(s){
						
						case "7":
						System.out.println("See History\n"); 
						System.out.println( listOfPremiumUsers.get(preusr).get_history().toString() ) ;
						break;

						case "8":
						System.out.println("See Downloaded Movies\n");	//list
						System.out.println(listOfPremiumUsers.get(preusr).get_downloaded_movies().toString());
						break;

						case "9":
						System.out.println("See Watchlist\n");		//skiplist
						listOfPremiumUsers.get(preusr).get_watchlist().print();
						break;

						case "10":
						System.out.println("See Comments\n");		//list
						try{
							System.out.println(listOfPremiumUsers.get(preusr).get_comments().toString());
						}catch(NullPointerException e){ System.out.println("No comments yet");
						}
							break;

						case "11":
						System.out.println("See Favorites\n");	//list
						System.out.println(listOfPremiumUsers.get(preusr).get_favourities().toString());
						break;

						case "12":
						System.out.println("-------- > Quit < ------");
						cleanScrean();
						// update_PreUserDB();
						break;

					}
				}
			
			
		}while(!s.equals("12"));
	}

	
	/** Takes a movie, and recommend movies with rating >= 8.0 and same with mv's genre
	 * @param mv Movie object in order to reccomend with it's infos.
	 */
	public static void fillRecommended(Movie mv){

		recommendedMovies = new PriorityQueue<String>();

		for(Movie mov : film){
			if(mov.getScore() >= 8.0 && mv.get_genre().equals(mov.get_genre()))
				recommendedMovies.offer(mov.get_title());
		}
			
		for(int i=0; i<recommendedMovies.size(); i++){
			String t = recommendedMovies.poll();
			System.out.println(t);
		}			
		System.out.println("\n\n");
	}

	  
	/** reads file to arraylist. It includes all the films.
	 * @throws IOException
	 */
	public static void oku()    throws IOException{
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Movies.txt"))) {
            
			String line = null;
			String specialCharacter = ",";

            while((line = bufferedReader.readLine()) != null){
                //System.out.println(line);
				String[] values = line.split(specialCharacter);
                float scoreT = Float.parseFloat(values[3]) ;
                int year = Integer.parseInt(values[4]);
                Movie temp = new Movie(values[0],values[1],values[2],scoreT, year);
                film.add(temp);
            }

		}

		for(int i = 0; i < film.size(); i++)
		  movieDB.add(film.get(i));

    }
   /** It converts premium for free. Traverse all the stores with minimum distance. And takes selfie in stores.
     * Our adv. policy gives free premium user. 
	 * @throws IOException
	 */
	public static void convertPremium()
	{
		int V = 4;
        Graph g = new Graph(V);
        g.addEdge(0, 1, 2);
        g.addEdge(0, 2, 2);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 3, 1);
        g.addEdge(2, 0, 1);
        g.addEdge(2, 3, 2);
        g.addEdge(3, 3, 2);
  
        int src = 0, dest = 3;

        System.out.printf("\nShortest Distance between" + 
                            " %d and %d is %d\n", src, 
                            dest, g.findShortestPath(src, dest));

		System.out.printf("\nYou've visited all the required stores\n");
	}

	
	/** Sorts film array by score.
	 * @param film is arraylist of film
	 */
	public static void sortByScore(ArrayList<Movie> film)
	{	
		ArrayList<Movie> buffer = new ArrayList<>();
		

		for(int i = 0 ; i < film.size(); i++)
		{
			float tp = film.get(0).getScore();
		    int index = 0;	
			for(int j = 0 ; j < film.size(); j++)
			{
				if(film.get(j).getScore() <= tp)
				{
					index = j;
					tp = film.get(j).getScore();
				}
			}

			buffer.add(film.get(index));
			film.remove(index);
		}

		
	}

	/**
	 * 
	 * Graph Class.
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	static class Graph 
    {
        int V; // No. of vertices
        Vector<Integer>[] adj; // No. of vertices
  
        static int level;
  
        // Constructor
        @SuppressWarnings("unchecked")
        Graph(int V)
        {
            this.V = V;
            this.adj = new Vector[2 * V];
  
            for (int i = 0; i < 2 * V; i++)
                this.adj[i] = new Vector<>();
        }
  
      /**adds an edge
         * @param v is v
         * @param w is w
         * @param weight is weight
  		*/
        public void addEdge(int v, int w, int weight)
        {
  
           
            if (weight == 2) 
            {
                adj[v].add(v + this.V);
                adj[v + this.V].add(w);
            } else
                adj[v].add(w); 
        }
 		 /**prints shortest path.
  		 * *
  		 *  @param s is s
         * @param d is d
         * @param parent is parents.
  		*/
        public int printShortestPath(int[] parent, int s, int d)
        {
            level = 0;
  
       
            if (parent[s] == -1)
            {
                System.out.printf("Shortest Path between"+ 
                                "%d and %d is %s ", s, d, s);
                return level;
            }
  
            printShortestPath(parent, parent[s], d);
  
            level++;
            if (s < this.V)
                System.out.printf("%d ", s);
  
            return level;
        }
  /**prints shortest path.
  		 * *
  		 *  @param src is src
         * @param dest is dest
  		*/
        public int findShortestPath(int src, int dest)
        {
            boolean[] visited = new boolean[2 * this.V];
            int[] parent = new int[2 * this.V];
  

            for (int i = 0; i < 2 * this.V; i++) 
            {
                visited[i] = false;
                parent[i] = -1;
            }

            Queue<Integer> queue = new LinkedList<>();
  
            visited[src] = true;
            queue.add(src);
  
            while (!queue.isEmpty()) 
            {
  
   
                int s = queue.peek();
  
                if (s == dest)
                    return printShortestPath(parent, s, dest);
                queue.poll();
  

                for (int i : this.adj[s]) 
                {
                    if (!visited[i]) 
                    {
                        visited[i] = true;
                        queue.add(i);
                        parent[i] = s;
                    }
                }
            }
            return 0;
        }
    }
	
	public static void cleanScrean(){
		System.out.print("\033[H\033[2J");
        System.out.flush();
	}


	int partition(ArrayList<Movie> film, int low, int high)
    {
        Movie pivot = film.get(high);
        int i = (low-1);
        for (int j=low; j<high; j++)
        {
            if (film.get(j).getScore() <= pivot.getScore())
            {
                i++;
                Movie temp = film.get(i);
                film.set(i, film.get(j));
                film.set(j, temp);
            }
        }
        Movie temp = film.get(i+1);
        film.set(i+1, film.get(high));
        film.set(high, temp);
 
        return i+1;
    }
 
 
    void sort(ArrayList<Movie> film, int low, int high)
    {
        if (low < high)
        {
            int pi = partition(film, low, high);
            sort(film, low, pi-1);
            sort(film, pi+1, high);
        }
    }
}

