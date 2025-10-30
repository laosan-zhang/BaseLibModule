项目引用库方式：
setting.gradle中加入配置：
    
    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            maven { url "https://laosan-zhang.github.io/BaseLibModule/" }
        }
    }

app.gradle 中加入引用

    implementation 'com.zb.baselibrarymodule:baselibrary:1.0.1'