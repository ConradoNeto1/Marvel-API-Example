package br.eti.wagnermessias.marvelexample.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(tableName = "comic_creator",
        primaryKeys = { "comicId", "creatorId" },
        foreignKeys = {
                @ForeignKey(entity = Comic.class,
                        parentColumns = "id",
                        childColumns = "comicId"),
                @ForeignKey(entity = Creator.class,
                        parentColumns = "id",
                        childColumns = "creatorId")
        })
public class ComicCreator {

    public Integer comicId;
    public Integer creatorId;

    public Integer getComicId() {
        return comicId;
    }

    public void setComicId(Integer comicId) {
        this.comicId = comicId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }
}
