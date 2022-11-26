package com.example.catalogservice.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class CatalogDto implements Serializable {

	private String productId;
	private Integer quantity;
	private Integer unitPrice;
	private Integer totalPrice;

	private String orederId;
	private String userId;
}
