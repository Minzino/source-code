package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{


    private static Map<Long,Member> store = new HashMap<>();
    //원래는 실무에서는 concurrenthashmap을 써야한다 동시성 이슈때문에!

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
