package jun.movies.domain;

public class Cast {
	private Actor actor;
	private Movie movie;
	private String characterName;
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
		return "Cast [actor=" + actor + ", movie=" + movie + ", characterName="
				+ characterName + "]";
	}
	public Cast() {
		super();
	}
	public Cast(Actor actor, Movie movie, String characterName) {
		super();
		this.actor = actor;
		this.movie = movie;
		this.characterName = characterName;
	}
}
