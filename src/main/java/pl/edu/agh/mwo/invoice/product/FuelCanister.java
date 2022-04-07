package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class FuelCanister extends TaxFreeProduct {
    private final BigDecimal excise = new BigDecimal("5.56");

    public FuelCanister(String name, BigDecimal price) {
        super(name, price);
    }

    public BigDecimal getExcise() {
        return excise;
    }

    @Override
    public BigDecimal getPriceWithTax() {
        return price.add(excise);
    }
}
