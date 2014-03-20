package jun.movies.domain;

import java.util.Date;

public class Comment {
	private String commentId;
	private User user;
	private Movie movie;
	private String comment;
	private Date date;
	
	
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Comment(String commentId, User user, Movie movie, String comment,
			Date date) {
		super();
		this.commentId = commentId;
		this.user = user;
		this.movie = movie;
		this.comment = comment;
		this.date = date;
	}
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", user=" + user
				+ ", movie=" + movie + ", comment=" + comment + ", date="
				+ date + "]";
	}
	public Comment() {
		super();
	}
	
	
}
