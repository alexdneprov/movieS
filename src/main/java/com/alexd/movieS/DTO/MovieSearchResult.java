package com.alexd.movieS.DTO;

public class MovieSearchResult {
    private Long id;
    private String title;
    private String releaseDate;
    private String posterUrl;
    private Double rating;

    public MovieSearchResult(Long id, String title, String releaseDate, String posterUrl, Double rating) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.rating = rating;
    }


    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getReleaseDate() { return releaseDate; }
    public String getPosterUrl() { return posterUrl; }
    public Double getRating() { return rating; }
}
