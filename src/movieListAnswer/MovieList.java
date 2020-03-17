package movieListAnswer;

import movieListQuestion.MovieListException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieList {
    private List<String> movieList = new ArrayList<>();
    private List<Integer> ratinglist = new ArrayList<Integer>();
    private List<String> thumbRatingMovies = new ArrayList<>();

    public void addMovie(String movieName) throws MovieListException {
        if (movieList.contains(movieName)) throw new MovieListException();
        movieList.add(movieName);
        Collections.sort(movieList);
    }

    public String getRating(String movieName) throws MovieListException {
        String result = "";
        if (movieList.contains(movieName)) {
            if (ratinglist.size() != 0) {
                int rating = ratinglist.get(movieList.indexOf(movieName));
                if (thumbRatingMovies.contains(movieName)) {
                    if (rating == 1 | rating == -1) result += "One thumb ";
                    else result += "Two thumbs ";
                    if (rating < 0) result += "down";
                    else result += "up";
                } else {
                    for (int j = 0; j < rating; ++j) {
                        result += "*";
                    }
                }
            } else result = "No rating";
        }
        else throw new MovieListException();
        return result;
    }

    public void setRating(String movieName, int rating) throws MovieListException{
        if (!thumbRatingMovies.contains(movieName) && (rating  <= 0 | rating > 5 | !movieList.contains(movieName))) throw new MovieListException();
        ratinglist.add(movieList.indexOf(movieName), rating);
    }

    public String getList() {
        String result = "";
        for (String movie : movieList) result += movie + "\n";
        return result;
    }

    public void setThumbRating(String movieName, int thumbRating) throws MovieListException{
        if (!thumbRatingMovies.contains(movieName)) thumbRatingMovies.add(movieName);
        if (thumbRatingMovies.contains(movieName)) setRating(movieName, thumbRating);
    }
}
