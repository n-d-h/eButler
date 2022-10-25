package com.swp391.ebutler.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.swp391.ebutler.model.dto.LoginAccDTO;
import com.swp391.ebutler.entities.Account;
import com.swp391.ebutler.entities.Customer;
import com.swp391.ebutler.entities.Provider;
import com.swp391.ebutler.model.dto.RegisterAccountDTO;
import com.swp391.ebutler.repositories.AccountRepository;
import com.swp391.ebutler.repositories.CustomerRepository;
import com.swp391.ebutler.repositories.ProviderRepository;
import com.swp391.ebutler.service.AccountService;
@Service
@Transactional
public class AccountServiceImp implements AccountService {
	
	@Autowired
	private AccountRepository repo;
	
	@Autowired
	private CustomerRepository cusrepo;
	
	@Autowired
	private ProviderRepository providerrepo;

	@Override
	public List<Account> listAll() {	
		return repo.findAll() ;
	}

	@Override
	public RegisterAccountDTO registAcc(RegisterAccountDTO registacc) {
		List<Account> list = repo.findAll();
		List<String> listmail = new ArrayList<>();
		list.forEach(a -> listmail.add(a.getLoginMail()));
		boolean existed = listmail.contains(registacc.getLoginMail());
		if(existed == true) {
			return null;
		}else {
			Account acc = new Account();
			acc.setLoginMail(registacc.getLoginMail());
			acc.setPassword(registacc.getPassword());
			acc.setStatus(true);
			repo.save(acc);
			if(registacc.getRole() == true) {
				Customer cus = new Customer();
				cus.setEmail(registacc.getLoginMail());
				cus.setFullName(registacc.getFullName());
				cus.setAddress(registacc.getAddress());
				cus.setPhoneNumber(registacc.getPhoneNumber());
				cus.setAccount(acc);
				cusrepo.save(cus);
			}if(registacc.getRole()==false){
				Provider pro = new Provider();
				pro.setEmail(registacc.getLoginMail());
				pro.setProviderName(registacc.getFullName());
				pro.setPhoneNumber(registacc.getPhoneNumber());
				pro.setAddress(registacc.getAddress());
				pro.setAccount(acc);
				providerrepo.save(pro);
			}
			return registacc;
		}
	}

	@Override
	public RegisterAccountDTO login(LoginAccDTO acc) {
		RegisterAccountDTO accdto = new RegisterAccountDTO();
		Account account = repo.findByLoginMailAndPassword(acc.getLoginMail(), acc.getPassword());
		if(account !=null) {
			Customer cusacc = cusrepo.findByAccountId(account.getAccountId());
			if(cusacc != null) {
				accdto.setLoginMail(account.getLoginMail());
				accdto.setFullName(cusacc.getFullName());
				accdto.setAddress(cusacc.getAddress());
				accdto.setPhoneNumber(cusacc.getPhoneNumber());
				accdto.setRole(true);
				accdto.setId(cusacc.getCustomerId());
			}else {
				Provider proacc = providerrepo.findByAccountId(account.getAccountId());
				accdto.setLoginMail(account.getLoginMail());
				accdto.setFullName(proacc.getProviderName());
				accdto.setAddress(proacc.getAddress());
				accdto.setPhoneNumber(proacc.getPhoneNumber());
				accdto.setRole(false);
				accdto.setId(proacc.getProviderId());
			}
			return accdto;
		}
		return null;
	}
}
