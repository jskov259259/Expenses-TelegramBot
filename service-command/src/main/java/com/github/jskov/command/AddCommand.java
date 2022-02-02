package com.github.jskov.command;

import com.github.jskov.dao.CategoryDao;
import com.github.jskov.dao.ExpenseDao;
import com.github.jskov.model.Category;
import com.github.jskov.model.Expense;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class AddCommand implements Command {

    public static final String ADD_MESSAGE = "Information added";
    public static final String ADD_MESSAGE_EMPTY = "Message is empty. Use following format: /add - [code] [price]\nUse /help to get detailed information";
    public static final String ADD_MESSAGE_ERROR = "Incorrect command format. Use /add - [code] [price]";
    public static final String ADD_MESSAGE_CODE_NOT_FOUND = "Category doesn't exists. Use /code to get code category information";
    public static final String ADD_MESSAGE_INCORRECT_CODE = "Incorrect code number. Code must be a whole number";
    public static final String ADD_MESSAGE_INCORRECT_PRICE = "Incorrect price. Use numbers";
    public static final String ADD_MESSAGE_PRICE_NEGATIVE = "Price must be a positive number";
    public static final String ADD_MESSAGE_DB_ERROR = "Oops.. Something wrong.";

    private ExpenseDao expenseDao;
    private CategoryDao categoryDao;

    public AddCommand(ExpenseDao expenseDao, CategoryDao categoryDao) {
        this.expenseDao = expenseDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public String execute(Update update) {

        String[] incomingText = update.getMessage().getText().trim().split(" ");

        // check incoming message
        if (incomingText.length == 1) {
            return ADD_MESSAGE_EMPTY;
        }
        if (incomingText.length != 3) {
            return ADD_MESSAGE_ERROR;
        }

        //check category code
        try {
            if (!checkCategoryIsExist(incomingText[1]))
                return ADD_MESSAGE_CODE_NOT_FOUND;
        } catch (NumberFormatException numberFormatException) {
            return ADD_MESSAGE_INCORRECT_CODE;
        }

        //check price
        try {
          if (!checkCorrectPrice(incomingText[2]))
              return ADD_MESSAGE_PRICE_NEGATIVE;
        } catch (NumberFormatException numberFormatException) {
            return ADD_MESSAGE_INCORRECT_PRICE;
        }

        // add expense
        Expense expense = getExpenseFromMessage(incomingText[1], incomingText[2]);
        try {
            addNewExpense(expense);
        } catch (Exception ex) {
            return ADD_MESSAGE_DB_ERROR;
            //todo make db_error for all service commands
        }
        return ADD_MESSAGE;
    }


    private boolean checkCategoryIsExist(String categoryCode) throws NumberFormatException {
        Integer categoryId = Integer.parseInt(categoryCode);
        List<Category> categoryList = categoryDao.findAllCategories();
        for (Category category : categoryList) {
            if (categoryId == category.getCategoryId())
                return true;
        }
        return false;
    }

    private boolean checkCorrectPrice(String price) throws NumberFormatException {
        Double priceNumber = Double.parseDouble(price);
        if (priceNumber < 0) {
            return false;
        }
        return true;
    }

    private Expense getExpenseFromMessage(String code, String price) {
        Expense expense = new Expense();
        expense.setDateOfExpense(LocalDate.now());
        expense.setCategoryId(Integer.parseInt(code));
        expense.setSumOfExpense(new BigDecimal(price));
        return expense;
    }

    private Integer addNewExpense(Expense expense) {
        return expenseDao.addNewExpense(expense);
    }
}