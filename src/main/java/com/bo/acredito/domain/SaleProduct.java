package com.bo.acredito.domain;

import javax.persistence.*;

/**
 * Created by aralco on 6/14/15.
 */
@Entity
@Table(name = "SALE_PRODUCT", schema = "", catalog = "acredito")
@NamedQuery(name = "SaleProduct.findBySaleId", query = "SELECT sp FROM SaleProduct sp WHERE SP.saleId=:saleId")
@IdClass(SaleProductPK.class)
public class SaleProduct {
    private Long saleId;
    private Long productId;
    private Integer quantity;
    private Double unitPrice;
    private Double partialAmount;
    private Sale saleBySaleId;
    private Product productByProductId;
    private Office officeByOfficeId;

    @Id
    @Column(name = "saleId", nullable = false, insertable = false, updatable = false)
    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    @Id
    @Column(name = "productId", nullable = false, insertable = false, updatable = false)
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "quantity", nullable = false, insertable = true, updatable = true)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "unitPrice", nullable = false, insertable = true, updatable = true, precision = 0)
    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Basic
    @Column(name = "partialAmount", nullable = false, insertable = true, updatable = true, precision = 0)
    public Double getPartialAmount() {
        return partialAmount;
    }

    public void setPartialAmount(Double partialAmount) {
        this.partialAmount = partialAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaleProduct that = (SaleProduct) o;

        if (partialAmount != null ? !partialAmount.equals(that.partialAmount) : that.partialAmount != null)
            return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (saleId != null ? !saleId.equals(that.saleId) : that.saleId != null) return false;
        if (unitPrice != null ? !unitPrice.equals(that.unitPrice) : that.unitPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = saleId != null ? saleId.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (unitPrice != null ? unitPrice.hashCode() : 0);
        result = 31 * result + (partialAmount != null ? partialAmount.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "saleId", referencedColumnName = "id", nullable = false)
    public Sale getSaleBySaleId() {
        return saleBySaleId;
    }

    public void setSaleBySaleId(Sale saleBySaleId) {
        this.saleBySaleId = saleBySaleId;
    }

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id", nullable = false)
    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }

    @ManyToOne
    @JoinColumn(name = "office_id", referencedColumnName = "id", nullable = false)
    public Office getOfficeByOfficeId() {
        return officeByOfficeId;
    }

    public void setOfficeByOfficeId(Office officeByOfficeId) {
        this.officeByOfficeId = officeByOfficeId;
    }
}
