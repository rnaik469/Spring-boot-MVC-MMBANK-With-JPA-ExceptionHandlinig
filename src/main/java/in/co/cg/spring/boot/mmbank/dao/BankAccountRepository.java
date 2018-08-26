package in.co.cg.spring.boot.mmbank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.co.cg.spring.boot.mmbank.pojo.BankAccount;

//BankAccount database
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
}
