package com.example.FiscalCore.strategy;

public class ImpostoICMS implements EstrategiaImposto{

    private static final float aliquota = 0.18f;

    @Override
    public float calcular(float valor){
        return valor/( 1 - aliquota);
    }
}
