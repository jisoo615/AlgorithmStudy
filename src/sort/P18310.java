package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 안테나
public class P18310 {
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr= new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        int answer = -1;
        for (int i = 0; i < N; i++) {
            int pos = arr[i];
            int dist = 0;
            for (int j = 0; j <N; j++) {
                dist += Math.abs(arr[j] - pos);
            }
            if(dist < min){
                min = dist;
                answer = arr[i];
            }
        }
        System.out.println(answer);
    }
}
