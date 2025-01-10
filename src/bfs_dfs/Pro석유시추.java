package bfs_dfs;
// https://school.programmers.co.kr/learn/courses/30/lessons/250136
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;
import java.util.HashSet;

public class Pro석유시추 {
    public static void main(String[] args) {
        System.out.println(
                new Solution().solution(
                        new int[][]{
                                {0, 0, 0, 1, 1, 1, 0, 0},
                                {0, 0, 0, 0, 1, 1, 0, 0},
                                {1, 1, 0, 0, 0, 1, 1, 0},
                                {1, 1, 1, 0, 0, 0, 0, 0},
                                {1, 1, 1, 0, 0, 0, 1, 1}
                        }
                )
        );
    }

    static class Solution {
        int[] dx = {-1, 1, 0, 0};// 상하좌우
        int[] dy = {0, 0, -1, 1};
        int N, M;
        int[] oils;

        public int solution(int[][] land) {
            // bfs 로 모든 칸에 총 석유량 구해두기
            N = land.length;
            M = land[0].length;
            oils = new int[M];// 각 열에 해당하는 석유량을 저장

            bfs(land);

            Arrays.sort(oils);
            return oils[M-1];
        }

        public void bfs(int[][] land){
            boolean[][] visited = new boolean[N][M];
            Queue<Point> queue = new LinkedList<>();
            // 모든 지역 돌면서 찾기
            for(int i=0; i<N; i++){
                for (int j = 0; j < M; j++) {
                    if(visited[i][j] || land[i][j] == 0) continue;

                    // 석유있는 열을 중복걸러 저장하는 set
                    HashSet<Integer> colSet = new HashSet<>();

                    // bfs시작
                    Point start = new Point(i, j);
                    colSet.add(j);
                    int count = 1;
                    queue.offer(start);
                    visited[i][j] = true;

                    while(!queue.isEmpty()){
                        Point p = queue.poll();
                        for(int d = 0; d < 4; d++){
                            int nx = p.x + dx[d];
                            int ny = p.y + dy[d];
                            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                            if(visited[nx][ny] || land[nx][ny] == 0) continue;
                            queue.offer(new Point(nx, ny));
                            colSet.add(ny);
                            count++;
                            visited[nx][ny] = true;
                        }
                    }

                    // 열별 총량 더하기
                    for(int col : colSet){
                        oils[col] += count;
                    }
                }
            }

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
