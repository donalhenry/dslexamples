apply plugin: CookingDslPlugin

class CookingDslPlugin implements Plugin<Project> {
  void apply(Project project) {
    project.extensions.create('devconfig', CookingExtension)
    def teams = project.container(Team) { name->
      new Team(name)
    }
    project.devconfig.extensions.teams = teams
  }
}

class CookingExtension {
  CookingExtension() {
  }
}

class Team {
  String name
  Yum yum

  Team(String name) {
    this.name = name
    this.yum = new Yum(name)
  }

  void yum(Closure closure) {
    yum.configure(closure)
  }
}

class Yum {
  String path

  Yum(String teamName) {
    this.path = "/opt/teamrepos/$teamName/rhel/6"
  }

  void configure(Closure closure) {
    ConfigureUtil.configure(closure, this)
  }
}
