package br.com.home.demo2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name = "MYTABLE")
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "in_only_test", procedureName = "test_pkg.in_only_test", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "inParam1", type = String.class) }),
		@NamedStoredProcedureQuery(name = "in_and_out_test", procedureName = "test_pkg.in_and_out_test", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "inParam1", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "outParam1", type = String.class) }) })
public class MyTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private int age;

	@Column(name = "active")
	private boolean active;

	public MyTable() {
	}

	public MyTable(String name, int age, boolean active) {
		super();

		this.name = name;
		this.age = age;
		this.active = active;
	}

	public long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", age=" + age + ", active=" + active + "]";
	}
}