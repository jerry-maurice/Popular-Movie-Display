package temple.edu.popularmovieapp.Utilities;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import temple.edu.popularmovieapp.Model.Movie;

public class ParseMovieJsonUtils {
    private static final String TAG = NetworkUtilities.class.getSimpleName();

    public static ArrayList<Movie> getDataFromJson(String movieJsonStr) throws JSONException {
        ArrayList<Movie> movies = new ArrayList<>();
        JSONObject data = new JSONObject(movieJsonStr);
        JSONArray jsonArray = data.getJSONArray("results");
        //getting the data from the object
        for(int i=0;i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            //new movie object
            Movie movie = new Movie();
            movie.setId(jsonObject.getInt("id"));
            movie.setTitle(jsonObject.getString("title"));
            movie.setOriginalTitle(jsonObject.getString("original_title"));
            movie.setOverview(jsonObject.getString("overview"));
            movie.setPoster(jsonObject.getString("poster_path"));
            movie.setVoteAverage(jsonObject.getInt("vote_average"));
            movie.setVoteCount(jsonObject.getInt("vote_count"));
            movie.setPopularity(jsonObject.getInt("popularity"));
            movie.setOriginalLanguage(jsonObject.getString("original_language"));
            movie.setBackdropPath(jsonObject.getString("backdrop_path"));
            movie.setReleaseDate(jsonObject.getString("release_date"));
            Log.v(TAG,"movie id"+movie.getId());
            //add to list
            movies.add(movie);
        }
        return movies;

    }
}