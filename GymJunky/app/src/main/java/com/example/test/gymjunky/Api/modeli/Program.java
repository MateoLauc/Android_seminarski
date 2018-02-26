package com.example.test.gymjunky.Api.modeli;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenka on 12.2.2018..
 */

public class Program implements Serializable{
    @SerializedName("IgraciProgrami")
    @Expose
    public List<IgracProgram> igraciProgrami = null;

    public Kategorija getKategorije() {
        return kategorije;
    }

    @SerializedName("Kategorije")
    @Expose
    public Kategorija kategorije;
    @SerializedName("ProgramId")
    @Expose
    public Integer programId;
    @SerializedName("Naziv")
    @Expose
    public String naziv;
    @SerializedName("Opis")
    @Expose
    public String opis;
    @SerializedName("BrojTreninga")
    @Expose
    public String brojTreninga;
    @SerializedName("VrijemeTrajanja")
    @Expose
    public String vrijemeTrajanja;
    @SerializedName("KategorijaId")
    @Expose
    public Integer kategorijaId;

}
