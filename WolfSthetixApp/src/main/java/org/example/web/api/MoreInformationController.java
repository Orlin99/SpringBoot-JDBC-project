package org.example.web.api;

import org.example.Services.Models.MoreInformation;
import org.example.Services.MoreInformationService;
import org.example.web.api.models.MoreInformationInput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/more_information")
public class MoreInformationController {
    private MoreInformationService moreInformationService;

    public MoreInformationController(MoreInformationService moreInformationService) {
        this.moreInformationService = moreInformationService;
    }

    @GetMapping
    public List<MoreInformation> listOfTheAdditionalInformationOfTheUsers(
            @RequestParam Integer page,
            @RequestParam Integer pageSize) {
        return moreInformationService.listOfTheAdditionalInformationOfTheUsers(page, pageSize);
    }

    @GetMapping(value = "/{secondary_ID}")
    public MoreInformation getTheAdditionalInformation(@PathVariable Integer secondary_ID) {
        return moreInformationService.getTheAdditionalInformation(secondary_ID);
    }

    @PostMapping
    public MoreInformation AddMoreInformationToUser(@RequestBody MoreInformationInput moreInfo) {
        return moreInformationService.AddMoreInformationToUser(
                moreInfo.gender, moreInfo.height_cm, moreInfo.weight_kg, moreInfo.activity_level, moreInfo.age, moreInfo.goal_kg, moreInfo.user_ID);
    }

    @PutMapping(value = "/{user_ID}")
    public MoreInformation updateTheAdditionalInformation(@RequestBody MoreInformationInput moreInformationInput, @PathVariable Integer user_ID) {
        return moreInformationService.updateTheAdditionalInformation(
                moreInformationInput.height_cm, moreInformationInput.weight_kg, moreInformationInput.activity_level, moreInformationInput.age, moreInformationInput.goal_kg, user_ID);
    }

    @DeleteMapping(value = "/{secondary_ID}")
    public void deleteTheAdditionalInformationOfUser(@PathVariable Integer secondary_ID) {
        moreInformationService.deleteTheAdditionalInformationOfUser(secondary_ID);
    }
}