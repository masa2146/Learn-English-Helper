package com.blt.helperenglish.model.adapter;

import androidx.fragment.app.Fragment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fatih Bulut
 * This class is for card_main_page.xml file.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageMainData {

    private int contentImage;
    private String contextHeader;
    private String contentText;
    private Fragment fragment;
}

