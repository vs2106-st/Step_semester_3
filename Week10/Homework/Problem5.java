class Node {
    int coeff;
    int exp;
    Node next;

    Node(int coeff, int exp) {
        this.coeff = coeff;
        this.exp = exp;
        this.next = null;
    }
}

public class Solution {
    public static Node addPolynomials(Node p, Node q) {
        Node dummy = new Node(0, 0);
        Node tail = dummy;

        while (p != null && q != null) {
            if (p.exp > q.exp) {
                tail.next = new Node(p.coeff, p.exp);
                tail = tail.next;
                p = p.next;
            } else if (p.exp < q.exp) {
                tail.next = new Node(q.coeff, q.exp);
                tail = tail.next;
                q = q.next;
            } else {
                int sumCoeff = p.coeff + q.coeff;
                if (sumCoeff != 0) {
                    tail.next = new Node(sumCoeff, p.exp);
                    tail = tail.next;
                }
                p = p.next;
                q = q.next;
            }
        }

        while (p != null) {
            tail.next = new Node(p.coeff, p.exp);
            tail = tail.next;
            p = p.next;
        }

        while (q != null) {
            tail.next = new Node(q.coeff, q.exp);
            tail = tail.next;
            q = q.next;
        }

        return dummy.next;
    }

    public static void printPolynomial(Node head) {
        if (head == null) {
            System.out.println("0");
            return;
        }

        StringBuilder sb = new StringBuilder();
        Node curr = head;
        boolean isFirst = true;

        while (curr != null) {
            int coeff = curr.coeff;
            int exp = curr.exp;

            if (isFirst) {
                if (coeff < 0) {
                    sb.append("-");
                }
            } else {
                if (coeff > 0) {
                    sb.append(" + ");
                } else {
                    sb.append(" - ");
                }
            }

            int absCoeff = Math.abs(coeff);

            if (exp == 0) {
                sb.append(absCoeff);
            } else if (exp == 1) {
                if (absCoeff == 1) {
                    sb.append("x");
                } else {
                    sb.append(absCoeff).append("x");
                }
            } else {
                if (absCoeff == 1) {
                    sb.append("x^").append(exp);
                } else {
                    sb.append(absCoeff).append("x^").append(exp);
                }
            }

            isFirst = false;
            curr = curr.next;
        }

        System.out.println(sb.toString());
    }
}
