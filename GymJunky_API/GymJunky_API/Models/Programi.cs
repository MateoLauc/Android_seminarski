namespace GymJunky_API.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Programi")]
    public partial class Programi
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Programi()
        {
            IgraciProgrami = new HashSet<IgraciProgrami>();
        }

        [Key]
        public int ProgramId { get; set; }

        public string Naziv { get; set; }

        public string Opis { get; set; }

        public string BrojTreninga { get; set; }

        public string VrijemeTrajanja { get; set; }

        public int? KategorijaId { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<IgraciProgrami> IgraciProgrami { get; set; }

        public virtual Kategorije Kategorije { get; set; }
    }
}
