import java.util.*;

public class Problem5 {
    interface Reservable {
        String reserve(String memberId);
    }

    interface Downloadable {
        String download();
    }

    static abstract class Resource {
        private String id;

        public Resource(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    static class Book extends Resource implements Reservable {
        public Book(String id) {
            super(id);
        }

        @Override
        public String reserve(String memberId) {
            return getId() + " reserved for " + memberId;
        }
    }

    static class EBook extends Resource implements Reservable, Downloadable {
        public EBook(String id) {
            super(id);
        }

        @Override
        public String reserve(String memberId) {
            return getId() + " reserved for " + memberId;
        }

        @Override
        public String download() {
            return getId() + " downloaded";
        }
    }

    static class Library {
        private Resource[] resources = new Resource[100];
        private int count = 0;

        public void addResource(Resource r) {
            for (int i = 0; i < count; i++) {
                if (resources[i].getId().equals(r.getId())) {
                    System.out.println("duplicate rejected");
                    return;
                }
            }

            int pos = count;
            while (pos > 0 && resources[pos - 1].getId().compareTo(r.getId()) > 0) {
                resources[pos] = resources[pos - 1];
                pos--;
            }
            resources[pos] = r;
            count++;
            System.out.println(r.getId() + " added");
        }

        public Resource findResource(String id) {
            int left = 0, right = count - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int cmp = resources[mid].getId().compareTo(id);
                if (cmp == 0) {
                    System.out.println(id + " found at index " + mid);
                    return resources[mid];
                } else if (cmp < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return null;
        }

        public void reserveResource(String id, String memberId) {
            Resource r = findResourceById(id);
            if (r instanceof Reservable) {
                System.out.println(((Reservable) r).reserve(memberId));
            } else if (r != null) {
                System.out.println(id + " rejected: reserve unsupported");
            }
        }

        public void downloadResource(String id) {
            Resource r = findResourceById(id);
            if (r instanceof Downloadable) {
                System.out.println(((Downloadable) r).download());
            } else if (r != null) {
                System.out.println(id + " rejected: download unsupported");
            }
        }

        private Resource findResourceById(String id) {
            for (int i = 0; i < count; i++) {
                if (resources[i].getId().equals(id)) {
                    return resources[i];
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Library library = new Library();

        library.addResource(new Book("B1"));
        library.addResource(new Book("B1"));
        library.addResource(new EBook("E1"));

        library.reserveResource("B1", "M1");
        library.downloadResource("B1");
        library.downloadResource("E1");

        library.findResource("E1");
    }
}
