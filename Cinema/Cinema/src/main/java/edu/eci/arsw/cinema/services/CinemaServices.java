/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.services;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import edu.eci.arsw.filter.FilterFilm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author cristian
 */
@Service
public class CinemaServices {

	@Autowired
	@Qualifier("Bean1")
	CinemaPersitence cps =null;
	
	@Autowired
	@Qualifier("Bean3")
	FilterFilm  filtro;
	public CinemaFunction cf;
	public Cinema cine;

	public void addNewCinema(Cinema c) {
		try {
			cps.saveCinema(c);
		} catch (CinemaPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Set<Cinema> getAllCinemas() {
		return null;
	}

	/**
	 * @param name cinema's name
	 * @return the cinema of the given name created by the given author
	 * @throws CinemaException
	 */
	public Cinema getCinemaByName(String name) throws CinemaException {

		try {
			return this.cps.getCinema(name);
		} catch (CinemaPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cine;
	}

	public void showFilter(String cine, String date,String filtroes) {
		
		if(filtro.getClass().getName().equals("Filteredbygender")) {
			for(Movie mv:filtro.filtreType(cine, date, filtroes,cps)){
				System.out.println(mv.getName());
			}
		}
		else {
			for(Movie mv:filtro.filtreType(cine, date, filtroes,cps)){
				System.out.println(mv.getName());
			}
		}
		
		
	}
	public void buyTicket(int row, int col, String cinema, String date, String movieName)
			throws CinemaException, CinemaPersistenceException {
		cps.buyTicket(row, col, cinema, date, movieName);

	}

	public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
		return cps.getFunctionsbyCinemaAndDate(cinema, date);
	}

	public CinemaPersitence getCps() {
		return cps;
	}

	public void setCps(CinemaPersitence cps) {
		this.cps = cps;
	}

}
