package com.example.articleapps.Function.MusicList;

/**
 * Created by mitaly on 4/6/17.
 */

public class SongObject {

    String fileName;
    String absolutePath;
    int ImageView;

    public SongObject(String fileName, String absolutePath, int ImageView) {
        this.fileName = fileName;
        this.absolutePath=absolutePath;
        this.ImageView=ImageView;
    }

    public int getImageView() {
        return ImageView;
    }

    public void setImageView(int imageView) {
        ImageView = imageView;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }
}
