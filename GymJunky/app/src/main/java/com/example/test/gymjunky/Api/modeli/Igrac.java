package com.example.test.gymjunky.Api.modeli;

/**
 * Created by lenka on 16.2.2018..
 */
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Igrac implements Serializable {

        @SerializedName("IgraciProgrami")
        @Expose
        public List<IgracProgram> igraciProgrami = null;
        @SerializedName("IgracId")
        @Expose
        public Integer igracId;
        @SerializedName("KorisnickoIme")
        @Expose
        public String korisnickoIme;
        @SerializedName("Lozinka")
        @Expose
        public String lozinka;
        @SerializedName("Email")
        @Expose
        public String email;
        @SerializedName("Visina")
        @Expose
        public Integer visina;
        @SerializedName("Tezina")
        @Expose
        public Integer tezina;
}
