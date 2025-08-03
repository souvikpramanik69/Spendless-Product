package com.Spendless.Product.Payload;

import com.Spendless.Product.Model.Items;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPayload {

    private String name;
    private Double price;
    private Items item;
}
