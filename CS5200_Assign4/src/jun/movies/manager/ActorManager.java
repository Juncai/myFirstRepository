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

import jun.movies.domain.Actor;

public class ActorManager {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	DataSource ds;

	public ActorManager() {
		Context jndi;
		try {
			jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/movies");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	public void createActor(Actor newActor) {
		String createUserSql = "INSERT INTO actor VALUES(?,?,?,?);";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(createUserSql);
			ps.setString(1, newActor.getId());
			ps.setString(2, newActor.getFirstName());
			ps.setString(3, newActor.getLastName());
			ps.setDate(4, new java.sql.Date(newActor.getDateOfBirth().getTime()));
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Actor> readAllActors() {
		String readAllSql = "SELECT * FROM actor";
		List<Actor> actors = new ArrayList<Actor>();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(readAllSql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Actor actor = new Actor(rs.getString("id"),
						rs.getString("firstName"), rs.getString("lastName"),
						rs.getDate("dateOfBirth"));
				actors.add(actor);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return actors;
	}
	
	public Actor readActor(String actorId){
		String readSql = "SELECT * FROM actor WHERE id=?";
		Actor actor = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(readSql);
			ps.setString(1, actorId);
			rs = ps.executeQuery();
			if (rs.next()) {
				actor = new Actor(actorId, rs.getString("firstName"),
						rs.getString("lastName"), rs.getDate("dateOfBirth"));
			}
			return actor;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void updateActor(String actorId, Actor actor) {
		String updateSql = "UPDATE actor SET firstName=?,lastName=?,dateOfBirth=? WHERE id=?";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(updateSql);
			ps.setString(1, actor.getFirstName());
			ps.setString(2, actor.getLastName());
			ps.setDate(3, new java.sql.Date(actor.getDateOfBirth().getTime()));
			ps.setString(4, actorId);
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void deleteActor(String actorId) {
		String deleteSql = "DELETE FROM actor WHERE id=?";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(deleteSql);
			ps.setString(1, actorId);
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
