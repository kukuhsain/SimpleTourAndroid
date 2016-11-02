package com.kukuhsain.simpletour.guest.model.pojo;

/**
 * Created by kukuh on 08/10/16.
 */

public class Package {
    private long packageId;
    private String title;
    private String content;
    private String imageUrl;
    private String location;
    private Double price;

    public Package(long packageId, String content, String imageUrl, String location, String title, Double price) {
        this.packageId = packageId;
        this.content = content;
        this.imageUrl = imageUrl;
        this.location = location;
        this.title = title;
        this.price = price;
    }

    public long getPackageId() {
        return packageId;
    }

    public void setPackageId(long packageId) {
        this.packageId = packageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
