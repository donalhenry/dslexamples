dslexamples
======================
Examples of gradle DSL

Wanted to create some working examples of some of the basic techniques used with gradle DSL.

The code of all examples have a common structure.  The top-level **build.gradle** file has the DSL instance and maybe a few tasks.  The DSL parsing code is within one more groovy files within the **buildSrc** tree.

- Example 1 - Uses a DSL to hold information about a collection of development teams.  The information in this case is the team size, which has a default value.

- Example 2 - Specifies the default value within the DSL instance, making it more self-contained.

- Example 3 - Adds nested DSL to the teams DSL

- Example 4 - Adds another DSL element (e.g. repositories) and creates structure to interact the teams DSL

- Example 5 - Moves the tasks to the DSL plugin code and uses convention mapping to wire the DSL instance into the tasks

- Example 6 -

- Example 7 - 
