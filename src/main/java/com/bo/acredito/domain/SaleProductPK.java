package com.bo.acredito.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by aralco on 6/14/15.
 */
public class SaleProductPK implements Serializable {
    private Long saleId;
    private Long productId;

    @Column(name = "saleId", nullable = false, insertable = true, updatable = true)
    @Id
    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    @Column(name = "productId", nullable = false, insertable = true, updatable = true)
    @Id
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaleProductPK that = (SaleProductPK) o;

        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (saleId != null ? !saleId.equals(that.saleId) : that.saleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = saleId != null ? saleId.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        return result;
    }
}
