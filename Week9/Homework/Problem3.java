import java.util.*;

public class Problem3 {
    public static class BookRecord {
        public String isbn;
        public String title;

        public BookRecord(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }
    }

    public static String findBook(List<BookRecord> catalog, String targetIsbn) {
        int left = 0;
        int right = catalog.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            BookRecord midRecord = catalog.get(mid);
            int cmp = midRecord.isbn.compareTo(targetIsbn);

            if (cmp == 0) {
                return midRecord.title;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return "Not Found";
    }
}
