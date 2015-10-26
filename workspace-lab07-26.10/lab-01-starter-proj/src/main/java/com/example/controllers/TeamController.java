package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.domain.Team;
import com.example.repository.TeamRepository;

//@RestController
public class TeamController {
	 	  
    @Autowired
    private TeamRepository teamRepository;

	 // @RequestMapping("/teams")
	  public Iterable<Team> getTeams() {
	      return teamRepository.findAll();
	  }

	 // @RequestMapping("/teams/{id}")
	  public Team getTeam(@PathVariable long id) {
	      return teamRepository.findOne(id);
	  }

}
