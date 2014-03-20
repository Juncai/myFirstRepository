package jun.movies.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jun.movies.domain.Movie;
import jun.movies.domain.User;

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
		String createUserSql = "INSERT INTO movie VALUES(?,?,?,?);";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(createUserSql);
			ps.setString(1, newMovie.getId());
			ps.setString(2, newMovie.getTitle());
			ps.setString(3, newMovie.getPosterImage());
			ps.setDate(4, new java.sql.Date(newMovie.getReleaseDate().getTime()));
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Movie> readAllMovies() {
		String readAllSql = "SELECT * FROM movie";
		List<Movie> movies = new ArrayList<Movie>();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(readAllSql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Movie movie = new Movie(rs.getString("id"),
						rs.getString("title"), rs.getString("posterImage"),
						rs.getDate("releaseDate"));
				movies.add(movie);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return movies;
	}
	
	public Movie readMovie(String movieId){
		String readSql = "SELECT * FROM movie WHERE id=?";
		Movie movie = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(readSql);
			ps.setString(1, movieId);
			rs = ps.executeQuery();
			if (rs.next()) {
				movie = new Movie(movieId, rs.getString("title"),
						rs.getString("posterImage"), rs.getDate("releaseDate"));
			}
			return movie;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void updateMovie(String movieId, Movie movie) {
		String updateSql = "UPDATE movie SET title=? posterImage=? releaseDate=? WHERE id=?";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(updateSql);
			ps.setString(1, movie.getTitle());
			ps.setString(2, movie.getPosterImage());
			ps.setDate(3, new java.sql.Date(movie.getReleaseDate().getTime()));
			ps.setString(5, movieId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void deleteMovie(String movieId) {
		String deleteSql = "DELETE FROM movie WHERE id=?";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(deleteSql);
			ps.setString(1, movieId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
