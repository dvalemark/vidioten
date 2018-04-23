package se.vidioten.databas;

public class FoundMovie {
    private String title;
    private String imdbId;

    public FoundMovie(){

    }
    public FoundMovie(String title, String imdbId) {
        this.imdbId = imdbId;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }
}
