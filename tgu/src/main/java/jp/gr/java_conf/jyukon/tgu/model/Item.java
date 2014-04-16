package jp.gr.java_conf.jyukon.tgu.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.Data;

@Data
public class Item {
    int id;
    String title;
    String description;
    @SerializedName("image_urls") ArrayList<HashMap<String, String>> imageUrls;
    ArrayList<Place> places;
}
