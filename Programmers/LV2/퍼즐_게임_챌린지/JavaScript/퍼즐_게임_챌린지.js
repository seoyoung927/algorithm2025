function calc(diffs, times, level){
    let res = 0;
    const LEN = diffs.length;
    
    for(let i=0; i<LEN; i++){
        if(diffs[i]<=level) res += times[i];
        else if(i===0) res += times[i]*(diffs[i]-level) + times[i];
        else res += (times[i]+times[i-1])*(diffs[i]-level) + times[i];
    }
    
    return res;
}

function solution(diffs, times, limit) {
    let left = 1;
    let right = diffs.reduce((acc, cur) => Math.max(acc,cur), 1); // array.reduce(callback, initialValue)
    
    while(left<=right){
        let mid = Math.floor((left+right)/2);
        let midTime = calc(diffs, times, mid);
        if(midTime<=limit) right = mid - 1;
        else left = mid + 1;
    }
    
    return left;
}