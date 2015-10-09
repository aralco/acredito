package com.bo.acredito.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author aralco
 */
@Entity
@Table(name = "SALE_PRODUCT", schema = "", catalog = "acredito")
@NamedQuery(name = "SaleProduct.findBySaleId", query = "SELECT sp FROM SaleProduct sp WHERE sp.sale.id=:saleId")
public class SaleProduct {
    @Id
    @GeneratedValue(generator = "SaleProduct")
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private String id;
    @Basic
    @Column(name = "quantity", nullable = false, insertable = true, updatable = true)
    private Integer quantity;
    @Basic
    @Column(name = "unitPrice", nullable = false, insertable = true, updatable = true, precision = 0)
    private Double unitPrice;
    @Basic
    @Column(name = "partialAmount", nullable = false, insertable = true, updatable = true, precision = 0)
    private Double partialAmount;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "saleId", referencedColumnName = "id", nullable = false)
    private Sale sale;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "productId", referencedColumnName = "id", nullable = false)
    private Product product;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "officeId", referencedColumnName = "id", nullable = false)
    private Office office;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getPartialAmount() {
        return partialAmount;
    }

    public void setPartialAmount(Double partialAmount) {
        this.partialAmount = partialAmount;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
