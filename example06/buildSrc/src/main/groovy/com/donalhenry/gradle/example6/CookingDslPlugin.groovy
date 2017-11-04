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

// http://mrhaki.blogspot.com/2016/02/gradle-goodness-using-nested-domain.html
// https://zenofchicken.wordpress.com/2012/12/31/article-series-creating-a-custom-gradle-plugin-with-custom-tasks-managing-configuration-and-domain-objects/

package com.donalhenry.gradle.example6

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.util.ConfigureUtil
import org.gradle.api.NamedDomainObjectContainer

class CookingDslPlugin implements Plugin<Project> {
  void apply(Project project) {
    def environments = project.container(Environment) { name->
      new Environment(name, project)
    }
    project.extensions.add('environments', environments)
  }
}

class Environment {
  String name
  NamedDomainObjectContainer<Node> nodes
  Scheduler scheduler

  Environment(String name, Project project) {
    this.name = name
    this.nodes = project.container(Node)
  }

  void nodes(Closure closure) {
    nodes.configure(closure)
  }

  void scheduler(Closure closure) {
    this.scheduler = new Scheduler()
    scheduler.configure(closure)
  }

  Node getSchedulerNode() {
    nodes.getByName(scheduler.name)
  }
}

class Scheduler {
  String name

  void configure(Closure closure) {
    ConfigureUtil.configure(closure, this)
  }
}

class Node {
  String name
  String host
  String userName
  String password
  String loadBalancer
  String frontendJkManagerUrl

  Node(String name) {
    this.name = name
  }
}
