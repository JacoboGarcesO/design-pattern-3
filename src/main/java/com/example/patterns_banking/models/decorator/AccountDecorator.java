package com.example.patterns_banking.models.decorator;

import com.example.patterns_banking.models.Account;
import com.example.patterns_banking.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AccountDecorator extends Account {
  private Account account;

  @Override
  public Long getId() {
    return account.getId();
  }

  @Override
  public String getAccountNumber() {
    return account.getAccountNumber();
  }

  @Override
  public Double getBalance() {
    return account.getBalance();
  }

  @Override
  public Customer getCustomer() {
    return account.getCustomer();
  }

  @Override
  public void setId(Long id) {
    account.setId(id);
  }

  @Override
  public void setAccountNumber(String accountNumber) {
    account.setAccountNumber(accountNumber);
  }

  @Override
  public void setBalance(Double balance) {
    account.setBalance(balance);
  }

  @Override
  public void setCustomer(Customer customer) {
    account.setCustomer(customer);
  }

  @Override
  public Double calculateDepositFee(Double amount) {
    return account.calculateDepositFee(amount);
  }

  @Override
  public double calculateWithdrawalFee(double amount) {
    return account.calculateWithdrawalFee(amount);
  }

  @Override
  public void withdraw(double amount) {
    if (amount > getBalance()) {
      throw new IllegalArgumentException("Insufficient funds");
    }
    //pase el fee al decorator porque al manejarse desde acá el calculo balance -= (amount + fee) podía dar resultados
    // negativos en el momento que se retira lo mismo que el balance, quedando con un nuevo balance de -fee
    setBalance(getBalance() - amount) ;
  }

  @Override
  public void deposit(Double amount) {
    account.deposit(amount);
  }
}
