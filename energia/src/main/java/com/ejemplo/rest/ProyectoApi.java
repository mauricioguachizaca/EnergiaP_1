package com.ejemplo.rest;

import java.util.Collections;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import controller.Dao.servicies.ProyectoServices;
@Path("/proyecto")
public class ProyectoApi {
    @Path("/lista")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProyecto() {
        HashMap map = new HashMap<>();
        ProyectoServices ps = new ProyectoServices();
        map.put("msg", "Lista de proyectos");
        map.put("data", ps.listAll().toArray());
        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[]{});
        }
        return Response.ok(map).build();		
    }

@Path("/guardar")
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response save(HashMap map) {
    HashMap res = new HashMap<>();

    ProyectoServices ps = new ProyectoServices();
    
    // Validación de datos
    if (map.get("nombre") == null || map.get("inversion") == null || 
        map.get("tiempodevida") == null || map.get("fechaInicio") == null || 
        map.get("fechaFin") == null || map.get("electicidadGeneradapordia") == null ||
        map.get("acronimo") == null || map.get("costototal") == null) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Collections.singletonMap("error", "Faltan datos requeridos"))
                .build();
    }

    try {
        ps.getProyecto().setNombre(map.get("nombre").toString());
        ps.getProyecto().setInversion(Float.parseFloat(map.get("inversion").toString()));
        ps.getProyecto().setTiempodevida(Integer.parseInt(map.get("tiempodevida").toString()));
        ps.getProyecto().setFechaInicio(map.get("fechaInicio").toString());
        ps.getProyecto().setFechaFin(map.get("fechaFin").toString());
        ps.getProyecto().setElecticidadGeneradapordia(Float.parseFloat(map.get("electicidadGeneradapordia").toString()));
        ps.getProyecto().setAcronimo(map.get("acronimo").toString());
        ps.getProyecto().setCostototal(Float.parseFloat(map.get("costototal").toString()));
        
        if (ps.save()) {
            res.put("msg", "ok");
            res.put("data", "Guardado con éxito");
            return Response.ok(res).build();
        } else {
            res.put("msg", "ERROR");
            res.put("data", "Error al guardar");
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
        }
    } catch (Exception ex) {
        res.put("msg", "ERROR");
        res.put("data", ex.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
}


@Path("/lista/{id}")
@GET	
@Produces(MediaType.APPLICATION_JSON)
public Response getProyec(@PathParam("id") Integer id) {
    HashMap map = new HashMap<>();
    ProyectoServices ps = new ProyectoServices();
    try {
        ps.setProyecto(ps.get(id));
    } catch (Exception e) {
    }
    map.put("msg", "Proyecto");
    map.put("data", ps.getProyecto());
    if(ps.getProyecto().getIdProyecto() == null) {
        map.put("data", "No existe el proyecto");
        return Response.status(Response.Status.NOT_FOUND).entity(map).build();
    }
        return Response.ok(map).build();
    }

@Path("/editar")
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response update(HashMap map) {
    HashMap res = new HashMap<>();    
    try {
        ProyectoServices ps = new ProyectoServices();
        ps.setProyecto(ps.get(Integer.parseInt(map.get("idProyecto").toString())));
        ps.getProyecto().setNombre(map.get("nombre").toString());
        ps.getProyecto().setInversion(Float.parseFloat(map.get("inversion").toString()));
        ps.getProyecto().setTiempodevida(Integer.parseInt(map.get("tiempodevida").toString()));
        ps.getProyecto().setFechaInicio(map.get("fechaInicio").toString());
        ps.getProyecto().setFechaFin(map.get("fechaFin").toString());
        ps.getProyecto().setElecticidadGeneradapordia(Float.parseFloat(map.get("electicidadGeneradapordia").toString()));
        ps.getProyecto().setAcronimo(map.get("acronimo").toString());
        ps.getProyecto().setCostototal(Float.parseFloat(map.get("costototal").toString()));
        
        ps.update();
        res.put("msg", "ok");
        res.put("data", "Editado con éxito");
        return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            res.put("msg", "ERROR");
            res.put("data", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
   
    
    }
}
   
   


 



























