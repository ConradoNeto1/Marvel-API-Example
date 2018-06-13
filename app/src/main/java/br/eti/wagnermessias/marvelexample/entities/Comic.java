package br.eti.wagnermessias.marvelexample.entities;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Comic {

    @SerializedName("issueNumber")
    @Expose
    private int issueNumber;

    @SerializedName("isbn")
    @Expose
    private String isbn;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("diamondCode")
    @Expose
    private String diamondCode;

    @SerializedName("ean")
    @Expose
    private String ean;

    @SerializedName("modified")
    @Expose
    private String modified;

    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;

//	@SerializedName("prices")
//	private List<xPricesItem> prices;

    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;

    @SerializedName("thumbnail")
    @Expose
    @Embedded(prefix = "thumbnail")
    private Thumbnail thumbnail;

//	@SerializedName("images")
//	private List<Object> images;

    @SerializedName("digitalId")
    @Expose
    private Integer digitalId;

    @SerializedName("format")
    @Expose
    private String format;

    @SerializedName("upc")
    @Expose
    private String upc;

//	@SerializedName("dates")
//	private List<xDatesItem> dates;

    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;

    @SerializedName("variantDescription")
    @Expose
    private String variantDescription;

    @SerializedName("issn")
    @Expose
    private String issn;


    public void setIssueNumber(Integer issueNumber) {
        this.issueNumber = issueNumber;
    }

    public Integer getIssueNumber() {
        return issueNumber;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDiamondCode(String diamondCode) {
        this.diamondCode = diamondCode;
    }

    public String getDiamondCode() {
        return diamondCode;
    }


    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getEan() {
        return ean;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getModified() {
        return modified;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setDigitalId(Integer digitalId) {
        this.digitalId = digitalId;
    }

    public Integer getDigitalId() {
        return digitalId;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getUpc() {
        return upc;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setVariantDescription(String variantDescription) {
        this.variantDescription = variantDescription;
    }

    public String getVariantDescription() {
        return variantDescription;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getIssn() {
        return issn;
    }
}