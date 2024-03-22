package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item_event")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class item_event {
	
	@Id
	Integer code;
	String name;
	Integer discount;
	private LocalDate startDate;
	private LocalDate endDate;

}
