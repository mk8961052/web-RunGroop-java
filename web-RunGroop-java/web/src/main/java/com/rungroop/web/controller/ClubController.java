package com.rungroop.web.controller;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.models.Club;
import com.rungroop.web.service.interfaces.ClubService;
import com.rungroop.web.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
        ;
    }
    @GetMapping("/clubs")
    public String listClubs(Model model){
        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "clubs-list";

    }
    @GetMapping("/clubs/{clubId}")
    public String ClubDetail(@PathVariable("clubId") long clubId, Model model){
        Club club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs-detail";
    }
    @GetMapping("/clubs/new")
    public String createClubForms(Model model){
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";

    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") long clubId){
        clubService.deleteClub(clubId);
        return "redirect:/clubs";
    }
    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(value = "query") String query, Model model) {
        List<ClubDto> clubDtos = clubService.searchClubs(query);
        model.addAttribute("clubs", clubDtos);
        return "clubs-list";
    }
    @PostMapping("/clubs/new")
    public String saveClub(@ModelAttribute("club") Club club){
        clubService.saveClub(club);
        return "redirect:/clubs";

    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") long clubId, Model model){
        Club club = clubService.findClubById(clubId);
        model.addAttribute(club);
        return "clubs-edit";
    }
    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") long clubId,
                             @Valid @ModelAttribute("club") ClubDto clubDto,
                              BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("club",clubDto);
            return "clubs-edit";
        }
        clubDto.setId(clubId);
        clubService.updateClub(clubDto);
        return "redirect:/clubs";

    }
}
