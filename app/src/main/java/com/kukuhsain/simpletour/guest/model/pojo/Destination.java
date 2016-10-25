package com.kukuhsain.simpletour.guest.model.pojo;

/**
 * Created by kukuh on 08/10/16.
 */

public class Destination {
    private long destinationId;
    private String title;
    private String content;
    private String imageUrl;
    private String location;

    public Destination() {
    }

    public Destination(long destinationId, String title, String content, String imageUrl, String location) {
        this.destinationId = destinationId;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.location = location;
    }

    public long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(long destinationId) {
        this.destinationId = destinationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
