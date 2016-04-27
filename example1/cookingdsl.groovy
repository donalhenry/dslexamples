apply plugin: CookingDslPlugin

class CookingDslPlugin implements Plugin<Project> {
  void apply(Project project) {
    project.extensions.create('cooking', CookingExtension)
    def meals = project.container(Meal) { name->
      new Meal(name)
    }
    project.cooking.extensions.meals = meals
//    project.cooking.extensions.add('meals', meals)
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
