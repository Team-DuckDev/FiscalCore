package com.example.FiscalCore.chain;

import com.example.FiscalCore.model.Orcamento;

public class DescontoValorAlto extends ProcessadorDesconto {
    @Override
    public void processar(Orcamento orcamento){
        if (orcamento.getValorTotal() >= 1000){
            orcamento.setValorTotal(orcamento.getValorTotal() * 0.95f);
        }
        if (proximoProcessador != null) {
            proximoProcessador.processar(orcamento);
        }
    }
}
