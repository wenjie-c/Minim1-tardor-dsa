package edu.upc.backend.services;

import edu.upc.backend.llibreria.BibliotecaManager;
import java.util.*;
import edu.upc.backend.models.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.naming.NameNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;

@Api(value = "/minimo1", description = "Endpoint to minimo1 Service")
@Path("/minimo1")
public class BibliotecaService {
    BibliotecaManager biblioteca;

    public BibliotecaService()
    {
        biblioteca = BibliotecaManager.getInstance();
    }
    @POST
    @ApiOperation(value = "Agrega un nuevo lector", notes = "Hola k ase")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added", response= Lector.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/lector")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newLector(Lector l) throws NameNotFoundException {

        try
        {
            biblioteca.addLector(l.getId(),l.getNom(),l.getCognoms(),l.getDni(),l.getNaixement(),l.getDireccio());
        }
        catch(Exception e){ return Response.status(500).entity(e.getMessage()).build();}
        return Response.status(201).entity(l).build();
    }

    @POST
    @ApiOperation(value = "Agrega un llibre en la pila", notes = "Hola k ase")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added", response= Llibre.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/llibre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newLlibre(Llibre l) throws NameNotFoundException {

        try
        {
            biblioteca.addLlibre(l.getId(),l.getISBN(),l.getTitul(),l.getEditorial(),
                    l.getPublicacio(),l.getEdicio(),l.getAutor(),l.getTematica());
        }
        catch(Exception e){ return Response.status(500).entity(e.getMessage()).build();}
        return Response.status(201).entity(l).build();
    }

    @GET
    @ApiOperation(value = "Catalogar llibres", notes = "Hola k ase")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successful catalogued"),
            @ApiResponse(code = 404, message = "Books not found", response = Exception.class)
    })
    @Path("/catalogar")
    public Response catalogar()
    {
        try {
            biblioteca.catalogarLlibre();
            return Response.status(204).build();
        }
        catch (Exception e)
        {
            return Response.status((404)).entity(e.getMessage()).build();
        }
        //return Response.status((204)).build();
    }

    @POST
    @ApiOperation(value = "Demanar un prestec", notes = "Els parámetres id, dataInici i tramit son ignorats, en canvi, el parámetre idLector corespon al nom del lector i  el camp idLlibre correspon al titul de la obra.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully added", response= Prestec.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newPrestec(Prestec p) throws NameNotFoundException {

        try
        {
            biblioteca.prestar(p.getIdLector(),p.getIdLlibre(),p.getDataFinal());
        }
        catch(Exception e){ return Response.status(500).entity(e.getMessage()).build();}
        return Response.status(204).build();
    }

    @GET
    @ApiOperation(value = "Consultar tots els prestecs", notes = "Hola k ase")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Prestec.class),
            @ApiResponse(code = 404, message = "User not found", response = Prestec.class)
    })
    @Path("/{idLector}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExample(@PathParam("idLector") String id)
    {
        try {
            Prestec[] p = biblioteca.getPrestects(id);
            GenericEntity<Prestec[]> entity = new GenericEntity<Prestec[]>(p) {};
            return Response.status(201).entity(entity).build();
        }
        catch (Exception e) { Response.status((404)).entity(e.getMessage()).build(); }
        return Response.status((201)).build();
    }

}
