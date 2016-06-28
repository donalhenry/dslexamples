dslexamples
======================
Examples of gradle DSL

Wanted to create some working examples that illustrate the basic techniques used with gradle DSL.

The code of all examples have a common structure.  The top-level **build.gradle** file has the DSL instance and maybe a few tasks.  The DSL plugin code is within one more groovy files in the **buildSrc** tree.

```bash
$ tree
.
├── build.gradle
└── buildSrc
    └── src
        └── main
            └── groovy
                └── com
                    └── donalhenry
                        └── gradle
                            └── example1
                                └── CookingDslPlugin.groovy

8 directories, 2 files
```

Below is a thumbnail summary of each example.  These examples will all eventually be tied to blog posts at [donalhenry.com](http://www.donalhenry.com/) which provide a much more detailed explanation of the code in each example.

- Example 1 - Creates a DSL to hold information about a few development teams.  The information in this case is the team size, which has a default value.

- Example 2 - Specifies the default value within the DSL instance, making it more self-contained.

- Example 3 - Adds nested configuration into the teams DSL.

- Example 4 - Adds another extension and derived configuration.

- Example 5 - Moves the tasks into the DSL plugin code and uses convention mapping to wire the DSL instance into the tasks

- Example 6 - Uses nested containers.  The DSL was actually lifted from [The Zen Of Chicken](https://zenofchicken.wordpress.com/2012/12/31/article-series-creating-a-custom-gradle-plugin-with-custom-tasks-managing-configuration-and-domain-objects/) with the DSL plugin code inspired by [mrhaki.com](http://mrhaki.blogspot.com/2016/02/gradle-goodness-using-nested-domain.html/), which, by the way, is an excellent resource for all things groovy and gradle related.

- Example 7 - Another example of nested containers where the inside container instances are managed as a list and an add method.  The DSL was inspired from an exchange on [stackoverflow.com](http://stackoverflow.com/questions/17626607/writing-gradle-plugin-with-nested-extension-objects) but with simplified the plugin code.
