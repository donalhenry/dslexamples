dslexamples
======================
Examples of gradle DSL

Wanted to create some working examples that illustrate some of the basic techniques used with gradle DSL.

The code of all examples have a common structure.  The top-level **build.gradle** file has the DSL instance and maybe a few tasks.  The DSL parsing code is within one more groovy files within the **buildSrc** tree.

```bash
dohenry@ubuntu-1404-donhenry:~/dslexamples/example1$ tree
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

- Example 1 - Uses a DSL to hold information about a collection of development teams.  The information in this case is the team size, which has a default value.

- Example 2 - Specifies the default value within the DSL instance, making it more self-contained.

- Example 3 - Adds nested DSL to the teams DSL

- Example 4 - Adds another DSL element (e.g. repositories) and creates structure to interact the teams DSL

- Example 5 - Moves the tasks to the DSL plugin code and uses convention mapping to wire the DSL instance into the tasks

- Example 6 -

- Example 7 -
