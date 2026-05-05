package com.accenture.consumo.controller;

import com.accenture.consumo.entity.EnderecoEntity;
import com.accenture.consumo.model.Endereco;
import com.accenture.consumo.service.CepAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cep")
@Tag(name = "CEP", description = "Consulta de endereços via ViaCEP com persistência no H2")
public class CepRestController {

    @Autowired
    private CepAppService cepAppService;
    @GetMapping("/{cep}")
    @Operation(
        summary = "Consultar CEP",
        description = "Busca o endereço na API ViaCEP (https://viacep.com.br) e salva no banco H2"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "CEP encontrado e salvo"),
        @ApiResponse(responseCode = "404", description = "CEP não encontrado"),
        @ApiResponse(responseCode = "400", description = "CEP inválido (deve ter 8 dígitos)")
    })
    public ResponseEntity<?> buscarCep(
            @Parameter(description = "CEP a consultar (com ou sem hífen)", example = "01523040")
            @PathVariable String cep) {

        try {
            Endereco endereco = cepAppService.buscarEPersistir(cep);

            if (endereco == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(endereco);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/historico")
    @Operation(
        summary = "Histórico de CEPs",
        description = "Lista todos os endereços que já foram consultados e salvos no banco H2"
    )
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<EnderecoEntity>> historico() {
        return ResponseEntity.ok(cepAppService.listarHistorico());
    }
}
