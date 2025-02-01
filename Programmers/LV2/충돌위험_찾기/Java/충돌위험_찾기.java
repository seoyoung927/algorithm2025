import java.util.*;
import java.io.*;

class Solution {
    public static int n;
    public static Point[] point;
    public static int x;
    public static int m;
    public int[][] map = new int[101][101];
    public int[][] isVisited = new int[101][101];  

    
    public class Point{
        int r;
        int c;
        
        public Point() {}
        
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    
    public class Node implements Comparable<Node>{
        int idx;
        int r;
        int c;
        int target;
        int n;
        
        public Node() {}
        
        public Node(int idx, int r, int c, int target, int n){
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.target = target;
            this.n = n;
        }
        
        @Override
        public int compareTo(Node other){
            return Integer.compare(this.n, other.n);
        }
        
        @Override
        public String toString(){
            return "(idx="+idx+",r="+r+",c="+c+",t="+target+",n="+n+")";
        }
    }
    
    public int solution(int[][] points, int[][] routes) {
        // 1. 입력 및 초기화
        int answer = 0;
        n = points.length;
        point = new Point[n+1];
        for(int i=0; i<n; i++){
            point[i+1] = new Point(points[i][0], points[i][1]);
        }
        x = routes.length; // 로봇의 수
        m = routes[0].length;
        
        System.out.println(x);
        // 2. 이동
        Queue<Node> q = new PriorityQueue<>();
        for(int i=0; i<x; i++){
            q.add(new Node(i, point[routes[i][0]].r, point[routes[i][0]].c, 1, 1));
        }
        while(!q.isEmpty()){
            Node cur = q.poll();
            int curI = cur.idx;
            int curR = cur.r;
            int curC = cur.c;
            int curT = cur.target;
            int curN = cur.n;
            
            // 충돌확인
            if(map[curR][curC]==curN && isVisited[curR][curC]==0) {
                answer++;
                isVisited[curR][curC] = 1;
            }else if(curN > map[curR][curC]) {
                map[curR][curC] = curN;
                isVisited[curR][curC] = 0;
            }
            
            if(curT >= m) continue; // 목적지에 도착했다면
            // 목적지에 아직 도착하지 않았다면 이동
            curN++;
            int targetR = point[routes[curI][curT]].r;
            int targetC = point[routes[curI][curT]].c;
            if(targetR>curR){
                curR++;
            }else if(targetR<curR){
                curR--;
            }else{ // targetR == curR
                if(targetC>curC){
                    curC++;
                }else if(targetC<curC){
                    curC--;
                }
            }
            // target 확인
            if(curR==targetR && curC==targetC) curT++;
            q.add(new Node(curI, curR, curC, curT, curN));
        }
        
        return answer;
    }
}