package bfs_dfs;

import java.util.LinkedList;
import java.util.Queue;

public class Pro석유시추 {
    static class Solution {
        int[] dx = {-1, 1, 0, 0};// 상하좌우
        int[] dy = {0, 0, -1, 1};
        int N, M;

        public int solution(int[][] land) {
            // bfs 로 면적을 구하기
            N = land.length;
            M = land[0].length;
            int answer = -1;
            for (int i = 0; i < M; i++) {
                answer = Math.max(answer, bfs(i, land));
            }
            return answer;
        }
        
        public int bfs(int m, int[][] land){// 석유시추 y의 위치 (1..N, m)
            // visited를 시추 마다 초기화 시켜서 진행, 하나의 시추 경우에 한번에 vistied 사용하기
            int sum = 0;
            boolean[][] visited = new boolean[N][M];
            Queue<Point> queue = new LinkedList<>();
            for(int i = 0; i < N; i++){// 아래로 내려가는 이동하는 지점 스타터
                if( visited[i][m] || land[i][m] == 0 ) continue;

                Point start = new Point(i, m);

                queue.offer(start);
                visited[i][m] = true;
                int count = 1;
                while(!queue.isEmpty()){
                    Point p = queue.poll();
                    for(int d = 0; d < 4; d++){
                        int nx = p.x + dx[d];
                        int ny = p.y + dy[d];
                        if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                        if(visited[nx][ny] || land[nx][ny] == 0) continue;
                        queue.offer(new Point(nx, ny));
                        visited[nx][ny] = true;
                        count++;
                    }
                }
                sum += count;
            }
            return sum;
        }

        static public class Point{
            int x, y;
            public Point(int x, int y){
                this.x = x;
                this.y = y;
            }
        }
    }
}
