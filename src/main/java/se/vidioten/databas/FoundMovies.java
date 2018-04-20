package se.vidioten.databas;

public class FoundMovies {
    private String title;

    public FoundMovies(){

    }
    public FoundMovies(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
