package pl.edu.wat.wcy.swp.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.wat.wcy.swp.bank.entities.BankAccountType;
import pl.edu.wat.wcy.swp.bank.entities.BankAccountTypes;

import java.util.List;

/**
 * Created by Konrad on 2015-01-22.
 */
public interface BankAccountTypesRepository extends JpaRepository<BankAccountTypes, Long> {


    @Query("Select distinct b.name From BankAccountTypes b")
    public List<String> getAllTypes();

}
