import java.util.*;
import java.io.*;

public class Main_20055_G5_컨베이어_벨트_위의_로봇 {
    static int N,K;
    static int[] A;
    static int[] map;
    static int idx1; //올리는 위치
    static int idxN; //내리는 위치
    static int k; // 내구도가 0인 칸의 개수

    public static void main(String[] args) throws Exception{
        // 1. 입력 및 초기화
        int answer = 1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[2*N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<2*N; i++) A[i] = Integer.parseInt(st.nextToken());
        map = new int[2*N];
        idx1 = 0;
        idxN = N-1;
        k = 0;

        // 2. 로직
        while(true){
            // 2-1. 벨트가 각 칸 위에 있는 로봇과 함께 회전한다.
            idx1 = (idx1-1+2*N)%(2*N);
            idxN = (idxN-1+2*N)%(2*N);
            if(map[idxN]==1) map[idxN]=0;

            // 2-2. 가장 먼저 벨트에 올라간 로봇부터 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다.
            // 만약 이동할 수 없다면 가만히 있는다.
            int i = (idxN-1+2*N)%(2*N);
            while(true){
                if(map[i]==1){
                    // 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며
                    // 그 칸의 내구도가 1 이상 남아 있어야 한다.
                    int nextI = (i+1)%(2*N);
                    if(map[nextI]==0 && A[nextI]>=1) {
                        map[nextI] = 1;
                        map[i] = 0;
                        A[nextI]--;
                        if (A[nextI] == 0) k++;
                        if(nextI==idxN && map[idxN]==1) map[idxN] = 0;
                    }
                }

                i = (i-1+2*N)%(2*N);
                if(i==idx1) break;
            }

            // 2-3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
            if(A[idx1]>=1){
                map[idx1] = 1;
                A[idx1]--;
                if(A[idx1]==0) k++;
            }

            if(k>=K) break;
            answer++;
        }

        // 3. 출력
        System.out.println(answer);
    }
}
