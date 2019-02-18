package edu.eci.arsw.filter;

import java.util.*;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;

public interface FilterFilm {
	public List<Movie> filtreType(String cine,String date,String data,CinemaPersitence cps);
}
