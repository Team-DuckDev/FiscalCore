package com.example.FiscalCore.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.FiscalCore.chain.DescontoMuitosItens;
import com.example.FiscalCore.chain.DescontoValorAlto;
import com.example.FiscalCore.chain.ProcessadorDesconto;
import com.example.FiscalCore.chain.SemDesconto;
import com.example.FiscalCore.model.Orcamento;
import com.example.FiscalCore.strategy.EstrategiaImposto;

import jakarta.annotation.PostConstruct;

@Service
public class CalculadoraTributariaService {

    private ProcessadorDesconto cadeiaDesconto;
    private final Map<String, EstrategiaImposto> mapEstrategias;

    public CalculadoraTributariaService(Map<String, EstrategiaImposto> mapEstrategias) {
        this.mapEstrategias = mapEstrategias;
    }

    @PostConstruct
    public void init() {
        ProcessadorDesconto d1 = new DescontoMuitosItens();
        ProcessadorDesconto d2 = new DescontoValorAlto();
        ProcessadorDesconto d3 = new SemDesconto();
        
        d1.definirProximo(d2);
        d2.definirProximo(d3);
        
        this.cadeiaDesconto = d1;
    }

    public float calcularImpostoFinal(Orcamento o, String tipoImposto) {
        if (o == null) throw new IllegalArgumentException("Orcamento não pode ser nulo");

        EstrategiaImposto estrategia = mapEstrategias.get(tipoImposto.toUpperCase());
        
        if (estrategia == null) {
            throw new IllegalArgumentException("Imposto desconhecido ou não suportado: " + tipoImposto);
        }

        cadeiaDesconto.processar(o);

        return estrategia.calcular(o.getValorTotal());
    }
}