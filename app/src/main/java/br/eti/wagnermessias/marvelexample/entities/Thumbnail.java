
package br.eti.wagnermessias.marvelexample.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnail {

    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("extension")
    @Expose
    private String extension;

    /**
     * No args constructor for use in serialization
     * 
     */
//    public Thumbnail() {
//    }

//    /**
//     *
//     * @param extension
//     * @param path
//     */
//    public Thumbnail(String path, String extension) {
//        super();
//        this.path = path;
//        this.extension = extension;
//    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

}
