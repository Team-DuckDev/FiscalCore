package com.example.FiscalCore.model;

import com.example.FiscalCore.chain.ProcessadorDesconto;
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

        return this.estrategiaImposto.calcular(o.getValorTotal());


}

}
