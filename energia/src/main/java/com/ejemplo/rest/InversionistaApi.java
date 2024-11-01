package com.ejemplo.rest;

import java.util.HashMap;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import controller.Dao.servicies.InversionistaServices;
@Path("/inversionista")
public class InversionistaApi {
   @Path("/listai")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getAllInversionista() {
       HashMap map = new HashMap<>();
       InversionistaServices is = new InversionistaServices();
       map.put("msg", "Lista de inversionistas");
       map.put("data", is.listAll().toArray());
       return Response.ok(map).build();		
   }

    @Path("/guardari")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap map) {
        InversionistaServices is = new InversionistaServices();
        is.getInversionista().setNombre(map.get("nombre").toString());
        is.getInversionista().setApellido(map.get("apellido").toString());
        is.getInversionista().setDni(map.get("dni").toString());
        is.getInversionista().setTelefono(map.get("telefono").toString());
        HashMap res = new HashMap<>();
        try {
            if(is.save()){
            res.put("msg", "ok");
            res.put("data", "Guardado con exito");
            }else{
            res.put("msg", "ERROR");
            res.put("data", "Error al guardar");
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
            }
        } catch (Exception ex) {
            res.put("msg", "ERROR");
            res.put("data", ex.getMessage());   
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
        return Response.ok(res).build();
        }
}
