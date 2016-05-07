package com.donalhenry.gradle.example5

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.util.ConfigureUtil

class CookingDslPlugin implements Plugin<Project> {
  void apply(Project project) {
    project.extensions.create('devconfig', CookingExtension)
    project.devconfig.extensions.repositories = new Repositories()
    def teams = project.container(Team) { name->
      new Team(name, project)
    }
    project.devconfig.extensions.teams = teams

    project.tasks.create(name: 'dumpAllTeamUrls', type: DumpAllTeamUrls) { }
    project.tasks.create(name: 'dumpTeamUrl', type: DumpTeamUrl) { }

    project.tasks.withType(DumpAllTeamUrls) {
      def devconfigExt = project.extensions.getByName('devconfig')
      conventionMapping.teams = { devconfigExt.extensions.getByName('teams') }
    }
    project.tasks.withType(DumpTeamUrl) {
      def devconfigExt = project.extensions.getByName('devconfig')
      conventionMapping.teams = { devconfigExt.extensions.getByName('teams') }
    }
  }
}

class CookingExtension {
  CookingExtension() {
  }
}

class Repositories {
  YumRepo yum

  Repositories() {
    this.yum = new YumRepo()
  }

  void yum(Closure closure) {
    yum.configure(closure)
  }
}

class YumRepo {
  String host

  YumRepo() {
  }

  void configure(Closure closure) {
    ConfigureUtil.configure(closure, this)
  }

  String getBaseUrl() {
    "http://$host"
  }
}

class Team {
  String name
  Yum yum

  Team(String name, Project project) {
    this.name = name
    this.yum = new Yum(name, project)
  }

  void yum(Closure closure) {
    yum.configure(closure)
  }
}

class Yum {
  String path
  YumRepo yumRepo

  Yum(String teamName, Project project) {
    this.path = "/opt/teamrepos/$teamName/rhel/6"
    this.yumRepo = project.devconfig.repositories.yum
  }

  void configure(Closure closure) {
    ConfigureUtil.configure(closure, this)
  }

  String getUrl() {
    "$yumRepo.baseUrl$path"
  }
}
