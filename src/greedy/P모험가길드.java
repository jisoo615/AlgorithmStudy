package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P모험가길드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(str[i]);
            arr[i] = x;
        }
        Arrays.sort(arr);

        int answer = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {// arr[i]공포도의 사람이 있는 곳에는 arr[i]명의 사람이 있어야함
            cnt++;
            if(cnt == arr[i]){
                answer++;
                cnt = 0;
            }
        }

        System.out.println(answer);
    }
}
