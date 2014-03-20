package jun.movies.domain;

public class Cast {
	private String castId;
	private Actor actor;
	private Movie movie;
	private String characterName;
	
	public String getCastId() {
		return castId;
	}
	public void setCastId(String castId) {
		this.castId = castId;
	}
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	@Override
	public String toString() {
		return "Cast [castId=" + castId + ", actor=" + actor + ", movie="
				+ movie + ", characterName=" + characterName + "]";
	}
	public Cast(String castId, Actor actor, Movie movie, String characterName) {
		super();
		this.castId = castId;
		this.actor = actor;
		this.movie = movie;
		this.characterName = characterName;
	}
	public Cast() {
		super();
		// TODO Auto-generated constructor stub
	}
}
