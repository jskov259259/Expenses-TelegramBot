package com.github.jskov.command;

import com.github.jskov.dao.CategoryDao;
import com.github.jskov.model.Category;
import com.github.jskov.model.Expense;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class AddCategoryCommand implements Command {

    public static final String ADD_CATEGORY_MESSAGE = "Added new category with code number ";
    public static final String ADD_CATEGORY_MESSAGE_EMPTY = "Message is empty. Use following format: /addcategory - [Category name]\nUse /help to get detailed information";
    public static final String ADD_CATEGORY_MESSAGE_ERROR = "Incorrect command format. Use /addcategory [Category name]";
    public static final String ADD_CATEGORY_MESSAGE_UNIQUE = "This category already exists";
    public static final String ADD_CATEGORY_MESSAGE_QUANTITY = "Category name must be up to 20 characters";
    public static final String ADD_MESSAGE_CATEGORY_DB_ERROR = "Oops.. Something wrong.";


    private CategoryDao categoryDao;

    public AddCategoryCommand(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public String execute(Update update) {

        String[] incomingText = update.getMessage().getText().trim().split(" ");

        // check incoming message
        if (incomingText.length == 1) {
            return ADD_CATEGORY_MESSAGE_EMPTY;
        }
        if (incomingText.length != 2) {
            return ADD_CATEGORY_MESSAGE_ERROR;
        }

        //check category on unique
        if (!isCategoryUnique(incomingText[1])) {
            return ADD_CATEGORY_MESSAGE_UNIQUE;
        }

        //check category quantity symbols
        if (!checkCategoryQuantitySymbols(incomingText[1])) {
            return ADD_CATEGORY_MESSAGE_QUANTITY;
        }

        //add category
        Category category = new Category();
        category.setCategoryName(incomingText[1]);
        Integer idOfNewCategory;
        try {
           idOfNewCategory = addNewCategory(category);
        } catch (Exception ex) {
            return ADD_MESSAGE_CATEGORY_DB_ERROR; }
        return ADD_CATEGORY_MESSAGE + idOfNewCategory;
    }

    private boolean isCategoryUnique(String categoryName) {
        return categoryDao.isCategoryUnique(categoryName);
    }
    private Integer addNewCategory(Category category) {
        return categoryDao.addNewCategory(category);
    }
    private boolean checkCategoryQuantitySymbols(String categoryName) {
        char[] categorySymbols = categoryName.toCharArray();
        if (categorySymbols.length > 20) {
            return false;
        }
        return true;
    }
}
