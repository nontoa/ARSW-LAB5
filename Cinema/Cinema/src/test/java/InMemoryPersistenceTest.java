
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.impl.InMemoryCinemaPersistence;
import edu.eci.arsw.cinema.services.CinemaServices;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cristian
 */
public class InMemoryPersistenceTest {
	
	@Test
	public void saveNewAndLoadTest() throws CinemaPersistenceException {
		InMemoryCinemaPersistence ipct = new InMemoryCinemaPersistence();

		String functionDate = "2018-12-18 15:30";
		List<CinemaFunction> functions = new ArrayList<>();
		CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate);
		CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate);
		functions.add(funct1);
		functions.add(funct2);
		Cinema c = new Cinema("Movies Bogotá", functions);
		ipct.saveCinema(c);

		assertNotNull("Loading a previously stored cinema returned null.", ipct.getCinema(c.getName()));
		assertEquals("Loading a previously stored cinema returned a different cinema.", ipct.getCinema(c.getName()), c);
	}

	@Test
	public void saveExistingCinemaTest() {
		InMemoryCinemaPersistence ipct = new InMemoryCinemaPersistence();

		String functionDate = "2018-12-18 15:30";
		List<CinemaFunction> functions = new ArrayList<>();
		CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate);
		CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate);
		functions.add(funct1);
		functions.add(funct2);
		Cinema c = new Cinema("Movies Bogotá", functions);

		try {
			ipct.saveCinema(c);
		} catch (CinemaPersistenceException ex) {
			fail("Cinema persistence failed inserting the first cinema.");
		}

		List<CinemaFunction> functions2 = new ArrayList<>();
		CinemaFunction funct12 = new CinemaFunction(new Movie("SuperHeroes Movie 3", "Action"), functionDate);
		CinemaFunction funct22 = new CinemaFunction(new Movie("The Night 3", "Horror"), functionDate);
		functions.add(funct12);
		functions.add(funct22);
		Cinema c2 = new Cinema("Movies Bogotá", functions2);
		try {
			ipct.saveCinema(c2);
			fail("An exception was expected after saving a second cinema with the same name");
		} catch (CinemaPersistenceException ex) {

		}

	}
	
	@Test
	public void getCinemaByNameTest() throws CinemaException {
		InMemoryCinemaPersistence ipct = new InMemoryCinemaPersistence();
		String functionDate = "2018-12-18 15:30";
		List<CinemaFunction> functions = new ArrayList<>();
		CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate);
		CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate);
		functions.add(funct1);
		functions.add(funct2);
		Cinema c = new Cinema("Movies Bogotá", functions);
		CinemaServices cs = new CinemaServices();
		try {
			ipct.saveCinema(c);
		} catch (CinemaPersistenceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cs.setCps(ipct);
		assertEquals(cs.getCinemaByName(c.getName()), c);

	}
	
	@Test
	public void  buyTicketTest() {
		InMemoryCinemaPersistence ipct = new InMemoryCinemaPersistence();
		String functionDate = "2018-12-18 15:30";
		List<CinemaFunction> functions = new ArrayList<>();
		CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate);
		CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate);
		functions.add(funct1);
		functions.add(funct2);
		Cinema c = new Cinema("Movies Medellin", functions);
		CinemaServices cs = new CinemaServices();
		try {
			ipct.saveCinema(c);
		} catch (CinemaPersistenceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cs.setCps(ipct);
		try {
			cs.buyTicket(2, 3, c.getName(), "2018-12-18 15:30", "The Night 2");
		} catch (CinemaPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CinemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag = false;
		try {
			for(CinemaFunction cf : cs.getCps().getCinema("Movies Medellin").getFunctions()) {
				if(cf.getMovie().equals("The Night 2") && cf.getDate().equals("2018-12-18 15:30")) {
					assertEquals(cf.getSeats().get(2).get(3), true);
					
				}
			}
		} catch (CinemaPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void getFunctionsbyCinemaAndDateTest() {
		InMemoryCinemaPersistence ipct = new InMemoryCinemaPersistence();
		String functionDate = "2018-12-18 15:30";
		List<CinemaFunction> functions = new ArrayList<>();
		CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate);
		CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate);
		functions.add(funct1);
		functions.add(funct2);
		Cinema c = new Cinema("Movies Medellin", functions);
		CinemaServices cs = new CinemaServices();
		try {
			ipct.saveCinema(c);
		} catch (CinemaPersistenceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cs.setCps(ipct);
		assertEquals(2, ipct.getFunctionsbyCinemaAndDate(c.getName(), functionDate).size());

	}
}
