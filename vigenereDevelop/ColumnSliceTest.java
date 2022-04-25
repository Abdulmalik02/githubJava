package space.banka.ifmo.infosec.vigenere.core.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ColumnSliceTest {

    @Test
    void is_subsequence_of_original_string() {
        String original = "QPWKALVRXCQZIKR";
        ColumnSlice columnSlice = new ColumnSlice(original, 5, 1);
        assertEquals("PVZ", columnSlice.toString());
    }

    @Test
    void columnIndex_must_be_less_than_numColumns() {
        String original = "anything";
        assertThrows(IllegalArgumentException.class,
                () -> new ColumnSlice(original, 3, 4));
    }

    @Test
    void numColumns_must_not_be_negative() {
        String original = "anything";
        assertThrows(IllegalArgumentException.class,
                () -> new ColumnSlice(original, -1, 0));
    }

    @Test
    void numColumns_must_not_be_zero() {
        String original = "anything";
        assertThrows(IllegalArgumentException.class,
                () -> new ColumnSlice(original, 0, 0));
    }

    @Test
    void if_last_row_is_short_left_columns_are_longer_than_right() {
        String original = "0123456789";
        assertEquals("048", new ColumnSlice(original, 4, 0).toString());
        assertEquals("159", new ColumnSlice(original, 4, 1).toString());
        assertEquals("26", new ColumnSlice(original, 4, 2).toString());
        assertEquals("37", new ColumnSlice(original, 4, 3).toString());
    }
}
