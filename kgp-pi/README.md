
Reproducer for https://youtrack.jetbrains.com/issue/KT-74394/KGP-isolated-projects-Something-has-been-appended-to-this-collector-already

To reproduce:

``` 
git clone https://github.com/martinbonnin/reproducers
cd reproducers/kgp-pi
git checkout eda2476a6232361e8ca628ef56ce6ede86c75eba
./gradlew test
```

Output:

```
iteration 0
iteration 1
iteration 2
iteration 3

Unexpected build execution failure in /Users/martinbonnin/git/reproducers/kgp-pi/build/testProject with arguments [build, --stacktrace]

Output:
Isolated projects is an incubating feature.
Calculating task graph as no cached configuration is available for tasks: build

FAILURE: Build failed with an exception.

* What went wrong:
Could not determine the dependencies of task ':compileJava'.
> Could not resolve all dependencies for configuration ':compileClasspath'.
   > Something has been appended to this collector already

* Try:
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.
> Get more help at https://help.gradle.org.

* Exception is:
org.gradle.api.internal.tasks.TaskDependencyResolveException: Could not determine the dependencies of task ':compileJava'.
	at org.gradle.api.internal.tasks.CachingTaskDependencyResolveContext.getDependencies(CachingTaskDependencyResolveContext.java:70)
	at org.gradle.execution.plan.TaskDependencyResolver.resolveDependenciesFor(TaskDependencyResolver.java:49)
	at org.gradle.execution.plan.LocalTaskNode.getDependencies(LocalTaskNode.java:148)
	at org.gradle.execution.plan.LocalTaskNode.resolveDependencies(LocalTaskNode.java:122)
	at org.gradle.execution.plan.DefaultExecutionPlan.discoverNodeRelationships(DefaultExecutionPlan.java:182)
	at org.gradle.execution.plan.DefaultExecutionPlan.doAddEntryNodes(DefaultExecutionPlan.java:154)
	at org.gradle.execution.plan.DefaultExecutionPlan.addEntryTasks(DefaultExecutionPlan.java:129)
	at org.gradle.execution.plan.DefaultExecutionPlan.addEntryTasks(DefaultExecutionPlan.java:121)
	at org.gradle.execution.TaskNameResolvingBuildTaskScheduler.scheduleRequestedTasks(TaskNameResolvingBuildTaskScheduler.java:61)
	at org.gradle.execution.DefaultTasksBuildTaskScheduler.scheduleRequestedTasks(DefaultTasksBuildTaskScheduler.java:72)
	at org.gradle.initialization.DefaultTaskExecutionPreparer.lambda$scheduleRequestedTasks$0(DefaultTaskExecutionPreparer.java:48)
	at org.gradle.internal.Factories$1.create(Factories.java:31)
	at org.gradle.internal.work.DefaultWorkerLeaseService.withReplacedLocks(DefaultWorkerLeaseService.java:359)
	at org.gradle.api.internal.project.DefaultProjectStateRegistry$DefaultBuildProjectRegistry.withMutableStateOfAllProjects(DefaultProjectStateRegistry.java:238)
	at org.gradle.api.internal.project.DefaultProjectStateRegistry$DefaultBuildProjectRegistry.withMutableStateOfAllProjects(DefaultProjectStateRegistry.java:231)
	at org.gradle.initialization.DefaultTaskExecutionPreparer.scheduleRequestedTasks(DefaultTaskExecutionPreparer.java:47)
	at org.gradle.initialization.VintageBuildModelController.lambda$scheduleRequestedTasks$0(VintageBuildModelController.java:76)
	at org.gradle.internal.model.StateTransitionController.lambda$inState$1(StateTransitionController.java:99)
	at org.gradle.internal.model.StateTransitionController.lambda$inState$2(StateTransitionController.java:114)
	at org.gradle.internal.work.DefaultSynchronizer.withLock(DefaultSynchronizer.java:46)
	at org.gradle.internal.model.StateTransitionController.inState(StateTransitionController.java:110)
	at org.gradle.internal.model.StateTransitionController.inState(StateTransitionController.java:98)
	at org.gradle.initialization.VintageBuildModelController.scheduleRequestedTasks(VintageBuildModelController.java:76)
	at org.gradle.internal.cc.impl.ConfigurationCacheAwareBuildModelController.scheduleRequestedTasks(ConfigurationCacheAwareBuildModelController.kt:55)
	at org.gradle.internal.build.DefaultBuildLifecycleController$DefaultWorkGraphBuilder.addRequestedTasks(DefaultBuildLifecycleController.java:404)
	at org.gradle.internal.buildtree.DefaultBuildTreeWorkPreparer.lambda$scheduleRequestedTasks$0(DefaultBuildTreeWorkPreparer.java:41)
	at org.gradle.internal.build.DefaultBuildLifecycleController.lambda$populateWorkGraph$7(DefaultBuildLifecycleController.java:189)
	at org.gradle.internal.build.DefaultBuildWorkPreparer.populateWorkGraph(DefaultBuildWorkPreparer.java:42)
	at org.gradle.internal.build.BuildOperationFiringBuildWorkPreparer$PopulateWorkGraph.populateTaskGraph(BuildOperationFiringBuildWorkPreparer.java:106)
	at org.gradle.internal.build.BuildOperationFiringBuildWorkPreparer$PopulateWorkGraph.run(BuildOperationFiringBuildWorkPreparer.java:92)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:29)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:26)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:166)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.run(DefaultBuildOperationRunner.java:47)
	at org.gradle.internal.build.BuildOperationFiringBuildWorkPreparer.populateWorkGraph(BuildOperationFiringBuildWorkPreparer.java:67)
	at org.gradle.internal.build.DefaultBuildLifecycleController.lambda$populateWorkGraph$8(DefaultBuildLifecycleController.java:189)
	at org.gradle.internal.model.StateTransitionController.lambda$inState$1(StateTransitionController.java:99)
	at org.gradle.internal.model.StateTransitionController.lambda$inState$2(StateTransitionController.java:114)
	at org.gradle.internal.work.DefaultSynchronizer.withLock(DefaultSynchronizer.java:46)
	at org.gradle.internal.model.StateTransitionController.inState(StateTransitionController.java:110)
	at org.gradle.internal.model.StateTransitionController.inState(StateTransitionController.java:98)
	at org.gradle.internal.build.DefaultBuildLifecycleController.populateWorkGraph(DefaultBuildLifecycleController.java:189)
	at org.gradle.internal.build.DefaultBuildWorkGraphController$DefaultBuildWorkGraph.populateWorkGraph(DefaultBuildWorkGraphController.java:169)
	at org.gradle.composite.internal.DefaultBuildController.populateWorkGraph(DefaultBuildController.java:76)
	at org.gradle.composite.internal.DefaultIncludedBuildTaskGraph$DefaultBuildTreeWorkGraphBuilder.withWorkGraph(DefaultIncludedBuildTaskGraph.java:153)
	at org.gradle.internal.buildtree.DefaultBuildTreeWorkPreparer.lambda$scheduleRequestedTasks$1(DefaultBuildTreeWorkPreparer.java:41)
	at org.gradle.composite.internal.DefaultIncludedBuildTaskGraph$DefaultBuildTreeWorkGraph$1.run(DefaultIncludedBuildTaskGraph.java:209)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:29)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:26)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:166)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.run(DefaultBuildOperationRunner.java:47)
	at org.gradle.composite.internal.DefaultIncludedBuildTaskGraph$DefaultBuildTreeWorkGraph.scheduleWork(DefaultIncludedBuildTaskGraph.java:204)
	at org.gradle.internal.buildtree.DefaultBuildTreeWorkPreparer.scheduleRequestedTasks(DefaultBuildTreeWorkPreparer.java:37)
	at org.gradle.internal.cc.impl.ConfigurationCacheAwareBuildTreeWorkController$scheduleAndRunRequestedTasks$executionResult$1$result$1.invoke(ConfigurationCacheAwareBuildTreeWorkController.kt:48)
	at org.gradle.internal.cc.impl.ConfigurationCacheAwareBuildTreeWorkController$scheduleAndRunRequestedTasks$executionResult$1$result$1.invoke(ConfigurationCacheAwareBuildTreeWorkController.kt:45)
	at org.gradle.internal.cc.impl.DefaultConfigurationCache$loadOrScheduleRequestedTasks$1.invoke(DefaultConfigurationCache.kt:239)
	at org.gradle.internal.cc.impl.DefaultConfigurationCache$loadOrScheduleRequestedTasks$1.invoke(DefaultConfigurationCache.kt:238)
	at org.gradle.internal.cc.impl.DefaultConfigurationCache.runWorkThatContributesToCacheEntry(DefaultConfigurationCache.kt:548)
	at org.gradle.internal.cc.impl.DefaultConfigurationCache.loadOrScheduleRequestedTasks(DefaultConfigurationCache.kt:238)
	at org.gradle.internal.cc.impl.ConfigurationCacheAwareBuildTreeWorkController$scheduleAndRunRequestedTasks$executionResult$1.apply(ConfigurationCacheAwareBuildTreeWorkController.kt:45)
	at org.gradle.internal.cc.impl.ConfigurationCacheAwareBuildTreeWorkController$scheduleAndRunRequestedTasks$executionResult$1.apply(ConfigurationCacheAwareBuildTreeWorkController.kt:44)
	at org.gradle.composite.internal.DefaultIncludedBuildTaskGraph.withNewWorkGraph(DefaultIncludedBuildTaskGraph.java:112)
	at org.gradle.internal.cc.impl.ConfigurationCacheAwareBuildTreeWorkController.scheduleAndRunRequestedTasks(ConfigurationCacheAwareBuildTreeWorkController.kt:44)
	at org.gradle.internal.buildtree.DefaultBuildTreeLifecycleController.lambda$scheduleAndRunTasks$1(DefaultBuildTreeLifecycleController.java:77)
	at org.gradle.internal.buildtree.DefaultBuildTreeLifecycleController.lambda$runBuild$4(DefaultBuildTreeLifecycleController.java:120)
	at org.gradle.internal.model.StateTransitionController.lambda$transition$6(StateTransitionController.java:169)
	at org.gradle.internal.model.StateTransitionController.doTransition(StateTransitionController.java:266)
	at org.gradle.internal.model.StateTransitionController.lambda$transition$7(StateTransitionController.java:169)
	at org.gradle.internal.work.DefaultSynchronizer.withLock(DefaultSynchronizer.java:46)
	at org.gradle.internal.model.StateTransitionController.transition(StateTransitionController.java:169)
	at org.gradle.internal.buildtree.DefaultBuildTreeLifecycleController.runBuild(DefaultBuildTreeLifecycleController.java:117)
	at org.gradle.internal.buildtree.DefaultBuildTreeLifecycleController.scheduleAndRunTasks(DefaultBuildTreeLifecycleController.java:77)
	at org.gradle.internal.buildtree.DefaultBuildTreeLifecycleController.scheduleAndRunTasks(DefaultBuildTreeLifecycleController.java:72)
	at org.gradle.tooling.internal.provider.runner.BuildModelActionRunner.run(BuildModelActionRunner.java:53)
	at org.gradle.launcher.exec.ChainingBuildActionRunner.run(ChainingBuildActionRunner.java:35)
	at org.gradle.internal.buildtree.ProblemReportingBuildActionRunner.run(ProblemReportingBuildActionRunner.java:49)
	at org.gradle.launcher.exec.BuildOutcomeReportingBuildActionRunner.run(BuildOutcomeReportingBuildActionRunner.java:71)
	at org.gradle.tooling.internal.provider.FileSystemWatchingBuildActionRunner.run(FileSystemWatchingBuildActionRunner.java:135)
	at org.gradle.launcher.exec.BuildCompletionNotifyingBuildActionRunner.run(BuildCompletionNotifyingBuildActionRunner.java:41)
	at org.gradle.launcher.exec.RootBuildLifecycleBuildActionExecutor.lambda$execute$0(RootBuildLifecycleBuildActionExecutor.java:54)
	at org.gradle.composite.internal.DefaultRootBuildState.run(DefaultRootBuildState.java:130)
	at org.gradle.launcher.exec.RootBuildLifecycleBuildActionExecutor.execute(RootBuildLifecycleBuildActionExecutor.java:54)
	at org.gradle.internal.buildtree.InitDeprecationLoggingActionExecutor.execute(InitDeprecationLoggingActionExecutor.java:62)
	at org.gradle.internal.buildtree.InitProblems.execute(InitProblems.java:36)
	at org.gradle.internal.buildtree.DefaultBuildTreeContext.execute(DefaultBuildTreeContext.java:40)
	at org.gradle.launcher.exec.BuildTreeLifecycleBuildActionExecutor.lambda$execute$0(BuildTreeLifecycleBuildActionExecutor.java:71)
	at org.gradle.internal.buildtree.BuildTreeState.run(BuildTreeState.java:60)
	at org.gradle.launcher.exec.BuildTreeLifecycleBuildActionExecutor.execute(BuildTreeLifecycleBuildActionExecutor.java:71)
	at org.gradle.launcher.exec.RunAsBuildOperationBuildActionExecutor$2.call(RunAsBuildOperationBuildActionExecutor.java:67)
	at org.gradle.launcher.exec.RunAsBuildOperationBuildActionExecutor$2.call(RunAsBuildOperationBuildActionExecutor.java:63)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:209)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:204)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:166)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:53)
	at org.gradle.launcher.exec.RunAsBuildOperationBuildActionExecutor.execute(RunAsBuildOperationBuildActionExecutor.java:63)
	at org.gradle.launcher.exec.RunAsWorkerThreadBuildActionExecutor.lambda$execute$0(RunAsWorkerThreadBuildActionExecutor.java:36)
	at org.gradle.internal.work.DefaultWorkerLeaseService.withLocks(DefaultWorkerLeaseService.java:263)
	at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:127)
	at org.gradle.launcher.exec.RunAsWorkerThreadBuildActionExecutor.execute(RunAsWorkerThreadBuildActionExecutor.java:36)
	at org.gradle.tooling.internal.provider.continuous.ContinuousBuildActionExecutor.execute(ContinuousBuildActionExecutor.java:110)
	at org.gradle.tooling.internal.provider.SubscribableBuildActionExecutor.execute(SubscribableBuildActionExecutor.java:64)
	at org.gradle.internal.session.DefaultBuildSessionContext.execute(DefaultBuildSessionContext.java:46)
	at org.gradle.internal.buildprocess.execution.BuildSessionLifecycleBuildActionExecutor$ActionImpl.apply(BuildSessionLifecycleBuildActionExecutor.java:92)
	at org.gradle.internal.buildprocess.execution.BuildSessionLifecycleBuildActionExecutor$ActionImpl.apply(BuildSessionLifecycleBuildActionExecutor.java:80)
	at org.gradle.internal.session.BuildSessionState.run(BuildSessionState.java:71)
	at org.gradle.internal.buildprocess.execution.BuildSessionLifecycleBuildActionExecutor.execute(BuildSessionLifecycleBuildActionExecutor.java:62)
	at org.gradle.internal.buildprocess.execution.BuildSessionLifecycleBuildActionExecutor.execute(BuildSessionLifecycleBuildActionExecutor.java:41)
	at org.gradle.internal.buildprocess.execution.StartParamsValidatingActionExecutor.execute(StartParamsValidatingActionExecutor.java:64)
	at org.gradle.internal.buildprocess.execution.StartParamsValidatingActionExecutor.execute(StartParamsValidatingActionExecutor.java:32)
	at org.gradle.internal.buildprocess.execution.SessionFailureReportingActionExecutor.execute(SessionFailureReportingActionExecutor.java:51)
	at org.gradle.internal.buildprocess.execution.SessionFailureReportingActionExecutor.execute(SessionFailureReportingActionExecutor.java:39)
	at org.gradle.internal.buildprocess.execution.SetupLoggingActionExecutor.execute(SetupLoggingActionExecutor.java:47)
	at org.gradle.internal.buildprocess.execution.SetupLoggingActionExecutor.execute(SetupLoggingActionExecutor.java:31)
	at org.gradle.launcher.daemon.server.exec.ExecuteBuild.doBuild(ExecuteBuild.java:70)
	at org.gradle.launcher.daemon.server.exec.BuildCommandOnly.execute(BuildCommandOnly.java:37)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:104)
	at org.gradle.launcher.daemon.server.exec.WatchForDisconnection.execute(WatchForDisconnection.java:39)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:104)
	at org.gradle.launcher.daemon.server.exec.ResetDeprecationLogger.execute(ResetDeprecationLogger.java:29)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:104)
	at org.gradle.launcher.daemon.server.exec.RequestStopIfSingleUsedDaemon.execute(RequestStopIfSingleUsedDaemon.java:35)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:104)
	at org.gradle.launcher.daemon.server.exec.ForwardClientInput.lambda$execute$0(ForwardClientInput.java:40)
	at org.gradle.internal.daemon.clientinput.ClientInputForwarder.forwardInput(ClientInputForwarder.java:80)
	at org.gradle.launcher.daemon.server.exec.ForwardClientInput.execute(ForwardClientInput.java:37)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:104)
	at org.gradle.launcher.daemon.server.exec.LogAndCheckHealth.execute(LogAndCheckHealth.java:64)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:104)
	at org.gradle.launcher.daemon.server.exec.LogToClient.doBuild(LogToClient.java:63)
	at org.gradle.launcher.daemon.server.exec.BuildCommandOnly.execute(BuildCommandOnly.java:37)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:104)
	at org.gradle.launcher.daemon.server.exec.EstablishBuildEnvironment.doBuild(EstablishBuildEnvironment.java:84)
	at org.gradle.launcher.daemon.server.exec.BuildCommandOnly.execute(BuildCommandOnly.java:37)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:104)
	at org.gradle.launcher.daemon.server.exec.StartBuildOrRespondWithBusy$1.run(StartBuildOrRespondWithBusy.java:52)
	at org.gradle.launcher.daemon.server.DaemonStateCoordinator.lambda$runCommand$0(DaemonStateCoordinator.java:321)
	at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
	at org.gradle.internal.concurrent.AbstractManagedExecutor$1.run(AbstractManagedExecutor.java:48)
Caused by: org.gradle.api.internal.artifacts.ivyservice.TypedResolveException: Could not resolve all dependencies for configuration ':compileClasspath'.
	at org.gradle.api.internal.artifacts.ResolveExceptionMapper.mapFailure(ResolveExceptionMapper.java:68)
	at org.gradle.api.internal.artifacts.configurations.DefaultConfiguration.lambda$resolveGraphForBuildDependenciesIfRequired$7(DefaultConfiguration.java:949)
	at org.gradle.api.internal.project.DefaultProjectStateRegistry$CalculatedModelValueImpl.update(DefaultProjectStateRegistry.java:509)
	at org.gradle.api.internal.artifacts.configurations.DefaultConfiguration.resolveGraphForBuildDependenciesIfRequired(DefaultConfiguration.java:933)
	at org.gradle.api.internal.artifacts.configurations.DefaultConfiguration.access$800(DefaultConfiguration.java:159)
	at org.gradle.api.internal.artifacts.configurations.DefaultConfiguration$ResolverResultsResolutionResultProvider.getTaskDependencyValue(DefaultConfiguration.java:716)
	at org.gradle.api.internal.artifacts.configurations.DefaultConfiguration$ResolverResultsResolutionResultProvider.getTaskDependencyValue(DefaultConfiguration.java:708)
	at org.gradle.api.internal.artifacts.configurations.ResolutionResultProvider$1.getTaskDependencyValue(ResolutionResultProvider.java:49)
	at org.gradle.api.internal.artifacts.configurations.ResolutionResultProviderBackedSelectedArtifactSet.visitDependencies(ResolutionResultProviderBackedSelectedArtifactSet.java:44)
	at org.gradle.api.internal.artifacts.configurations.ResolutionBackedFileCollection.visitDependencies(ResolutionBackedFileCollection.java:58)
	at org.gradle.api.internal.tasks.CachingTaskDependencyResolveContext$TaskGraphImpl.getNodeValues(CachingTaskDependencyResolveContext.java:108)
	at org.gradle.internal.graph.CachingDirectedGraphWalker$GraphWithEmptyEdges.getNodeValues(CachingDirectedGraphWalker.java:213)
	at org.gradle.internal.graph.CachingDirectedGraphWalker.doSearch(CachingDirectedGraphWalker.java:121)
	at org.gradle.internal.graph.CachingDirectedGraphWalker.findValues(CachingDirectedGraphWalker.java:73)
	at org.gradle.api.internal.tasks.CachingTaskDependencyResolveContext.getDependencies(CachingTaskDependencyResolveContext.java:67)
	... 146 more
Caused by: java.lang.IllegalStateException: Something has been appended to this collector already
	at com.google.common.base.Preconditions.checkState(Preconditions.java:512)
	at org.gradle.api.internal.provider.AbstractCollectionProperty$CollectingSupplier.plus(AbstractCollectionProperty.java:574)
	at org.gradle.api.internal.provider.AbstractCollectionProperty.addExplicitCollector(AbstractCollectionProperty.java:222)
	at org.gradle.api.internal.provider.AbstractCollectionProperty.access$700(AbstractCollectionProperty.java:88)
	at org.gradle.api.internal.provider.AbstractCollectionProperty$Configurer.addCollector(AbstractCollectionProperty.java:839)
	at org.gradle.api.internal.provider.AbstractCollectionProperty$Configurer.add(AbstractCollectionProperty.java:848)
	at org.gradle.api.internal.provider.AbstractCollectionProperty.add(AbstractCollectionProperty.java:161)
	at org.jetbrains.kotlin.gradle.utils.ReportUtilsKt.addConfigurationMetrics(reportUtils.kt:23)
	at org.jetbrains.kotlin.gradle.plugin.statistics.KotlinStdlibConfigurationMetrics.collectMetrics$kotlin_gradle_plugin_common(FusMetrics.kt:332)
	at org.jetbrains.kotlin.gradle.internal.StdlibDependencyManagementKt$addStdlibDependency$1$1$1.execute(stdlibDependencyManagement.kt:139)
	at org.jetbrains.kotlin.gradle.internal.StdlibDependencyManagementKt$addStdlibDependency$1$1$1.execute(stdlibDependencyManagement.kt:116)
	at org.gradle.internal.ImmutableActionSet$SetWithManyActions.execute(ImmutableActionSet.java:329)
	at org.gradle.api.internal.artifacts.configurations.DefaultConfiguration.lambda$runDependencyActions$2(DefaultConfiguration.java:509)
	at org.gradle.api.internal.artifacts.configurations.DefaultConfiguration.runActionInHierarchy(DefaultConfiguration.java:1170)
	at org.gradle.api.internal.artifacts.configurations.DefaultConfiguration.runDependencyActions(DefaultConfiguration.java:507)
	at org.gradle.api.internal.artifacts.ivyservice.moduleconverter.dependencies.DefaultLocalVariantGraphResolveStateBuilder.create(DefaultLocalVariantGraphResolveStateBuilder.java:92)
	at org.gradle.internal.component.local.model.LocalComponentGraphResolveStateFactory$ConfigurationsProviderVariantFactory.createVariantState(LocalComponentGraphResolveStateFactory.java:244)
	at org.gradle.internal.component.local.model.LocalComponentGraphResolveStateFactory$ConfigurationsProviderVariantFactory.lambda$getVariantByConfigurationName$2(LocalComponentGraphResolveStateFactory.java:239)
	at org.gradle.api.internal.project.DefaultProjectStateRegistry$ProjectStateImpl.fromMutableState(DefaultProjectStateRegistry.java:429)
	at org.gradle.internal.component.local.model.LocalComponentGraphResolveStateFactory$ConfigurationsProviderVariantFactory.getVariantByConfigurationName(LocalComponentGraphResolveStateFactory.java:233)
	at org.gradle.internal.component.local.model.DefaultLocalComponentGraphResolveState.doCreateLegacyConfiguration(DefaultLocalComponentGraphResolveState.java:237)
	at org.gradle.internal.model.InMemoryCacheFactory$CalculatedValueCache.lambda$new$0(InMemoryCacheFactory.java:177)
	at org.gradle.internal.model.CalculatedValueContainer$CalculationState.lambda$attachValue$0(CalculatedValueContainer.java:229)
	at org.gradle.internal.Try.ofFailable(Try.java:50)
	at org.gradle.internal.model.CalculatedValueContainer$CalculationState.attachValue(CalculatedValueContainer.java:224)
	at org.gradle.internal.model.CalculatedValueContainer.finalizeIfNotAlready(CalculatedValueContainer.java:195)
	at org.gradle.internal.model.CalculatedValueContainer.finalizeIfNotAlready(CalculatedValueContainer.java:186)
	at org.gradle.internal.model.InMemoryCacheFactory$CalculatedValueCache.get(InMemoryCacheFactory.java:187)
	at org.gradle.internal.component.local.model.DefaultLocalComponentGraphResolveState.getConfigurationLegacy(DefaultLocalComponentGraphResolveState.java:233)
	at org.gradle.api.internal.artifacts.ivyservice.DefaultConfigurationResolver.maybeGetEmptyGraphForInvalidMissingConfigurationWithNoDependencies(DefaultConfigurationResolver.java:180)
	at org.gradle.api.internal.artifacts.ivyservice.DefaultConfigurationResolver.resolveBuildDependencies(DefaultConfigurationResolver.java:73)
	at org.gradle.api.internal.artifacts.configurations.DefaultConfiguration.lambda$resolveGraphForBuildDependenciesIfRequired$7(DefaultConfiguration.java:947)
	... 159 more

```
