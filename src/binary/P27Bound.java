package binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P27Bound {
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int low = lower_bound(X, N);
        int up = upper_bound(X, N);
        int answer = up-low;
        System.out.println(answer==0? -1:answer);
    }
    public static int lower_bound(int x, int N){// 처음으로 x 이상의 숫자가 나오는 인덱스
        int l = 0; int r = N-1;
        while(l < r){
            int mid = (l+r)/2;

            if(x > arr[mid]){
                l = mid+1;// x보다 작은거 포함 안시킴
            }else{// arr[mid] >= x
                r = mid;// x 이상인 값 포함
            }
        }
        return l;
    }
    public static int upper_bound(int x, int N){// x보다 큰 값이 처음 나오는 인덱스
        int l = 0; int r = N-1;
        while(l < r){
            int mid = (l+r)/2;

            if(x >= arr[mid]){
                l = mid+1;// x보다 작거나 같은건 포함x
            }else{// x < arr[mid]
                r = mid;// x보다 큰건 포함
            }
        }
        return l;
    }
}
