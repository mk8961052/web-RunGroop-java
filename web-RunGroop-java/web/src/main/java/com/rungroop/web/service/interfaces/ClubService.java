package com.rungroop.web.service.interfaces;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();

    Club saveClub(Club club);

    Club findClubById(long clubId);

    void updateClub(ClubDto clubDto);

    void deleteClub(long clubId);

    List<ClubDto> searchClubs(String query);

}
