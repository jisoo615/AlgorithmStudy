package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P탑승구 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        boolean[] gate = new boolean[G + 1];
        int answer = 0;
        for (int i = 0; i < P; i++) {
            int number = Integer.parseInt(br.readLine());
            if (gate[number]) break;
            gate[number] = true;
            answer++;
        }

        System.out.println(answer);
    }
}
