package com.example.week1lab2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MarvelCharacter {
    private Long id;
    private String name;
    private String realName;
    private String team;
    private String firstAppearance;
    private String powers;
    private String origin;
}
