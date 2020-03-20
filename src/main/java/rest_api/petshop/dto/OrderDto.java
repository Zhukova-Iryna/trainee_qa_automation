package rest_api.petshop.dto;

import rest_api.petshop.OrderStatus;

import java.util.Objects;

public class OrderDto {
    int id;
    int petId;
    int quantity;
    String shipDate;
    String status;
    boolean complete;

    public int getId() {
        return id;
    }

    public OrderDto setId(int id) {
        this.id = id;
        return this;
    }

    public int getPetId() {
        return petId;
    }

    public OrderDto setPetId(int petId) {
        this.petId = petId;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderDto setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getShipDate() {
        return shipDate;
    }

    public OrderDto setShipDate(String shipDate) {
        this.shipDate = shipDate;
        return this;
    }

    public OrderStatus getStatus() {
        return OrderStatus.valueOf(status);
    }

    public OrderDto setStatus(OrderStatus status) {
        this.status = status.name();
        return this;
    }

    public boolean isComplete() {
        return complete;
    }

    public OrderDto setComplete(boolean complete) {
        this.complete = complete;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return getId() == orderDto.getId() &&
                getPetId() == orderDto.getPetId() &&
                getQuantity() == orderDto.getQuantity() &&
                isComplete() == orderDto.isComplete() &&
                Objects.equals(getStatus(), orderDto.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPetId(), getQuantity(), getStatus(), isComplete());
    }
}
