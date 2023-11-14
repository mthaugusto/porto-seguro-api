package br.com.fiap.portoapi;

import java.util.List;

import br.com.fiap.portoapi.model.Bicicleta;
import br.com.fiap.portoapi.service.BicicletaService;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("bicicletas")
public class BicicletaResource {

    BicicletaService service = new BicicletaService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bicicleta> index() throws ClassNotFoundException {
        return service.findAll();
    }

    @GET
    @Path("{nrSerie}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorNrSerie(@PathParam("nrSerie") String nrSerie) throws ClassNotFoundException {
        var bicicleta = service.findByNrSerie(nrSerie);
        if (bicicleta == null) return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(bicicleta).build();
    }

    @DELETE
    @Path("{nrSerie}")
    public Response delete(@PathParam("nrSerie") String nrSerie) throws ClassNotFoundException {
        var bicicleta = service.findByNrSerie(nrSerie);
        if (bicicleta == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.delete(bicicleta);
        return Response.noContent().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Bicicleta bicicleta) throws ClassNotFoundException {
        if (!service.create(bicicleta)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(bicicleta).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{nrSerie}")
    public Response update(@PathParam("nrSerie") String nrSerie, Bicicleta bicicleta) throws ClassNotFoundException {
        var bicicletaEncontrada = service.findByNrSerie(nrSerie);
        if (bicicletaEncontrada == null) return Response.status(Response.Status.NOT_FOUND).build();
        if (!service.update(bicicleta)) return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.ok(bicicleta).build();
    }
}
