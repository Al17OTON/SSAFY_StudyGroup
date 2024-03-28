function solution(arr)
{
    let answer = [];
    let top=-1;//0~9사이기 때문에
    for(let i=0;i<arr.length;i++){
        if(arr[i]!=top){
            top=arr[i];
            answer.push(top);
        }
    }
    
    return answer;
}
// arr<백만,
//스택 top과 같으면 넣지 않기.  13~37정도

//2번째 풀이. filter사용.같은 수가 연속됐을 때 마지막애만 선택함.
//마지막 인덱스는 undefined와 비교하게 됌. 48~47정도
// function solution(arr)
// {
//     return arr.filter((e,i)=>e!=arr[i+1]);
// }