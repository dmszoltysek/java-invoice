package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<Product, Integer>();
    private static int nextNumber = 0;
    private final int number = ++nextNumber;


    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        if (products.containsKey(product)) {
            quantity += products.get(product);
            products.put(product, quantity);
            return;
        }
            products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getNumber(){
        return number;
    }


    public int getNumberOfPositions() {
        return products.size();
    }

    public String getPositions() {
        String positions = "";
        for (Product product : products.keySet()) {
            positions = positions + product.getName() + " Quantity: " + products.get(product) + " price/pc: " + product.getPrice() + "\n";
        }
        return positions;
    }


    public String printInvoice() {
        String invoice = "Invoice number: " + getNumber() + "\n" + getPositions() + "Number of positions: " + getNumberOfPositions();
        return invoice;
    }

    public int getQuantity(Product product) {
        return products.get(product);
    }


}