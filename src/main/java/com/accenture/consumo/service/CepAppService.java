package com.accenture.consumo.service;

import com.accenture.consumo.entity.EnderecoEntity;
import com.accenture.consumo.interfaces.CepService;
import com.accenture.consumo.model.Endereco;
import com.accenture.consumo.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CepAppService {

    @Autowired
    private CepService cepService;

    @Autowired
    private EnderecoRepository enderecoRepository;
    public Endereco buscarEPersistir(String cep) {
        String cepLimpo = cep.replaceAll("[^0-9]", "");

        if (cepLimpo.length() != 8) {
            throw new IllegalArgumentException("CEP deve ter 8 dígitos: " + cep);
        }

        Endereco endereco = cepService.buscarEnderecoPorCep(cepLimpo);

        if (endereco == null || endereco.getCep() == null) {
            return null;
        }

        EnderecoEntity entity = toEntity(endereco);
        enderecoRepository.save(entity);

        return endereco;
    }

    public List<EnderecoEntity> listarHistorico() {
        return enderecoRepository.findAll();
    }
    private EnderecoEntity toEntity(Endereco e) {
        EnderecoEntity entity = new EnderecoEntity();
        entity.setCep(e.getCep());
        entity.setLogradouro(e.getLogradouro());
        entity.setComplemento(e.getComplemento());
        entity.setBairro(e.getBairro());
        entity.setLocalidade(e.getLocalidade());
        entity.setUf(e.getUf());
        entity.setIbge(e.getIbge());
        entity.setDdd(e.getDdd());
        entity.setSiafi(e.getSiafi());
        return entity;
    }
}
