To reproduce the issue:

```
./gradlew compileTestDevelopmentExecutableKotlinWasmJs
```

Output:

```
e: java.lang.NullPointerException
	at org.jetbrains.kotlin.backend.wasm.lower.GenerateWasmTests.makeTestFunctionDeclarator$lambda$4(GenerateWasmTests.kt:55)
	at org.jetbrains.kotlin.ir.backend.js.WholeWorldStageController.restrictTo(WholeWorldStageController.kt:29)
	at org.jetbrains.kotlin.backend.wasm.lower.GenerateWasmTests.makeTestFunctionDeclarator(GenerateWasmTests.kt:49)
	at org.jetbrains.kotlin.backend.wasm.lower.GenerateWasmTests.lower(GenerateWasmTests.kt:33)
	at org.jetbrains.kotlin.backend.common.phaser.PhaseBuildersKt.makeIrModulePhase$lambda$5(PhaseBuilders.kt:95)
	at org.jetbrains.kotlin.backend.common.phaser.PhaseBuildersKt$createSimpleNamedCompilerPhase$1.phaseBody(PhaseBuilders.kt:58)
	at org.jetbrains.kotlin.config.phaser.NamedCompilerPhase.invoke(CompilerPhase.kt:102)
	at org.jetbrains.kotlin.backend.wasm.WasmCompilerKt.lowerPreservingTags(wasmCompiler.kt:133)
```