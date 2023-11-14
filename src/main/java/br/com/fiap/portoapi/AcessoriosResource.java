package br.com.fiap.portoapi;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import br.com.fiap.portoapi.model.Acessorios;
import br.com.fiap.portoapi.service.AcessoriosService;

@Path("acessorios")
public class AcessoriosResource {

    AcessoriosService service = new AcessoriosService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Acessorios> index() throws ClassNotFoundException {
        return service.findAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) throws ClassNotFoundException {
        var acessorios = service.findById(id);
        if (acessorios == null) return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(acessorios).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) throws ClassNotFoundException {
        var acessorios = service.findById(id);
        if (acessorios == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.delete(acessorios);
        return Response.noContent().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Acessorios acessorios) throws ClassNotFoundException {
        if (!service.create(acessorios)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(acessorios).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") int id, Acessorios acessorios) throws ClassNotFoundException {
        var acessoriosEncontrado = service.findById(id);
        if (acessoriosEncontrado == null) return Response.status(Response.Status.NOT_FOUND).build();
        if (!service.update(acessorios)) return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.ok(acessorios).build();
    }
}
