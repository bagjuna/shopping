package api.shopping.response;

import lombok.Builder;
import lombok.Data;

@Data
public class ItemResponse {

    private String name;
    private String description;
    private int price;
    private int quantity;

    @Builder
    public ItemResponse( String name, String description, int price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

}
