package com.bo.acredito.domain;

/**
 * @author aralco
 */
public enum SaleType {
    CASH, CREDIT, ADVANCE;

    @Override
    public String toString() {
        String label = "";
        switch (this)  {
            case CASH:
                label = "Contado";
                break;
            case CREDIT:
                label = "Crédito";
                break;
            case ADVANCE:
                label = "Anticipo";
                break;
        }
        return label;
    }

}
