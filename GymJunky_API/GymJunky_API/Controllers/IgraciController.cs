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
    public class IgraciController : ApiController
    {
        private Model db = new Model();

        // GET: api/Igraci
        public IQueryable<Igraci> GetIgraci()
        {
            return db.Igraci;
        }

        // GET: api/Igraci/5
        [ResponseType(typeof(Igraci))]
        public IHttpActionResult GetIgraci(int id)
        {
            Igraci igraci = db.Igraci.Find(id);
            if (igraci == null)
            {
                return NotFound();
            }

            return Ok(igraci);
        }

        // PUT: api/Igraci/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutIgraci(int id, Igraci igraci)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != igraci.IgracId)
            {
                return BadRequest();
            }

            db.Entry(igraci).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!IgraciExists(id))
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

        // POST: api/Igraci
        [ResponseType(typeof(Igraci))]
        public IHttpActionResult PostIgraci(Igraci igraci)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Igraci.Add(igraci);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = igraci.IgracId }, igraci);
        }

        // DELETE: api/Igraci/5
        [ResponseType(typeof(Igraci))]
        public IHttpActionResult DeleteIgraci(int id)
        {
            Igraci igraci = db.Igraci.Find(id);
            if (igraci == null)
            {
                return NotFound();
            }

            db.Igraci.Remove(igraci);
            db.SaveChanges();

            return Ok(igraci);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool IgraciExists(int id)
        {
            return db.Igraci.Count(e => e.IgracId == id) > 0;
        }
    }
}