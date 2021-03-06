package edu.andrew.dao;

import edu.andrew.model.Account;
import edu.andrew.util.TestingUtil;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

import static edu.andrew.dao.model.AccountBuilder.accountBuilder;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class AccountRepositoryTest {
    private AccountRepository repository = new AccountRepositoryImpl();

    @BeforeClass
    public static void setup() {
        Database.init();
    }

    @Test
    public void testPersisting() {
        repository.save(accountBuilder().with("5400001234").with(BigDecimal.ONE).build());
    }

    @Test
    public void testUpdating() {
        Account account = accountBuilder().with("123456").with(BigDecimal.ONE).build();
        repository.save(account);

        account.setHolder("Warren Buffet");

        Transaction tx = SessionFactoryProvider.getSessionFactory().getCurrentSession().beginTransaction();
        repository.update(account);
        tx.commit();

        assertEquals("Warren Buffet", repository.findBy("123456").getHolder());
    }

    @AfterClass
    public static void cleanup() {
        TestingUtil.clearDatabase();
    }
}
