package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingleTonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService = appConfig.memberService();

        //2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService);
        System.out.println("memberService2 = " + memberService2);

        // 즉, 호출(요청을) 할때마다 새로운 객체를 생성한다. => 메모리 낭비
        // 해결 방안 = 해당 객체가 딱 1개만 생성되고, 공유하도록 설꼐하면 된다. => 싱글톤 패턴
        // 싱글톤 패턴 : 클래스의 인스턴스가 딱 1개만 생성되는것을 보장하는 디자인 패턴
        assertThat(memberService).isNotSameAs(memberService2);
    }

//    public static void main(String[] args) {
//        SingletonService singletonService = new SingletonService();
//    }

    // 스프링컨테이너를 사용하면 스프링이 알아서 싱글톤 패턴을 만들어준다.
    // 싱글톤 패턴을 구현하는 방법은 여러가지가 있다.

    // 싱글톤 패턴 문제점 : 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
    // 클라이언트가 구체 클래스에
    // 의존관계상 클라이언트가 구체 클래스에 의존 - > DIP 위반
    // private 생성자로서 자식 클래스를 만들기 어렵다.
    // 안티패턴으로 불리기도 한다.
    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
       //  new SingletonService(); --> 컴파일 오류
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
    }



    // 스프링 컨테이너는 싱글톤 패턴의 문제점을 해결하면서, 객체 인스턴스를 싱글톤으로 관리한다.
    // 우리가 학습한 스프링 빈이 바로 싱글톤으로 관리되는 빈이다.
    // 컨테이너는 객체를 하나만 생성해서 관리한다. 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리라 한다.
    // 싱글톤 패턴의 단점을 해결한다.
    // 스프링 컨테이너 덕분에 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유해서 효율적으로 재사용 할 수 있다.

    // * 스프링의 기본 빈 등록 방신은 싱글톤이지만, 싱글톤 방식만 지원하는 것이 아니다. 요청할 때 마다 새로운 객체를 생성해서 반환하는 기능도 제공한다 (추후 빈 스코프 내용)
    @Test
    @DisplayName("스프링 컨테이너의 싱글톤")
    void springContainer() {
        // AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 같은것을 확인.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // 즉, 호출(요청을) 할때마다 새로운 객체를 생성한다. => 메모리 낭비
        // 해결 방안 = 해당 객체가 딱 1개만 생성되고, 공유하도록 설꼐하면 된다. => 싱글톤 패턴
        // 싱글톤 패턴 : 클래스의 인스턴스가 딱 1개만 생성되는것을 보장하는 디자인 패턴
        assertThat(memberService1).isSameAs(memberService2);
    }
}
