package com.example.FiscalCore.strategy;

public class ImpostoIPI implements EstrategiaImposto{

    private static final float aliquota = 0.10f;

    @Override
    public float calcular(float valor){
        return valor * (1 + aliquota);
    }
}
