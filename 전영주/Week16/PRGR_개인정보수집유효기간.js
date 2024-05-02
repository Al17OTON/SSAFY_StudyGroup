function solution(today, terms, privacies) {
  var answer = [];
  var [year, month, date] = today.split(".").map(Number);
  //오늘 날짜를 일로 변경
  var todates = year * 12 * 28 + month * 28 + date;
  var t = {};
  //약관을 객체로 매핑.
  terms.forEach((e) => {
    let [a, b] = e.split(" ");
    t[a] = Number(b);
  });
  //유효기간을 돌면서 파기해야하는지 판별
  privacies.forEach((e, i) => {
    var [day, term] = e.split(" ");
    day = day.split(".").map(Number);
    var dates = day[0] * 12 * 28 + day[1] * 28 + day[2] + t[term] * 28;
    if (dates <= todates) answer.push(i + 1);
  });
  return answer;
}
/*
terms<20 완탐
모든 달은 28
년월일-> 일 단위로 계산
*/