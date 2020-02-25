package com.blt.helperenglish.model.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fatih Bulut
 */
@Data
@ToString
public class News {

    @SerializedName("level_1")
    @Expose
    private List<Level> level_1 = new ArrayList<>();
    @SerializedName("level_2")
    @Expose
    private List<Level> level_2 = new ArrayList<>();
    @SerializedName("level_3")
    @Expose
    private List<Level> level_3 = new ArrayList<>();

    @Data
    @ToString
    static class Level {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("content")
        @Expose
        private String content;
        @SerializedName("video_link")
        @Expose
        private String video_link;
        @SerializedName("video_image")
        @Expose
        private String video_image;
    }
}
