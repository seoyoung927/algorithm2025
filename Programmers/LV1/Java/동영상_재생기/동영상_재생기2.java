// 시간을 비교할 때는 초 단위로 ... ^^ ~~
class Solution {
    public int prev(int cur_pos){
        int res_pos = cur_pos - 10;
        // 동영상이 처음 위치보다 전일 수는 없음
        if(res_pos<0) res_pos = 0;
        // 현재 위치가 10초 미만일 경우 영상의 처음 위치로 이동
        if(res_pos<10) res_pos = 0; 
        return res_pos;
    }
    
    public int next(int cur_pos, int video_len){
        int res_pos = cur_pos + 10;
        // 동영상의 남은 시간이 10초 미만일 경우 영상의 마지막 위치로 이동
        if(video_len-res_pos<10) res_pos = video_len;
        return res_pos;
    }
    
    public int skip(int cur_pos, int op_start, int op_end){
        // 현재 재생 위치가 오프닝 구간이라면 자동으로 오프닝이 끝나는 위치로 이동
        if(cur_pos>=op_start && cur_pos<=op_end) return op_end;
        else return cur_pos;
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String[] video_len_arr = video_len.split(":");
        int video_len_time = 60*Integer.parseInt(video_len_arr[0])+Integer.parseInt(video_len_arr[1]);
        String[] pos_arr = pos.split(":");
        int pos_time = 60*Integer.parseInt(pos_arr[0])+Integer.parseInt(pos_arr[1]);
        String[] op_start_arr =op_start.split(":");
        int op_start_time = 60*Integer.parseInt(op_start_arr[0])+Integer.parseInt(op_start_arr[1]);
        String[] op_end_arr =op_end.split(":");
        int op_end_time = 60*Integer.parseInt(op_end_arr[0])+Integer.parseInt(op_end_arr[1]);
        
        for(String command : commands){        
            pos_time = skip(pos_time, op_start_time, op_end_time);
            if(command.equals("prev")) pos_time = prev(pos_time);
            if(command.equals("next")) pos_time = next(pos_time, video_len_time);
        }
        pos_time = skip(pos_time, op_start_time, op_end_time);
    
        String answer = String.format("%02d:%02d", pos_time/60, pos_time%60);
        return answer;
    }
}