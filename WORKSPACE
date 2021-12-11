load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

RULES_JVM_EXTERNAL_TAG = "4.2"

http_archive(
    name = "rules_jvm_external",
    sha256 = "cd1a77b7b02e8e008439ca76fd34f5b07aecb8c752961f9640dea15e9e5ba1ca",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")


bind(
    name = "databinding_annotation_processor",
    actual = "//tools:compiler_annotation_processor",
)

maven_install(
    artifacts = [
        "com.squareup.moshi:moshi:1.9.2",
        "com.squareup.moshi:moshi-kotlin-codegen:1.9.2",
        "androidx.databinding:databinding-adapters:3.4.2",
        "androidx.databinding:databinding-common:3.4.2",
        "androidx.databinding:databinding-compiler:3.4.2",
        "androidx.databinding:databinding-runtime:3.4.2",
    ],
    repositories = [
        "https://jcenter.bintray.com/",
        "https://maven.google.com",
        "https://repo1.maven.org/maven2",
    ],
)

android_sdk_repository(
    name = "androidsdk",
    api_level = 30,
    build_tools_version = "30.0.3", # Build tools 31+ is not supported https://github.com/bazelbuild/bazel/issues/10240#issuecomment-884419566
)

http_archive(
    name = "io_bazel_rules_kotlin",
    sha256 = "6cbd4e5768bdfae1598662e40272729ec9ece8b7bded8f0d2c81c8ff96dc139d",
    urls = ["https://github.com/bazelbuild/rules_kotlin/releases/download/v1.5.0-beta-4/rules_kotlin_release.tgz"],
)

load("@io_bazel_rules_kotlin//kotlin:repositories.bzl", "kotlin_repositories")

kotlin_repositories()

load("@io_bazel_rules_kotlin//kotlin:core.bzl", "kt_register_toolchains")

kt_register_toolchains()
