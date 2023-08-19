# AndroidCustomLibrary

# How to create your custom library on jitpack.io and github?

To create your custom library on github and use it with jitpack.io please
Follow this steps :-

## 1. Create a New Project 
* Go to File -> New -> New Project...
  
* Choose either Empty Activity or Empty Compose Activity, click Next
  
* Update Name and Save Location
  
* Click Finish 

 ![1](https://github.com/zeel-dhorajiya/AndroidCustomLibrary/assets/52526126/55656b22-4645-411b-b9c9-82464b96e850)


## 2. Create a New Module
 
* Go to File -> New -> New Module...
  
* Select Android Library, update Module Name and Package Name
  
* Click Finish

![2](https://github.com/zeel-dhorajiya/AndroidCustomLibrary/assets/52526126/6e532c2c-dcb7-4ac5-b72d-69461d3108cc)

## 3. Add Code Into Your Module

The module should be created in the root project folder.
 
* Go to the package, right click, select New -> Kotlin Class/File

 ![3](https://github.com/zeel-dhorajiya/AndroidCustomLibrary/assets/52526126/feb30f1a-1f7b-4ced-8b6c-7217638d6751)

* Select Android Library, update Module Name and Package Name

```
package com.vtsen.sydneysuburbs

object Sydney {
    val suburbs = listOf("Ryde", "Chippendale")
}

```

## 4. Use the Local Module

In order to use the module that you just created,
 
* Add implementation project(':<Module_Name>') dependency in the build.gradle (app level) file.
 
Groovy

```
dependencies {
    ...
    implementation project(':SydneySuburbs')
}
```

Kotlin

```
dependencies {
    ...
    implementation(project(":SydneySuburbs"))
}
```

* Access / use the code that you created in step 3 above. E.g. Sydney.suburbs[0]

```
import com.vtsen.sydneysuburbs.Sydney
...
    // Example of accessing SydneyBurbs module
    Surface(color = MaterialTheme.colors.background) {
        Greeting(Sydney.suburbs[0])
    }
...
```

* Run your app, it should work!

## 5. Setup and Configure for `JitPack.io`

* Add maven-publish plugin in build.gradle file (Android library module level).

Groovy

```
plugins {
    ...
    id 'maven-publish'
}
```

Kotlin

```
plugins {
    ...
    id("maven-publish")
}
```

Note: There are 3 build.gradle files - project level, app module level and Android library module level that you just created). Please make sure you update the correct build.gradle file.

* Add afterEvaluate at the end of the build.gradle file (Android library module level)

Groovy

```
publishing {
    publications {
        release(MavenPublication) {
            groupId = 'com.github.vinchamp77'
            artifactId = 'demo-simple-android-lib'
            version = '0.0.0'

            afterEvaluate {
                from components.release
            }
        }
    }
}
```

Kotlin

```
publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "demo-simple-android-lib"
            artifactId = "demo-simple-android-lib"
            version = "0.0.3"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
```

i. groupId = com.github.<Your_GitHub_User_Name>
i. artifactId = '<Your_GitHub_Repository_Name>'

* Switch to project mode, add the jitpack.yml in project root folder
  
![4](https://github.com/zeel-dhorajiya/AndroidCustomLibrary/assets/52526126/b40f1989-5881-4e7a-b6a7-1523e41dd360)


The content in jitpack.yml:

```
jdk:
  - openjdk11
```

[Updated - May 27, 2023]: If you upgrade your Android AGP to version 8.0, you will get the following error when JitPack.io is trying to build your library

> Android Gradle plugin requires Java 17 to run. You are currently using Java 11.

To fix that, change openjdk11 to openjdk17 in your jitpack.yml file.

```
jdk:
  - openjdk17
```

## 6. Share Project on GitHub

Now, it is ready to upload your projects to the GitHub repository.

You can also clean up unused dependencies before you upload your project to GitHub. This can help save the build time when JitPack.io builds your project.

* Follow the detailed steps below if you don't know how to do it

* Please make sure the repository name matches the artifactId in step 5 and uncheck the private check box

 ![5](https://github.com/zeel-dhorajiya/AndroidCustomLibrary/assets/52526126/035b457f-26c0-4887-9673-6b7829a539eb)

## 7. Sign Up JitPack

* Go to jitpack.io, click the Sign In button at the top left

* Authorize JitPack to allow JitPack access to your GitHub account
  
 ![6](https://github.com/zeel-dhorajiya/AndroidCustomLibrary/assets/52526126/9f457ff2-b1b2-437d-baca-abb775bf7761)

* Select your repository and click Look Up. You should see the following:

 ![7](https://github.com/zeel-dhorajiya/AndroidCustomLibrary/assets/52526126/35b8ddce-7532-4e30-8030-d5fb632da546)

## 8. Create a New Release to Trigger JitPack Build

* Go to your repository, click Releases at the right (below the About).

* Click Draft a New Release

* Click Chose a tag, and enter the same version that you specify in step 5 above

* Press enter

* Click Publish release

## 9. Monitor JitPack Build

* Go back to jitpack.io, and click Look Up.

* Wait for a while, you should see the Log icon is build in progress.

 ![8](https://github.com/zeel-dhorajiya/AndroidCustomLibrary/assets/52526126/ca54059f-8d3e-46fc-95f6-37b744d2f0fc)

* When the build is done, you should see something like this:

 ![9](https://github.com/zeel-dhorajiya/AndroidCustomLibrary/assets/52526126/0f415653-5b3c-4cd2-b124-57c7bac916ae)

> Note: If the build failed, you should see the red report. If it passes, you should see the green report above.

* Click on the green report, you should see something like this at the end.
  
 ![10](https://github.com/zeel-dhorajiya/AndroidCustomLibrary/assets/52526126/bef20712-9390-4dc2-8183-7ad7cc5c165b)

## 10. Import JitPack Android Library

Once the JitPack has successfully built your Android library, it is ready to import your Android library to your project from JitPack.io.

> Note: I'm using the same project that I use to create this Android library as an example.

* In settings.gradle, add maven { url 'https://jitpack.io' }
 
Groovy

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven { url 'https://jitpack.io' }
    }
}
```

Kotlin

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven ("https://jitpack.io")
    }
}
```

> Note: If you add this in build.gradle (project level), it won't work. You must add it in the settings.gradle instead.

* In build.gradle (app level), replace implementation project(':SydneySuburbs') with implementation 'com.github.<github_user_name>:<repository_name>:<version_name>'
 
Groovy

```
dependencies {
    ...
    implementation 'com.github.vinchamp77:demo-simple-android-lib:0.0.0'
}
```

Kotlin

```
dependencies {
    ...
    implementation ("com.github.vinchamp77:demo-simple-android-lib:0.0.0")
}
```

* Now, your project can import the Android library package and start using it. For example:

```
import com.vtsen.sydneysuburbs.Sydney
```




##
