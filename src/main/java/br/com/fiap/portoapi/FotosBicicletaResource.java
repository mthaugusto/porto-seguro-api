package br.com.fiap.portoapi;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.portoapi.model.FotosBicicleta;
import br.com.fiap.portoapi.service.FotosBicicletaService;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("fotos-bicicleta")
public class FotosBicicletaResource {

    FotosBicicletaService service = new FotosBicicletaService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FotosBicicleta> index() throws ClassNotFoundException {
        return service.findAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
        var fotosBicicleta = service.findById(id);
        if (fotosBicicleta == null) return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(fotosBicicleta).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) throws ClassNotFoundException {
        var fotosBicicleta = service.findById(id);
        if (fotosBicicleta == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.delete(fotosBicicleta);
        return Response.noContent().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(FotosBicicleta fotosBicicleta) throws ClassNotFoundException {
        if (!service.create(fotosBicicleta)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(fotosBicicleta).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") int id, FotosBicicleta fotosBicicleta) throws SQLException, ClassNotFoundException {
        var fotosBicicletaEncontrada = service.findById(id);
        if (fotosBicicletaEncontrada == null) return Response.status(Response.Status.NOT_FOUND).build();
        if (!service.update(fotosBicicleta)) return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.ok(fotosBicicleta).build();
    }
}
