package com.ejemplo.rest;

import java.util.Collections;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import controller.Dao.servicies.TransaccionServices;
import models.Proyecto;
import controller.Dao.servicies.ProyectoServices;
import controller.tda.list.LinkedList;
@Path("/proyecto")
public class ProyectoApi {
    private TransaccionServices transaccionServices = new TransaccionServices();
    @Path("/lista")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProyecto() {
        HashMap<String, Object> map = new HashMap<>();
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
public Response save(HashMap<String, Object> map) {
    HashMap<String, Object> res = new HashMap<>();

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
            transaccionServices.registrarTransaccion("Guardar Proyecto", ps.getProyecto().getIdProyecto().toString(), "Proyecto creado: " + ps.getProyecto().getNombre());
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
    HashMap<String, Object> map = new HashMap<>();
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
public Response update(HashMap<String, Object> map) {
    HashMap<String, Object> res = new HashMap<>();    
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
        transaccionServices.registrarTransaccion("Edicion Proyecto", ps.getProyecto().getIdProyecto().toString(), "Proyecto Editado: " + ps.getProyecto().getNombre());
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

@Path("/eliminar/{id}")
@DELETE
@Produces(MediaType.APPLICATION_JSON)
public Response delete(@PathParam("id") Integer id) {
    HashMap<String, Object> res = new HashMap<>();
    try {
        ProyectoServices ps = new ProyectoServices();
        ps.setProyecto(ps.get(id)); // Se obtiene el proyecto antes de eliminarlo.
        if (ps.delete(id)) { // Llama al método delete del servicio.
            transaccionServices.registrarTransaccion("Eliminar Proyecto", 
                ps.getProyecto().getIdProyecto().toString(), 
                "Proyecto Eliminado: " + ps.getProyecto().getNombre());
            res.put("msg", "ok");
            res.put("data", "Eliminado con éxito");
            return Response.ok(res).build(); // Respuesta exitosa.
        } else {
            res.put("msg", "ERROR");
            res.put("data", "Error al eliminar");
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build(); // Error en eliminación.
        }
    } catch (Exception e) {
        System.out.println("Error: " + e.toString());
        res.put("msg", "ERROR");
        res.put("data", e.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build(); // Error interno del servidor.
    }
}

// Método para ordenar los proyectos por un criterio específico y un tipo de orden (ascendente o descendente) 
// con un algoritmo de ordenamiento específico (quicksort, mergesort o shellsort).

@Path("/ordenar/{algorithm}/{tipoorden}/{criterio}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response ordenarProyectosUnificado(
    @PathParam("algorithm") String algorithm,
    @PathParam("tipoorden") Integer tipoorden,
    @PathParam("criterio") String criterio) {

    HashMap<String, Object> res = new HashMap<>();
    ProyectoServices ps = new ProyectoServices();

    try {
        LinkedList<Proyecto> proyectosOrdenados;

        if ("quicksort".equalsIgnoreCase(algorithm)) {
            proyectosOrdenados = ps.order(tipoorden, criterio);
        } else if ("mergesort".equalsIgnoreCase(algorithm)) {
            proyectosOrdenados = ps.mergeSort(tipoorden, criterio); 
        } else if ("shellsort".equalsIgnoreCase(algorithm)) {
            proyectosOrdenados = ps.shellSort(tipoorden, criterio); 
        } else {
            res.put("msg", "ERROR");
            res.put("data", "Algoritmo no válido: " + algorithm);
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
        }

        res.put("msg", "Proyectos ordenados");
        res.put("algoritmo", algorithm);
        res.put("tipo de orden", tipoorden);
        res.put("data", proyectosOrdenados.toArray());
        return Response.ok(res).build();

    } catch (Exception e) {
        res.put("msg", "ERROR");
        res.put("data", e.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
}

@Path("/buscarproyecto/{tipoBusqueda}/{criterio}/{valor}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response buscarProyectoUnificado(
    @PathParam("tipoBusqueda") String tipoBusqueda,
    @PathParam("criterio") String criterio, 
    @PathParam("valor") String valor) {
    
    HashMap<String, Object> res = new HashMap<>();
    ProyectoServices proyectoDao = new ProyectoServices();
    
    try {
        LinkedList<Proyecto> proyectoEncontrado;

        if ("binario".equalsIgnoreCase(tipoBusqueda)) {
            proyectoEncontrado = proyectoDao.buscarBinario(criterio, valor);
        } else if ("lineal".equalsIgnoreCase(tipoBusqueda)) {
            proyectoEncontrado = proyectoDao.buscarLineal(criterio, valor);
        } else {
            res.put("msg", "ERROR");
            res.put("data", "Tipo de búsqueda no válido");
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
        }

        if (proyectoEncontrado != null && !proyectoEncontrado.isEmpty()) {
            res.put("msg", "Proyecto encontrado");
            res.put("data", proyectoEncontrado);
            return Response.ok(res).build();
        } else {
            res.put("msg", "Proyecto no encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(res).build();
        }
    } catch (Exception e) {
        res.put("msg", "ERROR");
        res.put("data", e.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
}





}
   
   
