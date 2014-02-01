package com.bo.acredito.domain;

import java.util.Date;
import java.util.Set;

public class Sale {

	private int saleId;

	private Date saleDate;

	private Double subTotal;

	private Double discountedAmount;

	private Double total;

	private String saleType;

	private Double initialPayment;

	private Double residualPayment;

	private String notes;

    private Product product;

    private Customer customer;

    private Set<Payment> payments;

}
