package jun.movies.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jun.movies.domain.Actor;
import jun.movies.domain.Cast;
import jun.movies.domain.Comment;
import jun.movies.domain.Movie;
import jun.movies.domain.User;

public class TestServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
////			response.getWriter().print(UserManager.getConnection().getClass().getName());
			
			// UserManager test!
//			User user = new User("asdf", "asd", "asd", "as", new Date());
//			UserManager um = new UserManager();
////			um.createUser(user);
//			List<User> list = um.readAllUsers();
//			for (User user2 : list) {
//				System.out.println(user2.getUsername());
//			}
//			System.out.println("Test readUser:" + um.readUser("asd").getFirstName());
//			user.setUsername("asd");
//			user.setFirstName("jon");
//			um.updateUser("asd", user);
//			System.out.println("Test updateUser:" + um.readUser("asd").getFirstName());
//			um.deleteUser("asd");
			
			// ActorManager test!
//			ActorManager am = new ActorManager();
//			Actor actor = new Actor("a2", "jj", "jj", new Date());
////			am.createActor(actor);
////			List<Actor> list = am.readAllActors();
////			for (Actor actor2 : list) {
////				System.out.println(actor2.getFirstName());
////			}
//			System.out.println("test readActor: " + am.readActor("a2").getFirstName());
//			actor.setFirstName("af");
//			am.updateActor("a2", actor);
//			System.out.println("test updateActor: " + am.readActor("a2").getFirstName());
//			am.deleteActor("a2");
			
			
			// MovieManager test!
//			MovieManager mm = new MovieManager();
//			Movie movie = new Movie("m1", "titanic", "123.jpg", new Date());
//			mm.createMovie(movie);
//			List<Movie> list = mm.readAllMovies();
//			for (Movie movie2 : list) {
//				System.out.println(movie2.getTitle());
//			}
//			System.out.println("test readMovie: " + mm.readMovie("m1").getTitle());
//			movie.setTitle("shark");
//			mm.updateMovie("m1", movie);
//			System.out.println("test readMovie: " + mm.readMovie("m1").getTitle());
//			mm.deleteMovie("m1");
			
			// CastManager test!
//			CastManager cm = new CastManager();
//			ActorManager am = new ActorManager();
//			MovieManager mm = new MovieManager();
//			Actor actor = am.readActor("a1");
//			Movie movie = mm.readMovie("m1");
//			Cast cast = new Cast("c1", actor, movie, "manion");
////			cm.createCast(cast);
//			List<Cast> list = cm.readAllCasts();
//			for (Cast cast2 : list) {
//				System.out.println("test the readAllCasts: " + cast2.getCharacterName());
//			}
//			System.out.println("test the readCast method: " + cm.readCastForId("c1"));
//			cast.setCharacterName("master");
//			cm.updateCast("c1", cast);
//			System.out.println("test the updateCast method: " + cm.readCastForId("c1"));
//			cm.deleteCast("c1");
			
			// ComementManager test!
//			MovieManager mm = new MovieManager();
//			UserManager um = new UserManager();
//			CommentManager cm = new CommentManager();
//			
//			User user = um.readUser("asdf");
//			Movie movie = mm.readMovie("m1");
//			Comment comment = new Comment("cm1", user, movie, "hahahaha", new Date());
//			cm.createComment(comment);
//			List<Comment> list = cm.readAllComments();
//			for (Comment comment2 : list) {
//				System.out.println("test readAllComments: " + comment2.getComment());
//			}
//			System.out.println("test readComment: " + cm.readCommentForId("cm1"));
//			comment.setComment("hehehehe");
//			cm.updateComment("cm1", comment);
//			System.out.println("test updateComement: " + cm.readCommentForId("cm1"));
//			cm.deleteComment("cm1");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

}
