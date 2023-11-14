package br.com.fiap.portoapi;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.portoapi.model.Marca;
import br.com.fiap.portoapi.service.MarcaService;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("marcas")
public class MarcaResource {

    MarcaService service = new MarcaService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Marca> index() throws ClassNotFoundException {
        return service.findAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
        var marca = service.findById(id);
        if (marca == null) return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(marca).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) throws ClassNotFoundException {
        var marca = service.findById(id);
        if (marca == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.delete(marca);
        return Response.noContent().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Marca marca) throws ClassNotFoundException {
        if (!service.create(marca)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(marca).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") int id, Marca marca) throws SQLException, ClassNotFoundException {
        var marcaEncontrada = service.findById(id);
        if (marcaEncontrada == null) return Response.status(Response.Status.NOT_FOUND).build();
        if (!service.update(marca)) return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.ok(marca).build();
    }
}
