package com.donalhenry.gradle.example1

import org.gradle.api.Project
import org.gradle.api.Plugin

class CookingDslPlugin implements Plugin<Project> {
  void apply(Project project) {
    project.extensions.create('devconfig', CookingExtension)
    def teams = project.container(Team)
    project.devconfig.extensions.teams = teams
  }
}

class CookingExtension {
  CookingExtension() {
  }
}

class Team {
  String name
  Integer size = 1

  Team(String name) {
    this.name = name
  }

  String getSizeString() {
    (size == 1) ? "$size member" : "$size members"
  }
}
