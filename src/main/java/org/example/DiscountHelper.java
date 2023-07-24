package org.example;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.example.enums.*;
import org.example.entities.Order;
import org.example.entities.OrderItem;
public class DiscountHelper {

    private final Map<Category, Discount> discountByCategory = generateDiscountByCategory();

    private static Map<Category, Discount> generateDiscountByCategory() {

        final Supplier<Discount> rndDiscSupp = () -> {
            var ds = Discount.values();
            int rndIdx = ThreadLocalRandom.current().nextInt(ds.length);
            return ds[rndIdx];
        };

        var map = Arrays.stream(Category.values())
                .collect(Collectors.toMap(c -> c, c -> rndDiscSupp.get()));
        return map;
    }

    public int totalDiscountVerbatim(Order order) {
        var items = order.getItems();
        var total = items.stream().mapToInt(this::getPercentValue).sum();
        return total;
    }

    public int getPercentValue(OrderItem orderItem) {
        var product = orderItem.product();
        var category = product.getCategory();
        var discount = discountByCategory.get(category);
        return discount.getPercentValue();
    }

    public int calcItemCost(OrderItem orderItem) {
        int value = orderItem.product().getPrice() * orderItem.amount();
        int dicsPercVal = getPercentValue(orderItem);
        assert dicsPercVal >= 0 && dicsPercVal < 100 : "illegal discount";
        value *= 100 - dicsPercVal;
        value /= 100;
        return value;
    }

    public int calcTotalCost(Order order) {
        return order.getItems().stream().mapToInt(this::calcItemCost).sum();
    }
}