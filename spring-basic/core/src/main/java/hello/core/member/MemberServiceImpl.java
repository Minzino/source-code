package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //보통 일반적으로 구현체가 하나일때 클래스이름에 Impl을 붙인다

    private final MemberRepository memberRepository;

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
}
