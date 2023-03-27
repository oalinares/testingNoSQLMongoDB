package com.springmongodbtest.config;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.springmongodbtest.model.Expense;
import com.springmongodbtest.model.ExpenseCategory;
import com.springmongodbtest.repository.ExpenseRepository;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.springmongodbtest.model.ExpenseCategory.*;

/*
current error with this class:
Parameter 0 of method getBuilder in io.mongock.runner.springboot.config.MongockContext required a bean of type
'io.mongock.driver.api.driver.ConnectionDriver' that could not be found.
 */
@ChangeLog
public class DatabaseChangeLog {

    @ChangeSet(order = "001", id = "seedDatabase", author = "Oscar")
    public void seedDatabase(ExpenseRepository expenseRepository) {
        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(createNewExpense("Movie Tickets", ENTERTAINMENT, BigDecimal.valueOf(40)));
        expenseList.add(createNewExpense("Dinner", RESTAURANT, BigDecimal.valueOf(60)));
        expenseList.add(createNewExpense("Netflix", ENTERTAINMENT, BigDecimal.valueOf(10)));
        expenseList.add(createNewExpense("Gym", MISC, BigDecimal.valueOf(20)));
        expenseList.add(createNewExpense("Internet", UTILITIES, BigDecimal.valueOf(30)));

        expenseRepository.insert(expenseList);
    }

    private Expense createNewExpense(String expenseName, ExpenseCategory expenseCategory, BigDecimal amount) {
        Expense expense = new Expense();
        expense.setExpenseName(expenseName);
        expense.setExpenseAmount(amount);
        expense.setExpenseCategory(expenseCategory);
        return expense;
    }
}