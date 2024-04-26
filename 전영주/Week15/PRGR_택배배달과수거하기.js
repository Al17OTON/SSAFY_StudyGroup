/*
n(십만)개의 집, cap(50)개 실을 수 있음. 최악의 시간 복잡도: 50/1*십만
최소이동거리=> 제일 먼 집부터 배달후 수거
스택으로 먼 집부터 배달 수거 완료 후 빼기
*/
function solution(cap, n, deliveries, pickups) {
  var answer = 0;
  let dStack = deliveries.slice()
  let pStack = pickups.slice()
  //현재 트럭에 있는 상자 수 
  let curr = 0
  while (dStack.length || pStack.length) {
    curr = 0
    //스택의 맨 위에 0이 있으면 빼주기
    if (dStack[dStack.length - 1] === 0) {
      dStack.pop()
      continue
    }
    if (pStack[pStack.length - 1] === 0) {
      pStack.pop()
      continue
    }
    //배달과 수거 중 제일 멀리 가는 거리 하나만 더해주기
    let maxDist = Math.max(dStack.length, pStack.length)
    // 배달
    while (curr < cap && dStack.length !== 0) {
      if (cap >= curr + dStack[dStack.length - 1]) {
        curr += dStack[dStack.length - 1]
        dStack.pop()
      } else {
        dStack[dStack.length - 1] -= cap - curr
        curr = cap
      }
    }
    curr = 0
    // 수거
    while (curr < cap && pStack.length !== 0) {
      if (cap >= curr + pStack[pStack.length - 1]) {
        curr += pStack[pStack.length - 1]
        pStack.pop()
      } else {
        pStack[pStack.length - 1] -= cap - curr
        curr = cap
      }
    }
    answer += maxDist
  }
  //왕복이기에 *2
  return answer * 2;
}
/*
다른 사람 코드를 보니 굳이 스택으로 안하고 인덱스 값만 갱신해주면 됐다,,
*/
// function solution(cap, n, deliveries, pickups) {
//     let num_deliveries = 0;
//     let num_pickups = 0;
//     let answer = 0;


//     for (i = deliveries.length - 1; i >= 0; i--) {
//         let num_times = 0;
//         deliveries[i] = deliveries[i] + num_deliveries;
//         pickups[i] = pickups[i] + num_pickups;

//         while (deliveries[i] > 0 || pickups[i] > 0) {
//             deliveries[i] = deliveries[i] - cap;
//             pickups[i] = pickups[i] - cap;
//             num_times++;
//         }

//         num_deliveries = deliveries[i];
//         num_pickups = pickups[i];

//         if (deliveries[i] > 0 || pickups[i] > 0) {
//             answer = answer + 2 * (i + 1) * (num_times + 1);
//         } else {
//             answer = answer + 2 * (i + 1) * (num_times);
//         }


//     }

//     return answer;

// }
