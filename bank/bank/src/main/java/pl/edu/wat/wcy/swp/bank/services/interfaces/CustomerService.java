package pl.edu.wat.wcy.swp.bank.services.interfaces;

import pl.edu.wat.wcy.swp.bank.entities.Customer;

import java.util.List;

/**
 * Created by Konrad Bosiek on 2015-01-05.
 */
public interface CustomerService {

    List<Customer> findAll();

}
