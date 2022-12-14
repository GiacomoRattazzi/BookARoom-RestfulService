/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bookaroomrestfulservice.models.Reservations;
import bookaroomrestfulservice.models.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
 * @author Giac
 */
@Stateless
@Path("bookaroomrestfulservice.models.users")
public class UsersFacadeREST extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public UsersFacadeREST() {
        super(Users.class);
    }

    @POST
    @Path("/create")
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Users entity) {
        super.create(entity);
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Users entity) {
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
    public Users find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findAll() {
        return super.findAll();
    }
    
    //new
    
    @GET
    @Path("/findByName/{name}")
    public Users findByName(@PathParam("name") String userName) {
        return super.findByName("Users.findByUsername", "username", userName);
    }

    @GET
    @Path("/emailExists/{email}")
    public boolean emailExists(@PathParam("email") String email) {
        Query query = em.createNamedQuery("Users.findByEmail");
        List<Users> results = query.setParameter("email", email).getResultList();
        return results.size() == 1;
    }
    
    @GET
    @Path( "/addToReservationUser/{uId}/{rId}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void addToReservationUser(@PathParam("uId") Integer uId, @PathParam("rId") Integer rId) {
        Reservations r = getEntityManager().find(Reservations.class, rId);
        Users u = find(uId);
        u.getReservationsList().add(r);
        getEntityManager().merge(u);
    }
    
    
    
    @GET
    @Path("/getReservations/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Reservations> getReservations(@PathParam("id") Integer id) {
        return find(id).getReservationsList();
    }
   
    @GET
    @Path("/removeFromReservations/{uId}/{rId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void removeFromReservations(@PathParam("uId") Integer uId, @PathParam("rId") Integer rId) {
        Reservations r = getEntityManager().find(Reservations.class, rId);
        Users u = find(uId);
        u.getReservationsList().remove(r);
        getEntityManager().merge(u);
    }
 
    
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    
    
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
