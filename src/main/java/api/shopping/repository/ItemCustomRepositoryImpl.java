package api.shopping.repository;

import api.shopping.domain.Item;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static api.shopping.domain.QItem.item;

public class ItemCustomRepositoryImpl implements ItemCustomRepository {

    public JPAQueryFactory jpaQueryFactory;

    public ItemCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Item> findAllWithCondition(String itemName, String description, Integer price,Integer quantity) {
        return jpaQueryFactory.selectFrom(item)
                .where(eqName(itemName),
                        eqDescription(description),
                        loePrice(price),
                        loeQuantity(quantity)
                )
                .fetch();


    }

    private static BooleanExpression eqName(String itemName) {
        return StringUtils.isEmpty(itemName) ? null : item.name.contains(itemName);
    }

    private Predicate eqDescription(String description) {
        return StringUtils.isEmpty(description) ? null : item.description.contains(description);
    }

    private Predicate loePrice(Integer price) {
        return price == null ? null : item.price.loe(price);
    }

    private Predicate loeQuantity(Integer quantity) {
        return quantity == null ? null : item.quantity.loe(quantity);
    }

}
