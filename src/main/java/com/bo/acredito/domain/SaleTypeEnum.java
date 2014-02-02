package com.bo.acredito.domain;

/**
 * Created by aralco on 2/2/14.
 */
public enum SaleTypeEnum {
    CONTADO, CREDITO;

    @Override
    public String toString() {
        String label = "";
        switch (this)  {
            case CONTADO:
                label = "Contado";
                break;
            case CREDITO:
                label = "Crédito";
                break;
        }
        return label;
    }

}
