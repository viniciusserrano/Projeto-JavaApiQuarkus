package br.com.shift.lis.projetodesenvolvimento.service;

import br.com.shift.common.exception.ViolacaoRegraNegocioException;
import br.com.shift.lis.projetodesenvolvimento.repository.dao.ProcedimentoDao;
import br.com.shift.lis.projetodesenvolvimento.repository.dto.CampoProcedimentoDto;
import br.com.shift.lis.projetodesenvolvimento.repository.dto.ProcedimentoDto;
import br.com.shift.lis.projetodesenvolvimento.repository.entity.Procedimento;
import br.com.shift.lis.projetodesenvolvimento.repository.enums.StatusEnum;
import br.com.shift.lis.projetodesenvolvimento.service.converter.CampoProcedimentoConverter;
import br.com.shift.lis.projetodesenvolvimento.service.converter.ProcedimentoConverter;
import br.com.shift.lis.projetodesenvolvimento.service.converter.SetorConverter;
import br.com.shift.lis.projetodesenvolvimento.service.validator.ProcedimentoValidator;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class ProcedimentoService {

  @Inject
  ProcedimentoDao procedimentoDao;

  @Inject
  ProcedimentoConverter converter;

  @Inject
  CampoProcedimentoConverter campoProcedimentoConverter;

  @Inject
  ProcedimentoValidator procedimentoValidator;

  @Inject
  SetorService setorService;

  @Inject
  SetorConverter setorConverter;

  public List<ProcedimentoDto> getProcedimentos() {
    List<Procedimento> procedimentos = procedimentoDao.listAll();
    return converter.toDtoList(procedimentos);
  }

  @Transactional
  public CampoProcedimentoDto salvarProcedimento(CampoProcedimentoDto campoProcedimentoDto) {
    validarSeMnemonicoExiste(campoProcedimentoDto);
    procedimentoValidator.verificaPreco(campoProcedimentoDto);
    var buscaSetorId = setorService.findByIdSetor(campoProcedimentoDto.getSetorId());
    var setorEntity = setorConverter.toEntity(buscaSetorId);
    var procedimento = campoProcedimentoConverter.toEntity(campoProcedimentoDto);
    procedimento.setSetor(setorEntity);
    procedimento.setStatusEnum(StatusEnum.ATIVO);
    var procedimentoPersist = procedimentoDao.salvarProcedimento(procedimento);
    return campoProcedimentoConverter.toDto(procedimentoPersist);

  }

  private void validarSeMnemonicoExiste(CampoProcedimentoDto campoProcedimentoDto) {
    var procedimentoStatus = procedimentoDao.findByUserMnemonico(campoProcedimentoDto.getMnemonico());
    if (procedimentoStatus.isPresent()) {
      throw ViolacaoRegraNegocioException.builder()
        .mensagem("Procedimento já cadastrado.")
        .build();
    }
  }

  public ProcedimentoDto getParametro(Long parametroId) {
    return converter.toDto(procedimentoDao.getParametroId(parametroId));
  }

  @Transactional
  public CampoProcedimentoDto alterar(Long procedimentoId, CampoProcedimentoDto campoProcedimentoDto)
    throws ViolacaoRegraNegocioException {
    var setorExiste = procedimentoDao.existeSetor(procedimentoId);
    procedimentoValidator.verificaStatusSetor(setorExiste, campoProcedimentoDto);
    procedimentoValidator.verificaPreco(campoProcedimentoDto);
    campoProcedimentoDto.setId(procedimentoId);
    var procedimento = procedimentoDao.findByIdOptional(campoProcedimentoDto.getId())
      .orElseThrow(() -> registroNaoEncontrado(campoProcedimentoDto.getId()));
    procedimento.setDescricao(campoProcedimentoDto.getDescricao());
    procedimento.setPreco(campoProcedimentoDto.getPreco());
    procedimento.getStatusEnum();
    procedimentoDao.salvarProcedimento(procedimento);
    return campoProcedimentoDto;
  }

  private ViolacaoRegraNegocioException registroNaoEncontrado(Long id) {
    return ViolacaoRegraNegocioException.builder()
      .mensagem("Id " + id + " inválido.")
      .codigoErro(Response.Status.NOT_FOUND.getStatusCode())
      .build();
  }
}



