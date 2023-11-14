package br.com.fiap.portoapi;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import br.com.fiap.portoapi.model.Cliente;
import br.com.fiap.portoapi.service.ClienteService;

@Path("clientes")
public class ClienteResource {

    ClienteService service = new ClienteService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> index() throws ClassNotFoundException {
        return service.findAll();
    }

    @GET
    @Path("{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorCpf(@PathParam("cpf") String cpf) throws ClassNotFoundException {
        var cliente = service.findByCpf(cpf);
        if (cliente == null) return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(cliente).build();
    }

    @DELETE
    @Path("{cpf}")
    public Response delete(@PathParam("cpf") String cpf) throws ClassNotFoundException {
        var cliente = service.findByCpf(cpf);
        if (cliente == null) return Response.status(Response.Status.NOT_FOUND).build();
        service.delete(cliente);
        return Response.noContent().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Cliente cliente) throws ClassNotFoundException {
        if (!service.create(cliente)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(cliente).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{cpf}")
    public Response update(@PathParam("cpf") String cpf, Cliente cliente) throws ClassNotFoundException {
        var clienteEncontrado = service.findByCpf(cpf);
        if (clienteEncontrado == null) return Response.status(Response.Status.NOT_FOUND).build();
        if (!service.update(cliente)) return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.ok(cliente).build();
    }
}
