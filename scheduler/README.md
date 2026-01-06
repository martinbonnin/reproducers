To reproduce:

```shell
./gradlew installDist
./build/install/scheduler/bin/scheduler

# In another terminal
./gradlew test -i

[...]
Successfully executed 100 parallel requests in 4.193107334s
```
Each request is delayed by 2 seconds. So the 100 request takes ~4s to complete with the default 64 parallelism.