package com.example.demo.persistence;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="STORE")
public class StoreEntity {
 
    @Id
    @GeneratedValue
    private Long id;
     
    @Column(name="data")
    private String data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "StoreEntity [id=" + id + ", data=" + data + "]";
	}
     
   
   
}
