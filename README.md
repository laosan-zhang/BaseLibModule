发布到本地maven的方法
1、在要发布的仓库build.gradle 中进行配置

配置maven插件

    plugins {
        id 'maven-publish'
    }

配置发布信息及本地maven路径
发布到本地Maven 仓库配置

    afterEvaluate {
        publishing {
            publications {
                release(MavenPublication) {
                    from components.release
                    groupId = 'com.zb.baselibrarymodule'
                    artifactId = 'baselibrary'
                    version = '1.0.0'
                }
            }
            repositories {
                maven {
                 url uri("E:\\maven_repository")
                }
            }
        }
    }

2、发布命令：

    ./gradlew publishToMavenLocal
    ./gradle publish 