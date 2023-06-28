package com.bank.Bank.Controller;
import com.bank.Bank.Model.AccountTypeModel;
import com.bank.Bank.Repository.AccountTypeRepository;
import com.bank.Bank.Service.AccountTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/account-type")
public class AccountTypeController {
    @Autowired
    AccountTypeService accountTypeService;
    @Autowired
    AccountTypeRepository accountTypeRepository;

    @PostMapping("/save")
    public ResponseEntity<?> createAccountType( @Valid @RequestBody AccountTypeModel accountTypeModel, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatusCode.valueOf(400));
        }
        return accountTypeService.createAccountType(accountTypeModel);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<AccountTypeModel>> getAllAccountTypes() {
        return accountTypeService.getAllAccountTypes();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AccountTypeModel> deleteAccountTypeById(@PathVariable Long id) {
        return accountTypeService.deleteAccountTypeById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountTypeModel> updateAccountById(@PathVariable Long id , @RequestBody AccountTypeModel accountTypeModel){
        return accountTypeService.updateAccountById(id, accountTypeModel);
    }
}
