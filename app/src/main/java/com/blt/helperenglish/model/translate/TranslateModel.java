package com.blt.helperenglish.model.translate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

/**
 * @author Fatih Bulut
 * https://script.google.com/macros/s/AKfycbxZFDQ4X8SpTafWnQ2p_CCib-LP4WaBGRA69p75EtbpzQaQRoRH/exec
 */
@Data
@ToString
public class TranslateModel {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("source")
    @Expose
    private String source;

}
