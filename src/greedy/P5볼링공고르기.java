package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5볼링공고르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] cnt = new int[M+1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            cnt[Integer.parseInt(st.nextToken())] += 1;
        }

        int result = 0;
        for (int i = 1; i < M; i++) {
            for (int j = i+1; j <= M; j++) {
                result += cnt[i] * cnt[j];
            }
        }
        System.out.println(result);
    }
}
