import java.util.*;
import java.io.*;

class Solution {
    public String prev10(String cur){
        String[] tmp = cur.split(":");
        int H = Integer.parseInt(tmp[0]);
        int M = Integer.parseInt(tmp[1]);
        // 10초 전으로 이동
        M -= 10;
        // 조정
        if(M<0){
            H -= 1;
            M += 60;
        }
        if(H<0){
            H = 0;
            M = 0;
        }
        return String.format("%02d:%02d", H, M);
    }
    
    public String next10(String cur, String end){
        String[] tmp = cur.split(":");
        int H = Integer.parseInt(tmp[0]);
        int M = Integer.parseInt(tmp[1]);
        
        String[] endTmp = end.split(":");
        int EH = Integer.parseInt(endTmp[0]);
        int EM = Integer.parseInt(endTmp[1]);
        
        // 10초 후로 이동
        M += 10;
        // 조정
        if(M>=60){
            H += 1;
            M -= 60;
        }
        if(H>EH || (H==EH && M>EM)){
            H = EH;
            M = EM;
        }
        return String.format("%02d:%02d", H, M);
    }
    
    public String skipOpening(String cur, String startO, String endO){
        String[] tmp = cur.split(":");
        int H = Integer.parseInt(tmp[0]);
        int M = Integer.parseInt(tmp[1]);
        
        String[] startTmp = startO.split(":");
        int SH = Integer.parseInt(startTmp[0]);
        int SM = Integer.parseInt(startTmp[1]);
        
        String[] endTmp = endO.split(":");
        int EH = Integer.parseInt(endTmp[0]);
        int EM = Integer.parseInt(endTmp[1]);
        
        // 현재 재생 위치가 오프닝 구간인 경우
        if((H>SH || (H==SH && M>=SM)) && (H<EH || (H==EH && M<=EM))) return String.format("%02d:%02d", EH, EM);
        return String.format("%02d:%02d", H, M);
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String cur = pos;
        for(String command : commands){
            cur = skipOpening(cur, op_start, op_end);
            if(command.equals("prev")) cur = prev10(cur);
            else cur = next10(cur, video_len);
        }
        cur = skipOpening(cur, op_start, op_end);
        
        return cur;
    }
}