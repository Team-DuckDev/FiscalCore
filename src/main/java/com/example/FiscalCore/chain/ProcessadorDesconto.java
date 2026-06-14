package com.example.FiscalCore.chain;

import com.example.FiscalCore.model.Orcamento;
import org.aspectj.weaver.ast.Or;

public abstract class ProcessadorDesconto {
    protected ProcessadorDesconto proximoProcessador;

    public void definirProximo(ProcessadorDesconto proximo){
        proximoProcessador = proximo;
    }

    public abstract float processar(Orcamento orcamento);
}
