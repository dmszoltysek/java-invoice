package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class BottleOfWine extends OtherProduct {
    private final BigDecimal excise = new BigDecimal("5.56");

    public BottleOfWine(String name, BigDecimal price) {
        super(name, price);
    }

    public BigDecimal getExcise() {
        return excise;
    }

    @Override
    public BigDecimal getPriceWithTax() {
        return price.multiply(taxPercent).add(price).add(excise);
    }
}
