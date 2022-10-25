package com.swp391.ebutler.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "tbl_customer")
@Entity
@Data
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id", nullable = false, updatable = false)
	private Integer customerId;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "address")
	private String address;
	
	// Foreign key [account Id]
	@OneToOne
	@JsonBackReference
	@JoinColumn(name = "account_id")
	private Account account;
	
	// Primary key [customer Id]
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<Order> orders;

	// Constructor with parameters
	public Customer(Integer customerId, String email, String fullName, String phoneNumber, String address,
			Account account) {
		super();
		this.customerId = customerId;
		this.email = email;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.account = account;
	}

	
}
