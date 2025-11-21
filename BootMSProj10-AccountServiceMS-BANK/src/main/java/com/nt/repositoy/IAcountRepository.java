package com.nt.repositoy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nt.entity.Account;

@Repository
public interface IAcountRepository extends JpaRepository<Account, Long>{

	@Query("From Account where accNo=?1")
	public Optional<Account> getAcountDetailsByAccountNumber(String accountNumber);
}
