load("@io_bazel_rules_kotlin//kotlin:android.bzl", "kt_android_library")
load("@io_bazel_rules_kotlin//kotlin:core.bzl", "kt_compiler_plugin")


kt_compiler_plugin(
    name = "moshi_kotlin_codegen",
    id = "com.squareup.moshi.kotlin.codegen",
    target_embedded_compiler = True,
    compile_phase = True,
    stubs_phase = True,
    deps = ["@maven//:com_squareup_moshi_moshi_kotlin_codegen",]
)


kt_android_library(
    name = "app_lib",
    srcs = glob(["src/main/java/**/*.kt"]),
    custom_package = "com.example.databinding.lib", # All databinding targets must have unique package name
    enable_data_binding = True,
    manifest = "src/main/AndroidManifest.xml",
    plugins = [
        ":moshi_kotlin_codegen",
    ],
    resource_files = glob(["src/main/res/**/*"]),
    deps = [
        "@maven//:androidx_annotation_annotation",
        "@maven//:androidx_databinding_databinding_adapters",
        "@maven//:androidx_databinding_databinding_common",
        "@maven//:androidx_databinding_databinding_runtime",
        "@maven//:com_squareup_moshi_moshi",
    ],
)

android_binary(
    name = "app",
    custom_package = "com.example.databinding",
    enable_data_binding = True,
    manifest = "src/main/AndroidManifest.xml",
    deps = [
        ":app_lib",
        "@maven//:androidx_annotation_annotation",
        "@maven//:androidx_databinding_databinding_adapters",
        "@maven//:androidx_databinding_databinding_common",
        "@maven//:androidx_databinding_databinding_runtime",
    ],
)
