load("@io_bazel_rules_kotlin//kotlin:android.bzl", "kt_android_library")
load("@io_bazel_rules_kotlin//kotlin:core.bzl", "kt_compiler_plugin")
# load("@io_bazel_rules_kotlin//kotlin:core.bzl", "kt_compiler_plugin", "define_kt_toolchain")

package(default_visibility = ["//visibility:public"])

# define_kt_toolchain(
#     name = "test_toolchain",
#     language_version = "1.3",
#     api_version = "1.3",
#     jvm_target = "1.8",
# )

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
    srcs = glob(["src/main/java/**/*.kt",]),
    custom_package = "com.example.databinding.lib",
    enable_data_binding = True,
    manifest = "src/main/AndroidManifest.xml",
    plugins = [
        ":moshi_kotlin_codegen",
    ],
    resource_files = glob(["src/main/res/**/*"]),
    deps = [
        "@maven//:com_squareup_moshi_moshi",
        "@maven//:androidx_annotation_annotation",
        "@maven//:androidx_databinding_databinding_adapters",
        "@maven//:androidx_databinding_databinding_common",
        "@maven//:androidx_databinding_databinding_runtime",
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
