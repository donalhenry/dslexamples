apply from: 'cookingdsl.groovy'

devconfig {
  repositories {
    yum {
      host = 'yumrepo.example.com'
    }
  }

  teams {
    // Team defaults:
    // yum.path = '/opt/teamrepos/$TeamName/rhel/6'
    elections { }
    engineering { }
    pfunk {
      yum {
        path = '/opt/teamrepos/P-Funk/rhel/6'
      }
    }
  }
}
