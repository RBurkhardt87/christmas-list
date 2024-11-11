package com.example.christmas_list.data;

import com.example.christmas_list.models.Wish;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface WishRepository extends CrudRepository<Wish, Integer> {
}
