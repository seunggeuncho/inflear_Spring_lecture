package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    DiscountPolicy discountPolicy = new RateDiscountPolicy();
    MemberRepository memberRepository = new MemoryMemberRepository();

    @Test
    @DisplayName("VIP는 10프로 할인")
    void vip_o(){
        //given
        Member member = new Member(1L, "seucho", Grade.VIP);
        memberRepository.save(member);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인x")
    void vip_x(){

        //given
        Member member = new Member(1L, "seucho", Grade.BASIC);
        memberRepository.save(member);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(1000);

    }
}