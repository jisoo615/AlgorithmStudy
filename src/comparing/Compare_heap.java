package comparing;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Compare_heap {
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Arrays.sort(arr);
        System.out.println("int[] : "+Arrays.toString(arr));
        // int[]는 comparator 못씀
        Node[] nodes = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node((arr[i]));
        }

        Arrays.sort(nodes, (n1, n2)->{
            return n2.num - n1.num;
        });
        StringBuilder sb = new StringBuilder("reverse nodes[] : ");
        for (int i = 0; i < nodes.length; i++) {
            sb.append(nodes[i].num+" ");
        }
        System.out.println(sb);
//--------------------------------------------------------------------
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.num - n2.num);
        pq.add(nodes[3]);
        pq.add(nodes[1]);
        pq.add(nodes[6]);
        System.out.println("origin order pq: "+pq.poll().num);

        PriorityQueue<Node> pq_reverse = new PriorityQueue<>((n1, n2) -> n2.num - n1.num);
        pq.add(nodes[3]);
        pq.add(nodes[1]);
        pq.add(nodes[6]);
        System.out.println("reverse order pq: "+pq_reverse.poll().num);

    }

    public static class Node{
        int num, status;
        public Node(int num){
            this.num = num;
        }
    }
}
