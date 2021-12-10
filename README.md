# bazel-kt-databinding-crash
Produces a crash:

```
$ bazel build //...
Starting local Bazel server and connecting to it...
Analyzing: 6 targets (49 packages loaded, 975 targets configured)
    Fetching @remotejdk11_macos_aarch64; fetching
    Fetching ...cos_aarch64; Extracting /private/var/tmp/_bazel_p/a48d838470da09ddcc3c8b96c3b3efad/external/remotejdk11_macos_aarch64/temp9776662442558441540/zulu11.45.27-ca-jdk11.0.10-macosx_aarch64.tar.gz
FATAL: bazel crashed due to an internal error. Printing stack trace:
java.lang.RuntimeException: Unrecoverable error while evaluating node 'ConfiguredTargetKey{label=//:app_lib_base, config=BuildConfigurationValue.Key[1054a2250c4245b865962919778764c4f205786ff7dd6c61ef877b456d4f1e39]}' (requested by nodes 'ConfiguredTargetKey{label=//:app_lib, config=BuildConfigurationValue.Key[1054a2250c4245b865962919778764c4f205786ff7dd6c61ef877b456d4f1e39]}', 'ConfiguredTargetKey{label=//:app_lib_kt, config=BuildConfigurationValue.Key[1054a2250c4245b865962919778764c4f205786ff7dd6c61ef877b456d4f1e39]}')
	at com.google.devtools.build.skyframe.AbstractParallelEvaluator$Evaluate.run(AbstractParallelEvaluator.java:563)
	at com.google.devtools.build.lib.concurrent.AbstractQueueVisitor$WrappedRunnable.run(AbstractQueueVisitor.java:398)
	at java.base/java.util.concurrent.ForkJoinTask$AdaptedRunnableAction.exec(Unknown Source)
	at java.base/java.util.concurrent.ForkJoinTask.doExec(Unknown Source)
	at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(Unknown Source)
	at java.base/java.util.concurrent.ForkJoinPool.scan(Unknown Source)
	at java.base/java.util.concurrent.ForkJoinPool.runWorker(Unknown Source)
	at java.base/java.util.concurrent.ForkJoinWorkerThread.run(Unknown Source)
Caused by: java.lang.NullPointerException
	at com.google.devtools.build.lib.rules.java.JavaCompilationHelper.usesAnnotationProcessing(JavaCompilationHelper.java:530)
	at com.google.devtools.build.lib.rules.java.JavaCompilationHelper.createOutputs(JavaCompilationHelper.java:187)
	at com.google.devtools.build.lib.rules.android.AndroidCommon.initJava(AndroidCommon.java:580)
	at com.google.devtools.build.lib.rules.android.AndroidCommon.init(AndroidCommon.java:517)
	at com.google.devtools.build.lib.rules.android.AndroidLibrary.create(AndroidLibrary.java:196)
	at com.google.devtools.build.lib.rules.android.AndroidLibrary.create(AndroidLibrary.java:41)
	at com.google.devtools.build.lib.analysis.ConfiguredTargetFactory.createRule(ConfiguredTargetFactory.java:385)
	at com.google.devtools.build.lib.analysis.ConfiguredTargetFactory.createConfiguredTarget(ConfiguredTargetFactory.java:195)
	at com.google.devtools.build.lib.skyframe.SkyframeBuildView.createConfiguredTarget(SkyframeBuildView.java:940)
	at com.google.devtools.build.lib.skyframe.ConfiguredTargetFunction.createConfiguredTarget(ConfiguredTargetFunction.java:1031)
	at com.google.devtools.build.lib.skyframe.ConfiguredTargetFunction.compute(ConfiguredTargetFunction.java:371)
	at com.google.devtools.build.skyframe.AbstractParallelEvaluator$Evaluate.run(AbstractParallelEvaluator.java:477)
	... 7 more
```
