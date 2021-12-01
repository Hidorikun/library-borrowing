package com.hidorikun.customers.service;

import com.hidorikun.customers.model.dto.BorrowingDTO;
import com.hidorikun.customers.model.entity.Borrowing;
import com.hidorikun.customers.repository.BorrowingRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingService {

    private BorrowingRepository borrowingRepository;

    private BorrowingDTO borrowingToDTO(Borrowing borrowing) {
        if (borrowing == null) {
            return null;
        }

        BorrowingDTO dto = new BorrowingDTO();

        dto.setId(borrowing.getId());
        dto.setBookId(borrowing.getBookId());
        dto.setCustomerId(borrowing.getCustomerId());
        dto.setBorrowedOn(borrowing.getBorrowedOn());
        dto.setReturnedOn(borrowing.getReturnedOn());

        return dto;
    }

    private Borrowing dtoToBorrowing(BorrowingDTO dto) {
        Borrowing borrowing = new Borrowing();

        borrowing.setId(dto.getId());
        borrowing.setBookId(dto.getBookId());
        borrowing.setCustomerId(dto.getCustomerId());
        borrowing.setBorrowedOn(dto.getBorrowedOn());
        borrowing.setReturnedOn(dto.getReturnedOn());

        return borrowing;

    }

    public BorrowingService(final BorrowingRepository borrowingRepository) {
        this.borrowingRepository = borrowingRepository;
    }

    public List<BorrowingDTO> getBorrowingsForCustomer(long customerId) {
        List<Borrowing> borrowings = borrowingRepository.findByCustomerId(customerId);

        return borrowings.stream().map(this::borrowingToDTO).collect(Collectors.toList());

    }

    public BorrowingDTO addBorrowing(BorrowingDTO borrowingDTO) {
        Borrowing borrowing = dtoToBorrowing(borrowingDTO);

        if (borrowing.getBorrowedOn() == null) {
            borrowing.setBorrowedOn(new Date());
        }

        return borrowingToDTO(borrowingRepository.save(borrowing));
    }

    public BorrowingDTO returnBook(BorrowingDTO borrowingDTO) {
        Borrowing borrowing = borrowingRepository.findByCustomerIdAndBookId(
                borrowingDTO.getCustomerId(),
                borrowingDTO.getBookId()
        );

        borrowing.setReturnedOn(new Date());

        return borrowingToDTO(borrowingRepository.save(borrowing));
    }

}
