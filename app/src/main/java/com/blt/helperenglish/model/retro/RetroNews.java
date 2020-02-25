package com.blt.helperenglish.model.retro;

import com.blt.helperenglish.model.news.News;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @author Fatih Bulut
 */

@Data
public class RetroNews {

    @SerializedName("content")
    @Expose
    private List<News> content = null;
}
