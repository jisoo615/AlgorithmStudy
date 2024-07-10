package ch19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P19237어른상어 {
    public static HashMap<Integer,Shark> sharks;
    public static int[][][] grid;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int[][][] directions;
    public static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        grid = new int[N][N][3];// 번호, 냄새남은시간, 상어면-1
        sharks = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                grid[i][j][0] = Integer.parseInt(st.nextToken());
                if (grid[i][j][0] > 0){
                    grid[i][j][1] = K;
                    grid[i][j][2] = -1;
                    sharks.put(grid[i][j][0], new Shark(i, j, -1));
                }
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {// 현재 상어 방향 저장
            sharks.get(i+1).dir = Integer.parseInt(st.nextToken())-1;
        }

        directions = new int[M+1][4][4];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < 4; k++) {
                    directions[i+1][j][k] = Integer.parseInt(st.nextToken())-1;
                }
            }
        }

        int result = 0;
        while(sharks.size()>1 && result <= 1000){
            System.out.println("========="+result+"==========");
            result++;
            // 상어 이동
            sharkMove();
            // 냄새 감소
            reduceScent();
            // 냄새뿌리기
            spreadScent();
        }
        System.out.println(result>1000 ? -1 : result);// 1000초 넘으면 -1
    }

    public static void spreadScent(){
        for(int key : sharks.keySet()){
            Shark s = sharks.get(key);
            grid[s.x][s.y][0] = key;
            grid[s.x][s.y][1] = K;
            grid[s.x][s.y][2] = -1;
        }
    }
    public static void reduceScent(){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if(grid[i][j][2]==0) continue;
                --grid[i][j][1];
                if(grid[i][j][1]==0) grid[i][j][0] = 0;
            }
        }
    }

    public static void sharkMove(){
        int[][] tempGrid = new int[grid.length][grid.length];
        HashMap<Integer, Shark> tmepSharks = new HashMap<>();

        for(int key : sharks.keySet()){
            Shark shark = sharks.get(key);
            boolean moved = false;
            for (int i = 0; i < 4; i++) {
                int ndir = directions[key][shark.dir][i];
                int nx = shark.x + dx[ndir];
                int ny = shark.y + dy[ndir];

                if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid.length) continue;
                if (grid[nx][ny][0] == 0){
                    moved = true;
                    tmepSharks.put(key, new Shark(nx, ny, ndir));
                    System.out.println(key+"번 상어: "+ shark.x+","+shark.y +"->"+nx+","+ny);
                    break;
                }
            }
            // 갈 방향 없으면 자기 방향으로
            if(!moved){
                for (int i = 0; i < 4; i++) {
                    int ndir = directions[key][shark.dir][i];
                    int nx = shark.x + dx[ndir];
                    int ny = shark.y + dy[ndir];

                    if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid.length) continue;
                    if (grid[nx][ny][0] == key) {
                        tmepSharks.put(key, new Shark(nx, ny, ndir));
                        System.out.println(key+"번 상어: "+ shark.x+","+shark.y +"->"+nx+","+ny);
                        break;
                    }
                }
            }
        }

        // 충돌처리
        for(int key : tmepSharks.keySet()){
            Shark s = tmepSharks.get(key);
            if(tempGrid[s.x][s.y] == 0 || tempGrid[s.x][s.y] > key) tempGrid[s.x][s.y] = key;
        }

        // 충돌처리에 살아남은 상어만 위치 업데이트
        sharks.clear();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if(tempGrid[i][j]>0){
                    Shark s = tmepSharks.get(tempGrid[i][j]);
                    sharks.put(tempGrid[i][j], s);
                }
            }
        }

    }

    static class Shark{
        int dir;
        int x, y;
        public Shark(int x, int y, int dir){
            this.x=x; this.y=y; this.dir = dir;
        }
    }

}
