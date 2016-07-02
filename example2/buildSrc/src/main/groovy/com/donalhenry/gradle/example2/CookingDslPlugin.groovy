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

package com.donalhenry.gradle.example2

import org.gradle.api.Project
import org.gradle.api.Plugin

class CookingDslPlugin implements Plugin<Project> {
  void apply(Project project) {
    project.extensions.create('devconfig', CookingExtension)
    def teams = project.container(Team) { name->
      new Team(name, project.devconfig.minTeamsize)
    }
    project.devconfig.extensions.add('teams', teams)
  }
}

class CookingExtension {
  Integer minTeamsize

  CookingExtension() {
  }
}

class Team {
  String name
  Integer size

  Team(String name, Integer minTeamsize) {
    this.name = name
    this.size = minTeamsize
  }

  String getSizeString() {
    (size == 1) ? "$size member" : "$size members"
  }
}
