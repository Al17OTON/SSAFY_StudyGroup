function solution(edges) {//a->b
    let answer = [];
    
    let newVertex=0;
    let barG=0,donutG=0,eightG=0;
    
    //make adjMatrix

    //매핑
    let mapping=new Map();
    edges.forEach(([from,to])=>{
        if(!mapping.has(from)){
            mapping.set(from,mapping.size);
        }
        if(!mapping.has(to)){
            mapping.set(to,mapping.size);
        }
    })
    let inDegree=Array.from({length:mapping.size},e=>0);
    let outDegree=Array.from({length:mapping.size},e=>0);
    edges.forEach(([from,to])=>{
        inDegree[mapping.get(to)]++;
        outDegree[mapping.get(from)]++;
        
    })
    for(let i=0;i<mapping.size;i++){
        if(inDegree[i]==0&&outDegree[i]>=2){
            newVertex=i;
        }else if(outDegree[i]==0){
            barG++;
        }
        else if(outDegree[i]==2){
            eightG++;
        }
    }
    //newVertex랑 연결된 간선은 없애기
    for([k,v] of mapping.entries()){
        if(mapping.get(k)==newVertex){
            newVertex=k;
            break;
        }
    }
    let newEdges=edges.filter(([from,to])=>from!==newVertex);
    let maxG=edges.length-newEdges.length;
    return [newVertex,maxG-barG-eightG,barG,eightG];

}
/*
e<=백만
 생성한 정점의 번호, 도넛 모양 그래프의 수, 막대 모양 그래프의 수, 8자 모양 그래프의 수??

자료구조: 인접행렬? 인접리스트?-> 행렬로 하자.
1. 정점들 중 하나를 뺏을때 즉 연결된 간선을 다 지우고 
2. 주어진 간선을 따라서 그래프를 그리기
3. 그럼 e번 그래프 그리기
4. visited를 만들어서 0부터 가보기.
 - 순환이 없다면(visited true인곳을 다시 가지 않고 끝남): 막대 n,n-1
 - 순환이 있는데(visited true인곳을 다시 방문하려고 함)일단 안가고 갈 곳이 없을때까지 돌아봄
    - n, n이면 도넛 , - 2n+1,2n+2개 면 8자 

n*n인데,, 시초날듯
임의의 정점의 특징은 진입은 없고 나가는것만 있음
***근데 막대그래프 처음애도 진출만 있음-> 하지만 그래프의 수의 합은 2 이상이므로 진출은 2 이상이어야함.
즉 막대그래프 처음인 애는 진출이 1이면서 진입이 0인 애.
=> 무조건 탐색이 아니라 다른 풀이가 있을것

만약 상관없는 정점이 i번재 정점이라면
i와 연결된 간선 다 없애고
남은 간선에서 그래프 판별
도넛: 각 정점이 진입차수와 시작간선이 하나밖에 없는 그래프 인데 순환이 잇다면,
막대:  각 정점이 진입차수와 시작간선이 하나밖에 없는 그래프
... 막대로 가다가 마지막에 순환이 있다면 도넛
8자: 그래프 안에 진입 차수가 1이 아닌 정점이 있을때..?

*/
