package com.example.FiscalCore.strategy;
import org.springframework.stereotype.Component;

@Component("ICMS")
public class ImpostoICMS implements EstrategiaImposto{
    private static final float aliquota = 0.18f;

    @Override
    public float calcular(float valor){
        return valor/( 1 - aliquota);
    }
}
