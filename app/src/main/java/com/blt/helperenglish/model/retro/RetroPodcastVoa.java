package com.blt.helperenglish.model.retro;

import com.blt.helperenglish.model.podcast.PodcastVoa;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @author Fatih Bulut
 */

@Data
public class RetroPodcastVoa {

    @SerializedName("content")
    @Expose
    private List<PodcastVoa> content = null;
}
