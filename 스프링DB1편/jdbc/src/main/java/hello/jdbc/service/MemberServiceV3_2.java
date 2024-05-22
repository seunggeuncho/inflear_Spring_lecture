package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Connection;
import java.sql.SQLException;

/**
*트랜잭션 - 트랜잭션 매니저
 */
@Slf4j
public class MemberServiceV3_2 {

   // private final DataSource dataSource;
//    private final PlatformTransactionManager transactionManager;
    private final TransactionTemplate txTemplate;
    private final MemberRepositoryV3 memberRepository;

    public MemberServiceV3_2(PlatformTransactionManager transactionManager, MemberRepositoryV3 memberRepository) {
        this.txTemplate = new TransactionTemplate(transactionManager);
        this.memberRepository = memberRepository;
    }

    public void accountTransfer(String fromId, String toID, int money) throws SQLException {

        txTemplate.executeWithoutResult((status) -> {
            try {
                extracted(fromId, toID, money);
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });

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

    private void release(Connection con) {
        if(con != null){
            try{
                con.setAutoCommit(true);
                con.close();
            }catch(Exception e){
                log.info("error",e);
            }
        }
    }
}
