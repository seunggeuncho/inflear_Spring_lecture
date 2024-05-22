package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Connection;
import java.sql.SQLException;

/**
*트랜잭션 - Transactional AOP
 */
@Slf4j
public class MemberServiceV3_3 {

   // private final DataSource dataSource;
//    private final PlatformTransactionManager transactionManager;
    private final MemberRepositoryV3 memberRepository;

    public MemberServiceV3_3(MemberRepositoryV3 memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void accountTransfer(String fromId, String toID, int money) throws SQLException {
    extracted(fromId, toID, money);
    }

    private void extracted( String fromId, String toID, int money) throws SQLException {
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toID);

        memberRepository.update(fromId, fromMember.getMoney() - money);
        if (toMember.getMemberId().equals("ex")){
            throw new IllegalStateException("이체중 예외 발생");
        }

        memberRepository.update(toID, toMember.getMoney() + money);
    }
}
