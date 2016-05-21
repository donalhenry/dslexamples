package com.donalhenry.gradle.example2

import org.gradle.api.Project
import org.gradle.api.Plugin

class CookingDslPlugin implements Plugin<Project> {
  void apply(Project project) {
    project.extensions.create('devconfig', CookingExtension)
    def teams = project.container(Team) { name->
      new Team(name, project.devconfig.minTeamsize)
    }
    project.devconfig.extensions.teams = teams
  }
}

class CookingExtension {
  Integer minTeamsize

  CookingExtension() {
  }
}

class Team {
  String name
  Integer size

  Team(String name, Integer minTeamsize) {
    this.name = name
    this.size = minTeamsize
  }

  String getSizeString() {
    (size == 1) ? "$size member" : "$size members"
  }
}
