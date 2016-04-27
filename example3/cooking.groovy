apply from: 'cookingdsl.groovy'

devconfig {
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
