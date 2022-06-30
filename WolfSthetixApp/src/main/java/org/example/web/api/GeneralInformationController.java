package org.example.web.api;

import org.example.Services.GeneralInformationService;
import org.example.Services.Models.GeneralInformation;
import org.example.Services.Models.Positions;
import org.example.web.api.models.GeneralInformationInput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/general_information")
public class GeneralInformationController {
    private GeneralInformationService generalInformationService;

    public GeneralInformationController(GeneralInformationService generalInformationService) {
        this.generalInformationService = generalInformationService;
    }

    @GetMapping
    public List<GeneralInformation> listOfUsers(
            @RequestParam Integer page,
            @RequestParam Integer pageSize) {
        return generalInformationService.listOfUsers(page, pageSize);
    }

    @GetMapping(value = "/{ID}")
    public GeneralInformation getUser(@PathVariable Integer ID) {
        return generalInformationService.getUser(ID);
    }

    @PostMapping
    public GeneralInformation createUser(@RequestBody GeneralInformationInput user) throws Exception {
        return generalInformationService.createUser(
                user.nickname, user.password, user.email_address, user.phone_number, user.first_name, user.last_name, user.date_of_birth, Positions.MEMBER);
    }
    @DeleteMapping(value = "/{ID}")
    public void deleteUser(@PathVariable Integer ID) {
        generalInformationService.deleteUser(ID);
    }
}
