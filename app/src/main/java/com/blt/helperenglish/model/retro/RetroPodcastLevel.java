package com.blt.helperenglish.model.retro;

import com.blt.helperenglish.model.podcast.PodcastLevel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @author Fatih Bulut
 */
@Data
public class RetroPodcastLevel {
    @SerializedName("content")
    @Expose
    private List<PodcastLevel> content = null;
}
