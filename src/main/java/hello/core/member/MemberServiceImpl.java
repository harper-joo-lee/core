package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{


    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // 생성자를 통해서 구현체가 어떤것을 들어갈지 설정할 수있다.


    // 이전 AppConfig에서는 @Bean이 의존관계를 주입해줬다.
    // autowired를 생성자에 붙이면 자동으로 의존관계를 주입해준다.
    // 디테일하게는 다르지만 ac.getBean(MemberRepository.class)
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
