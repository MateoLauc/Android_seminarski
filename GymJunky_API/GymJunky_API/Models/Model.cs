namespace GymJunky_API.Models
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class Model : DbContext
    {
        public Model()
            : base("name=MyConnectionString")
        {
            this.Configuration.LazyLoadingEnabled = false;
        }

        public virtual DbSet<Igraci> Igraci { get; set; }
        public virtual DbSet<IgraciProgrami> IgraciProgrami { get; set; }
        public virtual DbSet<Kategorije> Kategorije { get; set; }
        public virtual DbSet<Programi> Programi { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
        }
    }
}
