package ch19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

public class P19236청소년상어 {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int maxSum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 4 x 4 크기, 방향 8개
        int[][] map = new int[4][4];
        HashMap<Integer, Fish> fishMap = new HashMap<>();
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;
                map[i][j] = num;
                fishMap.put(num, new Fish(i, j, dir));
            }
        }
        // 상어가 0,0 물고기를 먹고 시작
        Fish startFish = fishMap.get(map[0][0]);
        int sharkDir = startFish.dir;
        int startFishNum = map[0][0];
        fishMap.remove(startFishNum);
        map[0][0] = -1;
        moveFish(map, fishMap, 0, 0);

        dfs(map, fishMap, 0, 0, sharkDir, startFishNum);
        System.out.println(maxSum);
    }

    static public void dfs(int[][] map, HashMap<Integer, Fish> fishMap, int sharkX, int sharkY, int sharkDir, int sum){
        maxSum = Math.max(maxSum, sum);
        for (int i = 1; i <= 3; i++) {
            int nx = sharkX + dx[sharkDir] * i;
            int ny = sharkY + dy[sharkDir] * i;
            if(nx<0 || nx>3 || ny<0 || ny>3 || map[nx][ny]==0) continue;
            // 새로운 map과 fishmap으로 dfs
            int[][] newMap = new int[4][4];
            for (int j = 0; j < 4; j++) {
                System.arraycopy(map[j], 0, newMap[j], 0, 4);
            }
            HashMap<Integer, Fish> newFishMap = new HashMap<>();
            for(Map.Entry<Integer, Fish> entry : fishMap.entrySet()){
                newFishMap.put(entry.getKey(), entry.getValue());
            }

            int fishNum = map[nx][ny];
            Fish eatenFish = newFishMap.remove(fishNum);
            newMap[sharkX][sharkY] = 0;// 빈공간
            newMap[nx][ny] = -1;// 상어
            moveFish(newMap, newFishMap, nx, ny);
            dfs(newMap, newFishMap, nx, ny, eatenFish.dir, sum + fishNum);
        }
    }

    static public void moveFish(int[][] map, HashMap<Integer, Fish> fishMap, int sharkX, int sharkY){
        for (int i = 1; i <= 16; i++) {
            if(!fishMap.containsKey(i)) continue;
            Fish fish = fishMap.get(i);
            if(fish==null) continue;
            for (int j = 0; j < 8; j++) {
                int ndir = (fish.dir + j) % 8;
                int nx = fish.x + dx[ndir];
                int ny = fish.y + dy[ndir];
                if(nx<0 || nx>3 || ny<0 || ny>3 || (nx==sharkX&&ny==sharkY)) continue;
                // 빈킨이면 이동만, 있으면 교체
                if(map[nx][ny]!=0){
                    fishMap.put(map[nx][ny], new Fish(fish.x, fish.y, fishMap.get(map[nx][ny]).dir));
                }
                map[fish.x][fish.y] = map[nx][ny];
                map[nx][ny] = i;
                fishMap.put(i, new Fish(nx, ny, ndir));
                break;
            }
        }
    }

    static class Fish{
        int x, y, dir;
        public Fish(int x, int y, int dir) {
            this.dir = dir;
            this.x = x; this.y = y;
        }
    }

}
