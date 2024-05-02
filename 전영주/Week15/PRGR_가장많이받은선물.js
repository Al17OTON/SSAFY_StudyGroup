function solution(friends, gifts) {
  let answer = 0;
  let size = friends.length;
  let mapping = new Map();
  let gift = Array.from({ length: size }, e => Array.from({ length: size }, e => 0));
  let gValue = Array.from({ length: size }, e => Array.from({ length: 2 }, e => 0));//주고 받고
  friends.forEach((e, i) => {
    mapping.set(e, i);
  })
  //이번달 주고 받은거
  gifts.forEach(e => {
    let [a, b] = e.split(" ");//a to b
    let aI = mapping.get(a);
    let bI = mapping.get(b);
    gift[bI][aI]++;
    gValue[aI][0]++;
    gValue[bI][1]++;
  })
  //다음달 계산
  let nextGift = Array.from({ length: size }, e => 0);
  for (let i = 0; i < size; i++) {
    for (let j = i + 1; j < size; j++) {
      if (gift[i][j] > gift[j][i]) {//i가 j에게 받은 수가 더 크면
        nextGift[j]++;
      } else if (gift[i][j] < gift[j][i]) {//j가 i에게 받은 수가 더 크면
        nextGift[i]++;
      } else {
        if (gValue[i][0] - gValue[i][1] > gValue[j][0] - gValue[j][1]) {
          nextGift[i]++;
        } else if (gValue[i][0] - gValue[i][1] < gValue[j][0] - gValue[j][1]) {
          nextGift[j]++;
        }
      }
    }
  }
  return Math.max(...nextGift);
}
/*
1대 1 관계에서
-더많이 준 사람이 다음달 선물 하나 받음
-선물지수(준거-받은거)가 더 큰 사람이 작은 사람에게 하나 받음 
선물 지수도 같으면 다음달 ㄴㄴ
가장 많이 받을 친구가 받은 선물 수?

1. 받은 수: arr[friends x][friends y] x가 y에게 받은 수
2. 준 수: arr[friends y][friends x] x가 y에게 준 수
한 사람이 받은 수는 arr[x]의 원소 합
50이라 가능

24분 1코에 성공
*/