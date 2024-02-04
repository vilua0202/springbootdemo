package com.example.Books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable Long id) {

        return loanRepository.findById(id).orElse(null);
    }
    @PostMapping
    public Loan addLoan(@RequestBody Loan loan) {
        return loanRepository.save(loan);
    }

    @PutMapping("/{id}")
    public Loan updateLoan(@PathVariable Long id, @RequestBody Loan updatedLoan) {
        Loan existingLoan = loanRepository.findById(id).orElse(null);
        if (existingLoan != null) {
            existingLoan.setBook(updatedLoan.getBook());
            existingLoan.setMember(updatedLoan.getMember());
            existingLoan.setLoanDate(updatedLoan.getLoanDate());
            existingLoan.setDueDate(updatedLoan.getDueDate());
            existingLoan.setReturnDate(updatedLoan.getReturnDate());
            return loanRepository.save(existingLoan);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Long id) {
        loanRepository.deleteById(id);
    }
}

