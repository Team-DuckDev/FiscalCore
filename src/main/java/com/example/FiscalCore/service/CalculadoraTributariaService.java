package com.example.FiscalCore.service;

import org.springframework.stereotype.Service;

import com.example.FiscalCore.chain.DescontoMuitosItens;
import com.example.FiscalCore.chain.DescontoValorAlto;
import com.example.FiscalCore.chain.ProcessadorDesconto;
import com.example.FiscalCore.chain.SemDesconto;
import com.example.FiscalCore.model.Orcamento;
import com.example.FiscalCore.strategy.EstrategiaImposto;
import com.example.FiscalCore.strategy.ImpostoICMS;
import com.example.FiscalCore.strategy.ImpostoIPI;
import com.example.FiscalCore.strategy.ImpostoISS;
import com.example.FiscalCore.strategy.ImpostoPIS;

import jakarta.annotation.PostConstruct;

@Service
public class CalculadoraTributariaService {

    private ProcessadorDesconto cadeiaDesconto;

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

        EstrategiaImposto estrategiaImposto;
        
        switch (tipoImposto.toUpperCase()) {
            case "ICMS": estrategiaImposto = new ImpostoICMS(); break;
            case "IPI":  estrategiaImposto = new ImpostoIPI(); break;
            case "ISS":  estrategiaImposto = new ImpostoISS(); break;
            case "PIS":  estrategiaImposto = new ImpostoPIS(); break;
            default: throw new IllegalArgumentException("Imposto desconhecido: " + tipoImposto);
        }

        cadeiaDesconto.processar(o);

        return estrategiaImposto.calcular(o.getValorTotal());
    }
}