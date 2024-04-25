package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {


    OrderService orderService;
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        this.memberService = appConfig.memberService();
        this.orderService = appConfig.orderService();
    }
    @Test
    void order(){
        //given
        Member member = new Member(1L,"seucho", Grade.VIP);

        //when
        memberService.join(member);
        Order order = orderService.CreateOrder(1L,"appliance",10000);
        //then

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
