package com.workintech.s19d1.dto;

import com.workintech.s19d1.entity.Actor;

import java.time.LocalDate;
import java.util.List;

public record MovieResponse(String name, String directorName, int rating, LocalDate releaseDate, List<Actor> actors) {
}
