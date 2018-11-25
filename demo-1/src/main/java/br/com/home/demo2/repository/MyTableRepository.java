package br.com.home.demo2.repository;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.home.demo2.model.MyTable;

public interface MyTableRepository extends CrudRepository<MyTable, Long> {
	@Procedure(name = "in_only_test")
	void inOnlyTest(@Param("inParam1") String inParam1);

	/**
	 * @return  O out param da procedure é representado pelo parâmetro de saída
	 * @param inParam1 - parâmetro de entrada na procedure - @procedure é o nome da procedure
	 * nomeado na classe de entrada
	 */
	@Procedure(name = "in_and_out_test")
	String inAndOutTest(@Param("inParam1") String inParam1);
}