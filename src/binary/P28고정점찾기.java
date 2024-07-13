package binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P28고정점찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0; int r = N-1;
        while(l <= r){
            int mid = (l+r)/2;

            if(mid > arr[mid]){
                l = mid+1;
            }else if(mid < arr[mid]){
                r = mid-1;
            }else{
                System.out.println(mid);
                System.exit(0);
            }
        }
        System.out.println(-1);
    }
}
