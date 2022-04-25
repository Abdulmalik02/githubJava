package space.banka.ifmo.infosec.vigenere.core.entities;

import java.text.MessageFormat;

/**
 * A column slice of a string.
 *
 * <p>We can consider the Vigenere ciphertext “stacked” into some number of columns.
 * This class represents one of such columns.
 *
 * <p>For example, given the ciphertext <code>“QPWKALVRXCQZIKR...”</code>
 * and assuming the key length is 5, we can stack the ciphertext into 5 columns:
 *
 * <pre>
 * QPWKA
 * LVRXC
 * QZIKG
 * R...
 * </pre>
 *
 * <p>Then a {@link ColumnSlice} of the given ciphertext with <code>numColumns</code>
 * of 5 and <code>columnIndex</code> of 1 will represent the character sequence
 * <code>“PVZ...”</code>.
 */
public class ColumnSlice implements CharSequence {

    private final CharSequence inner;
    private final int numColumns;
    private final int columnIndex;

    public ColumnSlice(CharSequence inner, int numColumns, int columnIndex) {
        this.inner = inner;
        checkNumColumns(numColumns);
        checkColumnIndex(numColumns, columnIndex);
        this.numColumns = numColumns;
        this.columnIndex = columnIndex;
    }

    private void checkNumColumns(int numColumns) {
        if (numColumns > 0) {
            return;
        }
        throw new IllegalArgumentException(
                "Cannot create ColumnSlice: " +
                "Number of columns must be positive, but it is " + numColumns
        );
    }

    private void checkColumnIndex(int numColumns, int columnIndex) {
        if (0 <= columnIndex && columnIndex < numColumns) {
            return;
        }
        throw new IllegalArgumentException(MessageFormat.format(
                "Cannot create a ColumnSlice: " +
                "Column index (which is {0}) must be non-negative and " +
                "less than number of columns (which is {1})",
                columnIndex, numColumns));
    }

    @Override
    public int length() {
        int originalLength = inner.length();
        int numWholeRows = originalLength / numColumns;
        int numColumnsInLastRow = originalLength % numColumns;
        return numWholeRows + (numColumnsInLastRow > columnIndex ? 1 : 0);
    }

    @Override
    public char charAt(int index) {
        int innerIndex = numColumns * index + columnIndex;
        return inner.charAt(innerIndex);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return this.toString().subSequence(start, end);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this);
        return stringBuilder.toString();
    }
}
