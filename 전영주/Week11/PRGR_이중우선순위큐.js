class MinHeap {
  constructor() {
    this.heap = [null];
  }

  getMin() {
    return this.heap[1];
  }
    getMax(){
        return this.heap[this.heap.length-1];
    }
  getSize() {
    return this.heap.length - 1;
  }

  isEmpty() {
    return this.heap.length < 2;
  }

  insert(node) {
    let current = this.heap.length;

    while (current > 1) {
      const parent = Math.floor(current / 2);
      if (this.heap[parent] > node) {
        this.heap[current] = this.heap[parent];
        current = parent;
      } else break;
    }

    this.heap[current] = node;
  }
pop(isTopPop) {
        if (this.isEmpty()) return;
        if (this.heap.length === 2) return this.heap.pop();
        if (!isTopPop) {
            const parentIndex = Math.floor((this.heap.length - 1) / 2);
            const lastLeaf = this.heap.slice(parentIndex);
            const max = Math.max(...lastLeaf);
            this._swap(parentIndex + lastLeaf.indexOf(max), this.heap.length - 1);
            return this.heap.pop();
        }

        const val = this.heap[1];
        this.heap[1] = this.heap.pop();

        let currentIndex = 1;
        let leftIndex = 2;
        let rightIndex = 3;

        while (
            this.heap[leftIndex] && this.heap[currentIndex] > this.heap[leftIndex] ||
            this.heap[rightIndex] && this.heap[currentIndex] > this.heap[rightIndex] 
        ) {
            if (this.heap[leftIndex] === undefined) {
                this._swap(rightIndex, currentIndex);
            } else if (this.heap[rightIndex] === undefined) {
                this._swap(leftIndex, currentIndex);
            } else if (this.heap[leftIndex] > this.heap[rightIndex]) {
                this._swap(currentIndex, rightIndex);
                currentIndex = rightIndex;
            } else if (this.heap[leftIndex] <= this.heap[rightIndex]) {
                this._swap(currentIndex, leftIndex);
                currentIndex = leftIndex;
            }

            leftIndex = currentIndex * 2;
            rightIndex = currentIndex * 2 + 1;
        }

        return val;
    }

  remove() {
      
    let min = this.heap[1];

    if (this.heap.length > 2) {
      this.heap[1] = this.heap[this.heap.length - 1];
      this.heap.splice(this.heap.length - 1);

      let current = 1;
      let leftChildIndex = current * 2;
      let rightChildIndex = current * 2 + 1;

      while (this.heap[leftChildIndex]) {
        let childIndexToCompare = leftChildIndex;
        if (
          this.heap[rightChildIndex] &&
          this.heap[rightChildIndex] < this.heap[childIndexToCompare]
        )
          childIndexToCompare = rightChildIndex;

        if (this.heap[current] > this.heap[childIndexToCompare]) {
          [this.heap[current], this.heap[childIndexToCompare]] = [
            this.heap[childIndexToCompare],
            this.heap[current],
          ];
          current = childIndexToCompare;
        } else break;

        leftChildIndex = current * 2;
        rightChildIndex = current * 2 + 1;
      }
    } else if (this.heap.length === 2) {
      this.heap.splice(1, 1);
    } else {
      return null;
    }

    return min;
  }
    removeBack(){
        if(this.heap.length>1)return this.heap.pop();
    }
}
function solution(operations) {
    let answer = [];
    let pq=new MinHeap();
    operations.forEach(e=>{
        let [type,num]=e.split(" ")
        switch(type){
            case "I":
                pq.insert(Number(num));
                break;
            case "D":
                
                if(Number(num)==1){
                    pq.removeBack(true);
                }else{
                    pq.remove(false);
                }
        }
    })
    return  pq.getSize()>0? [pq.getMax(),pq.getMin()]:[0,0];
}
/*
최소 힙의 경우 최대값을 특정할 수 없음
마지막 리프노드단에서 최대값비교한 후 삭제해야함
*/