package temple.edu.popularmovieapp.Model;

import java.io.Serializable;

public class Movie implements Serializable {
    private int id;
    private String title;
    private String originalTitle;
    private String overview;
    private String poster;
    private int voteAverage;
    private int voteCount;
    private double popularity;
    private String originalLanguage;
    private String backdropPath;
    private String releaseDate;

    public Movie() {
    }

    public Movie(int id, String title, String originalTitle, String overview, String poster,
                 int voteAverage, int voteCount, double popularity, String originalLanguage,
                 String backdropPath, String releaseDate) {
        this.id = id;
        this.title = title;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.poster = poster;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.popularity = popularity;
        this.originalLanguage = originalLanguage;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", overview='" + overview + '\'' +
                ", poster='" + poster + '\'' +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                ", popularity=" + popularity +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
