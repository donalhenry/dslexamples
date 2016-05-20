package com.donalhenry.gradle.example1

import org.gradle.api.Project
import org.gradle.api.Plugin

class CookingDslPlugin implements Plugin<Project> {
  void apply(Project project) {
    project.extensions.create('cooking', CookingExtension)
    def meals = project.container(Meal)
    project.cooking.extensions.meals = meals
  }
}

class CookingExtension {
  CookingExtension() {
  }
}

class Meal {
  String name
  Integer peopleEating = 1

  Meal(String name) {
    this.name = name
  }
}
