package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.annotation.Generated;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="Account101")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 20)
	private String accNo;
	//@Nonnull
	@Column(length = 20)
	private String adharNumber;
	//@Nonnull
	@Column(length = 20)
	private Long mobileNumber;
	//@Nonnull
	@Column(length = 30)
	private String name;
	//@Nonnull
	private Double balance=0.0;
	@Column(length = 30)
	private String status="Active";
	@CreationTimestamp
	private LocalDateTime creationTime;
	@UpdateTimestamp
	private LocalDateTime updationTime;
}
