package movieListAnswer;

import movieListQuestion.MovieListException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieList {
    private List<String> movieList = new ArrayList<>();
    private List<Integer> ratinglist = new ArrayList<Integer>();

    public void addMovie(String movieName) throws MovieListException {
        if (movieList.contains(movieName)) throw new MovieListException();
        movieList.add(movieName);
        Collections.sort(movieList);
    }

    public String getRating(String movieName) throws MovieListException {
        String result = "";
        if (movieList.contains(movieName)) {
            if (ratinglist.size() != 0) {
                for (int j = 0; j < ratinglist.get(movieList.indexOf(movieName)); ++j) {
                    result += "*";
                }
            }
            else result = "No rating";
            return result;
        }
        else throw new MovieListException();
    }

    public void setRating(String movieName, int rating) throws MovieListException{
        if (rating <= 0 | rating > 5 | !movieList.contains(movieName)) throw new MovieListException();
        ratinglist.add(movieList.indexOf(movieName), rating);
    }

    public String getList() {
        String result = "";
        for (String movie : movieList) result += movie + "\n";
        return result;
    }
}
