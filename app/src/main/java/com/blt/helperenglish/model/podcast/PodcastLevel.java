package com.blt.helperenglish.model.podcast;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class PodcastLevel extends PodcastBaseModel {
    private String description;
}


