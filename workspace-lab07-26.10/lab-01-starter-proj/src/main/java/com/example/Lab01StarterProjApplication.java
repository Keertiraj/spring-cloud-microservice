package com.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domain.Player;
import com.example.domain.Team;
import com.example.repository.TeamRepository;

@SpringBootApplication
public class Lab01StarterProjApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab01StarterProjApplication.class, args);
    }
    
    @Autowired
    private TeamRepository teamRepository;
    
    @PostConstruct
    public void init() {
		List<Team> list = new ArrayList<>();

		Set<Player> set = new HashSet<>();
		set.add(new Player("Big Easy", "Showman"));
		set.add(new Player("Buckets", "Guard"));
		set.add(new Player("Dizzy", "Guard"));
		
		Team team = new Team("Harlem", "Globetrotters", set);
		list.add(team);
		
		team = new Team();
		team.setLocation("Washington");
		team.setName("Generals");
		list.add(team);


        teamRepository.save(list);
    } 
    
    
    
}
