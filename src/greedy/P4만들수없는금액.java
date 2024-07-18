package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P4만들수없는금액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(coins);
        // 동전으로 만들수 없는 최소 금액은?

        int target = 1;
        for (int i = 0; i < N; i++) {
            if(target >= coins[i]) target += coins[i];
            else break;
        }
        // 1>=1 2>=1 3>=2 4>=3 7<9
        System.out.println(target);
    }
}
