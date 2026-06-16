package com.example.FiscalCore.chain;

import com.example.FiscalCore.model.Orcamento;

public class SemDesconto extends ProcessadorDesconto{
    @Override
    public void processar(Orcamento orcamento) {
        if (proximoProcessador != null) {
            proximoProcessador.processar(orcamento);
        }
    }
}
