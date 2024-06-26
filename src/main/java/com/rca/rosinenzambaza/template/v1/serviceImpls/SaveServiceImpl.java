package com.rca.rosinenzambaza.template.v1.serviceImpls;

import com.rca.rosinenzambaza.template.v1.dto.requests.CreateSaveDTO;
import com.rca.rosinenzambaza.template.v1.models.Account;
import com.rca.rosinenzambaza.template.v1.models.Customer;
import com.rca.rosinenzambaza.template.v1.models.Save;
import com.rca.rosinenzambaza.template.v1.repositories.AccountRepository;
import com.rca.rosinenzambaza.template.v1.repositories.CustomerRepository;
import com.rca.rosinenzambaza.template.v1.repositories.SaveRepository;
import com.rca.rosinenzambaza.template.v1.services.SaveService;
import com.rca.rosinenzambaza.template.v1.utils.ExceptionUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SaveServiceImpl  implements SaveService {
    private  final SaveRepository saveRepository ;
    private  final AccountRepository accountRepository ;
    private  final CustomerRepository customerRepository;

    @Override
    public Save createSave(CreateSaveDTO createSaveDTO) {
        try {
            Account account = accountRepository.findById(createSaveDTO.getAccountId()).orElseThrow(() -> new RuntimeException("Account not found"));
            Customer customer =  customerRepository.findById(createSaveDTO.getCustomerId()).orElseThrow(() -> new RuntimeException("Customer not found"));
            Save save = new Save();
            save.setAccount(account);
            save.setCustomer(customer);
            save.setAmount(save.getAmount());
            save.setBankingTime(save.getBankingTime());



            return saveRepository.save(save);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }
        return null;
    }

    @Override
    public Save getSaveById(UUID saveId) {
        try {
            return saveRepository.findById(saveId)
                    .orElseThrow(() -> new RuntimeException("Save not found"));
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);

        }
        return null;
    }

    @Override
    public Save updateSave(UUID uuid, CreateSaveDTO createSaveDTO) {
        try {
            Save save = saveRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Save not found"));
            Account account = accountRepository.findById(createSaveDTO.getAccountId()).orElseThrow(() -> new RuntimeException("Account not found"));
            Customer  customer =  customerRepository.findById(createSaveDTO.getCustomerId()).orElseThrow(() -> new RuntimeException("Customer not found"));
            save.setAccount(account);
            save.setCustomer(customer);
            save.setAmount(save.getAmount());
            save.setBankingTime(save.getBankingTime());
            return saveRepository.save(save);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }
        return null;
    }
}
