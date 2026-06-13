package com.example.FiscalCore.strategy;

public class ImpostoPIS implements EstrategiaImposto{

    private static final float aliquota = 0.0165f;

    @Override
    public float calcular(float valor){
        return valor * (1 + aliquota);
    }
}
