package com.example.patterns_banking.models.decorator;

import com.example.patterns_banking.models.Account;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class AccountOtherDecorator extends AccountDecorator {
    public AccountOtherDecorator(Account account) {
        super(account);
    }

    @Override
    public void withdraw(double amount) {
        double limit = calculateLimitWithdrawal();
        double balance = getBalance();
        double fee = calculateWithdrawalFee(amount);
        double totalAmount = amount + fee;

        if (totalAmount > balance) {
            double remainingAmount = totalAmount - balance;
            System.out.println("El saldo no es suficiente. Se usará el límite extra. Excedente de:"  + remainingAmount);
            if (remainingAmount > limit) {
                System.out.println("El monto excede el límite extra permitido." + (remainingAmount - limit));
                throw new IllegalArgumentException("El monto excede el límite extra permitido.");
            }
            setBalance(0.0);
            limit -= remainingAmount;
            System.out.println("Se uso del límite extra de: " + remainingAmount);
        } else {
            setBalance(balance - totalAmount);
        }
        System.out.println("Se realizo un retiro de: " + amount + " con fee de: " + fee);
        System.out.println("Su saldo es: " + getBalance() + ", El límite extra que queda es de: " + limit);
    }
}