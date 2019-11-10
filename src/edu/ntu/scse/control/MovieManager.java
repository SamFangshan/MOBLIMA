package edu.ntu.scse.control;

public class MovieManager {

    /**
     * List of movie that this movie manager manages
     */
    private ArrayList<Movie> movies;

    /**
     * Contructor for MovieManager
     * @param movies
     */
    public MovieManager(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    /**
     * Create a new movie
     */
    private void createMovie(){
        Scanner sc = new Scanner(System.in);
        System.out.println("ID of the movie?");
        int id = sc.nextInt();
        System.out.println("Title of the movie?");
        String title = sc.nextLine();
        System.out.println("Synopsis of the movie?");
        String synopsis = sc.nextLine();
        System.out.println("Director of the movie?");
        String director = sc.nextLine();
        System.out.println("Cast of the movie?");
        String cast = sc.nextLine();
        System.out.println("Status of the movie? Default: Coming Soon");
        System.out.println("1: coming soon | 2: preview | 3: now showing | 4: end of showing");
        int status = sc.nextInt();
        MovieStatus mvStatus = MovieStatus.COMING_SOON;
        switch (status){
            case 1:
                mvStatus = MovieStatus.COMING_SOON;
                break;
            case 2:
                mvStatus = MovieStatus.PREVIEW;
                break;
            case 3:
                mvStatus = MovieStatus.NOW_SHOWING;
                break;
            case 4:
                mvStatus = MovieStatus.END_OF_SHOWING;
                break;
            default:
                System.out.println("No such status");
                break;
        }
        System.out.println("What is the type of the movie? Default : Regular");
        System.out.println("1: Regular 2: 3D");
        int type = sc.nextInt();
        MovieType mvType = MovieType.MovieType_REGULAR;
        switch(type){
            case 1:
                mvType = MovieType.MovieType_REGULAR;
                break;
            case 2:
                mvType = MovieType.MovieType_3D;
                break;
            default:
                System.out.println("No such type");
                break;
        }

        System.out.println("Is this movie a blockbuster?");
        System.out.println("1: True 0: False");
        int isblockbuster = sc.nextInt();
        Blockbuster blockbuster;
        if(isblockbuster == 1) {
            blockbuster = Blockbuster.TRUE;
        }else{
            blockbuster = Blockbuster.FALSE;
        }

        System.out.println("What is the rating of this movie?");
        System.out.println("1: General | 2: Parental Guidance | 3: Parental Guidance");
        System.out.println("4: No Children under 16 | 5: Mature 18 | 6: Restricted 21");
        MovieRating mvRating = MovieRating.G;
        int rating = sc.nextInt();
        switch (rating){
            case 1:
                mvRating = MovieRating.G;
                break;
            case 2:
                mvRating = MovieRating.PG;
                break;
            case 3:
                mvRating = MovieRating.PG13;
                break;
            case 4:
                mvRating = MovieRating.NC16;
                break;
            case 5:
                mvRating = MovieRating.M18;
                break;
            case 6:
                mvRating = MovieRating.R21;
                break;
            default:
                System.out.println("No such rating");
        }
        Movie movie = new Movie(id,title,synopsis,director,cast,blockbuster,0,mvRating,mvStatus,mvType);
        movies.add(movie);
    }

    /**
     * Update details of a movie
     * @param s: title of the movie to update
     */
    private void updateMovie(String s){
        Scanner sc = new Scanner(System.in);
        for(Movie movie: movies){
            if(movie.getTitle().toUpperCase().contains(s.toUpperCase())){
                System.out.println("Which attribute of the movie do you want to change?");
                System.out.println("1: Title");
                System.out.println("2: Synopsis");
                System.out.println("3: Director");
                System.out.println("4: Cast");
                System.out.println("5: Blockbuster");
                System.out.println("6: Movie Rating");
                System.out.println("7: Movie Status");
                System.out.println("8: Movie Type");
                int option = sc.nextInt();
                switch (option){
                    case 1:
                        System.out.println("New Title: ");
                        String newTitle = sc.nextLine();
                        movie.setTitle(newTitle);
                        break;
                    case 2:
                        System.out.println("New Synopsis: ");
                        String newSynopsis = sc.nextLine();
                        movie.setSynopsis(newSynopsis);
                        break;
                    case 3:
                        System.out.println("New Director: ");
                        String newDirector = sc.nextLine();
                        movie.setDirector(newDirector);
                        break;
                    case 4:
                        System.out.println("New Cast: ");
                        String newCast = sc.nextLine();
                        movie.setCast(newCast);
                        break;
                    case 5:
                        System.out.println("Is this movie a Blockbuster: ");
                        System.out.println("1: True 0: False");
                        int bb = sc.nextInt();
                        if(bb == 1){
                            movie.setBlockbuster(Blockbuster.TRUE);
                        }else{
                            movie.setBlockbuster(Blockbuster.FALSE);
                        }
                        break;
                    case 6:
                        System.out.println("New Movie Rating: (Default to General)");
                        int newRating = sc.nextInt();
                        System.out.println("1: General | 2: Parental Guidance | 3: Parental Guidance");
                        System.out.println("4: No Children under 16 | 5: Mature 18 | 6: Restricted 21");
                        MovieRating mvRating = MovieRating.G;
                        switch (newRating){
                            case 1:
                                mvRating = MovieRating.G;
                                break;
                            case 2:
                                mvRating = MovieRating.PG;
                                break;
                            case 3:
                                mvRating = MovieRating.PG13;
                                break;
                            case 4:
                                mvRating = MovieRating.NC16;
                                break;
                            case 5:
                                mvRating = MovieRating.M18;
                                break;
                            case 6:
                                mvRating = MovieRating.R21;
                                break;
                            default:
                                System.out.println("No such rating");
                        }
                        movie.setMovieRating(mvRating);
                        break;
                    case 7:
                        System.out.println("New Movie Status: (Default to 'Coming Soon')");
                        int newStatus = sc.nextInt();
                        MovieStatus mvStatus = MovieStatus.COMING_SOON;
                        switch (newStatus){
                            case 1:
                                mvStatus = MovieStatus.COMING_SOON;
                                break;
                            case 2:
                                mvStatus = MovieStatus.PREVIEW;
                                break;
                            case 3:
                                mvStatus = MovieStatus.NOW_SHOWING;
                                break;
                            case 4:
                                mvStatus = MovieStatus.END_OF_SHOWING;
                                break;
                            default:
                                System.out.println("No such status");
                                break;
                        }
                        movie.setMovieStatus(mvStatus);
                        break;
                    case 8:
                        System.out.println("New Movie Type: ");
                        int newType = sc.nextInt();
                        MovieType mvType = MovieType.MovieType_REGULAR;
                        switch(newType){
                            case 1:
                                mvType = MovieType.MovieType_REGULAR;
                                break;
                            case 2:
                                mvType = MovieType.MovieType_3D;
                                break;
                            default:
                                System.out.println("No such type");
                                break;
                        }
                        movie.setMovieType(mvType);
                        break;
                    default:
                        System.out.println("No such option");
                        break;
                }
            }
        }
    }

    /**
     * Remove a movie by setting its status to be 'End of Showing'
     * @param s: title of the movie
     */
    private void removeMovie(String s){
        for(Movie movie: movies){
            if(movie.getTitle().toUpperCase().contains(s.toUpperCase())){
                // Remove the movie by changing the status to 'End of Showing"
                movie.setMovieStatus(MovieStatus.END_OF_SHOWING);
                System.out.println("Movie removed!");
                return;
            }
        }
        System.out.println("Movie not found");
    }


}