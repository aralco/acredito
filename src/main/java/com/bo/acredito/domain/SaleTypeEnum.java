package com.bo.acredito.domain;

/**
 * Created by aralco on 2/2/14.
 */
public enum SaleTypeEnum {
    CASH, CREDIT;

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
        }
        return label;
    }

}
