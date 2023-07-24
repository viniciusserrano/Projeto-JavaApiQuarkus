package br.com.shift.lis.projetodesenvolvimento.resource;

import br.com.shift.lis.projetodesenvolvimento.repository.dto.SetorDto;
import br.com.shift.lis.projetodesenvolvimento.service.SetorService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@ApplicationScoped
@Path("")
public class SetorResource {

  @Inject
  SetorService setorService;

  @GET
  public Response getSetor() {
    return Response.ok(setorService.getSetor()).build();
  }

  @GET
  @Path("/{id}")
  public Response getSetorId(@PathParam("id") Long parametroId) {
    return Response.ok(setorService.findByIdSetor(parametroId)).build();
  }

  @POST
  @Transactional
  public Response salvarSetor(@RequestBody @Valid SetorDto setorDto) {
    return Response.ok(setorService.salvarSetor(setorDto)).build();
  }

  @PUT
  @Path("/{setorId}")
  public Response editarSetor(@PathParam("setorId") Long setorId, SetorDto setorDto) {
    return Response.ok(setorService.alterar(setorId, setorDto)).build();
  }

}
