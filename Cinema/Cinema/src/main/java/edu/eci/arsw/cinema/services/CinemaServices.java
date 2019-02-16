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
	CinemaPersitence cps;

	public CinemaFunction cf;
	public Cinema cine;

	public void addNewCinema(Cinema c) {

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

	public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException, CinemaPersistenceException {
		/*this.cf.buyTicket(row, col);
		this.cine.setName(cinema);
		this.cf.setDate(date);
		this.cf.getMovie().setName(movieName);*/
		Cinema cs = cps.getCinema(cinema);
		List<CinemaFunction> func = cs.getFunctions();
		for(CinemaFunction cf : cps.getCinema(cinema).getFunctions()) {
			if(cf.getMovie().equals(movieName) && cf.getDate().equals(date)) {
				cf.buyTicket(row, col);
				break;
			}
		}

	}

	public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
		List<CinemaFunction> funcionesTotales = null;
		try {
			funcionesTotales = getCinemaByName(cinema).getFunctions();
		} catch (CinemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<CinemaFunction> escogidos = new ArrayList();
		for (CinemaFunction cf : funcionesTotales) {
			if (cf.getDate().equals(date))
				escogidos.add(cf);

		}
		return escogidos;
	}

	public CinemaPersitence getCps() {
		return cps;
	}

	public void setCps(CinemaPersitence cps) {
		this.cps = cps;
	}

}
