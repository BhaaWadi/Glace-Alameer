package com.example.glacealameer.Model;

public class Offer {

    private String docId;
    private String title;
    private String subtitle;
    private String image;


    public Offer() {
    }

    public Offer(String docId, String title, String subtitle, String image) {
        this.docId = docId;
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;

    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
