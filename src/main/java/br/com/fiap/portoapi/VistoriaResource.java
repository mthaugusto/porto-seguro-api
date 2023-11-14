package br.com.fiap.portoapi;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.portoapi.model.Vistoria;
import br.com.fiap.portoapi.service.VistoriaService;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("vistorias")
public class VistoriaResource {

    VistoriaService service = new VistoriaService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vistoria> index() throws ClassNotFoundException {
        return service.findAll();
    }

    @GET
    @Path("{bicicletaNrSerie}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("bicicletaNrSerie") String bicicletaNrSerie) throws SQLException, ClassNotFoundException {
        var vistoria = service.findBySerieNr(bicicletaNrSerie);
        if (vistoria == null) return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(vistoria).build();
    }

    @DELETE
    @Path("{bicicletaNrSerie}")
    public Response delete(@PathParam("bicicletaNrSerie") String bicicletaNrSerie) throws ClassNotFoundException {
        var vistoria = service.findBySerieNr(bicicletaNrSerie);
        if (vistoria == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.delete(vistoria);
        return Response.noContent().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Vistoria vistoria) throws ClassNotFoundException {
        if (!service.create(vistoria)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(vistoria).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{bicicletaNrSerie}")
    public Response update(@PathParam("bicicletaNrSerie") String bicicletaNrSerie, Vistoria vistoria) throws SQLException, ClassNotFoundException {
        var vistoriaEncontrada = service.findBySerieNr(bicicletaNrSerie);
        if (vistoriaEncontrada == null) return Response.status(Response.Status.NOT_FOUND).build();
        if (!service.update(vistoria)) return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.ok(vistoria).build();
    }
}
