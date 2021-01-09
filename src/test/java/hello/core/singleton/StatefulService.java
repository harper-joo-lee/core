package hello.core.singleton;

public class StatefulService {

    //상태를 유지하는 필드 (공유되는 필드 ) 인데 특정 클라이언트가 값을 변경한다. 10000 -> 20000
    // 스프링 빈은 항상 무상태(stateless)로 설계하자.
   //  private int price;


//    public void order(String name, int price) {
//        System.out.println("name  = " + name + "price " + price);
//        this.price = price; //여기가 문제
//    }


    // --> 리팩토링
    public int order(String name, int price) {
        System.out.println("name  = " + name + "price " + price);
        return price;
    }

   // public int getPrice() {
    //    return price;
   // }
}
