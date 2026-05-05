package com.accenture.consumo.controller;

import com.accenture.consumo.entity.EnderecoEntity;
import com.accenture.consumo.model.Endereco;
import com.accenture.consumo.service.CepAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class CepWebController {

    @Autowired
    private CepAppService cepAppService;

    @GetMapping("/")
    public String index(Model model) {
        List<EnderecoEntity> historico = cepAppService.listarHistorico();
        model.addAttribute("historico", historico);
        return "index";
    }

    @PostMapping("/buscar")
    public String buscarCep(@RequestParam("cep") String cep, Model model) {
        List<EnderecoEntity> historico = cepAppService.listarHistorico();
        model.addAttribute("historico", historico);
        model.addAttribute("cepDigitado", cep);

        try {
            Endereco endereco = cepAppService.buscarEPersistir(cep);
            if (endereco == null) {
                model.addAttribute("erro", "CEP não encontrado: " + cep);
            } else {
                model.addAttribute("endereco", endereco);
                model.addAttribute("historico", cepAppService.listarHistorico());
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
        }

        return "index";
    }
}
