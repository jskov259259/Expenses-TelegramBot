package com.github.jskov.command;

import com.github.jskov.dao.CategoryDao;
import com.github.jskov.model.Category;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Service
public class CodeCommand implements Command {

    public static final String CODE_MESSAGE = "Code list:\n";

    private CategoryDao categoryDao;

    public CodeCommand(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public String execute(Update update) {
        List<Category> categoryList = findAllCategories();
        StringBuilder responseMessage = new StringBuilder(CODE_MESSAGE);
        for (Category category : categoryList) {
            responseMessage.append(category.getCategoryId() + " " + category.getCategoryName() + "\n");
        }
        // TODO make responseMessage from codeList with stream API
        return responseMessage.toString();
    }

    private List<Category> findAllCategories() {
        return categoryDao.findAllCategories();
    }
}
