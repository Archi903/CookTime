package me.pele.cooktime.services.Impl;

import me.pele.cooktime.model.Ingredient;
import me.pele.cooktime.model.Receipt;
import me.pele.cooktime.services.ValidationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(Receipt receipt) {
        return receipt != null
                && !StringUtils.isEmpty(receipt.getName())
                && !StringUtils.isBlank(receipt.getName())
                && receipt.getIngredient() != null
                && receipt.getSteps() != null
                && !receipt.getIngredient().isEmpty()
                && !receipt.getSteps().isEmpty();

    }

    @Override
    public boolean validate(Ingredient ingredient) {
        return ingredient != null
                && !StringUtils.isEmpty(ingredient.getName())
                && !StringUtils.isBlank(ingredient.getName());
    }
}
