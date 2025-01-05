import java.util.*;
import java.io.*;

class Solution {
    public int binary_search(int[] diffs, int[] times, long limit){
        int right = Arrays.stream(diffs).max().getAsInt(); // 조건을 만족하는 한계값을 찾기
        int left = 1;
        
        while(left <= right){
            int mid = (left+right)/2;
            long midTime = getTime(diffs, times, mid);
            if(midTime <= limit) right = mid-1; // 무조건 조건을 만족
            else left = mid+1; // mid일 때는 조건을 만족하지 않았으므로 left를 mid+1로 증가시킴
        }                                                                 
        
        return left;
    }
    
    public long getTime(int[] diffs, int[] times, int level){
        long res = 0;
        int N = diffs.length;
        
        for(int i=0; i<N; i++){
            if(diffs[i]<=level) res += times[i]; 
            else if(i==0) res += times[i]*(diffs[i]-level) + times[i];
            else res += (times[i-1]+times[i])*(diffs[i]-level) + times[i];
        }
        
        return res;
    }
    
    public int solution(int[] diffs, int[] times, long limit) {
        return binary_search(diffs, times, limit);
    }
}