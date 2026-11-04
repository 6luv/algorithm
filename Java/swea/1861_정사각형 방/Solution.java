import java.io.*;
import java.util.*;

class Solution {
    private static final int[] DY = { 1, -1, 0, 0 };
    private static final int[] DX = { 0, 0, 1, -1 };

    private static int[][] map;
    private static int n, max, start;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());

            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            start = n * n;
            max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int cnt = bt(i, j, 1);

                    if (max < cnt) {
                        max = cnt;
                        start = map[i][j];
                    } else if (max == cnt) {
                        start = Math.min(map[i][j], start);
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(start).append(" ").append(max).append("\n");
        }

        System.out.print(sb);
    }

    private static int bt(int y, int x, int cnt) {
        int value = map[y][x];

        for (int d = 0; d < 4; d++) {
            int ny = y + DY[d];
            int nx = x + DX[d];

            if (0 > ny || ny >= n || 0 > nx || nx >= n) {
                continue;
            }

            if (map[ny][nx] != value + 1) {
                continue;
            }

            cnt = Math.max(cnt, bt(ny, nx, cnt + 1));
        }

        return cnt;
    }
}