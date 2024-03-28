function solution(s){
    let answer = true;
    let stack=[];
    [...s].forEach(e=>{
        if(e==="("){ //(0 )1
            stack.push(0)
        }else{
            if(stack.length==0) {
                stack.push(1)
                return;
            }
            if(stack[stack.length-1]===0)stack.pop();
        }
    })
    if(stack.length>0)return false;
    else return true;
}