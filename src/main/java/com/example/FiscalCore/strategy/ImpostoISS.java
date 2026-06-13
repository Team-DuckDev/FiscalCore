package com.example.FiscalCore.strategy;

public class ImpostoISS implements EstrategiaImposto{

    private static final float aliquota = 0.05f;

    @Override
    public float calcular(float valor){
        return valor * (1 + aliquota);
    }
}
