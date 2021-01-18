package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    // 어플리케이션 구성을 설정
    // 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부 (Appconfig) 에서 결정한다.


    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()
    // -> 싱글톤이 깨질까 ? ..테스트 코드로 돌려보는것이다.

    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call call AppConfig.orderService
   // call AppConfig.memberRepository


     // 실제 결과
    // call AppConfig.memberService
    // AppConfig.memberRepository
    // call AppConfig.orderService

    // @Autowired MemberRepository memberRepository 를 사용하면 의존관계 주입이되어 문제가 해결된다. (스프링에서 가져오는것)
    @Bean
    public MemberService memberService(){
        // 기존에 MemberServiceImpl에서 구현한것을 Appconfig에서 구현 ( 생성자 주입 )
        // 클라이언트인 memberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고해서 DI (의존관계 주입 의존성 주입이라 한다 )
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        System.out.println("call AppConfig.memberRepository");
            return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        // 생성자 주입
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
