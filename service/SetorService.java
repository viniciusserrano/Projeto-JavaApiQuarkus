package br.com.shift.lis.projetodesenvolvimento.service;

import br.com.shift.common.exception.ViolacaoRegraNegocioException;
import br.com.shift.lis.projetodesenvolvimento.repository.dao.ProcedimentoDao;
import br.com.shift.lis.projetodesenvolvimento.repository.dao.SetorDao;
import br.com.shift.lis.projetodesenvolvimento.repository.dto.SetorDto;
import br.com.shift.lis.projetodesenvolvimento.repository.entity.Setor;
import br.com.shift.lis.projetodesenvolvimento.repository.enums.StatusEnum;
import br.com.shift.lis.projetodesenvolvimento.service.converter.SetorConverter;
import br.com.shift.lis.projetodesenvolvimento.service.validator.SetorValidator;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;


@ApplicationScoped
public class SetorService {

  @Inject
  SetorDao setorDao;

  @Inject
  SetorConverter setorConverter;

  @Inject
  SetorValidator setorValidator;

  @Inject
  ProcedimentoDao procedimentoDao;


  public List<SetorDto> getSetor() {
    List<Setor> setors = setorDao.listAll();
    return setorConverter.toDtoList(setors);
  }

  public SetorDto findByIdSetor(Long setorId) {
    return setorConverter.toDto(setorDao.findById(setorId));
  }

  @Transactional
  public SetorDto salvarSetor(SetorDto setorDto) {
    var setor = setorConverter.toEntity(setorDto);
    validarSeRegistroExiste(setorDto);
    setor.setStatusEnum(StatusEnum.ATIVO);
    var setorPersist = setorDao.salvarSetor(setor);
    return setorConverter.toDto(setorPersist);
  }

  @Transactional
  public SetorDto alterar(Long setorId, SetorDto setorDto) throws ViolacaoRegraNegocioException {
    var buscaSetor = procedimentoDao.existeSetor(setorId);
    setorValidator.verificaStatusSetor(buscaSetor, setorDto);
    setorDto.setId(setorId);
    var setor = setorDao.findByIdOptional(setorDto.getId())
      .orElseThrow(() -> registroNaoEncontrado(setorDto.getId()));
    validarSeRegistroExiste(setorDto);
    setor.setDescricao(setorDto.getDescricao());
    setor.getStatusEnum();
    setorDao.persist(setor);
    return setorDto;
  }

  private void validarSeRegistroExiste(SetorDto setorDto) {
    var buscaDescricao = setorDao.findByDescricao(setorDto.getDescricao());
    if (buscaDescricao.isPresent()) {
      throw ViolacaoRegraNegocioException.builder()
        .mensagem("Setor já cadastrado")
        .build();
    }
  }
  private ViolacaoRegraNegocioException registroNaoEncontrado(Long id) {
    return ViolacaoRegraNegocioException.builder()
      .mensagem("Id " + id + " inválido.")
      .codigoErro(Response.Status.NOT_FOUND.getStatusCode())
      .build();
  }


}
