package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Arrays;
import java.util.List;

@Builder
@Getter
public class Payment {
    String id;
    String method;
    Order order;
    Map<String, String> paymentData;
    String status;

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this(id, method, order, paymentData, "PENDING");
    }
    public Payment(String id, String method, Order order, Map<String, String> paymentData, String status) {
        this.id = id;
        this.method = method;
        this.setOrder(order);
        this.setPaymentData(paymentData);
        this.setStatus(status);
    }

    private void setOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        this.order = order;
    }

    protected void setPaymentData(Map<String, String> paymentData) {
        this.paymentData = null;
    }

    public void setStatus(String status) {
        List<String> statusList = Arrays.asList("PENDING", "SUCCESS", "REJECTED");

        if (!statusList.contains(status)) {
            throw new IllegalArgumentException("Invalid payment status");
        }
        this.status = status;
    }
}