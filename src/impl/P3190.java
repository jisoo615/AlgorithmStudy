package impl;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 뱀
public class P3190 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int x, y;
        Deque<Info> dq = new LinkedList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            map[x][y] = -1;
        }
        int L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            dq.add(new Info(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        // 뱀이 0,0에서 시작
        Queue<Point> q = new LinkedList<>();
        Deque<Point> snake = new LinkedList<>();
        q.add(new Point(0, 0));
        int second = 0;// 벽이나 자신 몸에 부딪히면 끝
        map[0][0] = 1;
        snake.add(new Point(0, 0));
        while(!q.isEmpty()){
            Point node = q.poll();
            for (int i = 0; i < 4; i++) {//TODO: 방향이 정해지면 일관된 방향으로 가는 걸로 바꾸기
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if(nx<0 || nx>N || ny<0 || ny>N) continue;// 벽에 부딪힘
                if(map[nx][ny]==1) continue;// 몸에 부딪힘

                if(map[nx][ny]==-1){// 사과있음
                    map[nx][ny] = map[node.x][node.y] + 1;// 초를 저장
                }else{// 사과없음
                    snake.pollFirst();
                    map[nx][ny] = map[node.x][node.y] + 1;// 초를 저장
                }
                snake.add(new Point(nx, ny));

            }
        }
        

    }
    static class Info{
        int t;
        String dir;
        public Info(int t, String dir){this.t = t; this.dir = dir;}
    }
}
