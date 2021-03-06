package edu.andrew.service;

import edu.andrew.TransferFailedException;
import edu.andrew.model.Account;

import java.math.BigDecimal;

public interface MoneyTransferService {
    void save(Account account);
    Account findBy(String accountNumber);
    void transfer(Account from, Account to, BigDecimal amount) throws TransferFailedException;
}
