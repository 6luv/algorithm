import java.io.*;
import java.util.*;

class Solution {
    private static final int[] DY = { 1, -1, 0, 0 };
    private static final int[] DX = { 0, 0, 1, -1 };

    private static int n, m, sx, sy, dx, dy, xx, xy;
    private static char[][] map;
    private static int[][] devilTime;
    private static Deque<int[]> devilQueue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            map = new char[n][m];
            devilQueue = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                String line = br.readLine();

                for (int j = 0; j < m; j++) {
                    map[i][j] = line.charAt(j);

                    if (map[i][j] == 'S') {
                        sy = i;
                        sx = j;
                    } else if (map[i][j] == 'D') {
                        dy = i;
                        dx = j;
                    } else if (map[i][j] == '*') {
                        devilQueue.offer(new int[] { i, j });
                    }
                }
            }

            bfsDevil();
            int res = bfsSuyeon();

            sb.append("#").append(tc).append(" ").append(res == -1 ? "GAME OVER" : res).append("\n");
        }

        System.out.print(sb);
    }

    private static void bfsDevil() {
        Deque<int[]> queue = new ArrayDeque<>();
        for (int[] q : devilQueue) {
            queue.offer(new int[] { q[0], q[1], 0 });
        }

        boolean[][] visited = new boolean[n][m];
        visited[xy][xx] = true;

        devilTime = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(devilTime[i], Integer.MAX_VALUE);
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int y = current[0];
            int x = current[1];
            int time = current[2];

            devilTime[y][x] = time;

            for (int d = 0; d < 4; d++) {
                int ny = y + DY[d];
                int nx = x + DX[d];

                if (0 > ny || ny >= n || 0 > nx || nx >= m)
                    continue;

                if (visited[ny][nx] || map[ny][nx] == 'X' || map[ny][nx] == 'D')
                    continue;

                queue.offer(new int[] { ny, nx, time + 1 });
                visited[ny][nx] = true;
            }
        }
    }

    private static int bfsSuyeon() {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { sy, sx, 0 });

        boolean[][] visited = new boolean[n][m];
        visited[sy][sx] = true;

        int[][] suyeonTime = new int[n][m];

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int y = current[0];
            int x = current[1];
            int time = current[2];

            suyeonTime[y][x] = time;

            for (int d = 0; d < 4; d++) {
                int ny = y + DY[d];
                int nx = x + DX[d];

                if (0 > ny || ny >= n || 0 > nx || nx >= m)
                    continue;

                if (visited[ny][nx] || map[ny][nx] == 'X' || map[ny][nx] == '*')
                    continue;

                if (time + 1 >= devilTime[ny][nx])
                    continue;

                if (ny == dy && nx == dx) {
                    return time + 1;
                }

                queue.offer(new int[] { ny, nx, time + 1 });
                visited[ny][nx] = true;
            }
        }

        return -1;
    }
}