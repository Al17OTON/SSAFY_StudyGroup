
function solution(coin, cards) {
  let n = cards.length
  let target = n + 1
  let visited = Array.from({ length: n + 1 }, e => -1);
  // let curr=cards.slice(0,n/3)
  let cardset = [];
  for (let i = 0; i < n; i++) {
    let index = Math.floor((i - n / 3) / 2 + 1);
    let e = cards[i];
    if (i < n / 3) {
      index = 0;
    }
    if (visited[e] != -1) {
      cardset[visited[e]].sIndex = index;
    } else {
      let tmp = {
        first: e,
        second: target - e,
        fIndex: index,
        sIndex: 0,
        index: cardset.length
      }
      cardset.push(tmp);
      visited[e] = cardset.length - 1;
      visited[target - e] = cardset.length - 1;
    }

  }

  //이제 시작
  cardset.sort((a, b) => {
    return a.sIndex - b.sIndex
  })

  console.log(cardset)
  let i = 0
  let round = 1;
  let currSet = 0;
  while (i < cardset.length) {
    console.log(cardset[i])
    // console.log(currSet)
    console.log("round " + round)

    //라운드 전에꺼
    if (cardset[i].sIndex < round) {
      let tmp = coin;
      if (cardset[i].fIndex > 0) coin--;
      if (cardset[i].sIndex > 0) coin--;
      i++;
      //코인이 부족해서 넘어가야한다.
      if (coin < 0) {
        coin = tmp;

        continue;
      }
      currSet++;

    }
    else if (cardset[i].sIndex == round) {
      let tmp = coin;
      if (cardset[i].fIndex > 0) coin--;
      if (cardset[i].sIndex > 0) coin--;
      round++;
      i++;
      //코인이 부족해서 넘어가야한다.
      if (coin < 0) {
        coin = tmp;
        if (currSet == 0) {
          round--;
          break;
        }
        currSet--;
        continue;
      }

    } else {
      round++;
      currSet--;
      if (currSet < 0) round--;
    }
    console.log("currSet " + currSet)
    console.log("coin " + coin)
    if (currSet < 0 || coin < 0) break;

  }

  return round;

}
/*
6 ≤ cards의 길이 = n < 1,000
n장의 카드, n/3 가짐, 두개씩 뽑음, 코인과 교환해 갖거나 버리거나
카드에 적힌 수의 합이 n+1이 되도록 카드 두장을 내고 다음 라운드 진행
남은 카드가 없거나, 카드 못 내면 끝

최대 라운드 수?=> 뽑은 카드를 갖을지 말지에 따라 달라짐
1. 그리디
2. 

--뽑은 카드를 갖을 지 말지
동전이 있고,
- 현재 갖고 있는 카드와 합이 n+1이 될 수 있다면
- 동전이 2개 이상일때.. 뽑은 후 동전 -2를 해준다.

근데 카드를 낼게 없으면 안되는데,,,
그럼 카드 배열에서 각 n+1을 만들수 있는 짝과 떨어져있는 라운드 회수를 정해..?
그리고 정렬해. round,오름차순 margin오름차순

동전 0개 소모: 카드 두 장에 적힌 수의 합이 n+1이 되도록 a 배열의 카드 두 장을 내고 다음 라운드로 진행합니다.
동전 1개 소모: 카드 두 장에 적힌 수의 합이 n+1이 되도록 a 배열의 카드 한 장과 b 배열의 카드 한 장을 내고 다음 라운드로 진행합니다.
동전 2개 소모: 카드 두 장에 적힌 수의 합이 n+1이 되도록 b 배열의 카드 두 장을 내고 다음 라운드로 진행합니다.
*/