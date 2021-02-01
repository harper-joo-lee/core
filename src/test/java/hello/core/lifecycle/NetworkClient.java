package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    // 스프링 빈은 간단하게 다음과 같은 라이프사이클을 가진다.
    // 객체 생성 -> 의존관계 주입
    // 초기화 작업은 의존관계 주입이 모두 완료되고 난 다음에 호출해야한다. 그런데 의존관계 주입이 모두 완료된 시점을 어떻게 알 수 있을까 ?
    // -> 스프링은 의존관계 주입이 완료되면 스프링 빈에게 콜백 메서드를 통해서 초기화 시점을 알려주는 다양한 기능을 제공한다.
    // 또한, 스프링 컨테이너가 종료되기 직전에 소멸 콜백을 준다.

    // 스프링 빈의 이벤트 라이프사이클
    // 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸 전 콜백 -> 스프링 종료

    // 초기화 콜백 : 빈이 생성되고, 빈의 의존관계가 주입이 완료된 후 호출
    // 소멸전 콜백 : 빈이 소멸되기 직전에 호출

    /**
     * 참고 : 객체의 생성과 초기화를 분리하자.
     * 생성자는 필수정보(파라미터)를 받고, 메모리를 할당해서 객체를 생성하는 책임을 가진다.
     * 반면에 초기화는 이렇게 생성된 값들을 활용해서 외부 커넥션을 연결하는등 무거운 동작을 수행한다.
     * */

    /*
    * 스프링은 크게 3가지 방법으로 빈 생명주기 콜백을 지원한다.
    *  1. 인터페이스
    *  2. 설정 정보에 초기화 메서드, 종료 메서드 지정 (외부 라이브러리에 사용 가능)
    *  3. 어노테이션 (@PostConstruct, @PreDestroy) -> 권고 되는 방법 (유일한 단점 : 외부 라이브러리에는 적용하지 못한다. )
    * */
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url );
    }

    public void setUrl(String url) {
        this.url =url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + "message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close" + url);
    }


//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메시지");
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
