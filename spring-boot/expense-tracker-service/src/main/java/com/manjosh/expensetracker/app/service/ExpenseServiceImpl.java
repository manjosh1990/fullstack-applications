package com.manjosh.expensetracker.app.service;

import com.manjosh.expensetracker.app.model.Expense;
import com.manjosh.expensetracker.app.repository.ExpenseRepository;
import com.manjosh.expensetracker.app.service.iterfaces.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public void addExpense(Expense expense){
        expenseRepository.insert(expense);
    }

    public Expense updateExpense(Expense expense){
        Expense expenseFromDb = expenseRepository.findById(expense.getId())
                .orElseThrow(()-> new RuntimeException(
                        String.format("Cannot find expense by ID %s",expense.getId())
                ));
        expenseFromDb.setExpenseName(expense.getExpenseName());
        expenseFromDb.setExpenseCategory(expense.getExpenseCategory());
        expenseFromDb.setExpenseAmount(expense.getExpenseAmount());
        expenseRepository.save(expenseFromDb);
        return expense;
    }

    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    public Expense getExpensesByName( String name){
        return expenseRepository.findByName(name)
                .orElseThrow(()-> new RuntimeException(String.format("cannot find expense by name %s",name))) ;
    }

    public void deleteExpense(String id){
        expenseRepository.deleteById(id);
    }
}
