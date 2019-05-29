package temple.edu.popularmovieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import temple.edu.popularmovieapp.Model.Movie;
import temple.edu.popularmovieapp.Utilities.NetworkUtilities;

public class MovieDetail extends AppCompatActivity {
    private ImageView posterImage;
    private TextView title;
    private TextView releaseDate;
    private TextView content;
    private TextView original;
    private TextView popularity;
    private TextView vAverage;
    private TextView vCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        //getting data from parent activity
        Intent intent = getIntent();
        Movie movie = (Movie)intent.getSerializableExtra("movie");

        //reference
        posterImage = (ImageView)findViewById(R.id.imagePoster);
        title = (TextView)findViewById(R.id.titleMovie);
        releaseDate = (TextView)findViewById(R.id.releaseDate);
        content = (TextView)findViewById(R.id.contentText);
        original = (TextView) findViewById(R.id.originalTitle);
        popularity = (TextView)findViewById(R.id.popularity);
        vAverage =  (TextView)findViewById(R.id.voteAvergae);
        vCount = (TextView)findViewById(R.id.voteCount);

        //setting to view
        Picasso.get().load(NetworkUtilities.getImageUrl(movie.getPoster()))
                .into(posterImage);
        title.setText(movie.getTitle());
        releaseDate.setText(getString(R.string.release)+movie.getReleaseDate());
        content.setText(movie.getOverview());
        original.setText(getString(R.string.original)+movie.getOriginalTitle());
        popularity.setText(getString(R.string.popularity)+movie.getPopularity());
        vCount.setText(getString(R.string.voteCount)+movie.getVoteCount());
        vAverage.setText(getString(R.string.voteAverage)+movie.getVoteAverage());

    }
}
