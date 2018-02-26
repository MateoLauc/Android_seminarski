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
    public class IgraciProgramiController : ApiController
    {
        private Model db = new Model();

        // GET: api/IgraciProgrami
        public IQueryable<IgraciProgrami> GetIgraciProgrami()
        {
            return db.IgraciProgrami;
        }

        // GET: api/IgraciProgrami/5
        [ResponseType(typeof(IgraciProgrami))]
        public IHttpActionResult GetIgraciProgrami(int id)
        {
            IgraciProgrami igraciProgrami = db.IgraciProgrami.Find(id);
            if (igraciProgrami == null)
            {
                return NotFound();
            }

            return Ok(igraciProgrami);
        }

        // PUT: api/IgraciProgrami/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutIgraciProgrami(int id, IgraciProgrami igraciProgrami)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != igraciProgrami.IgraciProgrami1)
            {
                return BadRequest();
            }

            db.Entry(igraciProgrami).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!IgraciProgramiExists(id))
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

        // POST: api/IgraciProgrami
        [ResponseType(typeof(IgraciProgrami))]
        public IHttpActionResult PostIgraciProgrami(IgraciProgrami igraciProgrami)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.IgraciProgrami.Add(igraciProgrami);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = igraciProgrami.IgraciProgrami1 }, igraciProgrami);
        }

        // DELETE: api/IgraciProgrami/5
        [ResponseType(typeof(IgraciProgrami))]
        public IHttpActionResult DeleteIgraciProgrami(int id)
        {
            IgraciProgrami igraciProgrami = db.IgraciProgrami.Find(id);
            if (igraciProgrami == null)
            {
                return NotFound();
            }

            db.IgraciProgrami.Remove(igraciProgrami);
            db.SaveChanges();

            return Ok(igraciProgrami);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool IgraciProgramiExists(int id)
        {
            return db.IgraciProgrami.Count(e => e.IgraciProgrami1 == id) > 0;
        }
    }
}