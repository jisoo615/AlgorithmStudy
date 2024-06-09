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
        Deque<Info> infoDq = new LinkedList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            x = Integer.parseInt(st.nextToken()) - 1;
            y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = -1;
        }
        int L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            infoDq.add(new Info(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        int[] dx = {-1, 0, 1, 0};// 상우하좌
        int[] dy = {0, 1, 0, -1};
        int d = 1;// 우
        // 뱀이 0,0에서 시작
        Deque<Point> snake = new LinkedList<>();
        map[0][0] = 1;
        snake.add(new Point(0, 0));
        int second = 0;

        while(true){
            second++;
            Point node = snake.peekLast();

            int nx = node.x + dx[d];
            int ny = node.y + dy[d];
            if(nx<0 || nx>=N || ny<0 || ny>=N) break;// 벽에 부딪힘
            if(map[nx][ny]>=1) break;// 몸에 부딪힘
            if(map[nx][ny]==-1){// 사과있음
                map[nx][ny] = 1;
                snake.add(new Point(nx, ny));
            }else{// 사과없음
                Point tail = snake.pollFirst();
                map[nx][ny] = 1;
                map[tail.x][tail.y] = 0;
                snake.add(new Point(nx, ny));
            }

            Info info = infoDq.peekFirst();
            if(!infoDq.isEmpty() && second == info.t){
                infoDq.removeFirst();
                if(info.dir.equals("D")){// 오른쪽
                    d = d+1 > 3 ? 0 : d+1;
                }else{// L 왼쪽
                    d = d-1 <0 ? 3 : d-1;
                }
            }
        }
        System.out.println(second);
    }
    static class Info{
        int t;
        String dir;
        public Info(int t, String dir){this.t = t; this.dir = dir;}
    }
}
