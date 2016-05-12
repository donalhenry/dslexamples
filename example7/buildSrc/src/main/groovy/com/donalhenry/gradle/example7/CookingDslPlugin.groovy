package com.donalhenry.gradle.example7

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.util.ConfigureUtil

class CookingDslPlugin implements Plugin<Project> {
  void apply(Project project) {
    project.extensions.create('asgard', AsgardExtension)
    def regions = project.container(Region) { name->
      new Region(name)
    }
    project.asgard.extensions.regions = regions
  }
}

class AsgardExtension {
}

class Region {
  String name
  List<AutoScaling> autoScalings = []

  Region(String name) {
    this.name = name
  }

  void autoScaling(Closure closure) {
    def autoScaling = new AutoScaling()
    autoScaling.configure(closure)
    autoScalings.add(autoScaling)
  }
}

class AutoScaling {
  String devPhase
  Integer min
  Integer max
  List<String> availabilityZones
  String ami
  String instanceType
  List<String> securityGroups

  void configure(Closure closure) {
    ConfigureUtil.configure(closure, this)
  }
}
