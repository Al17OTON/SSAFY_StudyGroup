function solution(jobs) {
    let answer = 0;
    let order=jobs.sort((a,b)=>{
        if(a[0]==b[0])return a[1]-b[1]
        return a[0]-b[0];
    })
    let time=0;
    let waiting=0;
    let q=[];
    let i=0;
    while(i<jobs.length||q.length!=0){
        
         //console.log(time)
        //console.log(waiting)
        if(i<jobs.length){
            if(time<jobs[i][0]&&q.length==0){
                
                waiting+=jobs[i][1];
                time=jobs[i][0]+jobs[i][1];
                //console.log(1+" "+jobs[i])
                i++;
               
            }else if(time<jobs[i][0]){
                //큐에 있는거 하기
                q.sort((a,b)=>b[1]-a[1]);  
                let next=q.pop();
                waiting+=time-next[0]+next[1];
                time+=next[1];
                //console.log(2+" "+next)
            }else if(time>=jobs[i][0]){
                
                q.push(jobs[i]);
               // console.log(3+" "+jobs[i])
                i++;
               
            }
        }else{
            q.sort((a,b)=>b[1]-a[1]);  
            let next=q.pop();
            waiting+=time-next[0]+next[1];
            time+=next[1];
           // console.log(4+" "+next)
            i++;
        }
        
    }
    return Math.floor(waiting/jobs.length);
}

/*
그리디? 500임 순서를 다 해보는건 시초

작업이 요청되는 시점 순으로 정렬, 작업의 소요 시간이 ㅉ랍은 순으로 정렬
그 후 for문 돌면서 총 종료까지 시간 구하기

아.. 제한 사항에 하드디스크가 작업을 수행하고 있지 않을 때에는 먼저 요청이 들어온 작업부터 처리합니다.이 잇다
그니까 맨 처음엔 무조건 제일 먼저 들어온애를 하고
그애를 하는 중에 새로운 일이 들어오면 거기서 뭘 할지 고르기

*/