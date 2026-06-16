package com.example.FiscalCore.chain;

import com.example.FiscalCore.model.Orcamento;

public abstract class ProcessadorDesconto {
    protected ProcessadorDesconto proximoProcessador;

    public void definirProximo(ProcessadorDesconto proximo){
        proximoProcessador = proximo;
    }

    public abstract void processar(Orcamento orcamento);
}
