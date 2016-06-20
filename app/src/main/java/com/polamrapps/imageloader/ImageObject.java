package com.polamrapps.imageloader;

/**
 * Created by Rajareddy on 17/06/16.
 */
public class ImageObject {

    public ImageObject(String _imageId,String _fileName, String _imageUrl) {
        this.fileName = _fileName;
        this.imageId = _imageId;
        this.imageUrl = _imageUrl;
    }

    public String getImageId() {
        return imageId;
    }

//    public void setImageId(String imageId) {
//        this.imageId = imageId;
//    }
//
    public String getFileName() {
        return fileName;
    }

//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }

    public String getImageUrl() {
        return imageUrl;
    }

    //public void setImageUrl(String _imageUrl) {
    //    imageUrl = _imageUrl;
    //}

    private String imageId;
    private String fileName;
    private String imageUrl;
}
