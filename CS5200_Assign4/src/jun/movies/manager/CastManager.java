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

import jun.movies.domain.Cast;
import jun.movies.domain.Movie;
import jun.movies.domain.Actor;

public class CastManager {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	DataSource ds;

	public CastManager() {
		Context jndi;
		try {
			jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/movies");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void createCast(Cast cast) {
		String createCastSql = "INSERT INTO cast VALUES(?,?,?,?);";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(createCastSql);
			ps.setString(1, cast.getCastId());
			ps.setString(2, cast.getMovie().getId());
			ps.setString(3, cast.getActor().getId());
			ps.setString(4, cast.getCharacterName());
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	List<Cast> readAllCasts() {
		String readAllSql = "SELECT * FROM cast";
		List<Cast> casts = new ArrayList<Cast>();
		ActorManager actorManager = new ActorManager();
		MovieManager movieManager = new MovieManager();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(readAllSql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Actor actor = actorManager.readActor(rs.getString("actorId"));
				Movie movie = movieManager.readMovie(rs.getString("movieId"));
				Cast cast = new Cast(rs.getString("castId"), actor,
						movie, rs.getString("characterName"));
				casts.add(cast);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return casts;
	}
	
	List<Cast> readAllCastsForActorname(String actorId) {
		String readAllCastsForActornameSql = "SELECT * FROM cast WHERE actorId=?";
		List<Cast> casts = new ArrayList<Cast>();
		ActorManager actorManager = new ActorManager();
		MovieManager movieManager = new MovieManager();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(readAllCastsForActornameSql);
			ps.setString(1, actorId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Actor actor = actorManager.readActor(rs.getString("actorId"));
				Movie movie = movieManager.readMovie(rs.getString("movieId"));
				Cast cast = new Cast(rs.getString("castId"), actor,
						movie, rs.getString("characterName"));
				casts.add(cast);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return casts;
	}
	
	List<Cast> readAllCastsForMovie(String movieId) {
		String readAllCastsForMovieSql = "SELECT * FROM cast WHERE movieId=?";
		List<Cast> casts = new ArrayList<Cast>();
		ActorManager actorManager = new ActorManager();
		MovieManager movieManager = new MovieManager();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(readAllCastsForMovieSql);
			ps.setString(1, movieId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Actor actor = actorManager.readActor(rs.getString("actorId"));
				Movie movie = movieManager.readMovie(rs.getString("movieId"));
				Cast cast = new Cast(rs.getString("castId"), actor,
						movie, rs.getString("characterName"));
				casts.add(cast);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return casts;
	}
	
	public Cast readCastForId(String castId) {
		String readCastForIdSql = "SELECT * FROM cast WHERE castId=?";
		ActorManager actorManager = new ActorManager();
		MovieManager movieManager = new MovieManager();
		Cast cast = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(readCastForIdSql);
			ps.setString(1, castId);
			rs = ps.executeQuery();
			if(rs.next()) {
				Actor actor = actorManager.readActor(rs.getString("actorId"));
				Movie movie = movieManager.readMovie(rs.getString("movieId"));
				cast = new Cast(rs.getString("castId"), actor,
						movie, rs.getString("characterName"));				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return cast;
	}
	
	void updateCast(String castId, Cast newCast) {
		String updateSql = "UPDATE cast SET actorId=?,movieId=?,characterName=? WHERE castId=?";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(updateSql);
			ps.setString(1, newCast.getActor().getId());
			ps.setString(2, newCast.getMovie().getId());
			ps.setString(3, newCast.getCharacterName());
			ps.setString(4, newCast.getCastId());
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	void deleteCast(String castId) {
		String deleteSql = "DELETE FROM cast WHERE castId=?";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(deleteSql);
			ps.setString(1, castId);
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
