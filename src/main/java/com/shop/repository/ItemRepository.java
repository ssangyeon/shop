package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>,
        QuerydslPredicateExecutor<Item>, ItemRepositoryCustom{

    List<Item> findByItemNm(String ItemNm);  //find+(엔티티이름)+By+변수이름

    List<Item> findByItemNmOrItemDetail(String ItemNm, String ItemDetail);

    List<Item> findByPriceLessThan(Integer price);

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
    //여기까지 쿼리 메소드 방식
    //여기부터 쿼리 어노테이션방식(jpql)-단점: 컴파일 시점에 에러를 발견할수 없음
    //jpql 엔티티 객체를 대상으로 쿼리를 수행,특정 데이터베이스 sql에 의존하지 않음
    //sql 데이터베이스의 테이블을 대상으로 쿼리를 수행
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    //Querydsl: 쿼리 어노테이션방식의 단점 보완

}
