package com.onlineshop.order.core.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductMapId implements Serializable {
    private Long myOrderId;
    private Long productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProductMapId)) return false;
        OrderProductMapId that = (OrderProductMapId) o;
        return getMyOrderId().equals(that.getMyOrderId()) && getProductId().equals(that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMyOrderId(), getProductId());
    }
}
