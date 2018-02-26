namespace GymJunky_API.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Igraci")]
    public partial class Igraci
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Igraci()
        {
            IgraciProgrami = new HashSet<IgraciProgrami>();
        }

        [Key]
        public int IgracId { get; set; }

        public string KorisnickoIme { get; set; }

        public string Lozinka { get; set; }

        public string Email { get; set; }

        public int? Visina { get; set; }

        public int? Tezina { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<IgraciProgrami> IgraciProgrami { get; set; }
    }
}
