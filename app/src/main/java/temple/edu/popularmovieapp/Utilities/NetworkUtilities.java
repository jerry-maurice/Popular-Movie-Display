package temple.edu.popularmovieapp.Utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import temple.edu.popularmovieapp.BuildConfig;


public class NetworkUtilities {

    private static final String TAG = NetworkUtilities.class.getSimpleName();
    //the base url to fetch data from
    public static final String POPULAR_MOVIE_URL =
            "https://api.themoviedb.org/3/movie/popular";
    public static final String RATED_MOVIE_URL =
            "https://api.themoviedb.org/3/movie/top_rated";
    //OUr api key
    private static final String API_KEY = BuildConfig.MY_MOVIE_API_KEY;
    //language of the fetched data
    private static final String langData = "en-US";
    //to get image
    private static final String imageBaseUrl = "http://image.tmdb.org/t/p/w185";

    final static String key = "api_key";
    final static String sortParam = "sort_by";
    final static String language = "language";


    //method to build the url
    public static URL buildUrl(String MOVIE_URL){
        Uri builtUri = Uri.parse(MOVIE_URL).buildUpon()
                .appendQueryParameter(key,API_KEY)
                .build();

        URL url = null;
        try{
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //Log.v(TAG,"Built URI "+url);

        return url;
    }

    //this method return the entire result from the HTTP response
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if(hasInput){
                return scanner.next();
            }
            else {
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }
    }

    //build image url
    public static String getImageUrl(String image){
        String newUrlImage = imageBaseUrl+image;
        Log.v(TAG,newUrlImage);
        return newUrlImage;
    }
}
