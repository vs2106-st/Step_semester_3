class BrokenLibraryMember {
    static String name;
    static String memberId;
    static int booksIssued;

    public BrokenLibraryMember(String name, String memberId, int booksIssued) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }
}

class FixedLibraryMember {
    static String libraryName = "Central Library";
    static int memberCount = 1000;

    String name;
    String memberId;
    int booksIssued;

    public FixedLibraryMember(String name, int booksIssued) {
        memberCount++;
        this.memberId = "LM-" + memberCount;
        this.name = name;
        this.booksIssued = booksIssued;
    }

    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    static void printTotalMembers() {
        System.out.println("Total members: " + (memberCount - 1000));
    }
}

class MainF4 {
    public static void main(String[] args) {
        System.out.println("--- Broken Version ---");
        BrokenLibraryMember m1 = new BrokenLibraryMember("Aditi", "LM-1001", 2);
        BrokenLibraryMember m2 = new BrokenLibraryMember("Rohan", "LM-1002", 1);
        System.out.println(BrokenLibraryMember.name);
        System.out.println(BrokenLibraryMember.name);

        System.out.println("\n--- Fixed Version ---");
        FixedLibraryMember fixed1 = new FixedLibraryMember("Aditi", 2);
        FixedLibraryMember fixed2 = new FixedLibraryMember("Rohan", 1);

        fixed1.printMemberCard();
        fixed2.printMemberCard();
        FixedLibraryMember.printTotalMembers();
    }
}
