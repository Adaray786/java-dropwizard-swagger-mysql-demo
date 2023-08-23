package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class OrderRequest {
    private int CustomerId;
    private Date OrderDate;
    private Date DispatchDate;

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }



    @JsonCreator
    public OrderRequest(
            @JsonProperty("CustomerId") int CustomerId,
            @JsonProperty("OrderDate")Date OrderDate) {
        this.CustomerId = CustomerId;
        this.OrderDate = OrderDate;
    }
}
