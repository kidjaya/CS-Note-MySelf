package Test;

/**
 * @author kidjaya
 */
public class 两数相加 {

    public static void main(String[] args) {
//        ListNode node1 = new ListNode(1);
//        node1.next = new ListNode(0);
//        ListNode node2 = new ListNode(9);
//        node2.next = new ListNode(9);
//
//        addTwoNumbers(node1,node2);
        System.out.println(Integer.MAX_VALUE);

    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode renode1 = reverse(l1);
        ListNode renode2 = reverse(l2);
        int number1 = 0,number2 = 0;
        int n = 1;
        while(renode1!=null){
            number1 += n*renode1.val;
            renode1 = renode1.next;
            n*=10;
        }
        n = 1;
        while(renode2!=null){
            number2 += n*renode2.val;
            renode2 = renode2.next;
            n*=10;
        }
        int res = number1 + number2;

        String str = Integer.toString(res);
        char[] chars = str.toCharArray();
        ListNode node = null,pre = null;
        for(int i = 0;i<str.length();i++){
            node = new ListNode((int)chars[i] - '0');
            node.next = pre;
            pre = node;
        }

        return reverse(node);

    }

    public static ListNode reverse(ListNode head){
        ListNode pre = null,cur = head;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
