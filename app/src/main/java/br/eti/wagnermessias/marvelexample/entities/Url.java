
package br.eti.wagnermessias.marvelexample.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Character.class,
        parentColumns = "id",
        childColumns = "characterId",
        onDelete = CASCADE))
public class Url {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @SerializedName("type")
    private String type;

    @SerializedName("url")
    private String url;

    @SerializedName("characterId")
    private Integer characterId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }


    /**
     * No args constructor for use in serialization
     */
    public Url() {
    }

    /**
     * @param type
     * @param url
     */
    public Url(String type, String url,Integer id) {
        super();
        this.type = type;
        this.url = url;
        this.characterId = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
