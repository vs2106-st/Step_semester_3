class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}
public class Solution {
    public static Node removeKthFromEnd(Node head, int k) {
        Node dummy = new Node(0);
        dummy.next = head;

        Node lead = dummy;
        Node trailing = dummy;

        for (int i = 0; i <= k; i++) {
            if (lead == null) {
                return head;
            }
            lead = lead.next;
        }

        while (lead != null) {
            lead = lead.next;
            trailing = trailing.next;
        }

        trailing.next = trailing.next.next;

        return dummy.next;
    }
}
