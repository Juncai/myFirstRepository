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

import jun.movies.domain.User;

public class UserManager {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	DataSource ds;

	public UserManager() {
		Context jndi;
		try {
			jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/movies");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void createUser(User newUser) {
		String createUserSql = "INSERT INTO user VALUES(?,?,?,?,?);";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(createUserSql);
			ps.setString(1, newUser.getUsername());
			ps.setString(2, newUser.getPassword());
			ps.setString(3, newUser.getFirstName());
			ps.setString(4, newUser.getLastName());
			ps.setDate(5, new java.sql.Date(newUser.getDateOfBirth().getTime()));
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<User> readAllUsers() {
		String readAllSql = "SELECT * FROM user";
		List<User> users = new ArrayList<User>();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(readAllSql);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getString("username"),
						rs.getString("password"), rs.getString("firstName"),
						rs.getString("lastname"), rs.getDate("dateOfBirth"));
				users.add(user);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return users;
	}

	public User readUser(String username) {
		String readSql = "SELECT * FROM user WHERE username=?";
		User user = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(readSql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(username, rs.getString("password"),
						rs.getString("firstName"), rs.getString("lastName"),
						rs.getDate("dateOfBirth"));
			}
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void updateUser(String username, User user) {
		String updateSql = "UPDATE user SET password=?,firstName=?,lastName=?,dateOfBirth=? WHERE username=?";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(updateSql);
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setDate(4, new java.sql.Date(user.getDateOfBirth().getTime())); 
			ps.setString(5, username);
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteUser(String username) {
		String deleteSql = "DELETE FROM user WHERE username=?";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(deleteSql);
			ps.setString(1, username);
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
