package ch19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P19236청소년상어 {
    static int[] dx = {-100, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {-100, 0, 1, 1, 1, 0, -1, -1, -1};
    static int[][] map;
    static Fish[] fishArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 4 x 4 크기, 방향 8개
        map = new int[4][4];
        fishArr = new Fish[17];
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                fishArr[i*4+j+1] = new Fish(i, j, num, dir);
            }
        }
        // 상어가 0,0 물고기를 먹고 시작



    }

    static public void moveFish(Fish shark){
        for (int i = 1; i <= 16; i++) {
            Fish fish = fishArr[i];
            if(fish==null) continue;
            for (int j = 0; j < 8; j++) {
                int ndir = (fish.dir + j) % 8;
                int nx = fish.x + dx[ndir];
                int ny = fish.y + dy[ndir];
                if(nx<0 || nx>3 || ny<0 || ny>3 || (nx==shark.x&&ny==shark.y)) continue;
                // 빈킨이면 이동
                if(map[nx][ny]==0){
                    map[nx][ny] = fish.num;
                    map[fish.x][fish.y] = 0;
                    fishArr[fish.num].x = nx;
                    fishArr[fish.num].y = ny;
                    break;
                }
                // 교체
                Fish nFish = fishArr[map[nx][ny]];
                nFish.x = fish.x;
                nFish.y = fish.y;
                fish.x = nx;
                fish.y = ny;
                map[fish.x][fish.y] = fish.num;
                map[nFish.x][nFish.y] = nFish.num;
                break;
            }
        }
    }

    static class Fish{
        int x, y, num, dir;
        public Fish(int x, int y, int num, int dir) {
            this.num = num; this.dir = dir;
            this.x = x; this.y = y;
        }
    }

}
