package com.donalhenry.gradle.example5

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class DumpAllTeamUrls extends DefaultTask {
  @Input def teams

  @TaskAction
  void perform() {
    def teams = getTeams()
    teams.each { team ->
      println team.yum.url
    }
  }
}

class DumpTeamUrl extends DefaultTask {
  @Input def teams

  @TaskAction
  void perform() {
    def teams = getTeams()
    def team = teams.getByName(project['team'])
    println team.yum.url
  }
}
