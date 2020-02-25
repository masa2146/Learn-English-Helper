package com.blt.helperenglish.model.adapter;

import com.blt.helperenglish.constant.PodcastType;
import com.blt.helperenglish.constant.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fatih Bulut
 * This class is for card_podcast_page.xml file.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagePodcastData {

    private int contentImage;
    private String contextHeader;
    private String contentText;
    private PodcastType podcastType;
    private ResponseType responseType;
}
