namespace GymJunky_API.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("IgraciProgrami")]
    public partial class IgraciProgrami
    {
        [Key]
        [Column("IgraciProgrami")]
        public int IgraciProgrami1 { get; set; }

        public int? IgracId { get; set; }

        public int? ProgramId { get; set; }

        public virtual Igraci Igraci { get; set; }

        public virtual Programi Programi { get; set; }
    }
}
