A build with 2 modules sharing a shared source directory (`sharedCode`) using `srcDir()`.

Looks like `sharedCode` can only be added to a single module at a time in the IDE. By default everything compiles but `moduleB` does not see symbols in `sharedCode`:

![](https://github.com/user-attachments/assets/8796f2c1-e877-435c-8704-632040a7a64c)

Commenting the `srcDir()` in `moduleA` makes `Foo` visible to `BFoo` in the IDE but `moduleA` fails to compile.
