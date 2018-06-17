package br.eti.wagnermessias.marvelexample.entities;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Story{

	@SerializedName("originalIssue")
	@Embedded(prefix = "issue")
	private OriginalIssue originalIssue;

	@SerializedName("thumbnail")
	@Embedded(prefix = "thumbnail")
	private Thumbnail thumbnail;

	@SerializedName("description")
	private String description;

	@SerializedName("modified")
	private String modified;

	@SerializedName("id")
	@PrimaryKey
	private int id;

	@SerializedName("resourceURI")
	private String resourceURI;

	@SerializedName("title")
	private String title;

	@SerializedName("type")
	private String type;


	public OriginalIssue getOriginalIssue() {
		return originalIssue;
	}

	public void setOriginalIssue(OriginalIssue originalIssue) {
		this.originalIssue = originalIssue;
	}

	public Thumbnail getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Thumbnail thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getThumbnailImagem() {
		if(thumbnail != null) {
			return thumbnail.getPath() + "." + thumbnail.getExtension();
		}else{
			return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPFMrLBzU0SlNIevsn7uqobrroE7MN-0oUSuXfUMf-AdDm9Q-a";
		}
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
}