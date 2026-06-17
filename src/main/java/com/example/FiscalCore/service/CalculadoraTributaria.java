package com.example.FiscalCore.service;

import com.example.FiscalCore.chain.ProcessadorDesconto;
import com.example.FiscalCore.model.Orcamento;
import com.example.FiscalCore.strategy.EstrategiaImposto;

public class CalculadoraTributaria {
    
private EstrategiaImposto estrategiaImposto;
private ProcessadorDesconto cadeiaDesconto;

//set
public void definirImposto(EstrategiaImposto estrategia){
    this.estrategiaImposto = estrategia;
}

public float calcularImpostoFinal(Orcamento o){

    if (o == null) throw new IllegalArgumentException("Orcamento não pode ser nulo");
    if (this.estrategiaImposto == null) throw new IllegalStateException("EstrategiaImposto não definida");
    if (this.cadeiaDesconto == null) throw new IllegalStateException("Cadeia de desconto não definida");

    cadeiaDesconto.processar(o);
    return estrategiaImposto.calcular(o.getValorTotal());
}
public void definirCadeiaDesconto(ProcessadorDesconto cadeiaDesconto){
    this.cadeiaDesconto = cadeiaDesconto;
}

}

