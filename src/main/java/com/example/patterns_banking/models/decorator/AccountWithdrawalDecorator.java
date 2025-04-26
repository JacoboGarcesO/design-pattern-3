package com.example.patterns_banking.models.decorator;

import com.example.patterns_banking.models.Account;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class AccountWithdrawalDecorator extends AccountDecorator {

    public AccountWithdrawalDecorator(Account account, double amount) {
        super(account);
    }

    @Override
    public double calculateWithdrawalFee(double amount) {
        return super.calculateWithdrawalFee(amount);
    }
}
