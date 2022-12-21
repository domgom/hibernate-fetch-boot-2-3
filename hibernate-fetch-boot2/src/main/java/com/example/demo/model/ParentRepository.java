package com.example.demo.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParentRepository extends CrudRepository<Parent, Long> {

    // OK
    @Query(value = "SELECT * FROM parent", nativeQuery = true)
    List<Parent> findNative();

    // OK
    @Query("SELECT p FROM Parent p")
    List<Parent> findJpql();

}
