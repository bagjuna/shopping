package api.shopping.web;

import api.shopping.config.UserPrincipal;
import api.shopping.domain.Item;
import api.shopping.domain.Member;
import api.shopping.request.ItemRequest;
import api.shopping.response.ItemResponse;
import api.shopping.service.ItemService;
import api.shopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final MemberService memberService;
    private final ItemService itemService;


    @PostMapping("/api/save/item")
    public void save(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody ItemRequest itemRequest) {
        Member member = memberService.findById(userPrincipal.getUserId());
        Item item = new Item(itemRequest.getName(), itemRequest.getDescription(), itemRequest.getPrice(), itemRequest.getQuantity(), member);
        itemService.save(item);
    }

    @GetMapping("/api/get/{id}")
    public ItemResponse findById(@PathVariable Long id) {
        System.out.println("id = " + id);
        Item item = itemService.findById(id);
        return new ItemResponse(item);
    }

    @PutMapping("/api/update/{itemid}")
    public void update(@PathVariable Long itemid, @RequestBody ItemRequest itemRequest) {
        itemService.update(itemid, itemRequest);
    }

    @DeleteMapping("/api/delete/{id}")
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }


    @GetMapping("/api/getList")
    public Page<ItemResponse> getList(
//            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
        Pageable pageable) {
        Page<ItemResponse> list = itemService.getList(pageable);

        return list;
    }

    @GetMapping("/api/getListByCond")
    public List<ItemResponse> getList(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("price") Integer price, @RequestParam("quantity") Integer quantity) {
        return itemService.getListByCond(name, description, price, quantity);
    }

    @GetMapping("/hahaha")
    public List<ItemResponse> getList(@RequestParam("name") String name) {
        return itemService.getListByCond(name, null, null, null);
    }


}
