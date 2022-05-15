package com.example.glacealameer.Model;

public class BollItem {
    
    String docId;
    String bollName;
    String imgURl;
    String bollType;

    public BollItem() {
    }

    public BollItem(String docId, String bollName, String imgURl, String bollType) {
        this.docId = docId;
        this.bollName = bollName;
        this.imgURl = imgURl;
        this.bollType = bollType;
    }



    public String getBollName() {
        return bollName;
    }

    public void setBollName(String bollName) {
        this.bollName = bollName;
    }

    public String getImgURl() {
        return imgURl;
    }

    public void setImgURl(String imgURl) {
        this.imgURl = imgURl;
    }

    public String getBollType() {
        return bollType;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public void setBollType(String bollType) {
        this.bollType = bollType;
    }
}
