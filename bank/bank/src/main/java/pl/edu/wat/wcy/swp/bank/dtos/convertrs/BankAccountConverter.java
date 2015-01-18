package pl.edu.wat.wcy.swp.bank.dtos.convertrs;

import pl.edu.wat.wcy.swp.bank.dtos.BankAccountDTO;
import pl.edu.wat.wcy.swp.bank.entities.BankAccount;

/**
 * Created by Konrad on 2015-01-18.
 */
public class BankAccountConverter {

    public static BankAccountDTO toDTO(BankAccount b){

        BankAccountDTO dto = new BankAccountDTO();
        dto.setIsCard(b.getIsCard());
        dto.setAccountNumber(b.getAccountNumber());
        dto.setBalance(b.getBalance());
        dto.setAccountType(b.getAccountType().name().toLowerCase());
        dto.setCurrency(b.getCurrency().name().toLowerCase());

        return dto;
    }
}
