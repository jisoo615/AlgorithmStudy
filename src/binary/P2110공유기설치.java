package binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 공유기 거리를 이분탐색
public class P2110공유기설치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        int answer = -1;
        int l = 1, r = n-1;
        while (l <= r) {
            int mid = (l+r)/2;
            int cnt = calc(mid, arr);

            if(cnt >= c){// 더 많은 공유기 설치되면 거리 늘리기
                l = mid+1;
                answer = mid;
            }else if(cnt < c){// 공유기 적게 설치되면 거리 줄이기
                r = mid-1;
            }
        }
        System.out.println(answer);
    }

    public static int calc(int distance, int[] arr){
        int cnt = 1;
        int before = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]-before >= distance){
                cnt++;
                before = arr[i];
            }
        }
        return cnt;
    }
}

