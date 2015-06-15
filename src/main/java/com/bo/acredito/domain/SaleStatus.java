package com.bo.acredito.domain;

/**
 * Created by aralco on 6/14/15.
 */
public enum SaleStatus {
    NOT_PAID, CANCELLED,
    ADVANCE_PAID, PARTIALLY_PAID,
    PAID, COMPLETED;

    @Override
    public String toString() {
        String label = "";
        switch (this)  {
            case NOT_PAID:
                label = "Pendiente de pago";
                break;
            case CANCELLED:
                label = "Cancelada";
                break;
            case ADVANCE_PAID:
                label = "Pago anticipado";
                break;
            case PARTIALLY_PAID:
                label = "Pagado parcialmente";
                break;
            case PAID:
                label = "Pagada";
                break;
            case COMPLETED:
                label = "Completada";
                break;
        }
        return label;

    }


}
