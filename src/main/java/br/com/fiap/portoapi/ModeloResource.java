package br.com.fiap.portoapi;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.portoapi.model.Modelo;
import br.com.fiap.portoapi.service.ModeloService;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("modelos")
public class ModeloResource {

    ModeloService service = new ModeloService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Modelo> index() throws ClassNotFoundException {
        return service.findAll();
    }

    @GET
    @Path("{bicicletaNrSerie}/{clienteNrCpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(
            @PathParam("bicicletaNrSerie") String bicicletaNrSerie,
            @PathParam("clienteNrCpf") String clienteNrCpf) throws SQLException, ClassNotFoundException {
        var modelo = service.findById(bicicletaNrSerie, clienteNrCpf);
        if (modelo == null) return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(modelo).build();
    }

    @DELETE
    @Path("{bicicletaNrSerie}/{clienteNrCpf}")
    public Response delete(
            @PathParam("bicicletaNrSerie") String bicicletaNrSerie,
            @PathParam("clienteNrCpf") String clienteNrCpf) throws ClassNotFoundException {
        service.delete(bicicletaNrSerie, clienteNrCpf);
        return Response.noContent().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Modelo modelo) throws ClassNotFoundException {
        if (!service.create(modelo)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(modelo).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{bicicletaNrSerie}/{clienteNrCpf}")
    public Response update(
            @PathParam("bicicletaNrSerie") String bicicletaNrSerie,
            @PathParam("clienteNrCpf") String clienteNrCpf, Modelo modelo) throws SQLException, ClassNotFoundException {
        var modeloEncontrado = service.findById(bicicletaNrSerie, clienteNrCpf);
        if (modeloEncontrado == null) return Response.status(Response.Status.NOT_FOUND).build();
        if (!service.update(modelo)) return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.ok(modelo).build();
    }
}
