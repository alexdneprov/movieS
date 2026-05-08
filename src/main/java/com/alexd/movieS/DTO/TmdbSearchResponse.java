package com.alexd.movieS.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TmdbSearchResponse {
	public List<TmdbMovieItem> results;

    public List<TmdbMovieItem> getResults() { return results; }
    public void setResults(List<TmdbMovieItem> results) { this.results = results; }
}

