package br.eti.wagnermessias.marvelexample.entities;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@Entity
public class Serie {


	@SerializedName("id")
	@PrimaryKey
	private Integer id;

	@SerializedName("thumbnail")
	@Embedded(prefix = "thumbnail")
	private Thumbnail thumbnail;

	@SerializedName("startYear")
	private int startYear;

	@SerializedName("rating")
	private String rating;

	@SerializedName("description")
	private String description;

	@SerializedName("resourceURI")
	private String resourceURI;

	@SerializedName("title")
	private String title;

	@SerializedName("type")
	private String type;

	@SerializedName("endYear")
	private Integer endYear;

//	@SerializedName("urls")
//	private List<UrlsItem> urls;

	@SerializedName("modified")
	private String modified;

	public Thumbnail getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Thumbnail thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getThumbnailImagem() {
		return thumbnail.getPath() +"."+ thumbnail.getExtension();
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResourceURI() {
		return resourceURI;
	}

	public void setResourceURI(String resourceURI) {
		this.resourceURI = resourceURI;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getEndYear() {
		return endYear;
	}

	public void setEndYear(Integer endYear) {
		this.endYear = endYear;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}