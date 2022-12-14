/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bookaroomrestfulservice.models.Dates;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Team BookARoom
 */
@Stateless
@Path("bookaroomrestfulservice.models.dates")
public class DatesFacadeREST extends AbstractFacade<Dates> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public DatesFacadeREST() {
        super(Dates.class);
    }

    @POST
    @Path("/create")
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Dates entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Dates entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Dates find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Dates> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Dates> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    
    //get booked dates from specific room.
    @GET
    @Path("/findByRoomName/{roomName}")
    public List<Dates> findDatesByRoomName(@PathParam("roomName") String roomName) {
        return super.findDatesByRoomName("Dates.findByRoomName", "roomName", roomName);
    }
   
    
    @GET
    @Path("/removeFromDates/{dId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void removeFromDates(@PathParam("dId") Integer dId) {
        Dates d = find(dId);
        getEntityManager().remove(d);
    }
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
