function solution(users, emoticons) {
  let cases = []
  let discountRatio = new Array(emoticons.length)
  //정해진 할인률로 사용자들이 살지 말지 계산
  const calIncome = () => {
    let sum = 0 //이모티콘 판매액
    let sub = 0 //이모티콘 플러스 서비스 가입자
    for (let j = 0; j < users.length; j++) {
      let charge = 0 //이모티콘 개벼로 살때 금액
      discountRatio.forEach((e, i) => {
        let discount = 1 - e / 10
        if (users[j][0] <= e * 10) {//사야함
          charge += emoticons[i] * discount
        }
      })
      //플러스 가입 여부 따지기    
      if (users[j][1] <= charge) {// 플러스 가입
        sub++
      } else sum += charge
    }
    cases.push([sub, sum])
  }
  const dfs = (emt) => {
    //재귀 종료 조건
    if (emt === emoticons.length) {
      //총 판매 금액 구하기
      calIncome()
      return
    }
    //이모티콘의 할인율을 1~40퍼로 설정후 모든 사용자의 구매 여부를 업데이트해서 다음 임티로 
    for (let i = 4; i >= 1; i--) {
      discountRatio[emt] = i
      //다음 임티에서 할인율 설정하기
      dfs(emt + 1)
    }
  }
  dfs(0)
  //서비스 가입자 순, 같다면 판매액 순 정렬
  cases.sort((a, b) => b[0] - a[0] || b[1] - a[1])
  //console.log(cases)
  return cases[0]
}
/*
dp?재귀
1 ≤ emoticons의 길이 = m ≤ 7
각각 이모티콘의 할인율 몇으로 할지-> 최대 4^7의 경우의 수 
임티의 모든 할인율에서 구매여부 따지며 가입자 수와 매출액 계산

**중간에 이모티콘 구매 모두 취소가 있음-> 어떻게??
사람마다 총 구매 금액 배열을 만들까? n<=100이니까 가능이긴 함

*/
