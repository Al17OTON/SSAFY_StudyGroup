package Week06;

import java.util.Scanner;

class EGG {
    int s, w;

    public EGG(int s, int w) {
        this.s = s;
        this.w = w;
    }
}


//이게 시간초과가 안나네...ㄷㄷ
//제한시간 2초에 N이 8밖에 안되어서 완탐으로 가능할 거라고 생각했는데
//8을 넣었을때 생각보다 느렸다.
//다행이도 시간초과는 안났다.
public class BOJ_16987_계란으로계란치기 {
    static EGG[] eggs;
    static int N, maxCount = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        eggs = new EGG[N];

        for(int i = 0; i < N; i++) {
            eggs[i] = new EGG(sc.nextInt(), sc.nextInt());
        }

        dfsEgg(0, eggs);

        System.out.println(maxCount);

    }

    static void dfsEgg(int idx, EGG[] egg) {
        //다 꺠면 깨진 계란 갯수 세기
        if(idx == N) {
            int hit = 0;
            for(int i = 0; i < egg.length; i++) {
                if(egg[i].w == -1) hit++;
            }
            maxCount = Math.max(maxCount, hit);
            return;
        }

        //만약 다음에 든 계란이 깨졌다면 다음 계란으로 스킵
        if(egg[idx].w == -1) {
            dfsEgg(idx + 1, egg);
            return;
        }

        //계란이 칠 수 있는 계란이 남아있는지 체크
        boolean flag = false;

        //가능한 타격은 모두 해본다.
        for(int i = 0; i < egg.length; i++) {
            if(egg[i].w != -1 && idx != i) {
                EGG[] copy = copyEgg(egg);
                hitEgg(copy, idx, i);
                dfsEgg(idx + 1, copy);
                flag = true;
            }
        }
        
        if(!flag) dfsEgg(idx + 1, egg);

    }

    //이렇게 해야 깊은 복사가 가능하다.
    //clone 메소드를 오버라이드 하면 가능할듯
    static EGG[] copyEgg(EGG[] origin) {
        EGG[] copy = new EGG[origin.length];
        for(int i = 0; i < origin.length; i++) {
            copy[i] = new EGG(origin[i].s, origin[i].w);
        }
        return copy;
    }
    
    //계란 깨보기
    static void hitEgg(EGG[] egg, int held, int target) {
        egg[held].s -= egg[target].w;
        egg[target].s -= egg[held].w;

        if(egg[held].s <= 0) {
            egg[held].w = -1;
        }
        if(egg[target].s <= 0) {
            egg[target].w = -1;
        }
    }
}
