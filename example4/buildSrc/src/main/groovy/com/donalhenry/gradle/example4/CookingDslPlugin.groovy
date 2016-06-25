// Copyright (C) 2016 Donal Henry, Jr.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.donalhenry.gradle.example4

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
