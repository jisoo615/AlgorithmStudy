package impl;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15686 치킨배달
public class P15686 {
    static int[][] map;
    static ArrayList<Point> homeList = new ArrayList<>();
    static ArrayList<Point> chickenList = new ArrayList<>();
    static int min = Integer.MAX_VALUE;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // M개를 뽑는 경우를 조합 - 다 해보고 최저거리구하기
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) homeList.add(new Point(i, j));
                else if(map[i][j] == 2) chickenList.add(new Point(i, j));
            }
        }

        johap(0, M, 0, new int[M]);

        System.out.println(min);
    }
    public static void johap(int start, int M, int idx, int[] chickenIdx){
        if(idx==M){
            min = Math.min(min, chickenDist(chickenIdx));
            return;
        }
        for (int i = start; i < chickenList.size(); i++) {
            chickenIdx[idx] = i;
            johap(i+1, M, idx+1, chickenIdx);
        }
    }

    public static int chickenDist(int[] chickenIdx){
        int distance = 0;
        for (int i = 0; i < homeList.size(); i++) {
            Point home = homeList.get(i);
            int shortest = Integer.MAX_VALUE;
            for (int j = 0; j < chickenIdx.length; j++) {
                Point chicken = chickenList.get(chickenIdx[j]);
                shortest = Math.min(shortest, Math.abs(chicken.x-home.x)+Math.abs(chicken.y-home.y) );
            }
            distance += shortest;
        }
        return distance;
    }
}
