package jun.movies.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jun.movies.domain.Movie;

public class MovieManager {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	DataSource ds;

	public MovieManager() {
		Context jndi;
		try {
			jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/movies");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	public void createMovie(Movie newMovie) {
		
	}
	
	public List<Movie> readAllMovies() {
		
	}
	
	public Movie readMovie(String movieId){
		
	}
	
	public void updateMovie(String movieId, Movie movie) {
		
	}
	
	public void deleteMovie(String movieId) {
		
	}
	
}
