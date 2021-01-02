package hello.core.singleton;

public class SingletonService {

    // 자기 자신을 static으로 private 으로 선언 ( static -> 클래스 레벨에 올라가서 딱 하나만 존재하게 된다. )
    private static final SingletonService instance = new SingletonService();

    // jvm이 올라갈때 SingletonService() 객체를 생성해서 instance에 참조를 넣어놓는다.
    public static SingletonService getInstance() {
        // 인스턴스 참조를 꺼낼수있는방법은 instance를 리턴해오는것 뿐이다.
        return instance;
    }

    // 딱 1개의 객체 이스턴스만 존재해야하므로, 생성자를 private으로 막아서 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는것을 막는다.
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}