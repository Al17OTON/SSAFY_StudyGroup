function solution(dice) {
  var answer = [];
  let max = 0
  const n = dice.length
  const array = new Array(n).fill(0).map((a, i) => i)
  const combinations = getComb(n / 2, array)

  combinations.forEach(dice1 => {
    const dice2 = array.filter(a => !dice1.includes(a))

    const sum1 = getSumList(dice, dice1).sort((a, b) => a - b)
    const sum2 = getSumList(dice, dice2).sort((a, b) => a - b)
    let count = 0
    //둘다 정렬하면 그 전에꺼를 사용해서 이분탐색 범위를 줄일 수 있다

    let prevWinIdx = 0;
    for (let i = 0; i < sum1.length; i++) {
      prevWinIdx = getWinCount(sum1[i], sum2, prevWinIdx);
      count += prevWinIdx;
    }
    if (count > max) {
      answer = dice1
      max = count
    }
  })

  return answer.map(a => a + 1);
}

function getWinCount(n, list, left) {
  let right = list.length - 1
  if (n > list[right]) {
    return right + 1
  }

  while (left < right) {
    let mid = Math.floor((left + right) / 2)

    if (list[mid] < n) {
      left = mid + 1
    } else {
      right = mid
    }
  }
  return right
}
function getComb(L, array) {
  if (L === 1) {
    return array.map(a => [a])
  }
  const result = []
  for (let i = 0; i < array.length; i++) {
    const rest = array.slice(i + 1)
    const comb = getComb(L - 1, rest)
    const attach = comb.map(c => [array[i], ...c])
    result.push(...attach)
  }
  return result
}

function getSumList(dice, list) {
  let tmp = [...dice[list[0]]]
  for (let i = 1; i < list.length; i++) {
    const tmp1 = []
    for (let j = 0; j < tmp.length; j++) {
      for (let k = 0; k < 6; k++) {
        tmp1.push(dice[list[i]][k] + tmp[j])
      }
    }
    tmp = tmp1
  }
  return tmp
}
/*
dice<10 , 10C5*6^10=> 완탐 가능..?
승리할 확률-> 승리, 패배 무승부 경우의 수 다 구하기?
그 후 승리 확률이 제일 큰 경우
=> 승리 횟수가 큰,,

주사위 수는 <100

1. 조합 구하기
2. 조합별 승패 계산

같은 숫자 일 경우 계산 줄이기? 이분탐색

1-1 주사위 마다 숫자의 갯수 map과 정렬된 키 값 추출
1-2 주사위 선택 조합 구하고
1-3 조합이 결정되면 
2-1 map을 통해 i보다 커지는 시점 값을 구하고
2-2 그전까지는 승 , 그 이후는 패
  그 전에 저장된 prevBig부터 검사,prevWin
  

*/