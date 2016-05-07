package com.donalhenry.gradle.example6

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.util.ConfigureUtil

class CookingDslPlugin implements Plugin<Project> {
  void apply(Project project) {
    project.extensions.create('envs', CookingExtension)
    /*
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
    */
  }
}

class CookingExtension {
  String defaultDomain
  Environment dev, qa

  CookingExtension() {
    dev = new Environment()
    qa = new Environment()
  }

  void dev(Closure closure) {
    dev.configure(closure)
  }

  void qa(Closure closure) {
    qa.configure(closure)
  }
}

class Environment {
  List<ChefNode> chefNodes = []

  Environment() {
  }

  void configure(Closure closure) {
    ConfigureUtil.configure(closure, this)
  }

  void chefNode(Closure closure) {
    def chefNode = new ChefNode()
    chefNode.configure(closure)
    chefNodes.add(chefNode)
  }
}

class ChefNode {
  String host

  ChefNode () {
  }

  void configure(Closure closure) {
    ConfigureUtil.configure(closure, this)
  }
}
