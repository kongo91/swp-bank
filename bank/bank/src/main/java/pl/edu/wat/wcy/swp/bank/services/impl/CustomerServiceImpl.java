package pl.edu.wat.wcy.swp.bank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.swp.bank.entities.Customer;
import pl.edu.wat.wcy.swp.bank.repositories.CustomerRepository;
import pl.edu.wat.wcy.swp.bank.services.interfaces.CustomerService;

import java.util.List;

/**
 * Created by Konrad Bosiek on 2015-01-05.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

}
