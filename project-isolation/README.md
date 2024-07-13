Open the project in IntelliJ and sync.

Error:

```
Isolated projects is an incubating feature.
Creating tooling model as no cached configuration is available for the requested model
> Task :prepareKotlinBuildScriptModel UP-TO-DATE

FAILURE: Build failed with an exception.

* What went wrong:
Configuration cache problems found in this build.

55 problems were found storing the configuration cache, 6 of which seem unique.
- Plugin class 'JetGradlePlugin': Cannot access project ':module1' from project ':'
- Plugin class 'JetGradlePlugin': Cannot access project ':module2' from project ':'
- Unknown location: Cannot access project ':' from project ':module1'
- Unknown location: Cannot access project ':' from project ':module2'
- Unknown location: Cannot access project ':module1' from project ':'
- Unknown location: Cannot access project ':module2' from project ':'

See the complete report at file:///Users/mbonnin/git/reproducers/project-isolation/build/reports/configuration-cache/9rmcnvry0dmmv7xho8b1dirxy/2o5tdx8uxsoeil3t2npsm0y7d/configuration-cache-report.html
> Cannot access project ':module1' from project ':'
> Cannot access project ':module2' from project ':'
> Cannot access project ':module1' from project ':'
> Cannot access project ':module2' from project ':'
> Cannot access project ':' from project ':module1'
```