package main.java;

import com.jfoenix.controls.JFXPasswordField;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Objects;

public class LimitedPasswordField extends JFXPasswordField {
    private final IntegerProperty maxLength;

    public LimitedPasswordField() {
        super();
        this.maxLength = new SimpleIntegerProperty(-1);
    }

    public IntegerProperty maxLengthProperty() {
        return this.maxLength;
    }

    public final Integer getMaxLength() {
        return this.maxLength.getValue();
    }

    public final void setMaxLength(Integer maxLength) {
        Objects.requireNonNull(maxLength, "Max length cannot be null, -1 for no limit");
        this.maxLength.setValue(maxLength);
    }

    @Override
    public void replaceText(int start, int end, String insertedText) {
        if (this.getMaxLength() <= 0) {
            // Default behavior, in case of no max length
            super.replaceText(start, end, insertedText);
        }
        else {
            // Get the text in the textfield, before the user enters something
            String currentText = this.getText() == null ? "" : this.getText();

            // Compute the text that should normally be in the textfield now
            String finalText = currentText.substring(0, start) + insertedText + currentText.substring(end);

            // If the max length is not excedeed
            int numberOfExceedingCharacters = finalText.length() - this.getMaxLength();
            if (numberOfExceedingCharacters <= 0) {
                // Normal behavior
                super.replaceText(start, end, insertedText);
            }
            else {
                // Otherwise, cut the the text that was going to be inserted
                String cutInsertedText = insertedText.substring(
                        0,
                        insertedText.length() - numberOfExceedingCharacters
                );

                // And replace this text
                super.replaceText(start, end, cutInsertedText);
            }
        }
    }
}
