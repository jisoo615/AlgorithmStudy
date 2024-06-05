package impl;
// https://school.programmers.co.kr/learn/courses/30/lessons/60059
// 자물쇠와 열쇠
public class Pro60059 {
    public static void main(String[] args) {
        int[][] key = {{0, 0, 0},{1, 0, 0},{0, 1, 1}};
        int[][] lock = {{1, 1, 1},{1, 1, 0},{1, 0, 1}};
        System.out.println(new Solution().solution(key, lock));
    }
    static class Solution{
        static int m, n;
        public boolean solution(int[][] key, int[][] lock) {
        // key가 막 움직이는데 lock이 들어맞으면 true, key가 밖으로 나와도 되니 key가 움직이는 공간을 2배로 설정
            m = key.length;
            n = lock.length;
            int[][] key_space = new int[2*m+n][2*m+n];// key가 돌아다니니까 m*2 + lock기본크기n
            // 0도로 쭉 돌고 90도로 쭉 돌고 180도로 쭉 돌고...

            for (int d = 0; d < 4; d++) {
                
                for (int i = 0; i < m+n; i++) {
                    for (int j = 0; j < m+n; j++) {
                        //lock 부분만 초기화
                        for (int x = m; x < m + n; x++) {
                            for (int y = m; y < m+n; y++) {
                                key_space[x][y] = 0;
                            }
                        }

                        for (int x = i; x < i+m; x++) {
                            for (int y = j; y < j+m; y++) {
                                key_space[x][y] = key[x-i][y-j];// 키스페이스에 옮기기
                            }
                        }

                        int[][] result_key = new int[n][n];// lock 만큼만 떼어낸 최종키
                        for (int k = m; k < m+n; k++) {
                            for (int l = m; l < m+n; l++) {
                                result_key[k-m][l-m] = key_space[k][l];
                            }
                        }

                        if(isFit(result_key, lock)) return true;
                    }
                }
                // 방향 바꾸기
                key = rotate90(key);
            }
            
            return false;
        }

        public int[][] rotate90(int[][] key){
            int[][] newKey = new int[m][m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    newKey[i][j] = key[m-1-j][i];
                }
            }
            return newKey;
        }

        public boolean isFit(int[][] result_key, int[][] lock){
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(result_key[i][j] == lock[i][j]) return false;
                }
            }
            return true;
        }
    }

}
