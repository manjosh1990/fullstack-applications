package com.manjosh.expensetracker.app.service.iterfaces;

import com.manjosh.expensetracker.app.model.Expense;

import java.util.List;

public interface ExpenseService {
    void addExpense(Expense expense);
    Expense updateExpense(Expense expense);
    List<Expense> getAllExpenses();
    Expense getExpensesByName( String name);
    void deleteExpense(String id);
}
