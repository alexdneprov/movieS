package com.alexd.movieS.DTO;


public class MovieDto {
    private Long id;
    private String title;
    private String overview;
    private String releaseDate;
    private String posterUrl;
    private Double rating;

    public MovieDto(Long id, String title, String overview, String releaseDate, String posterUrl, Double rating) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.rating = rating;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getOverview() { return overview; }
    public String getReleaseDate() { return releaseDate; }
    public String getPosterUrl() { return posterUrl; }
    public Double getRating() { return rating; }
}
