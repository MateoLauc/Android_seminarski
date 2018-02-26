package com.example.test.gymjunky.Api.modeli;

/**
 * Created by lenka on 16.2.2018..
 */
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kategorija implements Serializable {

        @SerializedName("Programi")
        @Expose
        public List<Program> programi = null;
        @SerializedName("KategorijaId")
        @Expose
        public Integer kategorijaId;
        @SerializedName("Naziv")
        @Expose
        public String naziv;
}
