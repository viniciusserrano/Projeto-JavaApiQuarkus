package br.com.shift.lis.projetodesenvolvimento.resource;

import br.com.shift.lis.projetodesenvolvimento.repository.dto.CampoProcedimentoDto;
import br.com.shift.lis.projetodesenvolvimento.service.ProcedimentoService;
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
public class ProcedimentoResource {

  @Inject
  ProcedimentoService service;

  @GET
  public Response getProcedimentos() {
    return Response.ok(service.getProcedimentos()).build();
  }

  @GET
  @Path("/{id}")
  public Response getProcedimentoId(@PathParam("id") Long parametroId) {
    return Response.ok(service.getParametro(parametroId)).build();
  }

  @POST
  @Transactional
  public Response salvarProcedimento(@RequestBody @Valid CampoProcedimentoDto procedimentoDto) {
    return Response.ok(service.salvarProcedimento(procedimentoDto)).build();
  }

  @PUT
  @Path("/{procedimentoId}")
  public Response getProcedimentoPorId(@PathParam("procedimentoId")Long procedimentoId, CampoProcedimentoDto campoProcedimentoDto) {
    return Response.ok(service.alterar(procedimentoId, campoProcedimentoDto)).build();
  }

}
