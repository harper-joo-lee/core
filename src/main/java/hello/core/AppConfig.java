package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;


public class AppConfig {

    // 어플리케이션 구성을 설정
    // 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부 (Appconfig) 에서 결정한다.
    public MemberService memberService(){
        // 기존에 MemberServiceImpl에서 구현한것을 Appconfig에서 구현 ( 생성자 주입 )
        // 클라이언트인 memberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고해서 DI (의존관계 주입 의존성 주입이라 한다 )
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        // 생성자 주입
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
