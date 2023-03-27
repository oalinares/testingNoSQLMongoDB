package com.springmongodbtest.service;

import com.springmongodbtest.model.Expense;
import com.springmongodbtest.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public void addExpense(Expense expense) {

        expenseRepository.insert(expense);
    }
    public void updateExpense(Expense expense) {
        Expense savedExpense = expenseRepository.findById(expense.getId()).orElseThrow(() ->
       new RuntimeException(String.format("Cannot find expense by ID %s", expense.getId())));
        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        savedExpense.setExpenseAmount(expense.getExpenseAmount());

        expenseRepository.save(expense);
    }

    public Expense getExpense(String name) {
        return expenseRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException(String.format("Cannot find expense by name - %s", name)));
    }

    public List<Expense> getAllExpenses() {

        return expenseRepository.findAll();
    }

    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }
}
