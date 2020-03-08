package com.blt.helperenglish.model.translate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Fatih Bulut
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Language {

    private String langCode;
    private String language;

    @Override
    public String toString() {
        return language;
    }

}
