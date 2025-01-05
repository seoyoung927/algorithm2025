function prev(cur_pos){
    let res_pos = cur_pos - 10;
    if(res_pos<10) res_pos = 0;
    return res_pos;
}

function next(cur_pos, video_len){
    let res_pos = cur_pos + 10;
    if(video_len-res_pos<10) res_pos = video_len; 
    return res_pos;
}

function skip(cur_pos, op_start, op_end){
    if(cur_pos>=op_start && cur_pos<=op_end) return op_end;
    return cur_pos;
}

function toSecond(time){
    const [H, M] = time.split(":").map(Number);
    return H*60+M;
}

function solution(video_len, pos, op_start, op_end, commands) {
    const videoTime = toSecond(video_len);
    let posTime = toSecond(pos);
    const opStartTime = toSecond(op_start);
    const opEndTime = toSecond(op_end);
    
    for(let command of commands){
        posTime = skip(posTime, opStartTime, opEndTime);
        if(command==="prev") posTime = prev(posTime);
        if(command==="next") posTime = next(posTime, videoTime);
    }
    posTime = skip(posTime, opStartTime, opEndTime);
    
    const H = Math.floor(posTime/60);
    const M = Math.floor(posTime%60);
        
    return ("0"+H).slice(-2)+":"+("0"+M).slice(-2);
}