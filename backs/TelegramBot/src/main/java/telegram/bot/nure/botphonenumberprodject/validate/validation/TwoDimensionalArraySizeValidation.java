package telegram.bot.nure.botphonenumberprodject.validate.validation;

import telegram.bot.nure.botphonenumberprodject.validate.annotation.TwoDimensionalArraySize;

import javax.validation.*;

public class TwoDimensionalArraySizeValidation implements ConstraintValidator<TwoDimensionalArraySize, Object[][]> {
    private int rowMin;
    private int rowMax;
    private int columnMin;
    private int columnMax;
    private boolean nullable;
    private boolean validateElements;
    private Class<?>[] groups;
    private Class<?>[] groupsElements;
    private final Validator validator;

    public TwoDimensionalArraySizeValidation() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public void initialize(TwoDimensionalArraySize arraySize) {
        this.rowMin = arraySize.rowMin();
        this.rowMax = arraySize.rowMax();
        this.columnMin = arraySize.columnMin();
        this.columnMax = arraySize.columnMax();
        this.nullable = arraySize.nullable();
        this.validateElements = arraySize.checkElements();
        this.groups = arraySize.groups();
        this.groupsElements = arraySize.groupsElements();
    }

    @Override
    public boolean isValid(Object[][] objects, ConstraintValidatorContext constraintValidatorContext) {
        if (nullable && objects == null) {
            return true;
        } else if (objects == null) {
            return false;
        }

        if (!isBetween(objects.length, rowMin, rowMax)) {
            return false;
        }

        for (Object[] objectArray : objects) {
            if (!isBetween(objectArray.length, columnMin, columnMax)) {
                return false;
            }
            if (validateElements) {
                for (Object element: objectArray) {
                    this.validate(element);
                }
            }

        }

        return true;
    }

    private boolean isBetween(int value, int min, int max) {
        return min <= value && value <= max;
    }

    private void validate(Object element) {
        var error = this.validator.validate(element, this.groupsElements);

        if (error.isEmpty()) {
            return;
        }

        throw new ConstraintViolationException(error);
    }
}
