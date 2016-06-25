dslexamples
======================
Examples of gradle DSL

Wanted to create some working examples of some of the basic techniques used with gradle DSL.

The code of all examples have a common structure.  The top-level **build.gradle** file has the DSL instance and maybe a few tasks.  The DSL parsing code is within one more groovy files within the **buildSrc** tree.

- Example 1
  Uses a DSL to hold information about a number of development teams.  The information in this case is the team size, which has a default value.

- Example 2
  Builds on example 1 and specifies the default value within the DSL instance, making it more self-contained.

- Example 3
- Example 4
- Example 5
- Example 6
- Example 7
