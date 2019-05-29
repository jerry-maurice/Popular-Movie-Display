package temple.edu.popularmovieapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import temple.edu.popularmovieapp.Model.Movie;
import temple.edu.popularmovieapp.Utilities.NetworkUtilities;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.itemViewHolder> {

    private ArrayList<Movie> movieData;
    private static final String TAG = NetworkUtilities.class.getSimpleName();

    //Interface created to make it easy for an activity to communicate with the recycleview
    private final MovieAdapterClickHandler mClickHandler;

    //interface that receive onclic message
    public interface MovieAdapterClickHandler{
        void onClick(Movie mInfo);
    }

    public MovieAdapter(MovieAdapterClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public void setMovieData(ArrayList<Movie> mData){
        this.movieData = new ArrayList<Movie>();
        this.movieData = mData;
        //Log.v(TAG,"checking size of mdata: "+mData);
        notifyDataSetChanged();
    }

    @Override
    public itemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForItem = R.layout.movie_layout;
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(layoutIdForItem, viewGroup, false);
        return new itemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(itemViewHolder itemViewHolder, int i) {
        Movie movie = movieData.get(i);
        Picasso.get().load(NetworkUtilities.getImageUrl(movie.getPoster()))
                .into(itemViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        if(null == movieData)
            return 0;
        return movieData.size();
    }



    /*
     * inner class
     * cache of the children view
     */
    class itemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ImageView imageView;

        public itemViewHolder(View itemView){
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.movieImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Movie info = movieData.get(adapterPosition);
            mClickHandler.onClick(info);
        }
    }
}
