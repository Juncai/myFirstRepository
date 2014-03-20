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
		String createUserSql = "INSERT INTO comment VALUES(?,?,?,?);";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(createUserSql);
			ps.setString(1, comment.getUser().getUsername());
			ps.setString(2, comment.getMovie().getId());
			ps.setString(3, comment.getComment());
			ps.setDate(4, new java.sql.Date(comment.getDate().getTime()));
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	List<Comment> readAllComments() {
		String readAllSql = "SELECT * FROM comment";
		List<Comment> comments = new ArrayList<Comment>();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(readAllSql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment(rs.getString("id"),
						rs.getString("title"), rs.getString("posterImage"),
						rs.getDate("releaseDate"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return movies;
	}
	
	List<Comment> readAllCommentsForUsername(String username) {
		
	}
	
	List<Comment> readAllCommentsForMovie(String movieId) {
		
	}
	
	public Comment readCommentForId(String commentId) {
		
	}
	
	void updateComment(String commentId, Comment newComment) {
		
	}
	
	void deleteComment(String commentId) {
		
	}
}
