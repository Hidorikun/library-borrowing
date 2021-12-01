package com.hidorikun.customers.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hidorikun.customers.model.entity.Borrowing;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowingRepository extends CrudRepository<Borrowing, Long> {

    List<Borrowing> findByCustomerId(long customerId);

    Borrowing findByCustomerIdAndBookId(long customerId, long bookId);
}
