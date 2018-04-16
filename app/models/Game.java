package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Game extends Model {

    @Id
    private String id;
    private String title;
    @Column(columnDefinition = "LONGVARCHAR")
    private String description;
    private List<String> gameTags;
    @ManyToMany
    private List<Media> media;
    private double price;
    private double rating;
    private double discount;

    @OneToMany(mappedBy = "game")
    private List<Thread> threads;

    private static Finder<String, Game> finder = new Finder<>(Game.class);

    public Game(String id, String title, String description, List<String> gameTags, double price, double rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.gameTags = gameTags;
        this.price = price;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getGameTags() {
        return gameTags;
    }

    public void setGameTags(List<String> gameTags) {
        this.gameTags = gameTags;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }

    public static Finder<String, Game> getFinder() {
        return finder;
    }

    public static Game get(String id) {
        return finder.ref(id);
    }

    public static List<Game> all() {
        return finder.all();
    }

    public static List<Game> search(String query, String price, String rating) {
        return Game.finder.query().where()
                .ilike("title", String.format("%%%s%%", query))
                .gt("rating", rating)
                .lt("price", price)
                .findList();
    }
}
