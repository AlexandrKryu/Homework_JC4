package org.example;


import java.util.Objects;

import org.example.entities.Customer;
import org.example.entities.Order;
import org.example.entities.Product;
import org.example.exception.*;

public class Store {

    private final Product[] products;
    private final Customer[] customers;
    private final DiscountHelper discountHelper;

    public Store(Product[] products, Customer[] customers) {
        this.products = Objects.requireNonNull(products);
        this.customers = Objects.requireNonNull(customers);
        this.discountHelper = new DiscountHelper();
    }

    public String getOrderInfo(Order order) {
        if (order == null) {
            return "NULL";
        }
        var viewModel = new OrderViewModel(order, discountHelper);
        return viewModel.toString();
    }

    public OrderComposer startOrder(String customerFirstName, String customerLastName) throws NoSuchCustomerException {
        return new OrderComposerImpl(this, customerFirstName, customerLastName);
    }

    /**
     * Фабрика заказов.
     */
    public abstract class OrderComposer {

        protected final Product[] products = Store.this.products;
        protected final Customer[] customers = Store.this.customers;
        protected final DiscountHelper discountHelper = Store.this.discountHelper;

        public abstract OrderComposer putItem(String productName, String productAmount)
                throws IllegalAmountException, NoSuchProductException;

        public abstract Order complete()
                throws IncompleteOrderException, TooMuchSaleException;
    }
}