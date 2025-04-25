package com.example.patterns_banking.models.decorator;

import com.example.patterns_banking.models.Account;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class AccountWithdrawalDecorator extends AccountDecorator{
    private static final Double OVER_DRAFT_LIMIT = Double.valueOf(20000);

    public AccountWithdrawalDecorator(Account account) {
        super(account);
    }

    @Override
    public void withdraw(double amount) {
        double fee = calculateWithdrawalFee(amount);
        double newAmount = amount + fee;
        double overDraft = newAmount - getBalance();

        if(overDraft > OVER_DRAFT_LIMIT){
            throw new IllegalArgumentException("La cuenta no tiene fondos suficientes");
        }

        if(overDraft > 0){
            System.out.println("Se hizo un retiro y el excedente fue de " + overDraft);
            super.withdraw(getBalance());
        }else{
            super.withdraw(newAmount);
        }
    }
}
