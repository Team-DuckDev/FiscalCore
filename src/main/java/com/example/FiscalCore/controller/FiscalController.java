package com.example.FiscalCore.controller;

import com.example.FiscalCore.model.Orcamento;
import com.example.FiscalCore.service.CalculadoraTributariaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fiscal")
@CrossOrigin(origins = "*")
public class FiscalController {

    private final CalculadoraTributariaService calculadoraService;

    public FiscalController(CalculadoraTributariaService calculadoraService) {
        this.calculadoraService = calculadoraService;
    }

    @PostMapping("/calcular/{imposto}")
    public RespostaCalculo calcularImposto(
            @PathVariable String imposto, 
            @RequestBody Orcamento orcamento) {
            
        float valorOriginal = orcamento.getValorTotal();
        
        float valorFinalImposto = calculadoraService.calcularImpostoFinal(orcamento, imposto);
        
        return new RespostaCalculo(
            valorOriginal,
            orcamento.getValorTotal(), 
            valorFinalImposto,
            imposto.toUpperCase()
        );
    }
    
    public record RespostaCalculo(
        float valorOriginal, 
        float valorComDesconto, 
        float valorFinalComImposto, 
        String tipoImposto
    ) {}
}