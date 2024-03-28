function solution(progresses, speeds) {
    let answer = [];
    let count=1;
    let prevTmp=0;
    progresses.forEach((e,i)=>{
        let tmp=Math.floor((100-e)/speeds[i])+(1&(((100-e)%speeds[i])>0));
        if(tmp>prevTmp){
            count=1;
            answer.push(count);
            prevTmp=tmp;
        }
        else answer[answer.length-1]++;
    })
    return answer;
}
/*
각 배포 마다 몇개 씩인지
배열 돌면서 다음 원소의 계산 값이 더 크면 앞에것들 배포
0.06~0.17 ms 소요*/