package com.example.test.gymjunky.Api.modeli;

/**
 * Created by lenka on 16.2.2018..
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IgracProgram  implements Serializable {
        @SerializedName("Igraci")
        @Expose
        public Igrac igraci;
        @SerializedName("Programi")
        @Expose
        public Program programi;
        @SerializedName("IgraciProgrami1")
        @Expose
        public Integer igraciProgrami1;
        @SerializedName("IgracId")
        @Expose
        public Integer igracId;
        @SerializedName("ProgramId")
        @Expose
        public Integer programId;
}
