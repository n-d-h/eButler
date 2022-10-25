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

@Table(name = "tbl_provider")
@Entity
@Data
@NoArgsConstructor
public class Provider {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="provider_id", nullable = false, updatable = false)
	private Integer providerId;
	
	@Column(name="email")
	private String email;
	
	@Column(name="provider_name")
	private String providerName;
	
	@Column(name="phone_number")
	private String phoneNumber;

	@Column(name="address")
	private String address;
	
	@OneToOne
	@JsonBackReference
	@JoinColumn(name = "accountId")
	private Account account;
	
	@OneToMany( mappedBy = "provider", fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<ProductProvider> pProvider;
	
	@OneToMany( mappedBy = "provider", fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<ServiceProvider> sProvider;

	public Provider(Integer providerId, String email, String providerName, String phoneNumber, String address,
			Account account) {
		super();
		this.providerId = providerId;
		this.email = email;
		this.providerName = providerName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.account = account;
	}
	
	
}
