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
