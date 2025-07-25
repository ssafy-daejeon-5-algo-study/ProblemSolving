import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Seorim {

    static int ans = -1;
    static int n;
    static int[][] innings;
    static List<Integer> order;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        innings = new int[n][9];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            for (int j = 0; j < 9; j++) {
                innings[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        order = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            order.add(0);
        }
        order.set(3, 0);

        backTracking(0);
        System.out.println(ans);
    }

    static void backTracking(int idx) {
        if (idx == 9) {
            ans = Math.max(ans, calPoint());
            return;
        }

        if (idx == 3) {
            backTracking(idx+1);
            return;
        }

        for (int i = 1; i < 9; i++) {
            if (!order.contains(i)) {
                order.set(idx, i);
                backTracking(idx+1);
                order.set(idx, 0);
            }
        }

    }

    static int calPoint() {
        int orderIdx = 0;
        int point = 0;

        for (int round=0; round<n; round++) {
            boolean[] base = new boolean[4];
            int out = 0;

            while (out < 3) {
                int thisResult = innings[round][order.get(orderIdx)];

                if (thisResult == 0) {
                    out++;
                } else if (thisResult < 4) {
                    for (int b=3; b>=1; b--) {
                        if (base[b]) {
                            if (b + thisResult >= 4) {
                                point++;
                            } else {
                                base[b+thisResult] = true;
                            }
                            base[b] = false;
                        }
                    }
                    base[thisResult] = true;
                } else {
                    for (int b=1; b<4; b++) {
                        if (base[b]) {
                            base[b] = false;
                            point++;
                        }
                    }
                    point++;
                }

                orderIdx = (orderIdx + 1) % 9;
            }

        }

        return point;
    }

}
