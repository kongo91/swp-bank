package pl.edu.wat.wcy.swp.bank.dtos.convertrs;

import pl.edu.wat.wcy.swp.bank.dtos.TransactionDTO;
import pl.edu.wat.wcy.swp.bank.entities.Transaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Konrad on 2015-01-14.
 */
public class TransactionConverter {

    public static TransactionDTO toDTO(Transaction t){

        TransactionDTO tDTO = new TransactionDTO();
        tDTO.setAdress(t.getAdress());
        tDTO.setName(t.getName());
        tDTO.setAmount(t.getAmount());
        tDTO.setId(t.getId());
        if (t.getTransactionDateTime()!= null);{
            SimpleDateFormat sdfdate = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdftime = new SimpleDateFormat("HHmmss");
            tDTO.setDate(sdfdate.format(t.getTransactionDateTime()));
            tDTO.setTime(sdftime.format(t.getTransactionDateTime()));
        }
        tDTO.setSurname(t.getSurname());
        tDTO.setAdress(t.getAdress());
        tDTO.setTitle(t.getTitle());
        tDTO.setTransactionType(t.getTransactionType().toString());

        return tDTO;
    }

}
