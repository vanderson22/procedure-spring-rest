package br.com.home.demo2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.home.demo2.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

//	 
// 	@Query("SELECT "
//		    + "  p "
//		    + "FROM "
//		    + " Customer p  WHERE  p.age = ?1 " )
//		    + "INNER JOIN "
//		    + "  p.user.friends f "
//		    + "WHERE "
//		    + "  (p.user = ?1 OR f = ?1) "
//		    + "AND p.dateCreated < ?2")

	List<Customer> findByAge(int age);
	
	
}