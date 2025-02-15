package api.shopping.request;

import lombok.Data;

@Data
public class ItemRequest {
    private String name;
    private String description;
    private int price;
    private int quantity;
}
