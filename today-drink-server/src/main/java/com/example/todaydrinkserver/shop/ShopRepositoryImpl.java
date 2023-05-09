package com.example.todaydrinkserver.shop;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ShopRepositoryImpl implements ShopRepositoryCustom{
    @PersistenceContext
    private EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ShopRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * updateShop() 메소드에서 shopDto에 특정 값만 들어오면 해당하는 값들만 update하고 나머지는 그대로 사용.
     */
    @Override
    public void updateShop(Long id, ShopDto shopDto) {
        QShop shop = QShop.shop;
        BooleanBuilder builder = new BooleanBuilder().and(shop.id.eq(id));
        JPAUpdateClause update = new JPAUpdateClause(em, shop);

        if (shopDto.getClassify() != null) {
            update.set(shop.classify, shopDto.getClassify());
        }

        if (shopDto.getNum() != null) {
            update.set(shop.num, shopDto.getNum());
        }

        if (shopDto.getEndTime() != null) {
            update.set(shop.endTime, shopDto.getEndTime());
        }

        if (shopDto.getAddress() != null) {
            update.set(shop.address, shopDto.getAddress());
        }

        if (shopDto.getLatitude() != null) {
            update.set(shop.latitude, shopDto.getLatitude());
        }

        if (shopDto.getLongitude() != null) {
            update.set(shop.longitude, shopDto.getLongitude());
        }

        if(shopDto.getTel() != null){
            update.set(shop.tel, shopDto.getTel());
        }

        if(shopDto.getStar() != null){
            update.set(shop.star,shopDto.getStar());
        }

        if(shopDto.getShopImage() !=null){
            update.set(shop.shopImage, shopDto.getShopImage());
        }
        update.where(builder).execute();
    }

    /**
     * 모두 다중 선택 가능하도록 설계
     * @param classify - 가게 분류
     * @param num 최대 인원 수
     * @param endTime 마감 시간
     */
    @Override
    public List<Shop> findShopByFiltering(List<String> classify, List<Integer> num, List<Integer> endTime) {
        QShop shop = QShop.shop;
        BooleanBuilder builder = new BooleanBuilder();

        if (classify != null && !classify.isEmpty()) {
            builder.and(createClassifyBooleanBuilder(shop, classify));
        }

        if (num != null && !num.isEmpty()) {
            builder.and(createNumBooleanBuilder(shop, num));
        }

        if (endTime != null && !endTime.isEmpty()) {
            builder.and(createEndTimeBooleanBuilder(shop, endTime));
        }

        JPAQuery<Shop> query = queryFactory.selectFrom(shop).where(builder);
        return query.fetch();
    }

    private BooleanBuilder createClassifyBooleanBuilder(QShop shop, List<String> classify) {
        BooleanBuilder classifyBuilder = new BooleanBuilder();
        classify.forEach(cls -> classifyBuilder.or(shop.classify.eq(cls)));
        return classifyBuilder;
    }

    private BooleanBuilder createNumBooleanBuilder(QShop shop, List<Integer> num) {
        BooleanBuilder numBuilder = new BooleanBuilder();
        num.forEach(n -> numBuilder.or(shop.num.eq(n)));
        return numBuilder;
    }

    private BooleanBuilder createEndTimeBooleanBuilder(QShop shop, List<Integer> endTime) {
        BooleanBuilder endTimeBuilder = new BooleanBuilder();
        endTime.forEach(endT -> {
            if (endT <= 22) {
                endTimeBuilder.or(shop.endTime.loe(22));
            } else if (endT > 22 && endT <= 24) {
                endTimeBuilder.or(shop.endTime.gt(22).and(shop.endTime.loe(24)));
            } else if (endT > 24 && endT <= 26) {
                endTimeBuilder.or(shop.endTime.gt(24).and(shop.endTime.loe(26)));
            } else if (endT > 26 && endT <= 30) {
                endTimeBuilder.or(shop.endTime.gt(26).and(shop.endTime.loe(30)));
            }
        });
        return endTimeBuilder;
    }
}
