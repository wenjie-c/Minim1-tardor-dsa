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
    @ApiOperation(value = "set a new example", notes = "Hola k ase")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added", response= Lector.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/")
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
}
