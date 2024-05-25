package bfs_dfs;
// 백준 제출시 package 지우고 내기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 인구이동
public class P16234 {
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    static int N, r, R;
    static int[][] map, temp;
    static boolean[][] visited;
    static boolean flag = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        while (flag){// 인구이동이 없을때까지
            flag = false;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(visited[i][j]) continue;
                    visited[i][j] = true;
                    BFS(i, j);// 연합국가 구하기
                }
            }
            cnt++;
        }
        System.out.println(cnt-1);
    }

    static public void BFS(int x, int y){
        Queue<int[]> union = new LinkedList<>();
        Queue<int[]> q = new LinkedList<>();
        int sum = 0;
        q.add(new int[] {x, y});
        union.add(new int[] {x, y});
        while (!q.isEmpty()) {
            int[] xy = q.poll();
            int population = map[xy[0]][xy[1]];
            sum += population;
            for (int i = 0; i < 4; i++) {
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visited[nx][ny]) continue;
                int diff = Math.abs(population - map[nx][ny]);
                if (diff < r || diff > R) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
                union.add(new int[]{nx, ny});
            }
        }

        if(union.size() >= 2){// 연합있음
            flag = true;// 모든 map을 돌때까지 false면 main안의 while문은 종료
        }else return;

        int avg = sum/union.size();
        while (!union.isEmpty()){
            int[] xy = union.poll();
            map[xy[0]][xy[1]] = avg;
        }

    }
}
