package com.donalhenry.gradle.example2

import org.gradle.api.Project
import org.gradle.api.Plugin

class CookingDslPlugin implements Plugin<Project> {
  void apply(Project project) {
    project.extensions.create('cooking', CookingExtension)
    def meals = project.container(Meal) { name->
      new Meal(name, project.cooking.expectedPeopleEating)
    }
    project.cooking.extensions.meals = meals
  }
}

class CookingExtension {
  Integer expectedPeopleEating

  CookingExtension() {
  }
}

class Meal {
  String name
  Integer peopleEating

  Meal(String name, Integer peopleEating) {
    this.name = name
    this.peopleEating = peopleEating
  }
}
