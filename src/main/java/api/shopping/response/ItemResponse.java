package api.shopping.response;

import api.shopping.domain.Item;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemResponse {

    private Long id;
    private String name;
    private String description;
    private int price;
    private int quantity;
    private LocalDateTime createdAt;
    private Long memberId;

    public ItemResponse(Item item) {
        id = item.getId();
        name = item.getName();
        description = item.getDescription();
        price = item.getPrice();
        quantity = item.getQuantity();
        createdAt = item.getCreatedAt();
        memberId = item.getMember().getId();
    }

    @Builder
    public ItemResponse(Long id, String name, String description, int price, int quantity, LocalDateTime createdAt, Long memberId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.memberId = memberId;
    }

}
