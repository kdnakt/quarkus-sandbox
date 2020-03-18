package com.kdnakt.quarkus.qute;

import java.math.BigDecimal;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemService {

    public Item findItem(Integer id) {
        Item item = new Item();
        item.name = id.toString();
        item.price = BigDecimal.valueOf(id.longValue());
        return item;
    }

}
