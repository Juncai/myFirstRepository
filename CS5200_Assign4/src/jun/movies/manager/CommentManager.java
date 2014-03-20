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

import jun.movies.domain.Comment;
import jun.movies.domain.Movie;
import jun.movies.domain.User;

public class CommentManager {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	DataSource ds;

	public CommentManager() {
		Context jndi;
		try {
			jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/movies");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void createComment(Comment comment) {
		String createCommentSql = "INSERT INTO comment VALUES(?,?,?,?,?);";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(createCommentSql);
			ps.setString(1, comment.getCommentId());
			ps.setString(2, comment.getUser().getUsername());
			ps.setString(3, comment.getMovie().getId());
			ps.setString(4, comment.getComment());
			ps.setDate(5, new java.sql.Date(comment.getDate().getTime()));
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	List<Comment> readAllComments() {
		String readAllSql = "SELECT * FROM comment";
		List<Comment> comments = new ArrayList<Comment>();
		UserManager userManager = new UserManager();
		MovieManager movieManager = new MovieManager();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(readAllSql);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = userManager.readUser(rs.getString("username"));
				Movie movie = movieManager.readMovie(rs.getString("movieId"));
				Comment comment = new Comment(rs.getString("commentId"), user,
						movie, rs.getString("content"),
						rs.getDate("date"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return comments;
	}
	
	List<Comment> readAllCommentsForUsername(String username) {
		String readAllCommentsForUsernameSql = "SELECT * FROM comment WHERE username=?";
		List<Comment> comments = new ArrayList<Comment>();
		UserManager userManager = new UserManager();
		MovieManager movieManager = new MovieManager();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(readAllCommentsForUsernameSql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = userManager.readUser(rs.getString("username"));
				Movie movie = movieManager.readMovie(rs.getString("movieId"));
				Comment comment = new Comment(rs.getString("commentId"), user,
						movie, rs.getString("content"),
						rs.getDate("date"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return comments;
	}
	
	List<Comment> readAllCommentsForMovie(String movieId) {
		String readAllCommentsForMovieSql = "SELECT * FROM comment WHERE movieId=?";
		List<Comment> comments = new ArrayList<Comment>();
		UserManager userManager = new UserManager();
		MovieManager movieManager = new MovieManager();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(readAllCommentsForMovieSql);
			ps.setString(1, movieId);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = userManager.readUser(rs.getString("username"));
				Movie movie = movieManager.readMovie(rs.getString("movieId"));
				Comment comment = new Comment(rs.getString("commentId"), user,
						movie, rs.getString("content"),
						rs.getDate("date"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return comments;
	}
	
	public Comment readCommentForId(String commentId) {
		String readCommentForIdSql = "SELECT * FROM comment WHERE commentId=?";
		UserManager userManager = new UserManager();
		MovieManager movieManager = new MovieManager();
		Comment comment = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(readCommentForIdSql);
			ps.setString(1, commentId);
			rs = ps.executeQuery();
			User user = userManager.readUser(rs.getString("username"));
			Movie movie = movieManager.readMovie(rs.getString("movieId"));
			comment = new Comment(rs.getString("commentId"), user,
					movie, rs.getString("content"),
					rs.getDate("date"));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return comment;
	}
	
	void updateComment(String commentId, Comment newComment) {
		String updateSql = "UPDATE comment SET username=? movieId=? content=? date=? WHERE commentId=?";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(updateSql);
			ps.setString(1, newComment.getUser().getUsername());
			ps.setString(2, newComment.getMovie().getId());
			ps.setString(3, newComment.getComment());
			ps.setDate(4, new java.sql.Date(newComment.getDate().getTime()));
			ps.setString(5, newComment.getCommentId());
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	void deleteComment(String commentId) {
		String deleteSql = "DELETE FROM comment WHERE commentId=?";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(deleteSql);
			ps.setString(1, commentId);
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
