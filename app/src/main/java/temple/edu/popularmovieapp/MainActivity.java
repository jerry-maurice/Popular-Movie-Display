package temple.edu.popularmovieapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import temple.edu.popularmovieapp.Model.Movie;
import temple.edu.popularmovieapp.Utilities.NetworkUtilities;
import temple.edu.popularmovieapp.Utilities.ParseMovieJsonUtils;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterClickHandler {

    private RecyclerView movieList;
    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find reference to the progressbar
        progressBar = (ProgressBar)findViewById(R.id.loading_indicator);
        //find reference to our recycleview from xml
        movieList = (RecyclerView)findViewById(R.id.movieHolder);
        //add a layout to the recylcleview
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        movieList.setLayoutManager(staggeredGridLayoutManager);

        movieList.setHasFixedSize(true);
        //the movie adapter is responsible for displaying each item
        movieAdapter = new MovieAdapter(this);

        //load data
        loadMovieData(NetworkUtilities.POPULAR_MOVIE_URL);

    }

    private void loadMovieData(String defaultUrl) {
        new FetchMovieTask().execute(defaultUrl);
    }

    @Override
    public void onClick(Movie mInfo) {
        Context context = this;
        Class destinationClass = MovieDetail.class;
        Intent intent = new Intent(context, destinationClass);

        intent.putExtra("movie", mInfo);
        startActivity(intent);
    }

    //fetch the movies
    public class FetchMovieTask extends AsyncTask<String,Void, ArrayList<Movie>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Movie> doInBackground(String... strings) {
            if(strings.length == 0){
                return null;
            }

            String sortResult = strings[0];
            URL movieRequestUrl = NetworkUtilities.buildUrl(sortResult);

            try{
                String jsonMovieData = NetworkUtilities.getResponseFromHttpUrl(movieRequestUrl);
                ArrayList<Movie> movies = ParseMovieJsonUtils.getDataFromJson(jsonMovieData);

                return movies;
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            super.onPostExecute(movies);
            progressBar.setVisibility(View.INVISIBLE);
            if(movies != null){
                movieList.setAdapter(movieAdapter);
                movieAdapter.setMovieData(movies);
                Log.v("asyncTask","movie size"+movies.size());
            }
            else{
                //show error message
            }
        }
    }
    //menu side

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menuItemPopular){
            movieAdapter.setMovieData(null);
            //load data
            loadMovieData(NetworkUtilities.POPULAR_MOVIE_URL);
            return true;
        }
        else if(id == R.id.menuItemRated){
            movieAdapter.setMovieData(null);
            //load data
            loadMovieData(NetworkUtilities.RATED_MOVIE_URL);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
