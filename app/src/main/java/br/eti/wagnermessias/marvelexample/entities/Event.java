package br.eti.wagnermessias.marvelexample.entities;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Event{

	@SerializedName("thumbnail")
	@Expose
	@Embedded(prefix = "thumbnail")
	private Thumbnail thumbnail;

	@SerializedName("start")
	@Expose
	private String start;

	@SerializedName("description")
	@Expose
	private String description;

	@SerializedName("resourceURI")
	@Expose
	private String resourceURI;

	@SerializedName("title")
	@Expose
	private String title;

	@SerializedName("modified")
	@Expose
	private String modified;

	@SerializedName("end")
	@Expose
	private String end;

	@SerializedName("id")
	@Expose
	@PrimaryKey
	private Integer id;

	public Thumbnail getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Thumbnail thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getThumbnailImagem() {
		return thumbnail.getPath() +"."+ thumbnail.getExtension();
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
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

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}