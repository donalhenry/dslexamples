apply plugin: CookingDslPlugin

class CookingDslPlugin implements Plugin<Project> {
  void apply(Project project) {
    project.extensions.create('devconfig', CookingExtension)
    project.devconfig.extensions.repositories = new Repositories()
    def teams = project.container(Team) { name->
      new Team(name, project)
    }
    project.devconfig.extensions.teams = teams
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
