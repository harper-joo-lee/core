package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 아래는 본인이 디스카운트 폴리시 객체를 생성하고 구체적인 선택까지 해서 할당함
    //  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
   // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();



    // 추상화 (인터페이스)에 의존 -> 구현체가없는데 어떻게 실행할까
    // -> 이 문제를 해결하려면 누군가가 클라이언트인 OrderServiceImpldp DiscountPolicy의 구현 객체를 댓니 생성하고 주입해주어야 한다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 필드 인젝션
    // 안티패턴 ? ... 외부에서 변경하는게 굉장히 어렵다.
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;
//
//    // 필드주입으로 하면 setter를 열어줘야한다.
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }


    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        // 2 ) 생성자를 통해서 의존 관계를 주입.
        // 값이 바꿀수없는 불변,필수 의존관계에서 사용.
        // 중요* 생성자가 딱 하나 있으면 @Autowired를 자동으로 적용된다.(스프링 빈에서만 해당)
        System.out.println("OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member  = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
