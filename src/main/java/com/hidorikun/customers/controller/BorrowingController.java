package com.hidorikun.customers.controller;

import com.hidorikun.customers.model.dto.BorrowingDTO;
import com.hidorikun.customers.service.BorrowingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BorrowingController {
    private final BorrowingService borrowingService;

    public BorrowingController(final BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @GetMapping("borrowings/{customerId}")
    public ResponseEntity<List<BorrowingDTO>> getBorrowingsByCustomers(@PathVariable() long customerId) {
        List<BorrowingDTO> borrowings = borrowingService.getBorrowingsForCustomer(customerId);

        if (borrowings.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(borrowings);
    }

    @PostMapping("borrowings")
    public ResponseEntity<BorrowingDTO> borrowBook(@RequestBody BorrowingDTO borrowingDTO) {
        return ResponseEntity.ok(borrowingService.addBorrowing(borrowingDTO));
    }

    @PostMapping("borrowings/return")
    public ResponseEntity<BorrowingDTO> returnBook(@RequestBody BorrowingDTO borrowingDTO) {
        return ResponseEntity.ok(borrowingService.returnBook(borrowingDTO));
    }

}
