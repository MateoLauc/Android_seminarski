using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using GymJunky_API.Models;

namespace GymJunky_API.Controllers
{
    public class ProgramiController : ApiController
    {
        private Model db = new Model();

        // GET: api/Programi
        public IQueryable<Programi> GetProgrami()
        {
            return db.Programi;
        }

        // GET: api/Programi/5
        [ResponseType(typeof(Programi))]
        public IHttpActionResult GetProgrami(int id)
        {
            Programi programi = db.Programi.Find(id);
            if (programi == null)
            {
                return NotFound();
            }

            return Ok(programi);
        }

        // PUT: api/Programi/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutProgrami(int id, Programi programi)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != programi.ProgramId)
            {
                return BadRequest();
            }

            db.Entry(programi).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ProgramiExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/Programi
        [ResponseType(typeof(Programi))]
        public IHttpActionResult PostProgrami(Programi programi)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Programi.Add(programi);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = programi.ProgramId }, programi);
        }

        // DELETE: api/Programi/5
        [ResponseType(typeof(Programi))]
        public IHttpActionResult DeleteProgrami(int id)
        {
            Programi programi = db.Programi.Find(id);
            if (programi == null)
            {
                return NotFound();
            }

            db.Programi.Remove(programi);
            db.SaveChanges();

            return Ok(programi);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool ProgramiExists(int id)
        {
            return db.Programi.Count(e => e.ProgramId == id) > 0;
        }
    }
}