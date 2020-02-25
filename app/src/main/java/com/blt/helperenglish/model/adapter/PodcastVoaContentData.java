package com.blt.helperenglish.model.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fatih Bulut
 * This class is for card_podcast_voa_content.xml file.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PodcastVoaContentData {

    private int contentImage;
    private String contextHeader;
    private String contentText;
    private String audioUrl;
}
