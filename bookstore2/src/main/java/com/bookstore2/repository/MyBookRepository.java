package com.bookstore2.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore2.entity.MyBookList;

@Repository
public interface MyBookRepository extends JpaRepository<MyBookList,Integer>{

}