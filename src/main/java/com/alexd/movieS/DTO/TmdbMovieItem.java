package com.alexd.movieS.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TmdbMovieItem {
    public Long id;
    public String title;
    public String overview;

    @JsonProperty("release_date")
    public String releaseDate;

    @JsonProperty("poster_path")
    public String posterPath;

    @JsonProperty("vote_average")
    public Double voteAverage;
}