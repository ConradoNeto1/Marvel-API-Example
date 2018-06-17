package br.eti.wagnermessias.marvelexample.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Wagner on 23/04/2018.
 */
@Entity
public class Character {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("modified")
    @Expose
    private String modified;

    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;

    @SerializedName("thumbnail")
    @Expose
    @Embedded(prefix = "thumbnail")
    private Thumbnail thumbnail;

    @SerializedName("urls")
    @Expose
    @Ignore
    private List<Url> urls = null;

    /**
     * No args constructor for use in serialization
     *
     */
//    public Character() {
//    }
//    public Character(String name,String imagem) {
//        this.name = name;
//                this.imagem = imagem;
//    }

//    /**
//     *
//     * @param id
//     * @param series
//     * @param stories
//     * @param thumbnail
//     * @param resourceURI
//     * @param urls
//     * @param events
//     * @param description
//     * @param name
//     * @param comics
//     * @param modified
//     */
//    public Character(Integer id, String name, String description, String modified, Thumbnail thumbnail, String resourceURI, Comic comics, Series series, Stories stories, Events events, List<Url> urls) {
//        super();
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.modified = modified;
////        this.thumbnail = thumbnail;
////        this.resourceURI = resourceURI;
////        this.comics = comics;
////        this.series = series;
////        this.stories = stories;
////        this.events = events;
////        this.urls = urls;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnailImagem() {
        return thumbnail.getPath() +"."+ thumbnail.getExtension();
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

//    public Comic getComics() {
//        return comics;
//    }
//
//    public void setComics(Comic comics) {
//        this.comics = comics;
//    }
//
//    public Series getSeries() {
//        return series;
//    }
//
//    public void setSeries(Series series) {
//        this.series = series;
//    }
//
//    public Stories getStories() {
//        return stories;
//    }

//    public void setStories(Stories stories) {
//        this.stories = stories;
//    }
//
//    public Events getEvents() {
//        return events;
//    }
//
//    public void setEvents(Events events) {
//        this.events = events;
//    }


}
